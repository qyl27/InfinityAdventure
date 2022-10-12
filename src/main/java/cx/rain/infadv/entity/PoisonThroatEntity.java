package cx.rain.infadv.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PoisonThroatEntity extends Monster implements IAnimatable {

    protected final AnimationFactory animationFactory = new AnimationFactory(this);

    public PoisonThroatEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier getDefaultAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.14)
                .build();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("poison_throat.move", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        // Todo: qyl27: attack animations.
        event.getController().setAnimation(new AnimationBuilder().addAnimation("poison_throat.idle", ILoopType.EDefaultLoopTypes.LOOP));
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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        // Todo: qyl27: more sound.
        return super.getAmbientSound();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        // Todo: qyl27: more sound.
        return super.getHurtSound(damageSource);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        // Todo: qyl27: more sound.
        return super.getDeathSound();
    }

}