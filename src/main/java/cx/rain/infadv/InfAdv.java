package cx.rain.infadv;

import cx.rain.infadv.block.ModBlockItems;
import cx.rain.infadv.block.ModBlocks;
import cx.rain.infadv.entity.ModEntities;
import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.world.IALevels;
import cx.rain.infadv.world.gen.ModConfiguredFeatures;
import cx.rain.infadv.world.gen.ModPlacedFeatures;
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

    private static final Logger logger = LoggerFactory.getLogger(NAME);

    public InfAdv() {
        INSTANCE = this;

        logger.info("Loading InfAdv. Version: " + VERSION);

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onSetup);
        bus.addListener(this::onClientSetup);

        ModBlocks.register(bus);
        ModItems.register(bus);
        ModBlockItems.register(bus);

        ModEntities.register(bus);

        ModConfiguredFeatures.register(bus);
        ModPlacedFeatures.register(bus);
        IALevels.REGISTRY.register(bus);

        logger.info("Fresh beginning.");
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
