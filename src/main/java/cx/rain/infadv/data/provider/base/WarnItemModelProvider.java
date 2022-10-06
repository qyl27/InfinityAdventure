package cx.rain.infadv.data.provider.base;

import com.google.common.base.Preconditions;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class WarnItemModelProvider extends ItemModelProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarnItemModelProvider.class);

    private final List<Pair<ResourceLocation, ResourceLocation>> notExistingModel = new ArrayList<>();

    public WarnItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    @Override
    public ModelFile.ExistingModelFile getExistingFile(ResourceLocation path) {
        try {
            return super.getExistingFile(path);
        } catch (IllegalStateException e) {
            ResourceLocation extended;
            if (path.getPath().contains("/")) {
                extended = path;
            } else {
                extended = new ResourceLocation(path.getNamespace(), folder + "/" + path.getPath());
            }
            notExistingModel.add(Pair.of(path, extended));
            return new ModelFile.ExistingModelFile(extended, existingFileHelper) {
                @Override
                public void assertExistence() {

                }
            };
        }
    }

    @Override
    public ItemModelBuilder getBuilder(String path) {
        Preconditions.checkNotNull(path, "Path must not be null");
        ResourceLocation rl = path.contains(":") ? new ResourceLocation(path) : new ResourceLocation(modid, path);
        ResourceLocation outputLoc = rl.getPath().contains("/") ? rl : new ResourceLocation(rl.getNamespace(), folder + "/" + rl.getPath());
        this.existingFileHelper.trackGenerated(outputLoc, MODEL);
        return generatedModels.computeIfAbsent(outputLoc, (p) -> new UnexceptionalItemModelBuilder(p, existingFileHelper));
    }

    @Override
    public void run(CachedOutput output) throws IOException {
        super.run(output);
        printAllExceptions();
    }

    @Override
    @Nonnull
    public String getName() {
        return "ItemModels: " + modid;
    }

    public void printAllExceptions() {
        if (!notExistingModel.isEmpty()) {
            LOGGER.warn("Not found model in {}", getName());
            notExistingModel.forEach(pair -> LOGGER.warn("  Key: {}, Path: {}", pair.getFirst(), pair.getSecond()));
        }
        generatedModels.forEach((key, builder) -> {
            if (builder instanceof UnexceptionalItemModelBuilder b && !b.notExistingTexture.isEmpty()) {
                LOGGER.warn("Not found texture in {} -> {}", getName(), key);
                b.notExistingTexture.forEach(pair ->
                        LOGGER.warn("  Texture: {}, Path: {}", pair.getFirst(), pair.getSecond()));
            }
        });
    }

    static class UnexceptionalItemModelBuilder extends ItemModelBuilder {
        final List<Pair<String, ResourceLocation>> notExistingTexture = new ArrayList<>();

        public UnexceptionalItemModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper) {
            super(outputLocation, existingFileHelper);
        }

        @Override
        public ItemModelBuilder texture(String key, ResourceLocation texture) {
            try {
                return super.texture(key, texture);
            } catch (IllegalArgumentException e) {
                notExistingTexture.add(Pair.of(key, texture));
                textures.put(key, texture.toString());
                return this;
            }
        }
    }
}
