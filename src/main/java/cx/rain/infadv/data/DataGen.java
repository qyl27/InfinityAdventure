package cx.rain.infadv.data;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.data.provider.ModItemModelProvider;
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
        }

        if (event.includeServer()) {

        }

        if (event.includeReports()) {

        }
    }
}
