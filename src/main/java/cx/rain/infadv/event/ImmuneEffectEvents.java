package cx.rain.infadv.event;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.effect.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfAdv.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ImmuneEffectEvents {
    @SubscribeEvent
    public void immuneEffect(MobEffectEvent.Applicable event) {
        MobEffectInstance effect = event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        MobEffect mobEffect = effect.getEffect();
        if (entity.hasEffect(ModEffects.DWARF_EYE.get())) {
            if (mobEffect == MobEffects.BLINDNESS) {
                event.setResult(Event.Result.DENY);
            }
        }
        if (entity.hasEffect(ModEffects.SCORPION_POISON.get())) {
            if (mobEffect == MobEffects.WEAKNESS || mobEffect == MobEffects.POISON) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

}
