package cx.rain.infadv.event;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.utility.Levels;
import cx.rain.infadv.world.gen.level.ModLevels;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfAdv.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EtherEventHandler {

    @SubscribeEvent
    public static void onSleep(PlayerSleepInBedEvent event) {
        Player player = event.getEntity();
        if (player.getMainHandItem().is(ModItems.DREAM_PILLOW.get()) && Levels.to(player, ModLevels.ETHER)) {
            event.setResult(Player.BedSleepingProblem.OTHER_PROBLEM);
        }
    }

    @SubscribeEvent
    public static void onDropLevel(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (event.getSource() == DamageSource.OUT_OF_WORLD && Levels.is(entity.getLevel(), ModLevels.ETHER)) {
            if (Levels.to(entity, Level.OVERWORLD)) {
                event.setCanceled(true);
            }
        }
    }
}
