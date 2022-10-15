package cx.rain.infadv.enchantment;

import cx.rain.infadv.enchantment.type.RifleEnchantment;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;

//TODO 击退效果
public class GunThwackEnchantment extends RifleEnchantment {
    public GunThwackEnchantment(Rarity arg) {
        super(arg);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return level + (enchantedItem.getEnchantmentLevel(this) * 5);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
