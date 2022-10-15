package cx.rain.infadv.enchantment;

import cx.rain.infadv.enchantment.type.ShotgunEnchantment;

//TODO 霰弹枪的一轮射击中的多发子弹命中同一个目标时额外造成（（命中弹丸总数-1）*5%*附魔等级）伤害
public class HeavilyDamagedEnchantment extends ShotgunEnchantment {

    public HeavilyDamagedEnchantment(Rarity arg) {
        super(arg);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
