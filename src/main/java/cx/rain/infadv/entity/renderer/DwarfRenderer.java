package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.DwarfEntity;
import cx.rain.infadv.entity.model.DwarfModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DwarfRenderer extends GeoEntityRenderer<DwarfEntity> {
    public DwarfRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfModel());
    }
}
