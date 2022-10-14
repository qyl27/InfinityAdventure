package cx.rain.infadv.effect;

import cx.rain.infadv.InfAdv;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    protected static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, InfAdv.MODID);

    public static void register(IEventBus bus) {
        EFFECT.register(bus);
    }

    public static final RegistryObject<MobEffect> DWARF_EYE = EFFECT.register("dwarf_eye", () ->
            new DwarfEyeEffect(MobEffectCategory.BENEFICIAL, 0x40E0D0));
    public static final RegistryObject<MobEffect> SCORPION_POISON = EFFECT.register("scorpion_poison", () ->
            new ScorpionPosionEffect(MobEffectCategory.BENEFICIAL, 0xDAA520, 1.5D)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "5cc33572-4b94-11ed-bdc3-0242ac120002", 0.0D, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> MUSHROOM_CURE = EFFECT.register("mushroom_cure", () ->
            new MushroomCureEffect(MobEffectCategory.BENEFICIAL, 0xDEB887));

    public static final RegistryObject<MobEffect> DISGUISE = EFFECT.register("disguise", () ->
            new DisguiseEffect(MobEffectCategory.BENEFICIAL, 0xF0E68C));
}