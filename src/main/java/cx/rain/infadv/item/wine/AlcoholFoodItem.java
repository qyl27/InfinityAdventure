package cx.rain.infadv.item.wine;

import cx.rain.infadv.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AlcoholFoodItem extends Item {

    protected float alcohol;
    protected boolean isDrink;

    public AlcoholFoodItem(Properties properties, float alcohol, boolean isDrink) {
        super(properties);
        this.alcohol = alcohol;
        this.isDrink = isDrink;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (stack.is(ModItems.VODKA.get())) {
            livingEntity.hurt(DamageSource.MAGIC, (float) (6 << 2));
        } else if (stack.is(ModItems.WINE.get())) {
            livingEntity.heal((float)Math.max(4 << 2, 0));
        } else if (stack.is(ModItems.VODKA_CHOCOLATE.get())) {
            livingEntity.hurt(DamageSource.MAGIC, (float) (6 << 1));
        } else if (stack.is(ModItems.WINE_CHOCOLATE.get())) {
            livingEntity.heal((float)Math.max(4 << 1, 0));
        } else if (stack.is(ModItems.AMBROSIA.get())) {
            livingEntity.heal(5.0F);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return this.isDrink ? UseAnim.DRINK : UseAnim.EAT;
    }

}