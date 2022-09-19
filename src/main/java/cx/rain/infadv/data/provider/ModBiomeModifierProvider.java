package cx.rain.infadv.data.provider;

import cx.rain.infadv.data.provider.base.BiomeModifierProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;

public class ModBiomeModifierProvider extends BiomeModifierProviderBase {
    public ModBiomeModifierProvider(DataGenerator generator, String modid) {
        super(generator, modid);
    }

    @Override
    public void addBiomeModifiers() {
        overworldOreFeatures("add_silver_ore", modLoc("silver_ore"));
        overworldOreFeatures("add_mithril_ore", modLoc("mithril_ore"));
        overworldOreFeatures("add_adamantine_ore", modLoc("adamantine_ore"));
        overworldOreFeatures("add_ruby_ore", modLoc("ruby_ore"));
        oreFeatures("add_aquamarine_ore", Tags.Biomes.IS_MOUNTAIN, modLoc("aquamarine_ore"));
    }
}
