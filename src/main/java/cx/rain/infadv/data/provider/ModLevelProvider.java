package cx.rain.infadv.data.provider;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.data.provider.base.WorldGenProviderBase;
import cx.rain.infadv.world.EndIslandDensityFunction;
import cx.rain.infadv.world.gen.level.ModLevels;
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
        super(generator, InfAdv.MODID, true);
    }

    @Override
    protected void addAll() {
        ResourceLocation dimensionEther = add(ModLevels.ETHER.location(), dimensionType(6000, true,
                false, false, true, 1, true, true,
                -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0, true, true, 0, 7, 0));

        ResourceLocation noiseSettings = add(modLoc("ether_noise_settings"), noiseGeneratorSettings(-64,
                384, 1, 2, Blocks.DIRT.defaultBlockState(), Blocks.AIR.defaultBlockState(),
                routers(densityZero(), densityZero(), densityZero(), densityZero(), densityZero(),
                        densityZero(), densityZero(), densityZero(), densityZero(), densityZero(),
                        density(EndIslandDensityFunction.INSTANCE).cache2d().add(-0.703125),
                        endDensity(),
                        densityZero(), densityZero(), densityZero()),
                SurfaceRules.state(Blocks.DIRT.defaultBlockState()),
                List.of(spawnParam().build()),
                -64, true, false, false, true));
        add(ModLevels.ETHER.location(), new LevelStem(getDimensionType(dimensionEther), chunkGenerator(noiseSettings, biomeSource(ModLevels.ETHER_BIOME))));
    }

    private DensityFunctionBuilder endDensity() {
        return density("end/sloped_cheese")
                .lerp(densityYClampedGradient(56, 312, 1, 0), -23.4375)
                .lerp(densityYClampedGradient(4, 32, 0, 1), -0.234375)
                .blendDensity().interpolated().mul(0.64).squeeze();
    }

    private DensityFunctionBuilder slide(DensityFunctionBuilder function, int base, int top, int offTopFrom, int offTopTo, double keep, int offFrom, int offTo, double keep2) {
        return function
                .lerp(densityYClampedGradient(base + top - offTopFrom, base + top - offTopTo, 1, 0), keep)
                .lerp(densityYClampedGradient(base + offFrom, base + offTo, 0, 1), keep2);
    }
}
