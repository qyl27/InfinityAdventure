package cx.rain.infadv.data.provider.base;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ItemModelProviderBase extends ItemModelProvider {
    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    protected DeferredRegister<Item> deferredRegister = null;
    protected Set<Item> skipped = new HashSet<>();

    public ItemModelProviderBase(DataGenerator generator, String modid,
                                 ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry) {
        super(generator, modid, existingFileHelper);

        deferredRegister = registry;
    }

    protected abstract void registerItemModels();

    @Override
    protected void registerModels() {
        registerItemModels();

        doRegisterModels();
    }

    protected void skipItems(Item... items) {
        skipItems(Arrays.asList(items));
    }

    protected void skipItems(Collection<? extends Item> items) {
        skipped.addAll(items);
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
        var namespace = ForgeRegistries.ITEMS.getKey(item).getNamespace();
        var path = ForgeRegistries.ITEMS.getKey(item).getPath();

        return new ResourceLocation(namespace, path);
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
