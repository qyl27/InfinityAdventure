package cx.rain.infadv.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

//TODO 若玩家半径64格内有任意生物正在追杀玩家，玩家将无法获得此效果;拥有此效果的玩家不会被敌人察觉。
public class DisguiseEffect extends MobEffect {
    protected DisguiseEffect(MobEffectCategory arg, int i) {
        super(arg, i);
    }


}
