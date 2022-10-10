package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.DwarfEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DwarfModel extends AnimatedGeoModel<DwarfEntity> {

    @Override
    public ResourceLocation getModelResource(DwarfEntity object) {
        return new ResourceLocation(InfAdv.MODID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfEntity object) {
        return new ResourceLocation(InfAdv.MODID, "textures/entity/dwarf.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfEntity animatable) {
        return new ResourceLocation(InfAdv.MODID, "animations/dwarf.animation.json");
    }

}