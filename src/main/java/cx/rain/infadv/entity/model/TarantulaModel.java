package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.TarantulaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TarantulaModel extends AnimatedGeoModel<TarantulaEntity> {

    @Override
    public ResourceLocation getModelResource(TarantulaEntity object) {
        return new ResourceLocation(InfAdv.MODID, "geo/tarantula.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TarantulaEntity object) {
        return new ResourceLocation(InfAdv.MODID, "textures/entity/tarantula.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TarantulaEntity animatable) {
        return new ResourceLocation(InfAdv.MODID, "animations/tarantula.animation.json");
    }

}