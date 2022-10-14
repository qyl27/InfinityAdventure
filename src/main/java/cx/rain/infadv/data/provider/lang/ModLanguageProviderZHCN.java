package cx.rain.infadv.data.provider.lang;

import cx.rain.infadv.block.ModBlocks;
import cx.rain.infadv.data.provider.base.LanguageProviderBase;
import cx.rain.infadv.effect.ModEffects;
import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.data.DataGenerator;

public class ModLanguageProviderZHCN extends LanguageProviderBase {
    public ModLanguageProviderZHCN(DataGenerator gen, String modid) {
        super(gen, modid, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        addCreativeTab(ModTabs.BUILDING_BLOCKS, "无尽冒险 | 建筑方块");
        addCreativeTab(ModTabs.FUNCTIONAL_BLOCKS, "无尽冒险 | 功能性方块");
        addCreativeTab(ModTabs.FUNCTIONAL_ITEMS, "无尽冒险 | 功能性物品");
        addCreativeTab(ModTabs.BATTLING, "无尽冒险 | 战斗用品");
        addCreativeTab(ModTabs.TOOLS, "无尽冒险 | 工具");
        addCreativeTab(ModTabs.FOODS, "无尽冒险 | 食物");
        addCreativeTab(ModTabs.MISC, "无尽冒险 | 杂项");
        addCreativeTab(ModTabs.ORES, "无尽冒险 | 矿石");

        addEffect(ModEffects.DWARF_EYE, "矮人之眼");
        addEffect(ModEffects.SCORPION_POISON, "蝎毒");
        addEffect(ModEffects.MUSHROOM_CURE, "蘑菇疗法");
        addEffect(ModEffects.DISGUISE, "伪装");

        addBlock(ModBlocks.SILVER_ORE, "银矿石");
        addBlock(ModBlocks.MITHRIL_ORE, "秘银矿石");
        addBlock(ModBlocks.DEEPSLATE_MITHRIL_ORE, "深层秘银矿石");
        addBlock(ModBlocks.ADAMANTINE_ORE, "精金矿石");
        addBlock(ModBlocks.DEEPSLATE_ADAMANTINE_ORE, "深层精金矿石");
        addBlock(ModBlocks.RUBY_ORE, "红宝石矿石");
        addBlock(ModBlocks.AQUAMARINE_ORE, "海蓝宝石矿石");
        addBlock(ModBlocks.WHITE_CLOUD, "白色云");
        addBlock(ModBlocks.LIGHT_GREY_CLOUD, "浅灰色云");
        addBlock(ModBlocks.DARK_GREY_CLOUD, "深灰色云");
        addBlock(ModBlocks.BLACK_CLOUD, "黑色云");
        addBlock(ModBlocks.RED_CLOUD, "红色云");
        addBlock(ModBlocks.DWARF_BRICKS, "矮人石砖");
        addBlock(ModBlocks.DWARF_PILLAR_BRICKS, "矮人石砖柱");
        addBlock(ModBlocks.ANCIENT_DWARF_BRICKS, "远古矮人石砖");
        addBlock(ModBlocks.ANCIENT_DWARF_CRATE, "远古矮人机关");
        addBlock(ModBlocks.DWARF_FURNACE, "矮人冶炼炉");
        addBlock(ModBlocks.BEER_BARREL, "酒桶");
        addBlock(ModBlocks.SKY_GRASS_BLOCK, "天境草方块");
        addBlock(ModBlocks.SKY_LOG, "天境树原木");
        addBlock(ModBlocks.SKY_PLANKS, "天境树木板");
        addBlock(ModBlocks.SKY_LEAVES, "天境树叶");
        addBlock(ModBlocks.SKY_STONE, "天境岩");
        addBlock(ModBlocks.HEAVEN_ROCK, "天堂基石");
        addBlock(ModBlocks.HEAVEN_BRICKS, "天堂砖块");
        addBlock(ModBlocks.MICA_ORE, "云母矿石");
        addBlock(ModBlocks.SILVER_BLOCK, "银块");
        addBlock(ModBlocks.MITHRIL_BLOCK, "秘银块");
        addBlock(ModBlocks.RUBY_BLOCK, "红宝石块");
        addBlock(ModBlocks.MICA_BLOCK, "云母块");

        addItem(ModItems.SILVER_INGOT, "银锭");
        addItem(ModItems.MITHRIL_INGOT, "秘银锭");
        addItem(ModItems.ADAMANTINE_INGOT, "精金锭");
        addItem(ModItems.RUBY, "红宝石");
        addItem(ModItems.AQUAMARINE, "海蓝宝石");
        addItem(ModItems.RAW_SILVER, "粗银");
        addItem(ModItems.MICA, "云母");

        addItem(ModItems.DREAM_PILLOW, "织梦之枕");
        addItem(ModItems.QUEEN_FANG, "女皇之牙");
        addItem(ModItems.SOUL_SALT, "灵魂盐");
        addItem(ModItems.TARANTULA_EGG, "狼蛛卵");
        addItem(ModItems.DREAM_PILLOW, "织梦之枕");

        addItem(ModItems.SILVER_SWORD, "银剑");
        addItem(ModItems.SILVER_AXE, "银斧");
        addItem(ModItems.SILVER_PICKAXE, "银镐");
        addItem(ModItems.SILVER_SHOVEL, "银锹");
        addItem(ModItems.SILVER_HOE, "银锄");
        addItem(ModItems.MICA_SWORD, "云母剑");
        addItem(ModItems.MICA_AXE, "云母斧");
        addItem(ModItems.MICA_PICKAXE, "云母镐");
        addItem(ModItems.MICA_SHOVEL, "云母锹");
        addItem(ModItems.MICA_HOE, "云母锄");
        addItem(ModItems.MITHRIL_HELMET, "秘银头盔");
        addItem(ModItems.MITHRIL_CHESTPLATE, "秘银胸甲");
        addItem(ModItems.MITHRIL_LEGGINGS, "秘银护腿");
        addItem(ModItems.MITHRIL_BOOTS, "秘银靴子");
        addItem(ModItems.MITHRIL_SWORD, "秘银剑");
        addItem(ModItems.MITHRIL_AXE, "秘银斧");
        addItem(ModItems.MITHRIL_PICKAXE, "秘银镐");
        addItem(ModItems.MITHRIL_SHOVEL, "秘银锹");
        addItem(ModItems.MITHRIL_HOE, "秘银锄");

        addItem(ModItems.DWARF_SPAWN_EGG, "矮人刷怪蛋");
        addItem(ModItems.DWARF_COPPER_COIN, "铜硬币");
        addItem(ModItems.DWARF_SILVER_COIN, "银硬币");
        addItem(ModItems.DWARF_GOLDEN_COIN, "金硬币");
        addItem(ModItems.DWARF_DIAMOND_COIN, "钻石硬币");
        addItem(ModItems.DWARF_EMERALD_COIN, "绿宝石硬币");
        addItem(ModItems.MICA_SWORD, "云母剑");
        addItem(ModItems.MICA_AXE, "云母斧");
        addItem(ModItems.MICA_PICKAXE, "云母镐");
        addItem(ModItems.MICA_SHOVEL, "云母锹");
        addItem(ModItems.MICA_HOE, "云母锄");

        addItem(ModItems.CUP, "酒杯");
        addItem(ModItems.AMBROSIA, "仙馔蜜酒");
        addItem(ModItems.CUP_DWARF_MALT, "矮人麦芽酒");
        addItem(ModItems.CUP_EMERALD_SUNRISE, "绿宝石日出鸡尾酒");
        addItem(ModItems.CUP_GOLDEN_SCORPION, "金蝎药酒");
        addItem(ModItems.CUP_RUM, "朗姆酒");
        addItem(ModItems.CUP_VODKA, "伏特加");
        addItem(ModItems.CUP_WINE, "葡萄酒");
        addItem(ModItems.WINE_CHOCOLATE_DWARF_MALT, "麦芽酒心巧克力");
        addItem(ModItems.WINE_CHOCOLATE_EMERALD_SUNRISE, "绿宝石酒心巧克力");
        addItem(ModItems.WINE_CHOCOLATE_GOLDEN_SCORPION, "金蝎酒心巧克力");
        addItem(ModItems.WINE_CHOCOLATE_RUM, "朗姆酒心巧克力");
        addItem(ModItems.WINE_CHOCOLATE_VODKA, "伏特加酒心巧克力");
        addItem(ModItems.WINE_CHOCOLATE, "葡萄酒心巧克力");
        addItem(ModItems.SOUL_SALT, "灵魂盐");
        addItem(ModItems.SOUL_WHISTLE, "灵魂笛哨");
        addItem(ModItems.TARANTULA_EGG, "狼蛛卵");
    }
}
