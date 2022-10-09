package cx.rain.infadv.event;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.utility.Levels;
import cx.rain.infadv.world.gen.level.ModLevels;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfAdv.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkylandEnterEvents {

    @SubscribeEvent
    public static void onSleep(PlayerSleepInBedEvent event) {
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        Level level = player.getLevel();
        // Todo: qyl27: dream pillow, and enter by chance.
    }

    @SubscribeEvent
    public static void onDropLevel(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (event.getSource() == DamageSource.OUT_OF_WORLD && Levels.is(entity.getLevel(), ModLevels.SKYLAND)) {
            if (Levels.to(entity, Level.OVERWORLD)) {
                event.setCanceled(true);
            }
        }
        // Todo: qyl27: drop from skyland, go to over world.
    }
}
