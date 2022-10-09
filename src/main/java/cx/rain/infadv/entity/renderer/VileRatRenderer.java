package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.VileRatEntity;
import cx.rain.infadv.entity.model.VileRatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class VileRatRenderer extends GeoEntityRenderer<VileRatEntity> {

    public VileRatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VileRatModel());
    }

}