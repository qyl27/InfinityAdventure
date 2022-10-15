package cx.rain.infadv.enchantment;

import cx.rain.infadv.enchantment.type.AllGunsEnchantment;

//TODO 减少（20%*附魔等级）的后坐力
public class StableShootingEnchantment extends AllGunsEnchantment {

    public StableShootingEnchantment(Rarity arg) {
        super(arg);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
