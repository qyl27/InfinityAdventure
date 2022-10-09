package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.VileRatEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VileRatModel extends AnimatedGeoModel<VileRatEntity> {

	@Override
	public ResourceLocation getModelResource(VileRatEntity object) {
		return new ResourceLocation(InfAdv.MODID, "geo/vile_rat.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(VileRatEntity object) {
		return new ResourceLocation(InfAdv.MODID, "textures/entity/vile_rat.png");
	}

	@Override
	public ResourceLocation getAnimationResource(VileRatEntity animatable) {
		return new ResourceLocation(InfAdv.MODID, "animations/vile_rat.animation.json");
	}

}