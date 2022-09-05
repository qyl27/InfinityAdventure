package cx.rain.infadv.data.provider.base;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BlockStateProviderBase extends BlockStateProvider {
    protected DeferredRegister<Block> deferredRegister = null;
    protected Set<Block> skipped = new HashSet<>();

    public BlockStateProviderBase(DataGenerator generator, String modid,
                                  ExistingFileHelper existingFileHelper, DeferredRegister<Block> registry) {
        super(generator, modid, existingFileHelper);

        deferredRegister = registry;
    }

    protected abstract void registerStates();

    @Override
    protected void registerStatesAndModels() {
        registerStates();

        doRegisterBlockStates();
    }

    protected void skipBlocks(Block... blocks) {
        skipBlocks(Arrays.asList(blocks));
    }

    protected void skipBlocks(Collection<? extends Block> blocks) {
        skipped.addAll(blocks);
    }

    // qyl27: Internal methods below.

    protected Set<Block> getBlocks() {
        return deferredRegister.getEntries()
                .stream()
                .map(RegistryObject::get)
                .collect(Collectors.toSet());
    }

    protected void doRegisterBlockStates() {
        var blocks = getBlocks();

        blocks.removeAll(skipped);

        for (var block : blocks) {
            // Todo: qyl27: more block types presets.
            simpleBlock(block);
        }
    }
}
