package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.BrooderQueenEntity;
import cx.rain.infadv.entity.model.BrooderQueenModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BrooderQueenRenderer extends GeoEntityRenderer<BrooderQueenEntity> {

    public BrooderQueenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BrooderQueenModel());
    }

}