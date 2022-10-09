package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.PoisonLurkerEntity;
import cx.rain.infadv.entity.model.PoisonLurkerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PoisonLurkerRenderer extends GeoEntityRenderer<PoisonLurkerEntity> {

    public PoisonLurkerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PoisonLurkerModel());
    }

}