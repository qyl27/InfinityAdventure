package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.TrapperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TrapperModel extends AnimatedGeoModel<TrapperEntity> {

	@Override
	public ResourceLocation getModelResource(TrapperEntity object) {
		return new ResourceLocation(InfAdv.MODID, "geo/trapper.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TrapperEntity object) {
		return new ResourceLocation(InfAdv.MODID, "textures/entity/trapper.png");
	}

	@Override
	public ResourceLocation getAnimationResource(TrapperEntity animatable) {
		return new ResourceLocation(InfAdv.MODID, "animations/trapper.animation.json");
	}

}