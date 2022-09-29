package cx.rain.infadv.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;

public class CloudBlock extends Block {
    // Todo: qyl27: more detailed cloud material. Maybe we need a custom builder?
    public static final Material NORMAL_CLOUD_MATERIAL = new Material(MaterialColor.COLOR_LIGHT_GRAY, false, false, true, false, true, true, PushReaction.DESTROY);

    public CloudBlock(Material material) {
        super(Properties.of(material));
    }
}
