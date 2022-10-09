package cx.rain.infadv.entity.model;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.entity.DarkGhostEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DarkGhostModel extends AnimatedGeoModel<DarkGhostEntity> {

	@Override
	public ResourceLocation getModelResource(DarkGhostEntity object) {
		return new ResourceLocation(InfAdv.MODID, "geo/dark_ghost.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DarkGhostEntity object) {
		return new ResourceLocation(InfAdv.MODID, "textures/entity/dark_ghost.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DarkGhostEntity animatable) {
		return new ResourceLocation(InfAdv.MODID, "animations/dark_ghost.animation.json");
	}

}