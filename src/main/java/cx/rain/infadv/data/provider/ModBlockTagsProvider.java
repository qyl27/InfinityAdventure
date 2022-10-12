package cx.rain.infadv.data.provider;

import cx.rain.infadv.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator arg, String modId, ExistingFileHelper existingFileHelper) {
        super(arg, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.SILVER_ORE.get(), ModBlocks.MITHRIL_ORE.get(),
                ModBlocks.ADAMANTINE_ORE.get(), ModBlocks.RUBY_ORE.get(), ModBlocks.AQUAMARINE_ORE.get(),
                ModBlocks.DEEPSLATE_MITHRIL_ORE.get(), ModBlocks.DEEPSLATE_ADAMANTINE_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.MITHRIL_ORE.get(), ModBlocks.ADAMANTINE_ORE.get(),
                ModBlocks.DEEPSLATE_MITHRIL_ORE.get(), ModBlocks.DEEPSLATE_ADAMANTINE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SILVER_ORE.get(), ModBlocks.RUBY_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.AQUAMARINE_ORE.get());
    }

    @Override
    public String getName() {
        return "Block Tags: " + modId;
    }
}
