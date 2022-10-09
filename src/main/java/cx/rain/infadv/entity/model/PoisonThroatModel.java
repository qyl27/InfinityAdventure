package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.PoisonThroatEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PoisonThroatModel extends AnimatedGeoModel<PoisonThroatEntity> {

	@Override
	public ResourceLocation getModelResource(PoisonThroatEntity object) {
		return new ResourceLocation(InfAdv.MODID, "geo/poison_throat.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PoisonThroatEntity object) {
		return new ResourceLocation(InfAdv.MODID, "textures/entity/lurker/poison_throat.png");
	}

	@Override
	public ResourceLocation getAnimationResource(PoisonThroatEntity animatable) {
		return new ResourceLocation(InfAdv.MODID, "animations/poison_throat.animation.json");
	}

}