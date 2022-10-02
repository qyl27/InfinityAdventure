package cx.rain.infadv.block.base;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class OreBlockBase extends Block {
    public OreBlockBase(float hardness, float explosionResistance) {
        super(Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(hardness, explosionResistance)
                .sound(SoundType.STONE));    // qyl27: Don't forget to add ores to the mine-able tag (pickaxe).
    }
}
