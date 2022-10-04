package cx.rain.infadv.data.provider;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.data.provider.base.WorldGenProviderBase;
import cx.rain.infadv.world.EndIslandDensityFunction;
import cx.rain.infadv.world.ModLevels;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public class ModLevelProvider extends WorldGenProviderBase {

    public ModLevelProvider(DataGenerator generator) {
        super(generator, InfAdv.MODID, false);
    }

    ResourceLocation dimensionType;

    @Override
    protected void addAll() {
        dimensionType = add(ModLevels.ETHER.location(), dimensionType(true, false, false,
                true, 1, true, true, -64, 384,
                384, BlockTags.INFINIBURN_OVERWORLD, BuiltinDimensionTypes.OVERWORLD_EFFECTS, 0,
                true, true, 0, 7, 0));

        // DensityFunctionBuilder yOreA = density("y").rangeChoice(-60, 51, densityNoise(Noises.ORE_VEIN_A, 4, 4), density(0)).interpolated().abs();
        // DensityFunctionBuilder yOreB = density("y").rangeChoice(-60, 51, densityNoise(Noises.ORE_VEIN_B, 4, 4), density(0)).interpolated().abs();

        ResourceLocation noiseSettings = add(modLoc("ether_noise_settings"), noiseGeneratorSettings(
                -64, 384, 1, 2, Blocks.DIRT.defaultBlockState(), Blocks.AIR.defaultBlockState(),
                densityZero(), densityZero(), densityZero(), densityZero(),
                densityZero(), // density("shift_x").shiftedNoise2D(Noises.TEMPERATURE, "shift_z", 0.25),
                densityZero(), // density("shift_x").shiftedNoise2D(Noises.VEGETATION, "shift_z", 0.25),
                densityZero(), // density("overworld/continents"),
                densityZero(), // new DensityFunctionBuilder(new EndIslandDensityFunction()).cache2d(), // density("overworld/erosion"),
                densityZero(), // density("overworld/depth"),
                densityZero(), // density("overworld/ridges"),
                new DensityFunctionBuilder(EndIslandDensityFunction.INSTANCE).cache2d().add(-0.703125), // slide(density("overworld/depth").mul(density("overworld/factor").cache2d()).quarterNegative().mul(4).add(0.7).clamp(-64, 64)),
                endDensity(),
                densityZero(), // density("y").rangeChoice(-60, 51, densityNoise(Noises.ORE_VEININESS, 1.5, 1.5), density(0)).interpolated(),
                densityZero(), // yOreA.max(yOreB).add(-0.08),
                densityZero(), // densityNoise(Noises.ORE_GAP),
                SurfaceRules.state(Blocks.DIRT.defaultBlockState()),
                100, true, false, false, true,
                List.of(spawnParam().build())));
        add(ModLevels.ETHER.location(), new LevelStem(getDimensionType(dimensionType), chunkGenerator(noiseSettings, biomeSource(ModLevels.ETHER_BIOME))));
    }

    private DensityFunctionBuilder endDensity() {
        return density("end/sloped_cheese")
                .lerp(densityYClampedGradient(56, 312, 1, 0), -23.4375)
                .lerp(densityYClampedGradient(4, 32, 0, 1), -0.234375)
                .blendDensity().interpolated().mul(0.64).squeeze();
    }

    private DensityFunctionBuilder slide(DensityFunctionBuilder arg) {
        DensityFunctionBuilder fun =
                densityYClampedGradient(240, 256, 1.0, 0.0).lerp(-0.078125, arg);
        return densityYClampedGradient(-64, -40, 0.0, 1.0).lerp(0.1171875, fun);
    }

}
