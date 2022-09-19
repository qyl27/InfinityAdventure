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
        addCreativeTab(ModTabs.ORES, "InfinityAdventure | Ores");

        addBlock(ModBlocks.SILVER_ORE, "Silver Ore");
        addBlock(ModBlocks.MITHRIL_ORE, "Mithril Ore");
        addBlock(ModBlocks.DEEPSLATE_MITHRIL_ORE, "Deepslate Mithril Ore");
        addBlock(ModBlocks.ADAMANTINE_ORE, "Adamantine Ore");
        addBlock(ModBlocks.DEEPSLATE_ADAMANTINE_ORE, "Deepslate Adamantine Ore");
        addBlock(ModBlocks.RUBY_ORE, "Ruby Ore");
        addBlock(ModBlocks.AQUAMARINE_ORE, "Aquamarine Ore");

        addItem(ModItems.SILVER_INGOT, "Silver Ingot");
        addItem(ModItems.MITHRIL_INGOT, "Mithril Ingot");
        addItem(ModItems.ADAMANTINE_INGOT, "Adamantine Ingot");
        addItem(ModItems.RUBY, "Ruby");
        addItem(ModItems.AQUAMARINE, "Aquamarine");
    }
}
