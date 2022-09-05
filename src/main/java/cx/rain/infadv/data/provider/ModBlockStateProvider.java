package cx.rain.infadv.data.provider;

import cx.rain.infadv.data.provider.base.BlockStateProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;

public class ModBlockStateProvider extends BlockStateProviderBase {
    public ModBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<Block> registry) {
        super(generator, modid, existingFileHelper, registry);
    }

    @Override
    protected void registerStates() {

    }
}
