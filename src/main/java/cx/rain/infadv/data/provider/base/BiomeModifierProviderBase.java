package cx.rain.infadv.data.provider.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BiomeModifierProviderBase implements DataProvider {
    protected final DataGenerator gen;
    protected final String modId;

    protected final Map<String, BiomeModifier> BIOME_MODIFIERS = new HashMap<>();

    public BiomeModifierProviderBase(DataGenerator generator, String modid) {
        gen = generator;
        modId = modid;
    }

    public abstract void addBiomeModifiers();

    protected void oreFeatures(String name, TagKey<Biome> biomes, ResourceLocation features) {
        features(name, biomes, features, GenerationStep.Decoration.UNDERGROUND_ORES);
    }

    protected void overworldOreFeatures(String name, ResourceLocation features) {
        overworldFeatures(name, features, GenerationStep.Decoration.UNDERGROUND_ORES);
    }

    protected void overworldFeatures(String name, ResourceLocation features, GenerationStep.Decoration step) {
        features(name, BiomeTags.IS_OVERWORLD, features, step);
    }

    protected void features(String name, TagKey<Biome> biomes, ResourceLocation features, GenerationStep.Decoration step) {
        BIOME_MODIFIERS.put(name, new BiomeModifier(forgeLoc("add_features"), biomes, features, step));
    }

    protected ResourceLocation mcLoc(String name) {
        return new ResourceLocation("minecraft", name);
    }

    protected ResourceLocation forgeLoc(String name) {
        return new ResourceLocation("forge", name);
    }

    protected ResourceLocation modLoc(String name) {
        return new ResourceLocation(modId, name);
    }

    @Override
    public void run(CachedOutput cache) throws IOException {
        addBiomeModifiers();

        for (var entry : BIOME_MODIFIERS.entrySet()) {
            var json = new JsonObject();
            json.addProperty("type", entry.getValue().type.toString());
            json.addProperty("biomes", "#" + entry.getValue().biomes.location());
            json.addProperty("features", entry.getValue().features.toString());
            json.addProperty("step", entry.getValue().step.getName());
            save(cache, json, entry.getKey());
        }
    }

    private void save(CachedOutput cache, JsonElement json, String name) throws IOException {
        DataProvider.saveStable(cache, json, gen.getOutputFolder().resolve("data")
                .resolve(modId).resolve("forge").resolve("biome_modifier").resolve(name + ".json"));
    }

    @Override
    public String getName() {
        return "Biome Modifiers: " + modId;
    }

    public record BiomeModifier(ResourceLocation type, TagKey<Biome> biomes, ResourceLocation features, GenerationStep.Decoration step) {
    }
}
