package cx.rain.infadv.data.provider.base;

import cx.rain.infadv.block.base.FurnaceBlockBase;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * BlockStateProviderBase
 *
 * @author skyinr
 */
public abstract class BlockStateProviderBase2 extends WarnBlockStateProvider {
    private final DeferredRegister<? extends Block> deferredRegister;
    private boolean adding = true;
    private final Set<Block> skipBlocks = new HashSet<>();

    public BlockStateProviderBase2(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper, DeferredRegister<? extends Block> deferredRegister) {
        super(generator, modId, existingFileHelper);
        this.deferredRegister = deferredRegister;
    }

    @Override
    protected void registerStatesAndModels() {
        adding = true;
        _registerStatesAndModels();
        adding = false;
        Set<Block> blocks = getBlocks();
        blocks.removeAll(skipBlocks);
        registerBlock(blocks);
    }

    protected abstract void _registerStatesAndModels();

    protected Set<Block> getBlocks() {
        // skyinr: Register models and state for blocks
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    protected void registerBlock(Set<Block> blocks) {
        blocks.forEach(this::simpleBlock);
    }

    protected void skipBlock(Block... blocks) {
        skipBlocks.addAll(Arrays.asList(blocks));
    }

    @Override
    public VariantBlockStateBuilder getVariantBuilder(Block b) {
        if (isAdding()) {
            skipBlock(b);
        }
        return super.getVariantBuilder(b);
    }

    @Override
    public MultiPartBlockStateBuilder getMultipartBuilder(Block b) {
        if (isAdding()) {
            skipBlock(b);
        }
        return super.getMultipartBuilder(b);
    }

    public boolean isAdding() {
        return adding;
    }

    // =================================================================================================================

    protected ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    protected String name(Block block) {
        return key(block).getPath();
    }

    public void grassBlock(Block block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation overlay) {
        getVariantBuilder(block).partialState()
                .modelForState()
                .modelFile(models().withExistingParent(name(block), "block/grass_block")
                        .texture("particle", side)
                        .texture("bottom", bottom)
                        .texture("top", top)
                        .texture("side", side)
                        .texture("overlay", overlay))
                .addModel();
    }

    public void block(Block block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side) {
        getVariantBuilder(block).partialState()
                .modelForState()
                .modelFile(models().withExistingParent(name(block), "block/block")
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

}