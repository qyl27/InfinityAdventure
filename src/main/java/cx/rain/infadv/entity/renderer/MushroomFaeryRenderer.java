package cx.rain.infadv.entity.renderer;

import cx.rain.infadv.entity.MushroomFaeryEntity;
import cx.rain.infadv.entity.model.MushroomFaeryModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MushroomFaeryRenderer extends GeoEntityRenderer<MushroomFaeryEntity> {

    public MushroomFaeryRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MushroomFaeryModel());
    }

}
