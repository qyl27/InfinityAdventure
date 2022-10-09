package cx.rain.infadv.utility;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ITeleporter;

public class Levels {

    public static boolean is(Level level, ResourceKey<Level> target) {
        return level.dimension().equals(target);
    }

    public static boolean to(Entity entity, ResourceKey<Level> target) {
        Level level = entity.getLevel();
        if (!level.isClientSide() && !level.dimension().equals(target) && entity.canChangeDimensions()) {
            ServerLevel targetLevel = entity.getServer().getLevel(target);
            if (targetLevel != null && !entity.isPassenger()) {
                level.getProfiler().push("portal");
                entity.changeDimension(targetLevel, Teleporter.INSTANCE);
                level.getProfiler().pop();
                return true;
            }
        }
        return false;
    }

    enum Teleporter implements ITeleporter {
        INSTANCE;
    }
}
