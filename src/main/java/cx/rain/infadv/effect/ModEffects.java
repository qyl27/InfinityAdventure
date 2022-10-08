package cx.rain.infadv.effect;

import cx.rain.infadv.InfAdv;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    protected static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        EFFECT.register(bus);
    }

    public static final RegistryObject<MobEffect> DWARF_EYE = EFFECT.register("dwarf_eye", () -> new EffectDwarfEye(MobEffectCategory.BENEFICIAL, 4251856));
    public static final RegistryObject<MobEffect> SCORPION_POISON = EFFECT.register("scorpion_poison", () -> new EffectScorpionVenom(MobEffectCategory.BENEFICIAL, 14329120));

}