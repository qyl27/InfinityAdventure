package cx.rain.infadv.data.provider;

import cx.rain.infadv.data.provider.base.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ItemModelProviderBase {
    private boolean wereBlockItems;

    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry) {
        this(generator, modid, existingFileHelper, registry, false);
    }

    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry, boolean areBlockItems) {
        super(generator, modid, existingFileHelper, registry);

        wereBlockItems = areBlockItems;
    }

    @Override
    protected void registerItemModels() {

    }

    @Override
    public @NotNull String getName() {
        return wereBlockItems ? super.getName() : "Block Item Models: " + modid;
    }
}
