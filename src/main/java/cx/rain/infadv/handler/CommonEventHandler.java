package cx.rain.infadv.handler;

import cx.rain.infadv.unrealized.UnrealizedElements;
import cx.rain.infadv.util.Levels;
import cx.rain.infadv.world.ModLevels;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEventHandler {

    @SubscribeEvent
    public static void onSleep(PlayerSleepInBedEvent event) {
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        Level level = player.getLevel();
        if (level.getBlockState(pos).is(UnrealizedElements.dreamBed()) && Levels.to(player, ModLevels.ETHER)) {
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
