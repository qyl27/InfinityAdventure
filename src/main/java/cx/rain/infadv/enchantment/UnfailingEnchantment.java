package cx.rain.infadv.enchantment;

import cx.rain.infadv.enchantment.type.AllGunsEnchantment;

//TODO 子弹不会因距离而衰减伤害
public class UnfailingEnchantment extends AllGunsEnchantment {
    public UnfailingEnchantment(Rarity arg) {
        super(arg);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
