package cx.rain.infadv.data.provider;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.data.provider.base.WorldGenProviderBase;
import cx.rain.infadv.world.EndIslandDensityFunction;
import cx.rain.infadv.world.EtherIslandDensityFunction;
import cx.rain.infadv.world.gen.level.ModLevels;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.Noises;
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
                384, 1, 2,
                Blocks.DIRT.defaultBlockState(), Blocks.AIR.defaultBlockState(),
                routers(densityZero(), densityZero(), densityZero(), densityZero(),
                        // 生物群系相关
                        densityZero(), densityZero(), densityZero(), densityZero(), densityZero(), densityZero(),
                        // 顶部位置
                        density(0),
                        // 内部掏洞
//                        slideEnd(density("end/sloped_cheese"))
//                                .blendDensity()
//                                .interpolated()
//                                .mul(0.64)
//                                .squeeze(),
                        density(new EtherIslandDensityFunction(List.of(

                        ))),
                        // 矿脉生成相关
                        densityZero(), densityZero(), densityZero()),
                SurfaceRules.state(Blocks.DIRT.defaultBlockState()),
                List.of(spawnParam().build()),
                -64, true, false, false, true));
        add(ModLevels.ETHER.location(), new LevelStem(getDimensionType(dimensionEther), chunkGenerator(noiseSettings, biomeSource(ModLevels.ETHER_BIOME))));
    }

    private NoiseRouter end() {
        return routers(densityZero(), densityZero(), densityZero(), densityZero(),
                // 生物群系相关
                densityZero(), densityZero(), densityZero(), densityZero(), densityZero(), densityZero(),
                // 顶部位置
                slideEnd(density(EndIslandDensityFunction.INSTANCE)
                        .cache2d()
                        .add(-0.703125)),
                // 内部掏洞
                slideEnd(density("end/sloped_cheese"))
                        .blendDensity()
                        .interpolated()
                        .mul(0.64)
                        .squeeze(),
                // 矿脉生成相关
                densityZero(), densityZero(), densityZero());
    }

    private NoiseRouter overworld() {
        DensityFunctionBuilder shiftX = density("shift_x");
        DensityFunctionBuilder shiftZ = density("shift_z");
        DensityFunctionBuilder factor = density("overworld/factor");
        DensityFunctionBuilder depth = density("overworld/depth");
        DensityFunctionBuilder function7 = noiseGradientDensity(factor, depth);
        DensityFunctionBuilder slopedCheese = density("overworld/sloped_cheese");
        DensityFunctionBuilder function9 = density("overworld/caves/entrances").mul(5).min(slopedCheese);
        DensityFunctionBuilder function10 = slopedCheese.rangeChoice(-1000000.0, 1.5625, function9, underground(slopedCheese));
        DensityFunctionBuilder y = density("y");
        DensityFunctionBuilder veinA = yLimitedInterpolatable(y, densityNoise(Noises.ORE_VEIN_A, 4.0, 4.0), -60, 50, 0).abs();
        DensityFunctionBuilder veinB = yLimitedInterpolatable(y, densityNoise(Noises.ORE_VEIN_B, 4.0, 4.0), -60, 50, 0).abs();
        return routers(
                densityZero(), // densityNoise(Noises.AQUIFER_BARRIER, 0.5),
                densityZero(), // densityNoise(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS, 0.67),
                densityZero(), // densityNoise(Noises.AQUIFER_FLUID_LEVEL_SPREAD, 0.7142857142857143),
                densityZero(), // densityNoise(Noises.AQUIFER_LAVA),
                // 生物群系相关
                densityZero(), // shiftX.shiftedNoise2D(Noises.TEMPERATURE, shiftZ, 0.25),
                densityZero(), // shiftX.shiftedNoise2D(Noises.VEGETATION, shiftZ, 0.25),
                densityZero(), // density("overworld/continents"),
                densityZero(), // density("overworld/erosion"),
                densityZero(), // density("overworld/depth"),
                densityZero(), // density("overworld/ridges"),
                // 顶部位置
                densityZero(), // slide(function7.add(-0.703125).clamp(-64.0, 64.0), -64, 384, 80, 64, -0.078125, 0, 24, 0.1171875),
                function10.lerp(densityYClampedGradient(-64 + 384 - 80, -64 + 384 - 64, 1, 0), -0.078125)
                        .lerp(densityYClampedGradient(-64 + 0, -64 + 24, 0, 1), 0.1171875)
                        .blendDensity().interpolated().mul(0.64).squeeze(),
                        //.size(density("overworld/caves/noodle")),
                densityZero(), // yLimitedInterpolatable(y, densityNoise(Noises.ORE_VEININESS, 1.5, 1.5), -60, 50, 0),
                densityZero(), // veinA.max(veinB).add(-0.08f),
                densityZero()); // densityNoise(Noises.ORE_GAP));
    }

    private DensityFunctionBuilder underground(DensityFunctionBuilder func) {
        return densityNoise(Noises.CAVE_LAYER, 8).square()
                .mul(4)
                .add(densityNoise(Noises.CAVE_CHEESE, 0.6666666666666666)
                        .add(0.27)
                        .clamp(-1, 1)
                        .add(func.mul(-0.64)
                                .add(1.5)
                                .clamp(0, 0.5)))
                .min(density("overworld/caves/entrances"))
                .min(density("overworld/caves/spaghetti_2d").add("overworld/caves/spaghetti_roughness_function"))
                .max(density("overworld/caves/pillars"));
    }

    private DensityFunctionBuilder yLimitedInterpolatable(DensityFunctionBuilder func1, DensityFunctionBuilder func2, int i, int j, int k) {
        return func1.rangeChoice(i, j + 1, func2, density(k)).interpolated();
    }

    private DensityFunctionBuilder postProcess(DensityFunctionBuilder function) {
        return function.blendDensity().interpolated().mul(0.64).squeeze();
    }

    private DensityFunctionBuilder noiseGradientDensity(DensityFunctionBuilder function1, DensityFunctionBuilder function2) {
        return density(4).mul(function2.mul(function1).quarterNegative());
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

    private DensityFunctionBuilder slideEnd(DensityFunctionBuilder function) {
        return slide(function, 0, 128, 72, -184, -23.4375, 4, 32, -0.234375);
    }

}
