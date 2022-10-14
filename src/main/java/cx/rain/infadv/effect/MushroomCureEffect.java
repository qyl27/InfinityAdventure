package cx.rain.infadv.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MushroomCureEffect extends MobEffect {
    public MushroomCureEffect(MobEffectCategory arg, int i) {
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
     * 免疫剧毒和虚弱。
     *
     * @param livingEntity 效果影响的实体
     * @param amplifier    修正
     */
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.getHealth() < livingEntity.getMaxHealth()) {
            livingEntity.heal(1.0f);
        }
    }
}
