package cx.rain.infadv.data.provider.base;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Base item model provider.
 *
 * @author skyinr
 */
public abstract class ItemModelProviderBase2 extends WarnItemModelProvider {
    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    protected final Set<Item> skipItems = new HashSet<>();
    protected final Set<String> addedItems = new HashSet<>();
    protected boolean adding;

    private final DeferredRegister<? extends Item> deferredRegister;

    public ItemModelProviderBase2(DataGenerator generator, String modId, ExistingFileHelper exHelper, DeferredRegister<? extends Item> deferredRegister) {
        super(generator, modId, exHelper);
        this.deferredRegister = deferredRegister;
        // qyl: We need not an additional modId variable. MC already have.
    }

    protected abstract void registerItemModels();

    /**
     * Register item model.
     */
    @Override
    protected final void registerModels() {
        adding = true;
        registerItemModels();
        adding = false;
        getItems().forEach(this::genItemModel);
    }

    protected Stream<? extends Item> getItems() {
        return deferredRegister.getEntries().stream()
                .map(RegistryObject::get)
                .filter(i -> !(i instanceof CrossbowItem))
                .filter(i -> !skipItems.contains(i))
                .filter(i -> !addedItems.contains(name(i)));
    }

    protected void genItemModel(Item item) {
        if (item instanceof BlockItem i) {
            ResourceLocation texture = modLoc("item/" + name(item));
            if (existingFileHelper.exists(texture, TEXTURE)) {
                generatedItem(item);
            } else {
                existsBlockItem(item);
            }
        } else if (item instanceof TieredItem) {
            handheldItem(item);
        } else if (item instanceof ForgeSpawnEggItem) {
            spawnEgg(item);
        } else {
            generatedItem(item);
        }
    }

    /**
     * Add handheld item model.
     *
     * @param name Name of items.
     * @return Builders.
     */
    protected ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", modLoc("item/" + name));    // qyl: Change prefix to mc builtin "modLoc".
    }

    /**
     * @see ItemModelProviderBase2#handheldItem(String)
     */
    protected ItemModelBuilder handheldItem(Item i) {
        return handheldItem(name(i));
    }

    /**
     * Add generated item model.
     *
     * @param name Name of items.
     * @return item model builder
     */
    protected ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", modLoc("item/" + name));    // qyl: Change prefix to mc builtin "modLoc".
    }

    /**
     * @see ItemModelProviderBase2#generatedItem(String)
     */
    protected ItemModelBuilder generatedItem(Item i) {
        return generatedItem(name(i));
    }

    /**
     * get item name
     *
     * @param item item
     * @return item name
     */
    protected static String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    @Override
    public ItemModelBuilder getBuilder(String path) {
        if (adding) {
            addedItems.add(path);
        }
        return super.getBuilder(path);
    }

    @Override
    protected void generateAll(CachedOutput cache) {
        for (ItemModelBuilder model : generatedModels.values()) {
            Path target = getPath(model);
            try {
                DataProvider.saveStable(cache, model.toJson(), target);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * qyl27: Generate all, use modid of DeferredRegister to save to.
     */
    private Path getPath(ItemModelBuilder model) {
        ResourceLocation loc = model.getLocation();
        return generator.getOutputFolder().resolve("assets/"
                + deferredRegister.createTagKey("test").location().getNamespace()
                + "/models/" + loc.getPath() + ".json");    // qyl: Did it work?
    }

    // =================================================================================================================

    protected static ResourceLocation itemResource(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    protected final ItemModelBuilder spawnEgg(Item item) {
        return withExistingParent(itemResource(item).getPath(), mcLoc("item/template_spawn_egg"));
    }

    protected void existsBlockItem(Item item) {
        uncheckedItem(itemResource(item).getPath(), blockPath(item));
    }

    protected void uncheckedItem(String name, ResourceLocation model) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(model));
    }

    protected final ResourceLocation blockPath(Item item) {
        return modLoc("block/" + itemResource(item).getPath());
    }

}
