package cx.rain.infadv.enchantment;

import cx.rain.infadv.enchantment.type.AllGunsEnchantment;

//TODO 子弹击中方块后不再穿透而是对方块后3格内所有生物造成（60%+（10%*附魔等级））的伤害，击中生物时对其后3格内的所有生物造成（60%+（10%*附魔等级））的伤害
public class SonicBoomEnchantment extends AllGunsEnchantment {
    public SonicBoomEnchantment(Rarity arg) {
        super(arg);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
