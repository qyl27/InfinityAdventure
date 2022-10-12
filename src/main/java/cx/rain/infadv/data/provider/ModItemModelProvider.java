package cx.rain.infadv.data.provider;

import cx.rain.infadv.block.ModBlockItems;
import cx.rain.infadv.data.provider.base.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;

public class ModItemModelProvider extends ItemModelProviderBase {
    private final boolean wereBlockItems;

    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry) {
        this(generator, modid, existingFileHelper, registry, false);
    }

    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry, boolean areBlockItems) {
        super(generator, modid, existingFileHelper, registry);
        wereBlockItems = areBlockItems;
    }

    @Override
    protected void registerItemModels() {
        getBuilder(name(ModBlockItems.DWARF_FURNACE.get())).parent(new ModelFile.UncheckedModelFile(modLoc("block/dwarf_furnace_off")));
    }

    @Override
    public String getName() {
        return wereBlockItems ? super.getName() : "Block Item Models: " + modid;
    }
}
