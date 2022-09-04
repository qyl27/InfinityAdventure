package cx.rain.infadv.data.provider;

import cx.rain.infadv.data.provider.base.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;

public class ModItemModelProvider extends ItemModelProviderBase {
    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<Item> registry) {
        super(generator, modid, existingFileHelper, registry);
    }

    @Override
    protected void registerItemModels() {

    }
}
