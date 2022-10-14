package cx.rain.infadv.effect;

import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ScorpionPosionEffect extends AttackDamageMobEffect {

    public ScorpionPosionEffect(MobEffectCategory arg, int i, double d) {
        super(arg, i, d);
    }

    /**
     * 始终允许tick
     */
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    /**
     * 免疫剧毒和虚弱。
     *
     * @param livingEntity 效果影响的实体
     * @param amplifier    修正
     */
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasEffect(MobEffects.POISON)) {
            livingEntity.removeEffect(MobEffects.POISON);
        }
        if (livingEntity.hasEffect(MobEffects.WEAKNESS)) {
            livingEntity.removeEffect(MobEffects.WEAKNESS);
        }
    }
}