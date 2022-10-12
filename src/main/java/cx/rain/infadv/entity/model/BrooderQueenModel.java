package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.BrooderQueenEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BrooderQueenModel extends AnimatedGeoModel<BrooderQueenEntity> {

    @Override
    public ResourceLocation getModelResource(BrooderQueenEntity object) {
        return new ResourceLocation(InfAdv.MODID, "geo/brooder_queen.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BrooderQueenEntity object) {
        return new ResourceLocation(InfAdv.MODID, "textures/entity/brooder_queen.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BrooderQueenEntity animatable) {
        return new ResourceLocation(InfAdv.MODID, "animations/brooder_queen.animation.json");
    }

}