package cx.rain.infadv.item.alcohol;

import cx.rain.infadv.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties WINE_EMERALD_SUNRISE = new FoodProperties.Builder()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 3600), 1)
            .build();

    public static final FoodProperties WINE_DWARF_MALT = new FoodProperties.Builder()
            .effect(() -> new MobEffectInstance(ModEffects.DWARF_EYE.get(), 12000), 1)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 12000), 1)
            .build();

    public static final FoodProperties WINE_GOLDEN_SCORPION = new FoodProperties.Builder()
            .effect(() -> new MobEffectInstance(ModEffects.SCORPION_POISON.get(), 2400), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1)
            .build();

    public static final FoodProperties WINE_RUM = new FoodProperties.Builder()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 900, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 900, 1), 1)
            .build();

    public static final FoodProperties WINE_VODKA = new FoodProperties.Builder()
            .effect(() -> new MobEffectInstance(MobEffects.HARM, 1, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1)
            .build();

    public static final FoodProperties WINE = new FoodProperties.Builder()
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1)
            .build();
    
    public static final FoodProperties AMBROSIA = new FoodProperties.Builder().build();

    public static final FoodProperties WINE_CHOCOLATE = new FoodProperties.Builder().nutrition(1).fast().build();

    public static final FoodProperties WINE_CHOCOLATE_EMERALD_SUNRISE = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 900), 1)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 900), 1)
            .build();

    public static final FoodProperties WINE_CHOCOLATE_DWARF_MALT = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .effect(() -> new MobEffectInstance(ModEffects.DWARF_EYE.get(), 3000), 1)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 3000), 1)
            .build();

    public static final FoodProperties WINE_CHOCOLATE_GOLDEN_SCORPION = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .effect(() -> new MobEffectInstance(ModEffects.SCORPION_POISON.get(), 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600), 1)
            .build();

    public static final FoodProperties WINE_CHOCOLATE_RUM = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 220), 1)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 220), 1)
            .build();

    public static final FoodProperties WINE_CHOCOLATE_VODKA = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.HARM, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300), 1)
            .build();

}