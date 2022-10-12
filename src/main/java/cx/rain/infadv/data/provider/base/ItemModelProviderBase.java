package cx.rain.infadv.data.provider.base;

import cx.rain.infadv.data.provider.base.warn.WarnItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ItemModelProviderBase extends WarnItemModelProvider {
    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    protected final DeferredRegister<Item> deferredRegister;
    protected final Set<Item> skipped = new HashSet<>();
    protected final Set<String> skippedPath = new HashSet<>();
    protected boolean manually = false;

    public ItemModelProviderBase(DataGenerator generator, String modid,
                                 ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry) {
        super(generator, modid, existingFileHelper);
        deferredRegister = registry;
    }

    protected abstract void registerItemModels();

    @Override
    protected void registerModels() {
        manually = true;
        registerItemModels();
        manually = false;

        doRegisterModels();
    }

    @Override
    public ItemModelBuilder getBuilder(String path) {
        if (manually) {
            skippedPath.add(path);
        }
        return super.getBuilder(path);
    }

    protected final ItemModelBuilder generated(Item item) {
        return generatedInternal(itemResource(item).getPath());
    }

    protected final ItemModelBuilder handheld(Item item) {
        return handheldInternal(itemResource(item).getPath());
    }

    protected void existsBlockItem(Item item) {
        uncheckedItem(itemResource(item).getPath(), blockPath(item));
    }

    protected void uncheckedItem(String name, ResourceLocation model) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(model));
    }

    protected static ResourceLocation itemResource(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    protected final ItemModelBuilder spawnEgg(Item item) {
        return withExistingParent(itemResource(item).getPath(), mcLoc("item/template_spawn_egg"));
    }

    // qyl27: Internal methods below.

    protected final ResourceLocation blockPath(Item item) {
        return modLoc("block/" + itemResource(item).getPath());
    }

    protected final ItemModelBuilder generatedInternal(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", modLoc("item/" + name));
    }

    protected final ItemModelBuilder handheldInternal(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", modLoc("item/" + name));
    }

    protected Set<Item> getItems() {
        return deferredRegister.getEntries()
                .stream()
                .map(RegistryObject::get)
                .filter(i -> !skippedPath.contains(itemResource(i).getPath()))
                .collect(Collectors.toSet());
    }

    protected Set<Item> getBlockItems() {
        return getItems().stream()
                .filter(i -> i instanceof BlockItem)
                .map(item -> (BlockItem) item)
                .collect(Collectors.toSet());
    }

    protected void doRegisterModels() {
        var items = getItems();

        items.removeAll(skipped);

        var blockItems = getBlockItems();

        for (var blockItem : blockItems) {
            existsBlockItem(blockItem);
        }

        items.removeAll(blockItems);

        for (var item : items) {
            // Todo: qyl27: more item types presets.
            if (item instanceof TieredItem) {
                handheld(item);
                continue;
            }

            if (item instanceof ForgeSpawnEggItem) {
                spawnEgg(item);
                continue;
            }

            generated(item);
        }
    }

    protected ResourceLocation key(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    protected String name(Item item) {
        return key(item).getPath();
    }
}
