package cx.rain.infadv.enchantment;

import cx.rain.infadv.enchantment.type.SniperRifleEnchantment;

//TODO 狙击步枪即使不开镜也是精准的
public class SniperBlindlyEnchantment extends SniperRifleEnchantment {
    public SniperBlindlyEnchantment(Rarity arg) {
        super(arg);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
