package cx.rain.infadv.world.gen.level;

import com.mojang.serialization.Codec;
import cx.rain.infadv.InfAdv;
import cx.rain.infadv.world.EndIslandDensityFunction;
import cx.rain.infadv.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLevels {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, InfAdv.MODID);
    public static final DeferredRegister<Codec<? extends DensityFunction>> CODEC_DF = DeferredRegister.create(Registry.DENSITY_FUNCTION_TYPE_REGISTRY, InfAdv.MODID);

    public static final ResourceKey<Level> ETHER = dim("ether");

    public static final RegistryObject<Biome> ETHER_BIOME = BIOMES.register("ether", () -> new Biome.BiomeBuilder()
            .downfall(0.4f)
            .temperature(0.8f)
            .mobSpawnSettings(MobSpawnSettings.EMPTY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .skyColor(calculateSkyColor(0.5f))
                    .waterColor(0x3F76E4)
                    .waterFogColor(0x050533)
                    .fogColor(0xC0D8FF)
                    .build())
            .precipitation(Biome.Precipitation.NONE)
            .generationSettings(new BiomeGenerationSettings.Builder()
                    .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.ETHER_IRON.holder())
                    .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.ETHER_COAL.holder())
                    .build())
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .build());

    public static final RegistryObject<Codec<EndIslandDensityFunction>> CODEC_DENSITY_FUNCTION_END_LIKE =
            CODEC_DF.register("end_like", EndIslandDensityFunction.CODEC::codec);

    public static void register(IEventBus bus) {
        BIOMES.register(bus);
        CODEC_DF.register(bus);
    }

    private static ResourceKey<Level> dim(String name) {
        ResourceLocation loc = new ResourceLocation(InfAdv.MODID, name);
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, loc);
    }

    private static int calculateSkyColor(float f) {
        float g = Mth.clamp(f / 3, -1.0f, 1.0f);
        return Mth.hsvToRgb(0.62222224f - g * 0.05f, 0.5f + g * 0.1f, 1.0f);
    }
}
