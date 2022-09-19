package cx.rain.infadv.data.provider.lang;

import cx.rain.infadv.block.ModBlocks;
import cx.rain.infadv.data.provider.base.LanguageProviderBase;
import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.item.ModTabs;
import net.minecraft.data.DataGenerator;

public class ModLanguageProviderZHCN extends LanguageProviderBase {
    public ModLanguageProviderZHCN(DataGenerator gen, String modid) {
        super(gen, modid, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        addCreativeTab(ModTabs.ORES, "无尽冒险 | 矿石");

        addBlock(ModBlocks.SILVER_ORE, "银矿石");
        addBlock(ModBlocks.MITHRIL_ORE, "秘银矿石");
        addBlock(ModBlocks.DEEPSLATE_MITHRIL_ORE, "深板岩秘银矿石");
        addBlock(ModBlocks.ADAMANTINE_ORE, "精金矿石");
        addBlock(ModBlocks.DEEPSLATE_ADAMANTINE_ORE, "深板岩精金矿石");
        addBlock(ModBlocks.RUBY_ORE, "红宝石矿石");
        addBlock(ModBlocks.AQUAMARINE_ORE, "海蓝宝石矿石");

        addItem(ModItems.SILVER_INGOT, "银锭");
        addItem(ModItems.MITHRIL_INGOT, "秘银锭");
        addItem(ModItems.ADAMANTINE_INGOT, "精金锭");
        addItem(ModItems.RUBY, "红宝石");
        addItem(ModItems.AQUAMARINE, "海蓝宝石");
    }
}
