package cx.rain.infadv.item.alcohol;

import cx.rain.infadv.item.ModItems;
import cx.rain.infadv.item.ModTabs;
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
public class AlcoholItem extends Item {

    protected AlcoholType alcoholType;

    public AlcoholItem(AlcoholType type) {
        super(new Properties()
                .tab(ModTabs.FOODS)
                .food(type.getFoodProperties()));
        alcoholType = type;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        alcoholType.drink(stack, level, livingEntity);
        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return alcoholType.isDrink() ? UseAnim.DRINK : UseAnim.EAT;
    }
}