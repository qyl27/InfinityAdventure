package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.PoisonLurkerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PoisonLurkerModel extends AnimatedGeoModel<PoisonLurkerEntity> {

    @Override
    public ResourceLocation getModelResource(PoisonLurkerEntity object) {
        return new ResourceLocation(InfAdv.MODID, "geo/poison_lurker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PoisonLurkerEntity object) {
        return new ResourceLocation(InfAdv.MODID, "textures/entity/lurker/poison_lurker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PoisonLurkerEntity animatable) {
        return new ResourceLocation(InfAdv.MODID, "animations/lurker.animation.json");
    }

}