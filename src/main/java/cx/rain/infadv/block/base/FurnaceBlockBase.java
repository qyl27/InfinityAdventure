package cx.rain.infadv.block.base;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class FurnaceBlockBase extends FacingBlockBase {
    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public FurnaceBlockBase() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.5F)
                .lightLevel(state -> state.getValue(BURNING) ? 13 : 0));
        registerDefaultState(stateDefinition.any().setValue(BURNING, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BURNING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(BURNING, false);
    }
}
