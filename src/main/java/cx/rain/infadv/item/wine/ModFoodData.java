package cx.rain.infadv.item.wine;

import cx.rain.infadv.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodData {

    public static final FoodProperties EMERALD_SUNRISE = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 3600), 1.0F).build();
    public static final FoodProperties DWARF_MALT_LIQUOR = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(ModEffects.DWARF_EYE.get(), 12000), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 12000), 1.0F).build();
    public static final FoodProperties GOLDEN_SCORPION = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(ModEffects.SCORPION_POISON.get(), 2400), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1.0F).build();
    public static final FoodProperties RUM = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 900, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 900, 1), 1.0F).build();
    public static final FoodProperties VODKA = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1.0F).build();
    public static final FoodProperties WINE = (new FoodProperties.Builder()).build();
    public static final FoodProperties AMBROSIA = (new FoodProperties.Builder()).build();

    public static final FoodProperties MALT_LIQUOR_CHOCOLATE =
            (new FoodProperties.Builder()).nutrition(1).fast()
            .effect(() -> new MobEffectInstance(ModEffects.DWARF_EYE.get(), 3000), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 3000), 1.0F).build();
    public static final FoodProperties EMERALD_CHOCOLATE =
            (new FoodProperties.Builder()).nutrition(1).fast()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 900), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 900), 1.0F).build();
    public static final FoodProperties RUM_CHOCOLATE =
            (new FoodProperties.Builder()).nutrition(1).fast()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 220), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 220), 1.0F).build();
    public static final FoodProperties VODKA_CHOCOLATE =
            (new FoodProperties.Builder()).nutrition(1).fast()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300), 1.0F).nutrition(1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300), 1.0F).build();
    public static final FoodProperties WINE_CHOCOLATE =
            (new FoodProperties.Builder()).nutrition(1).fast().build();
    public static final FoodProperties GOLDEN_SCORPION_CHOCOLATE =
            (new FoodProperties.Builder()).nutrition(1).fast()
            .effect(() -> new MobEffectInstance(ModEffects.SCORPION_POISON.get(), 600), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600), 1.0F).build();

}