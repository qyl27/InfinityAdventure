package cx.rain.infadv.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;

//TODO 夜视效果，及其闪烁。
@ParametersAreNonnullByDefault
public class DwarfEyeEffect extends MobEffect {

    public DwarfEyeEffect(MobEffectCategory arg, int i) {
        super(arg, i);
    }

    /**
     * 始终允许tick
     */
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    /**
     * 免疫失明和夜视。
     *
     * @param livingEntity 效果影响的实体
     * @param amplifier    修正
     */
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasEffect(MobEffects.BLINDNESS)) {
            livingEntity.removeEffect(MobEffects.BLINDNESS);
        }
        if (livingEntity.hasEffect(MobEffects.NIGHT_VISION)) {
            livingEntity.removeEffect(MobEffects.NIGHT_VISION);
        }
    }
}