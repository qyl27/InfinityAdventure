package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.PoisonThroatEntity;
import cx.rain.infadv.entity.model.PoisonThroatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PoisonThroatRenderer extends GeoEntityRenderer<PoisonThroatEntity> {

    public PoisonThroatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PoisonThroatModel());
    }

}