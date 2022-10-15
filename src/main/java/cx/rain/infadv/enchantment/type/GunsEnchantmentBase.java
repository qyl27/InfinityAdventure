package cx.rain.infadv.enchantment.type;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public abstract class GunsEnchantmentBase extends Enchantment {
    public GunsEnchantmentBase(Rarity arg, EnchantmentCategory arg2) {
        super(arg, arg2, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
}
