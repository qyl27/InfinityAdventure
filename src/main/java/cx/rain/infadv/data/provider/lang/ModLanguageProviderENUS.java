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
        addCreativeTab(ModTabs.FUNCTIONAL_ITEMS, "InfinityAdventure | Functional");
        addCreativeTab(ModTabs.BATTLING, "InfinityAdventure | Battling");
        addCreativeTab(ModTabs.TOOLS, "InfinityAdventure | Tools");
        addCreativeTab(ModTabs.FOODS, "InfinityAdventure | Foods");
        addCreativeTab(ModTabs.MISC, "InfinityAdventure | Miscellaneous");
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
        addBlock(ModBlocks.ANCIENT_DWARF_CRATE, "Ancient Dwarf Crate");
        addBlock(ModBlocks.DWARF_FURNACE, "Dwarf Furnace");
        addBlock(ModBlocks.BEER_BARREL, "Beer Barrel");
        addBlock(ModBlocks.SKY_GRASS_BLOCK, "Skyland Grass Block");
        addBlock(ModBlocks.SKY_LOG, "Skyland Log");
        addBlock(ModBlocks.SKY_PLANKS, "Skyland Planks");
        addBlock(ModBlocks.SKY_LEAVES, "Skyland Leaves");
        addBlock(ModBlocks.SKY_STONE, "Skyland Stone");
        addBlock(ModBlocks.HEAVEN_ROCK, "Heaven Rock");
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
        addItem(ModItems.DWARF_MALT_LIQUOR, "Dwarf Malt Liquor");
        addItem(ModItems.MALT_LIQUOR_CHOCOLATE, "Malt Liquor Chocolate");
        addItem(ModItems.EMERALD_SUNRISE, "Emerald Sunrise");
        addItem(ModItems.EMERALD_CHOCOLATE, "Emerald Chocolate");
        addItem(ModItems.GOLDEN_SCORPION_MEDICATED_LIQUOR, "Golden Scorpion Medicated Liquor");
        addItem(ModItems.GOLDEN_SCORPION_CHOCOLATE, "Golden Scorpion Chocolate");
        addItem(ModItems.RUM, "RUM");
        addItem(ModItems.RUM_CHOCOLATE, "Rum Chocolate");
        addItem(ModItems.VODKA, "Vodka");
        addItem(ModItems.VODKA_CHOCOLATE, "Vodka Chocolate");
        addItem(ModItems.WINE, "Wine");
        addItem(ModItems.WINE_CHOCOLATE, "Wine Chocolate");
        addItem(ModItems.AMBROSIA, "Ambrosia");
        addItem(ModItems.WINE_GLASS, "Wine Glass");
        addItem(ModItems.DREAM_PILLOW, "Dream Pillow");
        addItem(ModItems.TEETH_OF_EMPRESS, "Teeth of Empress");
        addItem(ModItems.SOUL_FLUTE, "Soul Flute");
        addItem(ModItems.SOUL_SALT, "Soul Salt");
        addItem(ModItems.TARANTULA_EGG, "Tarantula Egg");
        addItem(ModItems.COPPER_COIN, "Copper Coin");
        addItem(ModItems.SILVER_COIN, "Silver Coin");
        addItem(ModItems.GOLD_COIN, "Gold Coin");
        addItem(ModItems.JADE_COIN, "Jade Coin");
        addItem(ModItems.DIAMONDS_GOLD_COIN, "Diamonds Gold Coin");
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
    }
}
