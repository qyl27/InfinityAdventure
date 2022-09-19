package cx.rain.infadv.data;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.ModBlockItems;
import cx.rain.infadv.block.ModBlocks;
import cx.rain.infadv.data.provider.ModBiomeModifierProvider;
import cx.rain.infadv.data.provider.ModBlockStateProvider;
import cx.rain.infadv.data.provider.ModBlockTagsProvider;
import cx.rain.infadv.data.provider.ModItemModelProvider;
import cx.rain.infadv.data.provider.lang.ModLanguageProviderENUS;
import cx.rain.infadv.data.provider.lang.ModLanguageProviderZHCN;
import cx.rain.infadv.item.ModItems;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfAdv.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var exHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(true, new ModItemModelProvider(generator, InfAdv.MODID, exHelper, ModItems.getRegistry()));
            generator.addProvider(true, new ModItemModelProvider(generator, InfAdv.MODID, exHelper, ModBlockItems.getRegistry()));

            generator.addProvider(true, new ModBlockStateProvider(generator, InfAdv.MODID, exHelper, ModBlocks.getRegistry()));
        }

        if (event.includeServer()) {
            generator.addProvider(true, new ModBlockTagsProvider(generator, InfAdv.MODID, exHelper));

            generator.addProvider(true, new ModLanguageProviderZHCN(generator, InfAdv.MODID));
            generator.addProvider(true, new ModLanguageProviderENUS(generator, InfAdv.MODID));

            generator.addProvider(true, new ModBiomeModifierProvider(generator, InfAdv.MODID));
        }

        if (event.includeReports()) {

        }
    }
}
