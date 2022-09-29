package cx.rain.infadv.data.provider.base;

import cx.rain.infadv.block.base.FacingBlockBase;
import cx.rain.infadv.block.base.FurnaceBlockBase;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
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

    public void block(Block block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side) {
        getVariantBuilder(block).partialState()
                .modelForState()
                .modelFile(models().withExistingParent(name(block), "block/grass_block")
                        .texture("particle", side)
                        .texture("bottom", bottom)
                        .texture("top", top)
                        .texture("side", side))
                .addModel();
    }

    public void furnaceBlock(FurnaceBlockBase block, ResourceLocation side, ResourceLocation front, ResourceLocation frontOn, ResourceLocation end) {
        var builder = getVariantBuilder(block);

        var facing = Direction.NORTH;
        var burning = false;
        for (var i = 0; i < 2; i++) {
            for (var j = 0; j < 4; j++) {
                builder.partialState()
                        .with(FurnaceBlockBase.FACING, facing)
                        .with(FurnaceBlockBase.BURNING, burning)
                        .modelForState()
                        .modelFile(models().orientable(name(block) + (burning ? "_on" : "_off"), side, (burning ? frontOn : front), end))
                        .rotationY(j * 90)
                        .addModel();
                facing = facing.getClockWise();
            }
            burning = !burning;
        }
    }

    protected ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    protected String name(Block block) {
        return key(block).getPath();
    }
}
