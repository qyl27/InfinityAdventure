package cx.rain.infadv;

import cx.rain.infadv.item.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(InfAdv.MODID)
public class InfAdv {
    public static final String MODID = "infadv";
    public static final String NAME = "InfinityAdventure";
    public static final String VERSION = "@version@";

    private static InfAdv INSTANCE;

    private static Logger logger = LoggerFactory.getLogger(NAME);

    public InfAdv() {
        INSTANCE = this;

        logger.info("Loading InfAdv. Version: " + VERSION);

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onSetup);
        bus.addListener(this::onClientSetup);

        ModItems.register(bus);

        logger.info("InfAdv loaded.");
    }

    public static InfAdv getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return logger;
    }

    private void onSetup(FMLCommonSetupEvent event) {

    }

    private void onClientSetup(FMLClientSetupEvent event) {

    }
}
