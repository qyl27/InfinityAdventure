package cx.rain.infadv.data.provider.base;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class LanguageProviderBase extends LanguageProvider {
    public LanguageProviderBase(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    public void addCreativeTab(CreativeModeTab tab, String value) {
        this.add("itemGroup." + tab.langId, value);
    }
}
