package cx.rain.infadv.data.provider.lang;

import cx.rain.infadv.block.ModBlocks;
import cx.rain.infadv.data.provider.base.LanguageProviderBase;
import cx.rain.infadv.effect.ModEffects;
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
        addCreativeTab(ModTabs.FUNCTIONAL_ITEMS, "InfinityAdventure | Functional");
        addCreativeTab(ModTabs.BATTLING, "InfinityAdventure | Battling");
        addCreativeTab(ModTabs.TOOLS, "InfinityAdventure | Tools");
        addCreativeTab(ModTabs.FOODS, "InfinityAdventure | Foods");
        addCreativeTab(ModTabs.MISC, "InfinityAdventure | Miscellaneous");
        addCreativeTab(ModTabs.ORES, "InfinityAdventure | Ores");

        addEffect(ModEffects.DWARF_EYE, "Dwarf Eye");
        addEffect(ModEffects.SCORPION_POISON, "Scorpion Poison");

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
        addBlock(ModBlocks.ANCIENT_DWARF_CRATE, "Ancient Dwarf Crate");
        addBlock(ModBlocks.DWARF_FURNACE, "Dwarf Furnace");
        addBlock(ModBlocks.BEER_BARREL, "Beer Barrel");
        addBlock(ModBlocks.SKY_GRASS_BLOCK, "Skyland Grass Block");
        addBlock(ModBlocks.SKY_LOG, "Skyland Log");
        addBlock(ModBlocks.SKY_PLANKS, "Skyland Planks");
        addBlock(ModBlocks.SKY_LEAVES, "Skyland Leaves");
        addBlock(ModBlocks.SKY_STONE, "Skyland Stone");
        addBlock(ModBlocks.HEAVEN_ROCK, "Heaven Bedrock");
        addBlock(ModBlocks.HEAVEN_BRICKS, "Heaven Bricks");
        addBlock(ModBlocks.MICA_ORE, "Mica Ore");
        addBlock(ModBlocks.SILVER_BLOCK, "Silver Block");
        addBlock(ModBlocks.MITHRIL_BLOCK, "Mithril Block");
        addBlock(ModBlocks.RUBY_BLOCK, "Ruby Block");
        addBlock(ModBlocks.MICA_BLOCK, "Mica Block");

        addItem(ModItems.SILVER_INGOT, "Silver Ingot");
        addItem(ModItems.MITHRIL_INGOT, "Mithril Ingot");
        addItem(ModItems.ADAMANTINE_INGOT, "Adamantine Ingot");
        addItem(ModItems.RUBY, "Ruby");
        addItem(ModItems.AQUAMARINE, "Aquamarine");
        addItem(ModItems.RAW_SILVER, "Raw Silver");
        addItem(ModItems.MICA, "Mica");
        addItem(ModItems.DREAM_PILLOW, "Dream Pillow");
        addItem(ModItems.QUEEN_FANG, "Queen Fang");

        addItem(ModItems.SILVER_SWORD, "Silver Sword");
        addItem(ModItems.SILVER_AXE, "Silver Axe");
        addItem(ModItems.SILVER_PICKAXE, "Silver Pickaxe");
        addItem(ModItems.SILVER_SHOVEL, "Silver Shovel");
        addItem(ModItems.SILVER_HOE, "Silver Hoe");
        addItem(ModItems.MICA_SWORD, "Mica Sword");
        addItem(ModItems.MICA_AXE, "Mica Axe");
        addItem(ModItems.MICA_PICKAXE, "Mica Pickaxe");
        addItem(ModItems.MICA_SHOVEL, "Mica Shovel");
        addItem(ModItems.MICA_HOE, "Mica Hoe");
        addItem(ModItems.MITHRIL_HELMET, "Mithril Helmet");
        addItem(ModItems.MITHRIL_CHESTPLATE, "Mithril Chestplate");
        addItem(ModItems.MITHRIL_LEGGINGS, "Mithril Leggings");
        addItem(ModItems.MITHRIL_BOOTS, "Mithril Boots");
        addItem(ModItems.MITHRIL_SWORD, "Mithril Sword");
        addItem(ModItems.MITHRIL_AXE, "Mithril Axe");
        addItem(ModItems.MITHRIL_PICKAXE, "Mithril Pickaxe");
        addItem(ModItems.MITHRIL_SHOVEL, "Mithril Shovel");
        addItem(ModItems.MITHRIL_HOE, "Mithril Hoe");

        addItem(ModItems.DWARF_SPAWN_EGG, "Dwarf Spawn Egg");
        addItem(ModItems.DWARF_COPPER_COIN, "Dwarf Copper Coin");
        addItem(ModItems.DWARF_SILVER_COIN, "Dwarf Silver Coin");
        addItem(ModItems.DWARF_GOLDEN_COIN, "Dwarf Golden Coin");
        addItem(ModItems.DWARF_DIAMOND_COIN, "Dwarf Diamond Coin");
        addItem(ModItems.DWARF_EMERALD_COIN, "Dwarf Emerald Coin");
        addItem(ModItems.MICA_SWORD, "Mica Sword");
        addItem(ModItems.MICA_AXE, "Mica Axe");
        addItem(ModItems.MICA_PICKAXE, "Mica Pickaxe");
        addItem(ModItems.MICA_SHOVEL, "Mica Shovel");
        addItem(ModItems.MICA_HOE, "Mica Hoe");

        addItem(ModItems.CUP, "Cup");
        addItem(ModItems.AMBROSIA, "Cup of Ambrosia");
        addItem(ModItems.CUP_DWARF_MALT, "Cup of Dwarf Malt");
        addItem(ModItems.CUP_EMERALD_SUNRISE, "Cup of Emerald Sunrise");
        addItem(ModItems.CUP_GOLDEN_SCORPION, "Cup of Golden Scorpion");
        addItem(ModItems.CUP_RUM, "Cup of Rum");
        addItem(ModItems.CUP_VODKA, "Cup of Vodka");
        addItem(ModItems.CUP_WINE, "Cup of Wine");
        addItem(ModItems.WINE_CHOCOLATE_DWARF_MALT, "Wine Chocolate of Dwarf Malt");
        addItem(ModItems.WINE_CHOCOLATE_EMERALD_SUNRISE, "Wine Chocolate of Emerald Sunrise");
        addItem(ModItems.WINE_CHOCOLATE_GOLDEN_SCORPION, "Wine Chocolate of Golden Scorpion");
        addItem(ModItems.WINE_CHOCOLATE_RUM, "Wine Chocolate of Rum");
        addItem(ModItems.WINE_CHOCOLATE_VODKA, "Wine Chocolate of Vodka");
        addItem(ModItems.WINE_CHOCOLATE, "Wine Chocolate of Wine");
        addItem(ModItems.SOUL_SALT, "Soul Salt");
        addItem(ModItems.SOUL_WHISTLE, "Soul Whistle");
        addItem(ModItems.TARANTULA_EGG, "Tarantula Egg");
    }
}
