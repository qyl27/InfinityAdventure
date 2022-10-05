package cx.rain.infadv.data.tag;

import cx.rain.infadv.InfAdv;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> SILVER_MINEABLE = BlockTags.create(new ResourceLocation(InfAdv.MODID, "silver_mineable"));
    public static final TagKey<Block> MITHRIL_MINEABLE = BlockTags.create(new ResourceLocation(InfAdv.MODID, "mithril_mineable"));
    public static final TagKey<Block> MICA_MINEABLE = BlockTags.create(new ResourceLocation(InfAdv.MODID, "mica_mineable"));
}
