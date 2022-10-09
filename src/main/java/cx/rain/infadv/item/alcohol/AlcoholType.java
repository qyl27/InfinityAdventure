package cx.rain.infadv.item.alcohol;

import cx.rain.infadv.utility.TriSupplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public enum AlcoholType {
    CUP_AMBROSIA(ModFoodProperties.AMBROSIA, 0, true, (stack, level, living) -> {
        // Todo: qyl27: Ambrosia boosts the max health of the player. Write it here.
    }),

    CUP_WINE(ModFoodProperties.WINE, 5, true, (stack, level, living) -> { }),
    CUP_DWARF_MALT(ModFoodProperties.WINE_DWARF_MALT, 12, true, (stack, level, living) -> { }),
    CUP_EMERALD_SUNRISE(ModFoodProperties.WINE_EMERALD_SUNRISE, 8, true, (stack, level, living) -> { }),
    CUP_GOLDEN_SCORPION(ModFoodProperties.WINE_GOLDEN_SCORPION, 40, true, (stack, level, living) -> { }),
    CUP_RUM(ModFoodProperties.WINE_RUM, 45, true, (stack, level, living) -> { }),
    CUP_VODKA(ModFoodProperties.WINE_VODKA, 56, true, (stack, level, living) -> { }),

    WINE_CHOCOLATE(ModFoodProperties.WINE_CHOCOLATE, 4, false, (stack, level, living) -> { }),
    WINE_CHOCOLATE_DWARF_MALT(ModFoodProperties.WINE_CHOCOLATE_DWARF_MALT, 3, false, (stack, level, living) -> { }),
    WINE_CHOCOLATE_EMERALD_SUNRISE(ModFoodProperties.WINE_CHOCOLATE_EMERALD_SUNRISE, 2, false, (stack, level, living) -> { }),
    WINE_CHOCOLATE_GOLDEN_SCORPION(ModFoodProperties.WINE_CHOCOLATE_GOLDEN_SCORPION, 10, false, (stack, level, living) -> { }),
    WINE_CHOCOLATE_RUM(ModFoodProperties.WINE_CHOCOLATE_RUM, 11, false, (stack, level, living) -> { }),
    WINE_CHOCOLATE_VODKA(ModFoodProperties.WINE_CHOCOLATE_VODKA, 14, false, (stack, level, living) -> { }),
    ;

    private final FoodProperties foodProperties;
    private final short alcoholLevel;
    private final boolean isDrink;
    private final TriSupplier<ItemStack, Level, LivingEntity> doDrink;

    private AlcoholType(FoodProperties food, int alcoholPercent, boolean animDrink, TriSupplier<ItemStack, Level, LivingEntity> onDrink) {
        if (alcoholPercent < 0 || alcoholPercent > 100) {
            throw new RuntimeException("Bad alcohol percent, it must be 0 - 100.");
        }

        foodProperties = food;
        alcoholLevel = (short) alcoholPercent;
        isDrink = animDrink;
        doDrink = onDrink;
    }

    public FoodProperties getFoodProperties() {
        return foodProperties;
    }

    public short getAlcoholPercent() {
        return alcoholLevel;
    }

    public boolean isDrink() {
        return isDrink;
    }

    public void drink(ItemStack stack, Level level, LivingEntity entity) {
        doDrink.apply(stack, level, entity);
    }
}
