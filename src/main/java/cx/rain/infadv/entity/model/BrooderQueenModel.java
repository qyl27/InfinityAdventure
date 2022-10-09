package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.BrooderQueenEntity;
import cx.rain.infadv.entity.DwarfEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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