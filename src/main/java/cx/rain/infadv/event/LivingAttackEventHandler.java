package cx.rain.infadv.event;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfAdv.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingAttackEventHandler {
    /**
     * 在攻击时清除伪装buff
     *
     * @param event 实体攻击事件
     */
    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity livingEntity &&
                livingEntity.hasEffect(ModEffects.DISGUISE.get())) {
            livingEntity.removeEffect(ModEffects.DISGUISE.get());
        }
    }
}
