package cx.rain.infadv.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DarkGhostEntity extends Monster implements IAnimatable {

    protected AnimationFactory animationFactory = new AnimationFactory(this);

    protected DarkGhostEntity(EntityType<? extends Monster> type, Level arg2) {
        super(type, arg2);
    }

    public static AttributeSupplier getDefaultAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.ATTACK_DAMAGE, 2.5)
                .add(Attributes.ATTACK_SPEED, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .build();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("dark_ghost.move", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        // Todo: qyl27: attack animations.
        event.getController().setAnimation(new AnimationBuilder().addAnimation("dwarf.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(state.getSoundType().getStepSound(), 0.15f, 1.0f);
    }

}