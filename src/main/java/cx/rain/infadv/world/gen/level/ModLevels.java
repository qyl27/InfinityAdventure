package cx.rain.infadv.world.gen.level;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.world.ModFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLevels {

    public static final DeferredRegister<Biome> REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, InfAdv.MODID);

    public static final ResourceKey<Level> SKYLAND = dim("skyland");
    public static final ResourceKey<Level> SKYLAND_2 = dim("skyland2");

    public static final RegistryObject<Biome> SKYLAND_BIOME = REGISTRY.register("skyland", () -> new Biome.BiomeBuilder()
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
            .generationSettings(ModFeatures.peaceOres())
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .build());

    private static ResourceKey<Level> dim(String name) {
        ResourceLocation loc = new ResourceLocation(InfAdv.MODID, name);
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, loc);
    }

    private static int calculateSkyColor(float f) {
        float g = Mth.clamp(f / 3, -1.0f, 1.0f);
        return Mth.hsvToRgb(0.62222224f - g * 0.05f, 0.5f + g * 0.1f, 1.0f);
    }
}
