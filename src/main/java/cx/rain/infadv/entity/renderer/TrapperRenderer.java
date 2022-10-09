package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.TrapperEntity;
import cx.rain.infadv.entity.model.TrapperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TrapperRenderer extends GeoEntityRenderer<TrapperEntity> {

    public TrapperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TrapperModel());
    }

}