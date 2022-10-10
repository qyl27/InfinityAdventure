package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.DarkGhostEntity;
import cx.rain.infadv.entity.model.DarkGhostModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DarkGhostRenderer extends GeoEntityRenderer<DarkGhostEntity> {

    public DarkGhostRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DarkGhostModel());
    }

}