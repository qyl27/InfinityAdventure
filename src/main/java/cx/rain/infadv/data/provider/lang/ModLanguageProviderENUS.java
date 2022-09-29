package cx.rain.infadv.data.provider.lang;

import cx.rain.infadv.block.ModBlocks;
import cx.rain.infadv.data.provider.base.LanguageProviderBase;
import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.data.DataGenerator;

public class ModLanguageProviderENUS extends LanguageProviderBase {
    public ModLanguageProviderENUS(DataGenerator gen, String modid) {
        super(gen, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        addCreativeTab(ModTabs.BUILDING_BLOCKS, "InfinityAdventure | Building Blocks");
        addCreativeTab(ModTabs.FUNCTIONAL_BLOCKS, "InfinityAdventure | Functional Blocks");
        addCreativeTab(ModTabs.ORES, "InfinityAdventure | Ores");

        addBlock(ModBlocks.SILVER_ORE, "Silver Ore");
        addBlock(ModBlocks.MITHRIL_ORE, "Mithril Ore");
        addBlock(ModBlocks.DEEPSLATE_MITHRIL_ORE, "Deepslate Mithril Ore");
        addBlock(ModBlocks.ADAMANTINE_ORE, "Adamantine Ore");
        addBlock(ModBlocks.DEEPSLATE_ADAMANTINE_ORE, "Deepslate Adamantine Ore");
        addBlock(ModBlocks.RUBY_ORE, "Ruby Ore");
        addBlock(ModBlocks.AQUAMARINE_ORE, "Aquamarine Ore");
        addBlock(ModBlocks.WHITE_CLOUD, "White Cloud");
        addBlock(ModBlocks.LIGHT_GREY_CLOUD, "Light Grey Cloud");
        addBlock(ModBlocks.DARK_GREY_CLOUD, "Dark Grey Cloud");
        addBlock(ModBlocks.BLACK_CLOUD, "Black Cloud");
        addBlock(ModBlocks.RED_CLOUD, "Red Cloud");
        addBlock(ModBlocks.DWARF_BRICKS, "Dwarf Bricks");
        addBlock(ModBlocks.DWARF_PILLAR_BRICKS, "Dwarf Pillar Bricks");
        addBlock(ModBlocks.ANCIENT_DWARF_BRICKS, "Ancient Dwarf Bricks");
        addBlock(ModBlocks.DWARF_FURNACE, "Dwarf Furnace");
        addBlock(ModBlocks.BEER_BARREL, "Beer Barrel");
        addBlock(ModBlocks.SKY_GRASS_BLOCK, "Skyland Grass Block");
        addBlock(ModBlocks.SKY_LOG, "Skyland Log");
        addBlock(ModBlocks.SKY_PLANKS, "Skyland Planks");
        addBlock(ModBlocks.SKY_LEAVES, "Skyland Leaves");

        addItem(ModItems.SILVER_INGOT, "Silver Ingot");
        addItem(ModItems.MITHRIL_INGOT, "Mithril Ingot");
        addItem(ModItems.ADAMANTINE_INGOT, "Adamantine Ingot");
        addItem(ModItems.RUBY, "Ruby");
        addItem(ModItems.AQUAMARINE, "Aquamarine");
    }
}
