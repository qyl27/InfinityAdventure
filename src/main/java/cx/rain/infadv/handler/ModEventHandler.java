package cx.rain.infadv.handler;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.world.EndIslandDensityFunction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = InfAdv.MODID)
public class ModEventHandler {

    private static volatile boolean isRegisterEventRun = false;

    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        if (!isRegisterEventRun) {
            Registry.register(Registry.DENSITY_FUNCTION_TYPES, new ResourceLocation(InfAdv.MODID, "end_like"), EndIslandDensityFunction.CODEC.codec());
            isRegisterEventRun = true;
        }
    }
}
