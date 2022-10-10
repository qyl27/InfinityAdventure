package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.MushroomLurkerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MushroomLurkerModel extends AnimatedGeoModel<MushroomLurkerEntity> {

	@Override
	public ResourceLocation getModelResource(MushroomLurkerEntity object) {
		return new ResourceLocation(InfAdv.MODID, "geo/mushroom_lurker.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MushroomLurkerEntity object) {
		return new ResourceLocation(InfAdv.MODID, "textures/entity/lurker/mushroom_lurker.png");
	}

	@Override
	public ResourceLocation getAnimationResource(MushroomLurkerEntity animatable) {
		return new ResourceLocation(InfAdv.MODID, "animations/lurker.animation.json");
	}

}