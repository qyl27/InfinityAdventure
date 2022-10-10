package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.MushroomLurkerEntity;
import cx.rain.infadv.entity.model.MushroomLurkerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MushroomLurkerRenderer extends GeoEntityRenderer<MushroomLurkerEntity> {

    public MushroomLurkerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MushroomLurkerModel());
    }

}
