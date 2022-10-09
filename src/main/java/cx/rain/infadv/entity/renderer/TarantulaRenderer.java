package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.TarantulaEntity;
import cx.rain.infadv.entity.model.TarantulaModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TarantulaRenderer extends GeoEntityRenderer<TarantulaEntity> {

    public TarantulaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TarantulaModel());
    }

}