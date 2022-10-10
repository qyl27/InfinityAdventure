package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.MushroomFaeryEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MushroomFaeryModel extends AnimatedGeoModel<MushroomFaeryEntity> {

	@Override
	public ResourceLocation getModelResource(MushroomFaeryEntity object) {
		return new ResourceLocation(InfAdv.MODID, "geo/mushroom_faery.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MushroomFaeryEntity object) {
		return new ResourceLocation(InfAdv.MODID, "textures/entity/mushroom_faery.png");
	}

	@Override
	public ResourceLocation getAnimationResource(MushroomFaeryEntity animatable) {
		return new ResourceLocation(InfAdv.MODID, "animations/mushroom_faery.animation.json");
	}

}