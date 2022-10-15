package cx.rain.infadv.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantmentCategory {
    public static final EnchantmentCategory ALL_GUNS = EnchantmentCategory.create("all_guns", item -> true);
    public static final EnchantmentCategory RIFLE = EnchantmentCategory.create("rifle", item -> true);
    public static final EnchantmentCategory SHOTGUN = EnchantmentCategory.create("shotgun", item -> true);
    public static final EnchantmentCategory SNIPER_RIFLE = EnchantmentCategory.create("sniper_rifle", item -> true);
}
