package cx.rain.infadv.data.provider.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import cx.rain.infadv.utility.ResourceKeys;
import net.minecraft.core.*;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.info.WorldgenRegistryDumpReport;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.CubicSpline;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.SurfaceRules.ifTrue;
import static net.minecraft.world.level.levelgen.SurfaceRules.verticalGradient;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public abstract class WorldGenProviderBase implements DataProvider, ResourceKeys {

    private final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    private final Logger logger = LoggerFactory.getLogger(WorldGenProviderBase.class);

    protected final RegistryAccess.Writable registryAccess;
    protected final DynamicOps<JsonElement> ops;
    private final boolean dump;

    private final DataGenerator generator;
    private final String modid;

    protected final MappedRegistry<WorldPreset> worldPresets;
    protected final MappedRegistry<LevelStem> levelStems;
    protected final MappedRegistry<DimensionType> dimensionTypes;
    protected final MappedRegistry<StructureSet> structureSets;
    protected final MappedRegistry<Biome> biomes;
    protected final MappedRegistry<NormalNoise.NoiseParameters> noiseParameters;
    protected final MappedRegistry<NoiseGeneratorSettings> noiseGeneratorSettings;
    protected final MappedRegistry<DensityFunction> densityFunctions;

    protected final Map<ResourceKey<Registry<?>>, Map<ResourceLocation, Object>> objects = new HashMap<>();

    public WorldGenProviderBase(DataGenerator generator, String modid, boolean dump) {
        this.generator = generator;
        this.modid = modid;
        this.dump = dump;

        this.registryAccess = RegistryAccess.builtinCopy();
        this.levelStems = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.stable(), null);
        this.worldPresets = (MappedRegistry<WorldPreset>) registryAccess.ownedRegistryOrThrow(Registry.WORLD_PRESET_REGISTRY);
        this.dimensionTypes = (MappedRegistry<DimensionType>) registryAccess.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        this.structureSets = (MappedRegistry<StructureSet>) registryAccess.ownedRegistryOrThrow(Registry.STRUCTURE_SET_REGISTRY);
        this.biomes = (MappedRegistry<Biome>) registryAccess.ownedRegistryOrThrow(Registry.BIOME_REGISTRY);
        this.noiseParameters = (MappedRegistry<NormalNoise.NoiseParameters>) registryAccess.ownedRegistryOrThrow(Registry.NOISE_REGISTRY);
        this.noiseGeneratorSettings = (MappedRegistry<NoiseGeneratorSettings>) registryAccess.ownedRegistryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
        this.densityFunctions = (MappedRegistry<DensityFunction>) registryAccess.ownedRegistryOrThrow(Registry.DENSITY_FUNCTION_REGISTRY);

        this.ops = RegistryOps.create(JsonOps.INSTANCE, registryAccess);
    }

    protected abstract void addAll();

    @Override
    public void run(CachedOutput output) {
        if (dump) {
            new WorldgenRegistryDumpReport(generator).run(output);
        }

        Path path = generator.getOutputFolder();
        addAll();

        save(path, output, levelStems, LevelStem.CODEC);
        save(path, output, dimensionTypes, DimensionType.DIRECT_CODEC);
        save(path, output, structureSets, StructureSet.DIRECT_CODEC);
        save(path, output, noiseParameters, NormalNoise.NoiseParameters.DIRECT_CODEC);
        save(path, output, noiseGeneratorSettings, NoiseGeneratorSettings.DIRECT_CODEC);
    }

    private <T> void save(Path path, CachedOutput cache, Registry<T> register, Codec<T> codec) {
        ResourceKey<? extends Registry<T>> key = register.key();
        Path target = path.resolve("data").resolve(modid).resolve(key.location().getPath());
        Map<ResourceLocation, T> values = (Map<ResourceLocation, T>) objects.get(key);
        if (values != null && !values.isEmpty()) {
            values.forEach((name, value) -> {
                JsonElement json = codec.encodeStart(ops, value)
                        .getOrThrow(false, s -> {
                            throw new RuntimeException("Couldn't serialize element " + name + ": " + s);
                        });
                Path output = target.resolve(name.getPath() + ".json");
                save(cache, json, output);
            });
        }
    }

    private void save(CachedOutput cache, JsonElement element, Path path) {
        try {
            DataProvider.saveStable(cache, element, path);
        } catch (IOException e) {
            logger.error("Couldn't save element {}", path, e);
        }
    }

    protected <T> ResourceLocation add(ResourceLocation name, T value, WritableRegistry<T> registry) {
        ResourceKey key = registry.key();
        Holder<T> holder = register(name, value, registry, true);
        objects.computeIfAbsent(key, __ -> new HashMap<>()).put(name, holder.value());
        return name;
    }

    protected <T> Holder<T> get(ResourceLocation name, Supplier<T> value, WritableRegistry<T> registry) {
        ResourceKey<T> key = ResourceKey.create(registry.key(), name);
        if (registry.containsKey(key)) {
            return registry.getHolderOrThrow(key);
        }
        return register(name, value.get(), registry, false);
    }

    protected <T> Holder<T> get(ResourceKey<T> name, WritableRegistry<T> registry) {
        return registry.getHolderOrThrow(name);
    }

    protected <T> Holder<T> get(String name, WritableRegistry<T> registry) {
        ResourceKey<T> resourceKey = ResourceKey.create(registry.key(), new ResourceLocation(name));
        return registry.getHolderOrThrow(resourceKey);
    }

    protected <T> HolderSet<T> get(TagKey<T> tag, WritableRegistry<T> registry) {
        return registry.getOrCreateTag(tag);
    }

    private <T> Holder<T> register(ResourceLocation name, T value, WritableRegistry<T> registry, boolean override) {
        if (registry.containsKey(name)) {
            if (override) {
                int id = registry.getId(registry.get(name));
                return registry.registerOrOverride(OptionalInt.of(id), ResourceKey.create(registry.key(), name), value, Lifecycle.stable());
            } else {
                return registry.getHolderOrThrow(ResourceKey.create(registry.key(), name));
            }
        } else {
            return registry.register(ResourceKey.create(registry.key(), name), value, Lifecycle.stable());
        }
    }

    @Override
    public String getName() {
        return "Worldgen: " + modid;
    }

    // =================================================================================================================

    protected ResourceLocation add(ResourceLocation name, DimensionType value) {
        return add(name, value, dimensionTypes);
    }

    protected Holder<DimensionType> getDimensionType(ResourceLocation name) {
        return get(name, () -> new DimensionType(OptionalLong.empty(),
                true, false, false, true, 1.0, true, false,
                -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, 0.0f,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)), dimensionTypes);
    }

    protected Holder<DimensionType> getDimensionType(ResourceKey<DimensionType> name) {
        return get(name, dimensionTypes);
    }

    protected Holder<DimensionType> getDimensionType(String name) {
        return get(name, dimensionTypes);
    }

    protected ResourceLocation add(ResourceLocation name, WorldPreset value) {
        return add(name, value, worldPresets);
    }

    protected Holder<WorldPreset> getWorldPreset(ResourceLocation name) {
        return get(name, () -> new WorldPreset(Collections.emptyMap()), worldPresets);
    }

    protected Holder<WorldPreset> getWorldPreset(ResourceKey<WorldPreset> name) {
        return get(name, worldPresets);
    }

    protected Holder<WorldPreset> getWorldPreset(String name) {
        return get(name, worldPresets);
    }

    protected ResourceLocation add(ResourceLocation name, StructureSet value) {
        return add(name, value, structureSets);
    }

    protected Holder<StructureSet> getStructureSet(ResourceLocation name) {
        return get(name, () -> new StructureSet(Structures.STRONGHOLD,
                new ConcentricRingsStructurePlacement(32, 3, 128, getBiomes(BiomeTags.STRONGHOLD_BIASED_TO))), structureSets);
    }

    protected Holder<StructureSet> getStructureSet(ResourceKey<StructureSet> name) {
        return get(name, structureSets);
    }

    protected Holder<StructureSet> getStructureSet(String name) {
        return get(name, structureSets);
    }

    protected ResourceLocation add(ResourceLocation name, NormalNoise.NoiseParameters value) {
        return add(name, value, noiseParameters);
    }

    protected Holder<NormalNoise.NoiseParameters> getNoiseParameter(ResourceLocation name) {
        return get(name, () -> new NormalNoise.NoiseParameters(0, 1), noiseParameters);
    }

    protected Holder<NormalNoise.NoiseParameters> getNoiseParameter(ResourceKey<NormalNoise.NoiseParameters> name) {
        return get(name, noiseParameters);
    }

    protected Holder<NormalNoise.NoiseParameters> getNoiseParameter(String name) {
        return get(name, noiseParameters);
    }

    protected ResourceLocation add(ResourceLocation name, NoiseGeneratorSettings value) {
        return add(name, value, noiseGeneratorSettings);
    }

    protected Holder<NoiseGeneratorSettings> getNoiseGeneratorSetting(ResourceLocation name) {
        return get(name, () -> NoiseGeneratorSettings.overworld(false, false), noiseGeneratorSettings);
    }

    protected Holder<NoiseGeneratorSettings> getNoiseGeneratorSetting(ResourceKey<NoiseGeneratorSettings> name) {
        return get(name, noiseGeneratorSettings);
    }

    protected Holder<NoiseGeneratorSettings> getNoiseGeneratorSetting(String name) {
        return get(name, noiseGeneratorSettings);
    }

    protected Holder<Biome> getBiome(ResourceLocation name) {
        return get(name, () -> new Biome.BiomeBuilder()
                .downfall(1)
                .temperature(1)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .precipitation(Biome.Precipitation.NONE)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(0xFFFFFFFF)
                        .waterColor(0xFFFFFFFF)
                        .waterFogColor(0xFFFFFFFF)
                        .skyColor(0xFFFFFFFF)
                        .build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build(), biomes);
    }

    protected Holder<Biome> getBiome(ResourceKey<Biome> name) {
        return get(name, biomes);
    }

    protected Holder<Biome> getBiome(String name) {
        return get(name, biomes);
    }

    protected HolderSet<Biome> getBiomes(TagKey<Biome> name) {
        return get(name, biomes);
    }

    protected Holder<DensityFunction> getDensityFunction(ResourceLocation name) {
        return get(name, () -> densityZero().function, densityFunctions);
    }

    protected Holder<DensityFunction> getDensityFunction(ResourceKey<DensityFunction> name) {
        return get(name, densityFunctions);
    }

    protected Holder<DensityFunction> getDensityFunction(String name) {
        return get(name, densityFunctions);
    }

    protected ResourceLocation add(ResourceLocation name, LevelStem value) {
        return add(name, value, levelStems);
    }

    protected Holder<LevelStem> getLevelStem(ResourceLocation name) {
        return get(name, () -> new LevelStem(getDimensionType(BuiltinDimensionTypes.OVERWORLD),
                new FlatLevelSource(structureSets, FlatLevelGeneratorSettings.getDefault(biomes, structureSets))), levelStems);
    }

    protected Holder<LevelStem> getLevelStem(ResourceKey<LevelStem> name) {
        return get(name, levelStems);
    }

    protected Holder<LevelStem> getLevelStem(String name) {
        return get(name, levelStems);
    }

    // =================================================================================================================

    /**
     * @param hasSkyLight        该维度是否有天空光照
     * @param hasCeiling         该维度是否拥有一个基岩天花板。注意这仅仅是逻辑上是否拥有一个天花板。维度是否真的有一个天花板与此无关。
     * @param ultraWarm          维度是否表现得类似于原版的下界（水会蒸发，海绵会变干）。这也会使得熔岩流动更快、扩散更远。
     * @param natural            为 false 时，此维度中的指南针会随机转动，且无法用床睡觉或是重置重生点（即使 bed_works 为 true）。
     *                           为 true 时，此维中的下界传送门方块会生成僵尸猪灵。
     * @param coordinateScale    传送到该维度时的坐标缩放值。取值范围为 0.00001 到 30000000.0 的闭区间。下界为 8，主世界及末地为 1
     * @param bedWorks           玩家试图使用床时，其是否不会爆炸。
     * @param respawnAnchorWorks 玩家试图使用重生锚时，其是否不会爆炸。
     * @param minY               该维度中可以存在方块的最低高度。数值必须在 -2032 至 2031 之间且为 16 的整数倍（也就是说，-2032 是最小有效值，
     *                           2016 是最大有效值）。主世界为 -64，末地和下界为 0
     * @param height             该维度中可以存在方块的总高度。数值必须在 16 至 4064 之间且为 16 的整数倍。
     *                           维度中可以存在方块的最大高度值为 min_y 与 height 值之和减去 1，不能超过 2031。主世界为 384，末地和下界为 256
     * @param logicalHeight      玩家使用紫颂果或下界传送门可以到达的总高度。不会影响超过该限制高度的既有传送门。取值为 0 到 4064 的闭区间，
     *                           且不能大于 height。主世界为 384
     * @param infiniburn         该维度中火可以在哪些方块上永久燃烧。
     * @param effectsLocation    （可选，默认为minecraft:overworld）用于确定该维度的天空效果。
     *                           <br>设为overworld（主世界）会使维度的天空中出现云、太阳、星星和月亮；
     *                           <br>设为the_nether（下界）会使维度中有浓厚的迷雾阻挡视野，效果与下界类似；
     *                           <br>设为the_end（末地）会使维度拥有类似于末地的，黑暗的、斑驳的天空，并无视各生物群系自带的天空颜色与迷雾颜色。
     * @param ambientLight       该维度拥有多少环境光照（设置为 0 时，完全跟随光照变化；设置为 1 时，无环境光照；需要测试精确的效果）。主世界 0
     * @param monsterSettings    怪物生成设置
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BB%B4%E5%BA%A6/dimension_type">WIKI: 自定义维度/dimension type</a>
     */
    protected DimensionType dimensionType(boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm, boolean natural,
                                          double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks,
                                          int minY, int height, int logicalHeight, TagKey<Block> infiniburn,
                                          ResourceLocation effectsLocation, float ambientLight,
                                          DimensionType.MonsterSettings monsterSettings) {
        return new DimensionType(OptionalLong.empty(), hasSkyLight, hasCeiling, ultraWarm, natural, coordinateScale,
                bedWorks, respawnAnchorWorks, minY, height, logicalHeight, infiniburn, effectsLocation, ambientLight,
                monsterSettings);
    }

    /**
     * @param fixedTime          游戏内的昼夜时间将会固定在这个指定值上。
     * @param hasSkyLight        该维度是否有天空光照
     * @param hasCeiling         该维度是否拥有一个基岩天花板。注意这仅仅是逻辑上是否拥有一个天花板。维度是否真的有一个天花板与此无关。
     * @param ultraWarm          维度是否表现得类似于原版的下界（水会蒸发，海绵会变干）。这也会使得熔岩流动更快、扩散更远。
     * @param natural            为 false 时，此维度中的指南针会随机转动，且无法用床睡觉或是重置重生点（即使 bed_works 为 true）。
     *                           为 true 时，此维中的下界传送门方块会生成僵尸猪灵。
     * @param coordinateScale    传送到该维度时的坐标缩放值。取值范围为 0.00001 到 30000000.0 的闭区间。下界为 8，主世界及末地为 1
     * @param bedWorks           玩家试图使用床时，其是否不会爆炸。
     * @param respawnAnchorWorks 玩家试图使用重生锚时，其是否不会爆炸。
     * @param minY               该维度中可以存在方块的最低高度。数值必须在 -2032 至 2031 之间且为 16 的整数倍（也就是说，-2032 是最小有效值，
     *                           2016 是最大有效值）。主世界为 -64，末地和下界为 0
     * @param height             该维度中可以存在方块的总高度。数值必须在 16 至 4064 之间且为 16 的整数倍。
     *                           维度中可以存在方块的最大高度值为 min_y 与 height 值之和减去 1，不能超过 2031。主世界为 384，末地和下界为 256
     * @param logicalHeight      玩家使用紫颂果或下界传送门可以到达的总高度。不会影响超过该限制高度的既有传送门。取值为 0 到 4064 的闭区间，
     *                           且不能大于 height。主世界为 384
     * @param infiniburn         该维度中火可以在哪些方块上永久燃烧。
     * @param effectsLocation    （可选，默认为minecraft:overworld）用于确定该维度的天空效果。
     *                           <br>设为overworld（主世界）会使维度的天空中出现云、太阳、星星和月亮；
     *                           <br>设为the_nether（下界）会使维度中有浓厚的迷雾阻挡视野，效果与下界类似；
     *                           <br>设为the_end（末地）会使维度拥有类似于末地的，黑暗的、斑驳的天空，并无视各生物群系自带的天空颜色与迷雾颜色。
     * @param ambientLight       该维度拥有多少环境光照（设置为 0 时，完全跟随光照变化；设置为 1 时，无环境光照；需要测试精确的效果）。主世界 0
     * @param monsterSettings    怪物生成设置
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BB%B4%E5%BA%A6/dimension_type">WIKI: 自定义维度/dimension type</a>
     */
    protected DimensionType dimensionType(long fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm,
                                          boolean natural, double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks,
                                          int minY, int height, int logicalHeight, TagKey<Block> infiniburn,
                                          ResourceLocation effectsLocation, float ambientLight,
                                          DimensionType.MonsterSettings monsterSettings) {
        return new DimensionType(OptionalLong.of(fixedTime), hasSkyLight, hasCeiling, ultraWarm, natural, coordinateScale,
                bedWorks, respawnAnchorWorks, minY, height, logicalHeight, infiniburn, effectsLocation, ambientLight,
                monsterSettings);
    }

    /**
     * @param hasSkyLight                 该维度是否有天空光照
     * @param hasCeiling                  该维度是否拥有一个基岩天花板。注意这仅仅是逻辑上是否拥有一个天花板。维度是否真的有一个天花板与此无关。
     * @param ultraWarm                   维度是否表现得类似于原版的下界（水会蒸发，海绵会变干）。这也会使得熔岩流动更快、扩散更远。
     * @param natural                     为 false 时，此维度中的指南针会随机转动，且无法用床睡觉或是重置重生点（即使 bed_works 为 true）。
     *                                    为 true 时，此维中的下界传送门方块会生成僵尸猪灵。
     * @param coordinateScale             传送到该维度时的坐标缩放值。取值范围为 0.00001 到 30000000.0 的闭区间。下界为 8，主世界及末地为 1
     * @param bedWorks                    玩家试图使用床时，其是否不会爆炸。
     * @param respawnAnchorWorks          玩家试图使用重生锚时，其是否不会爆炸。
     * @param minY                        该维度中可以存在方块的最低高度。数值必须在 -2032 至 2031 之间且为 16 的整数倍（也就是说，-2032 是最小有效值，
     *                                    2016 是最大有效值）。主世界为 -64，末地和下界为 0
     * @param height                      该维度中可以存在方块的总高度。数值必须在 16 至 4064 之间且为 16 的整数倍。
     *                                    维度中可以存在方块的最大高度值为 min_y 与 height 值之和减去 1，不能超过 2031。主世界为 384，末地和下界为 256
     * @param logicalHeight               玩家使用紫颂果或下界传送门可以到达的总高度。不会影响超过该限制高度的既有传送门。取值为 0 到 4064 的闭区间，
     *                                    且不能大于 height。主世界为 384
     * @param infiniburn                  该维度中火可以在哪些方块上永久燃烧。
     * @param effectsLocation             （可选，默认为minecraft:overworld）用于确定该维度的天空效果。
     *                                    <br>设为overworld（主世界）会使维度的天空中出现云、太阳、星星和月亮；
     *                                    <br>设为the_nether（下界）会使维度中有浓厚的迷雾阻挡视野，效果与下界类似；
     *                                    <br>设为the_end（末地）会使维度拥有类似于末地的，黑暗的、斑驳的天空，并无视各生物群系自带的天空颜色与迷雾颜色。
     * @param ambientLight                该维度拥有多少环境光照（设置为 0 时，完全跟随光照变化；设置为 1 时，无环境光照；需要测试精确的效果）。主世界 0
     * @param piglinSafe                  猪灵和疣猪兽是否不会僵尸化。
     * @param hasRaids                    带有不祥之兆的玩家是否可以触发袭击。
     * @param monsterSpawnLightMin        取值为 0 到 15 的闭区间。怪物生成位置的最大光照区间中的最小值。
     * @param monsterSpawnLightMax        取值为 0 到 15 的闭区间。怪物生成位置的最大光照区间中的最大值。
     *                                    <br>该光照的计算公式是：雷雨时 max(skyLight - 10, blockLight)，
     *                                    其他天气时 max(internalSkyLight, blockLight)。
     * @param monsterSpawnBlockLightLimit 取值为 0 到 15 的闭区间。怪物生成位置的最大方块光照。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BB%B4%E5%BA%A6/dimension_type">WIKI: 自定义维度/dimension type</a>
     */
    protected DimensionType dimensionType(boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm, boolean natural,
                                          double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks,
                                          int minY, int height, int logicalHeight, TagKey<Block> infiniburn,
                                          ResourceLocation effectsLocation, float ambientLight, boolean piglinSafe,
                                          boolean hasRaids, int monsterSpawnLightMin, int monsterSpawnLightMax,
                                          int monsterSpawnBlockLightLimit) {
        IntProvider monsterSpawnLight = monsterSpawnLightMax == monsterSpawnLightMin
                ? ConstantInt.of(monsterSpawnLightMin)
                : UniformInt.of(monsterSpawnLightMin, monsterSpawnLightMax);
        return new DimensionType(OptionalLong.empty(), hasSkyLight, hasCeiling, ultraWarm, natural, coordinateScale,
                bedWorks, respawnAnchorWorks, minY, height, logicalHeight, infiniburn, effectsLocation, ambientLight,
                new DimensionType.MonsterSettings(piglinSafe, hasRaids, monsterSpawnLight, monsterSpawnBlockLightLimit));
    }

    /**
     * @param fixedTime                   游戏内的昼夜时间将会固定在这个指定值上。
     * @param hasSkyLight                 该维度是否有天空光照
     * @param hasCeiling                  该维度是否拥有一个基岩天花板。注意这仅仅是逻辑上是否拥有一个天花板。维度是否真的有一个天花板与此无关。
     * @param ultraWarm                   维度是否表现得类似于原版的下界（水会蒸发，海绵会变干）。这也会使得熔岩流动更快、扩散更远。
     * @param natural                     为 false 时，此维度中的指南针会随机转动，且无法用床睡觉或是重置重生点（即使 bed_works 为 true）。
     *                                    为 true 时，此维中的下界传送门方块会生成僵尸猪灵。
     * @param coordinateScale             传送到该维度时的坐标缩放值。取值范围为 0.00001 到 30000000.0 的闭区间。下界为 8，主世界及末地为 1
     * @param bedWorks                    玩家试图使用床时，其是否不会爆炸。
     * @param respawnAnchorWorks          玩家试图使用重生锚时，其是否不会爆炸。
     * @param minY                        该维度中可以存在方块的最低高度。数值必须在 -2032 至 2031 之间且为 16 的整数倍（也就是说，-2032 是最小有效值，
     *                                    2016 是最大有效值）。主世界为 -64，末地和下界为 0
     * @param height                      该维度中可以存在方块的总高度。数值必须在 16 至 4064 之间且为 16 的整数倍。
     *                                    维度中可以存在方块的最大高度值为 min_y 与 height 值之和减去 1，不能超过 2031。主世界为 384，末地和下界为 256
     * @param logicalHeight               玩家使用紫颂果或下界传送门可以到达的总高度。不会影响超过该限制高度的既有传送门。取值为 0 到 4064 的闭区间，
     *                                    且不能大于 height。主世界为 384
     * @param infiniburn                  该维度中火可以在哪些方块上永久燃烧。
     * @param effectsLocation             （可选，默认为minecraft:overworld）用于确定该维度的天空效果。
     *                                    <br>设为overworld（主世界）会使维度的天空中出现云、太阳、星星和月亮；
     *                                    <br>设为the_nether（下界）会使维度中有浓厚的迷雾阻挡视野，效果与下界类似；
     *                                    <br>设为the_end（末地）会使维度拥有类似于末地的，黑暗的、斑驳的天空，并无视各生物群系自带的天空颜色与迷雾颜色。
     * @param ambientLight                该维度拥有多少环境光照（设置为 0 时，完全跟随光照变化；设置为 1 时，无环境光照；需要测试精确的效果）。主世界 0
     * @param piglinSafe                  猪灵和疣猪兽是否不会僵尸化。
     * @param hasRaids                    带有不祥之兆的玩家是否可以触发袭击。
     * @param monsterSpawnLightMin        取值为 0 到 15 的闭区间。怪物生成位置的最大光照区间中的最小值。
     * @param monsterSpawnLightMax        取值为 0 到 15 的闭区间。怪物生成位置的最大光照区间中的最大值。
     *                                    <br>该光照的计算公式是：雷雨时 max(skyLight - 10, blockLight)，
     *                                    其他天气时 max(internalSkyLight, blockLight)。
     * @param monsterSpawnBlockLightLimit 取值为 0 到 15 的闭区间。怪物生成位置的最大方块光照。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BB%B4%E5%BA%A6/dimension_type">WIKI: 自定义维度/dimension type</a>
     */
    protected DimensionType dimensionType(long fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm,
                                          boolean natural, double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks,
                                          int minY, int height, int logicalHeight, TagKey<Block> infiniburn,
                                          ResourceLocation effectsLocation, float ambientLight, boolean piglinSafe,
                                          boolean hasRaids, int monsterSpawnLightMin, int monsterSpawnLightMax,
                                          int monsterSpawnBlockLightLimit) {
        IntProvider monsterSpawnLight = monsterSpawnLightMax == monsterSpawnLightMin
                ? ConstantInt.of(monsterSpawnLightMin)
                : UniformInt.of(monsterSpawnLightMin, monsterSpawnLightMax);
        return new DimensionType(OptionalLong.of(fixedTime), hasSkyLight, hasCeiling, ultraWarm, natural, coordinateScale,
                bedWorks, respawnAnchorWorks, minY, height, logicalHeight, infiniburn, effectsLocation, ambientLight,
                new DimensionType.MonsterSettings(piglinSafe, hasRaids, monsterSpawnLight, monsterSpawnBlockLightLimit));
    }

    /**
     * @param minY                  地形开始生成的最低高度。取值为 -2032 到 2031 的闭区间，且必须是 16 的整数倍。
     * @param height                地形生成的总高度。取值为 0 到 4064 的闭区间，且必须是 16 的整数倍。min_y + height 不能超过 2032。
     * @param noiseSizeHorizontal   取值为 0 到 4 的闭区间。主世界和下界为 1，末地为 2
     * @param noiseSizeVertical     取值为 0 到 4 的闭区间。主世界和下界为 2，末地为 1
     * @param defaultBlock          该维度地形的默认的方块。
     * @param defaultFluid          该维度默认的流体，用于生成海洋和湖。
     * @param defaultBlock          该维度地形的默认的方块。
     * @param defaultFluid          该维度默认的流体，用于生成海洋和湖。
     * @param noiseRouter           将密度函数应用于用于世界生成的噪声参数。
     * @param surfaceRule           为地形填充方块。
     * @param spawnTarget           用于决定玩家出生点的环境条件。会选取距离 (0,0) 不超过 2560 格的多个位置，
     *                              获取其噪声值（depth 噪声为 0，offset 为 0），
     *                              计算 ((x^2+z^2)^2) / 390625 + 噪声值距离列表中所有范围的最近的距离的平方，值最小的位置就是最佳位置。
     *                              玩家出生位置会在这个位置附近。
     * @param seaLevel              此维度的海平面高度。注意该值只影响世界生成。生物生成所使用的海平面高度目前为固定值63。
     * @param disableMobGeneration  是否禁止普通动物随地形一起生成。
     * @param aquifersEnabled       是否生成含水层和含熔岩层。
     * @param oreVeinsEnabled       是否生成矿脉。
     * @param useLegacyRandomSource 是否使用 1.18 之前的旧的随机数生成器来生成世界。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E4%B8%96%E7%95%8C%E7%94%9F%E6%88%90#%E5%99%AA%E5%A3%B0%E8%AE%BE%E7%BD%AE">WIKI：自定义世界生成#噪声设置</a>
     */
    protected NoiseGeneratorSettings noiseGeneratorSettings(int minY, int height, int noiseSizeHorizontal, int noiseSizeVertical,
                                                            BlockState defaultBlock, BlockState defaultFluid,
                                                            NoiseRouter noiseRouter, SurfaceRules.RuleSource surfaceRule,
                                                            List<Climate.ParameterPoint> spawnTarget, int seaLevel,
                                                            boolean disableMobGeneration, boolean aquifersEnabled,
                                                            boolean oreVeinsEnabled, boolean useLegacyRandomSource) {
        NoiseSettings noiseSettings = NoiseSettings.create(minY, height, noiseSizeHorizontal, noiseSizeVertical);
        return new NoiseGeneratorSettings(noiseSettings, defaultBlock, defaultBlock, noiseRouter, surfaceRule,
                spawnTarget, seaLevel, disableMobGeneration, aquifersEnabled, oreVeinsEnabled, useLegacyRandomSource);
    }

    protected FixedBiomeSource biomeSource(RegistryObject<Biome> biome) {
        return new FixedBiomeSource(getBiome(biome.getId()));
    }

    protected MultiNoiseBiomeSource biomeSource(ResourceLocation name, Function<Registry<Biome>, Climate.ParameterList<Holder<Biome>>> function) {
        return new MultiNoiseBiomeSource.Preset(name, function).biomeSource(biomes);
    }

    /**
     * 用于普通地形生成
     *
     * @param noiseGeneratorSettings 噪音设置
     * @param biomeSource            生物群系配置
     */
    protected NoiseBasedChunkGenerator chunkGenerator(ResourceLocation noiseGeneratorSettings, BiomeSource biomeSource) {
        return new NoiseBasedChunkGenerator(structureSets, noiseParameters, biomeSource, getNoiseGeneratorSetting(noiseGeneratorSettings));
    }

    /**
     * 用于超平坦地形生成
     */
    protected FlatLevelSource chunkGenerator(@Nullable HolderSet<StructureSet> structures) {
        return new FlatLevelSource(structureSets, new FlatLevelGeneratorSettings(Optional.ofNullable(structures), biomes));
    }

    /**
     * @param barrierNoise                    影响是否使用方块分隔含水层和洞穴其他区域。函数值越大越有可能分隔。
     * @param fluidLevelFloodednessNoise      影响含水层生成液体的的概率。函数值越大越有可能生成。
     *                                        该噪声值大于 1.0 的被视为 1.0，小于 -1.0 的被视为 -1.0。
     * @param fluidLevelSpreadNoise           影响某处含水层液体表面的高度。函数值越小液体表面越可能较低。
     * @param lavaNoise                       影响某处含水层是否使用熔岩代替水。0.3为其阈值。
     * @param temperature                     生物群系的温度噪声。此字段及以下五个字段为用于生物群系放置的噪声。
     * @param vegetation                      即 humidity，生物群系的湿度噪声。
     * @param continents                      生物群系的大陆性噪声。
     * @param erosion                         生物群系的侵蚀噪声。
     * @param depth                           生物群系的深度噪声。
     * @param ridges                          即 weirdness，生物群系的奇异噪声。
     * @param initialDensityWithoutJaggedness 与地表高度有关。在一 XZ 坐标下，
     *                                        从世界顶部开始以 size_vertical*4 个方块的精度从上到下查找，
     *                                        初次遇到大于 25/64 的值的高度作为世界生成的初始地表高度。
     * @param finalDensity                    决定了一个坐标是空气（可以生成含水层）还是世界的默认方块 default_block（将会被 surface_rule 填充）。
     * @param veinToggle                      控制矿脉生成。
     * @param veinRidged                      控制矿脉生成。
     * @param veinGap                         控制矿脉生成。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E5%AF%86%E5%BA%A6%E5%87%BD%E6%95%B0">WIKI：密度函数</a>
     */
    protected NoiseRouter routers(DensityFunctionBuilder barrierNoise,
                                  DensityFunctionBuilder fluidLevelFloodednessNoise,
                                  DensityFunctionBuilder fluidLevelSpreadNoise,
                                  DensityFunctionBuilder lavaNoise,
                                  DensityFunctionBuilder temperature,
                                  DensityFunctionBuilder vegetation,
                                  DensityFunctionBuilder continents,
                                  DensityFunctionBuilder erosion,
                                  DensityFunctionBuilder depth,
                                  DensityFunctionBuilder ridges,
                                  DensityFunctionBuilder initialDensityWithoutJaggedness,
                                  DensityFunctionBuilder finalDensity,
                                  DensityFunctionBuilder veinToggle,
                                  DensityFunctionBuilder veinRidged,
                                  DensityFunctionBuilder veinGap) {
        return new NoiseRouter(barrierNoise.function,
                fluidLevelFloodednessNoise.function,
                fluidLevelSpreadNoise.function,
                lavaNoise.function,
                temperature.function,
                vegetation.function,
                continents.function,
                erosion.function,
                depth.function,
                ridges.function,
                initialDensityWithoutJaggedness.function,
                finalDensity.function,
                veinToggle.function,
                veinRidged.function,
                veinGap.function);
    }

    /**
     * @param barrierNoise                    影响是否使用方块分隔含水层和洞穴其他区域。函数值越大越有可能分隔。
     * @param fluidLevelFloodednessNoise      影响含水层生成液体的的概率。函数值越大越有可能生成。
     *                                        该噪声值大于 1.0 的被视为 1.0，小于 -1.0 的被视为 -1.0。
     * @param fluidLevelSpreadNoise           影响某处含水层液体表面的高度。函数值越小液体表面越可能较低。
     * @param lavaNoise                       影响某处含水层是否使用熔岩代替水。0.3为其阈值。
     * @param temperature                     生物群系的温度噪声。此字段及以下五个字段为用于生物群系放置的噪声。
     * @param vegetation                      即 humidity，生物群系的湿度噪声。
     * @param continents                      生物群系的大陆性噪声。
     * @param erosion                         生物群系的侵蚀噪声。
     * @param depth                           生物群系的深度噪声。
     * @param ridges                          即 weirdness，生物群系的奇异噪声。
     * @param initialDensityWithoutJaggedness 与地表高度有关。在一 XZ 坐标下，
     *                                        从世界顶部开始以 size_vertical*4 个方块的精度从上到下查找，
     *                                        初次遇到大于 25/64 的值的高度作为世界生成的初始地表高度。
     * @param finalDensity                    决定了一个坐标是空气（可以生成含水层）还是世界的默认方块 default_block（将会被 surface_rule 填充）。
     * @param veinToggle                      控制矿脉生成。
     * @param veinRidged                      控制矿脉生成。
     * @param veinGap                         控制矿脉生成。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E5%AF%86%E5%BA%A6%E5%87%BD%E6%95%B0">WIKI：密度函数</a>
     */
    protected NoiseRouter routers(DensityFunction barrierNoise,
                                  DensityFunction fluidLevelFloodednessNoise,
                                  DensityFunction fluidLevelSpreadNoise,
                                  DensityFunction lavaNoise,
                                  DensityFunction temperature,
                                  DensityFunction vegetation,
                                  DensityFunction continents,
                                  DensityFunction erosion,
                                  DensityFunction depth,
                                  DensityFunction ridges,
                                  DensityFunction initialDensityWithoutJaggedness,
                                  DensityFunction finalDensity,
                                  DensityFunction veinToggle,
                                  DensityFunction veinRidged,
                                  DensityFunction veinGap) {
        return new NoiseRouter(barrierNoise, fluidLevelFloodednessNoise, fluidLevelSpreadNoise, lavaNoise, temperature,
                vegetation, continents, erosion, depth, ridges, initialDensityWithoutJaggedness,
                finalDensity, veinToggle, veinRidged, veinGap);
    }

    // =================================================================================================================

    /**
     * 对噪声进行一次采样。
     *
     * @param noise 一个噪声ID
     */
    protected DensityFunctionBuilder densityNoise(ResourceKey<NormalNoise.NoiseParameters> noise) {
        return new DensityFunctionBuilder(DensityFunctions.noise(getNoiseParameter(noise)));
    }

    /**
     * 对噪声进行一次采样。
     *
     * @param noise 一个噪声ID
     * @param y     Y坐标的缩放比例。
     */
    protected DensityFunctionBuilder densityNoise(ResourceKey<NormalNoise.NoiseParameters> noise, double y) {
        return new DensityFunctionBuilder(DensityFunctions.noise(getNoiseParameter(noise), y));
    }

    /**
     * 对噪声进行一次采样。
     *
     * @param noise 一个噪声ID
     * @param xz    X坐标和Z坐标的缩放比例。
     * @param y     Y坐标的缩放比例。
     */
    protected DensityFunctionBuilder densityNoise(ResourceKey<NormalNoise.NoiseParameters> noise, double xz, double y) {
        return new DensityFunctionBuilder(DensityFunctions.noise(getNoiseParameter(noise), xz, y));
    }

    /**
     * 使用末地岛屿的噪声算法对当前水平坐标进行采样。
     *
     * @param seed 其最小值为 -0.84375，最大值为 0.5625。
     */
    protected DensityFunctionBuilder densityEndIslands(long seed) {
        return new DensityFunctionBuilder(DensityFunctions.endIslands(seed));
    }

    /**
     * 目前在原版用于旧版区块与新版区块间的过渡。总是返回 0.0
     */
    protected DensityFunctionBuilder densityBlendOffset() {
        return new DensityFunctionBuilder(DensityFunctions.blendOffset());
    }

    /**
     * 目前在原版用于旧版区块与新版区块间的过渡。总是返回 1.0
     */
    protected DensityFunctionBuilder densityBlendAlpha() {
        return new DensityFunctionBuilder(DensityFunctions.blendAlpha());
    }

    /**
     * 在 (x/4, 0, z/4) 处进行噪声采样后乘上 4。
     */
    protected DensityFunctionBuilder densityShiftA(ResourceKey<NormalNoise.NoiseParameters> noise) {
        return new DensityFunctionBuilder(DensityFunctions.shiftA(getNoiseParameter(noise)));
    }

    /**
     * 在 (z/4, x/4, 0) 处进行噪声采样后乘上 4。
     */
    protected DensityFunctionBuilder densityShiftB(ResourceKey<NormalNoise.NoiseParameters> noise) {
        return new DensityFunctionBuilder(DensityFunctions.shiftB(getNoiseParameter(noise)));
    }

    /**
     * 在 (x/4, y/4, z/4) 处进行噪声采样后乘上 4。
     */
    protected DensityFunctionBuilder densityShift(ResourceKey<NormalNoise.NoiseParameters> noise) {
        return new DensityFunctionBuilder(DensityFunctions.shift(getNoiseParameter(noise)));
    }

    /**
     * 计算一个三次样条。
     */
    protected DensityFunctionBuilder densitySpline(CubicSpline<DensityFunctions.Spline.Point, DensityFunctions.Spline.Coordinate> spline) {
        return new DensityFunctionBuilder(DensityFunctions.spline(spline));
    }

    /**
     * 返回一个常数。
     */
    protected DensityFunctionBuilder density(double value) {
        return new DensityFunctionBuilder(DensityFunctions.constant(value));
    }

    protected DensityFunctionBuilder density(String function) {
        return new DensityFunctionBuilder(getDensityFunction(function).get());
    }

    protected DensityFunctionBuilder density(ResourceKey<DensityFunction> function) {
        return new DensityFunctionBuilder(getDensityFunction(function).get());
    }

    protected DensityFunctionBuilder density(ResourceLocation function) {
        return new DensityFunctionBuilder(getDensityFunction(function).get());
    }

    protected DensityFunctionBuilder density(DensityFunction function) {
        return new DensityFunctionBuilder(function);
    }

    /**
     * 将 Y 坐标钳制后线性映射到一个区间上返回。
     */
    protected DensityFunctionBuilder densityYClampedGradient(int fromY, int toY, double fromValue, double toValue) {
        return new DensityFunctionBuilder(DensityFunctions.yClampedGradient(fromY, toY, fromValue, toValue));
    }

    protected DensityFunctionBuilder densityMappedNoise(ResourceKey<NormalNoise.NoiseParameters> noise,
                                                        double xzScale, double yScale, double fromY, double toY) {
        return new DensityFunctionBuilder(DensityFunctions.mappedNoise(getNoiseParameter(noise), xzScale, yScale, fromY, toY));
    }

    protected DensityFunctionBuilder densityMappedNoise(ResourceKey<NormalNoise.NoiseParameters> noise,
                                                        double yScale, double fromY, double toY) {
        return new DensityFunctionBuilder(DensityFunctions.mappedNoise(getNoiseParameter(noise), yScale, fromY, toY));
    }

    protected DensityFunctionBuilder densityMappedNoise(ResourceKey<NormalNoise.NoiseParameters> noise,
                                                        double fromY, double toY) {
        return new DensityFunctionBuilder(DensityFunctions.mappedNoise(getNoiseParameter(noise), fromY, toY));
    }

    protected DensityFunctionBuilder densityZero() {
        return new DensityFunctionBuilder(DensityFunctions.zero());
    }

    protected SurfaceRuleBuilder rule() {
        return new SurfaceRuleBuilder();
    }

    protected RuleSourceSequenceBuilder rules() {
        return new RuleSourceSequenceBuilder();
    }

    /**
     * 用于寻找出生点的东西
     *
     * @param temperature     温度。不要与生物群系的温度混淆。最大/最小值为 -2.0 到 2.0 的闭区间。
     * @param humidity        湿度。格式与 temperature 相同。
     * @param continentalness 大陆性。在原版主世界，低大陆性对应于海洋地形，高大陆性对应于内陆地形。格式与 temperature 相同
     * @param erosion         侵蚀度。在原版主世界，高侵蚀度对应平坦地形。格式与 temperature 相同。
     * @param depth           深度。在原版主世界，只有此参数会随垂直位置改变：在XZ坐标不变的情况下，Y轴坐标越高，该值越高。格式与 temperature 相同。
     * @param weirdness       怪异度。在原版主世界，用于生物群系崎岖程度。格式与 temperature 相同。
     * @param offset          偏移。取值为 0.0 到 1.0 的闭区间。类似于其他参数，但是 offset 在任何地方都是 0，因此若其他参数都相等，将这个参数设为靠近 0 的值会使其占更多优势。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E4%B8%96%E7%95%8C%E7%94%9F%E6%88%90/parameter_point">WIKI：自定义世界生成/parameter point</a>
     */
    protected Climate.ParameterPoint spawnParam(Climate.Parameter temperature, Climate.Parameter humidity,
                                                Climate.Parameter continentalness, Climate.Parameter erosion,
                                                Climate.Parameter depth, Climate.Parameter weirdness, long offset) {
        return new Climate.ParameterPoint(temperature, humidity, continentalness, erosion, depth, weirdness, offset);
    }

    /**
     * 用于寻找出生点的东西
     *
     * @param temperatureMin     温度。不要与生物群系的温度混淆。最小值。取值为 -2.0 到 2.0 的闭区间。
     * @param temperatureMax     温度。不要与生物群系的温度混淆。最大值。取值为 -2.0 到 2.0 的闭区间。必须大于等于 min。
     * @param humidityMin        湿度。格式与 temperature 相同。
     * @param humidityMax        湿度。格式与 temperature 相同。
     * @param continentalnessMin 大陆性。在原版主世界，低大陆性对应于海洋地形，高大陆性对应于内陆地形。格式与 temperature 相同
     * @param continentalnessMax 大陆性。在原版主世界，低大陆性对应于海洋地形，高大陆性对应于内陆地形。格式与 temperature 相同
     * @param erosionMin         侵蚀度。在原版主世界，高侵蚀度对应平坦地形。格式与 temperature 相同。
     * @param erosionMax         侵蚀度。在原版主世界，高侵蚀度对应平坦地形。格式与 temperature 相同。
     * @param depthMin           深度。在原版主世界，只有此参数会随垂直位置改变：在XZ坐标不变的情况下，Y轴坐标越高，该值越高。格式与 temperature 相同。
     * @param depthMax           深度。在原版主世界，只有此参数会随垂直位置改变：在XZ坐标不变的情况下，Y轴坐标越高，该值越高。格式与 temperature 相同。
     * @param weirdnessMin       怪异度。在原版主世界，用于生物群系崎岖程度。格式与 temperature 相同。
     * @param weirdnessMax       怪异度。在原版主世界，用于生物群系崎岖程度。格式与 temperature 相同。
     * @param offset             偏移。取值为 0.0 到 1.0 的闭区间。类似于其他参数，但是 offset 在任何地方都是 0，因此若其他参数都相等，将这个参数设为靠近 0 的值会使其占更多优势。
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E4%B8%96%E7%95%8C%E7%94%9F%E6%88%90/parameter_point">WIKI：自定义世界生成/parameter point</a>
     */
    protected Climate.ParameterPoint spawnParam(float temperatureMin, float temperatureMax,
                                                float humidityMin, float humidityMax,
                                                float continentalnessMin, float continentalnessMax,
                                                float erosionMin, float erosionMax,
                                                float depthMin, float depthMax,
                                                float weirdnessMin, float weirdnessMax,
                                                long offset) {
        return new Climate.ParameterPoint(Climate.Parameter.span(temperatureMin, temperatureMax),
                Climate.Parameter.span(humidityMin, humidityMax),
                Climate.Parameter.span(continentalnessMin, continentalnessMax),
                Climate.Parameter.span(erosionMin, erosionMax),
                Climate.Parameter.span(depthMin, depthMax),
                Climate.Parameter.span(weirdnessMin, weirdnessMax), offset);
    }

    protected ParameterPointBuilder spawnParam() {
        return new ParameterPointBuilder();
    }

    // =================================================================================================================

    public class DensityFunctionBuilder {

        public DensityFunction function;

        public DensityFunctionBuilder(DensityFunction function) {
            this.function = function;
        }

        public DensityFunctionBuilder map(Function<DensityFunction, DensityFunction> f) {
            return new DensityFunctionBuilder(f.apply(function));
        }

        public DensityFunctionBuilder apply(BiFunction<DensityFunction, DensityFunction, DensityFunction> f, DensityFunction another) {
            return new DensityFunctionBuilder(f.apply(function, another));
        }

        public DensityFunctionBuilder apply(BiFunction<DensityFunction, DensityFunction, DensityFunction> f, DensityFunctionBuilder another) {
            return new DensityFunctionBuilder(f.apply(function, another.function));
        }

        /**
         * 根据周围元胞（Cell）的密度函数值，对元胞里的每个方块进行插值。每个元胞的大小为 size_horizontal * 4 和 size_vertical * 4。
         */
        public DensityFunctionBuilder interpolated() {
            return map(DensityFunctions::interpolated);
        }

        /**
         * 以 4*4 的列为单位计算函数值，列中每个方块的值一样。且在每一列中只计算一次函数值，在 Y=0 处。
         */
        public DensityFunctionBuilder flatCache() {
            return map(DensityFunctions::flatCache);
        }

        /**
         * 在每一个水平坐标下只计算一次函数值。
         */
        public DensityFunctionBuilder cache2d() {
            return map(DensityFunctions::cache2d);
        }

        /**
         * 如果这个密度函数被引用两次，那么只会在每个方块处计算一次。
         */
        public DensityFunctionBuilder cacheOnce() {
            return map(DensityFunctions::cacheOnce);
        }

        /**
         * 将两个密度函数值相加。
         */
        public DensityFunctionBuilder add(DensityFunctionBuilder another) {
            return apply(DensityFunctions::add, another);
        }

        public DensityFunctionBuilder add(String another) {
            return add(density(another));
        }

        public DensityFunctionBuilder add(double another) {
            return add(density(another));
        }

        /**
         * 将两个密度函数值相乘。
         */
        public DensityFunctionBuilder mul(DensityFunctionBuilder another) {
            return apply(DensityFunctions::mul, another);
        }

        public DensityFunctionBuilder mul(String another) {
            return mul(density(another));
        }

        public DensityFunctionBuilder mul(double another) {
            return mul(density(another));
        }

        /**
         * 取两个密度函数值的最小值。
         */
        public DensityFunctionBuilder min(DensityFunctionBuilder another) {
            return apply(DensityFunctions::min, another);
        }

        /**
         * 取两个密度函数值的最大值。
         */
        public DensityFunctionBuilder max(DensityFunctionBuilder another) {
            return apply(DensityFunctions::max, another);
        }

        /**
         * 目前在原版用于旧版区块与新版区块间的过渡。返回 Blender#blendDensity
         */
        public DensityFunctionBuilder blendDensity() {
            return map(DensityFunctions::blendDensity);
        }

        /**
         * 根据本密度函数，对指定噪声的特定区域进行缩放、减弱增强，并取绝对值后返回。
         *
         * @param noise 一个噪声ID
         * @deprecated 无法访问 DensityFunctions.WeirdScaledSampler.RarityValueMapper
         */
        @Deprecated
        public DensityFunctionBuilder weirdScaledSampler1(ResourceKey<NormalNoise.NoiseParameters> noise) {
            // DensityFunctions.weirdScaledSampler(function, getNoiseParameter(noise), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE1);
            return new DensityFunctionBuilder(function);
        }

        /**
         * 根据本密度函数，对指定噪声的特定区域进行缩放、减弱增强，并取绝对值后返回。
         *
         * @param noise 一个噪声ID
         * @deprecated 无法访问 DensityFunctions.WeirdScaledSampler.RarityValueMapper
         */
        @Deprecated
        public DensityFunctionBuilder weirdScaledSampler2(ResourceKey<NormalNoise.NoiseParameters> noise) {
            // DensityFunctions.weirdScaledSampler(function, getNoiseParameter(noise), DensityFunctions.WeirdScaledSampler.RarityValueMapper.TYPE2);
            return new DensityFunctionBuilder(function);
        }


        /**
         * 类似noise，但是先缩放并偏移输入的坐标。
         */
        public DensityFunctionBuilder shiftedNoise2D(ResourceKey<NormalNoise.NoiseParameters> noise, DensityFunctionBuilder shiftZ, double xzScale) {
            return map(f -> DensityFunctions.shiftedNoise2d(f, shiftZ.function, xzScale, getNoiseParameter(noise)));
        }

        public DensityFunctionBuilder shiftedNoise2D(ResourceKey<NormalNoise.NoiseParameters> noise, String shiftZ, double xzScale) {
            return shiftedNoise2D(noise, density(shiftZ), xzScale);
        }

        /**
         * 计算输入的密度函数值，然后基于结果返回两个密度函数中的一个。
         */
        public DensityFunctionBuilder rangeChoice(double minInclusive, double maxExclusive,
                                                  DensityFunctionBuilder whenInRange, DensityFunctionBuilder whenOutOfRange) {
            return map(f -> DensityFunctions.rangeChoice(f, minInclusive, maxExclusive, whenInRange.function, whenOutOfRange.function));
        }

        public DensityFunctionBuilder rangeChoiceIn(double minInclusive, double maxExclusive, DensityFunctionBuilder whenOutOfRange) {
            return rangeChoice(minInclusive, maxExclusive, this, whenOutOfRange);
        }

        public DensityFunctionBuilder rangeChoiceIn(double minInclusive, double maxExclusive, double whenOutOfRange) {
            return rangeChoice(minInclusive, maxExclusive, this, density(whenOutOfRange));
        }

        public DensityFunctionBuilder rangeChoiceIn(double minInclusive, double maxExclusive, String whenOutOfRange) {
            return rangeChoice(minInclusive, maxExclusive, this, density(whenOutOfRange));
        }

        public DensityFunctionBuilder rangeChoiceOut(double minInclusive, double maxExclusive, DensityFunctionBuilder whenInRange) {
            return rangeChoice(minInclusive, maxExclusive, whenInRange, this);
        }

        public DensityFunctionBuilder rangeChoiceOut(double minInclusive, double maxExclusive, double whenInRange) {
            return rangeChoice(minInclusive, maxExclusive, density(whenInRange), this);
        }

        public DensityFunctionBuilder rangeChoiceOut(double minInclusive, double maxExclusive, String whenInRange) {
            return rangeChoice(minInclusive, maxExclusive, density(whenInRange), this);
        }

        /**
         * 返回密度函数值的绝对值。
         */
        public DensityFunctionBuilder abs() {
            return map(DensityFunction::abs);
        }

        /**
         * 返回密度函数值的平方。
         */
        public DensityFunctionBuilder square() {
            return map(DensityFunction::square);
        }

        /**
         * 返回密度函数值的立方。
         */
        public DensityFunctionBuilder cube() {
            return map(DensityFunction::cube);
        }

        /**
         * 如果密度函数的值大于零，则返回该值本身；否则返回该值的一半。
         */
        public DensityFunctionBuilder halfNegative() {
            return map(DensityFunction::halfNegative);
        }

        /**
         * 如果密度函数值大于零，则返回该值本身；否则返回该值的 1/4。
         */
        public DensityFunctionBuilder quarterNegative() {
            return map(DensityFunction::quarterNegative);
        }

        /**
         * 首先把输入值钳制在 -1 到 1 的区间内（小于 -1 的视为 -1，大于 1 的视为 1），然后将它代入公式 x/2 - x*x*x/24 并返回。
         */
        public DensityFunctionBuilder squeeze() {
            return map(DensityFunction::squeeze);
        }

        public DensityFunctionBuilder clamp(double min, double max) {
            return new DensityFunctionBuilder(function.clamp(min, max));
        }

        public DensityFunctionBuilder lerp(DensityFunctionBuilder min, DensityFunctionBuilder max) {
            return map(f -> DensityFunctions.lerp(min.function, f, max.function));
        }

        /**
         * 返回 (this[base] - keep) * scale + keep
         */
        public DensityFunctionBuilder lerp(DensityFunctionBuilder scale, double keep) {
            return new DensityFunctionBuilder(DensityFunctions.lerp(scale.function, keep, function));
        }
    }

    public static class SurfaceRuleBuilder {

        private final List<SurfaceRules.ConditionSource> conditions = new ArrayList<>();

        public SurfaceRuleBuilder and(SurfaceRules.ConditionSource condition) {
            conditions.add(condition);
            return this;
        }

        public SurfaceRuleBuilder andNot(SurfaceRules.ConditionSource condition) {
            conditions.add(SurfaceRules.not(condition));
            return this;
        }

        public SurfaceRules.RuleSource build(SurfaceRules.RuleSource result) {
            if (conditions.isEmpty()) return result;
            if (conditions.size() == 1) return SurfaceRules.ifTrue(conditions.get(0), result);
            Iterator<SurfaceRules.ConditionSource> iterator = conditions.iterator();
            SurfaceRules.RuleSource r = SurfaceRules.ifTrue(iterator.next(), result);
            while (iterator.hasNext()) {
                r = SurfaceRules.ifTrue(iterator.next(), r);
            }
            return r;
        }
    }

    public static class RuleSourceSequenceBuilder {

        private final List<SurfaceRules.RuleSource> rules = new ArrayList<>();

        public RuleSourceSequenceBuilder add(SurfaceRules.RuleSource rule) {
            rules.add(rule);
            return this;
        }

        public RuleSourceSequenceBuilder ifTure(SurfaceRules.ConditionSource ifTrue, SurfaceRules.RuleSource thenRun) {
            return add(ifTrue(ifTrue, thenRun));
        }

        public RuleSourceSequenceBuilder ifTure(SurfaceRules.ConditionSource ifTrue, RuleSourceSequenceBuilder thenRun) {
            return add(ifTrue(ifTrue, thenRun.build()));
        }

        public RuleSourceSequenceBuilder ifBottomY(String randomName, int min, int max, SurfaceRules.RuleSource thenRun) {
            return ifTure(verticalGradient(randomName, VerticalAnchor.aboveBottom(min), VerticalAnchor.aboveBottom(max)), thenRun);
        }

        public RuleSourceSequenceBuilder ifAbovePreliminarySurface(SurfaceRules.RuleSource thenRun) {
            return ifTure(SurfaceRules.abovePreliminarySurface(), thenRun);
        }

        public SurfaceRules.RuleSource build() {
            return SurfaceRules.sequence(rules.toArray(SurfaceRules.RuleSource[]::new));
        }
    }


    /**
     * @see <a href="https://minecraft.fandom.com/zh/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E4%B8%96%E7%95%8C%E7%94%9F%E6%88%90/parameter_point">WIKI：自定义世界生成/parameter point</a>
     */
    public static class ParameterPointBuilder {

        final Climate.Parameter[] values = new Climate.Parameter[]{
                Climate.Parameter.span(-1f, 1f),
                Climate.Parameter.span(-1f, 1f),
                Climate.Parameter.span(-0.11f, 1f),
                Climate.Parameter.span(-1f, 1f),
                Climate.Parameter.point(0),
                Climate.Parameter.span(-1.0f, -0.16f),
        };

        /**
         * 温度。不要与生物群系的温度混淆。最大/最小值为 -2.0 到 2.0 的闭区间。
         */
        public ParameterPointBuilder temperature(float value) {
            return point(0, value);
        }

        /**
         * 温度。不要与生物群系的温度混淆。最大/最小值为 -2.0 到 2.0 的闭区间。
         */
        public ParameterPointBuilder temperature(float min, float max) {
            return span(0, min, max);
        }

        /**
         * 湿度。格式与 temperature 相同。
         */
        public ParameterPointBuilder humidity(float value) {
            return point(1, value);
        }

        /**
         * 湿度。格式与 temperature 相同。
         */
        public ParameterPointBuilder humidity(float min, float max) {
            return span(1, min, max);
        }

        /**
         * 大陆性。在原版主世界，低大陆性对应于海洋地形，高大陆性对应于内陆地形。格式与 temperature 相同
         */
        public ParameterPointBuilder continentalness(float value) {
            return point(2, value);
        }

        /**
         * 大陆性。在原版主世界，低大陆性对应于海洋地形，高大陆性对应于内陆地形。格式与 temperature 相同
         */
        public ParameterPointBuilder continentalness(float min, float max) {
            return span(2, min, max);
        }

        /**
         * 侵蚀度。在原版主世界，高侵蚀度对应平坦地形。格式与 temperature 相同。
         */
        public ParameterPointBuilder erosion(float value) {
            return point(3, value);
        }

        /**
         * 侵蚀度。在原版主世界，高侵蚀度对应平坦地形。格式与 temperature 相同。
         */
        public ParameterPointBuilder erosion(float min, float max) {
            return span(3, min, max);
        }

        /**
         * 深度。在原版主世界，只有此参数会随垂直位置改变：在XZ坐标不变的情况下，Y轴坐标越高，该值越高。格式与 temperature 相同。
         */
        public ParameterPointBuilder depth(float value) {
            return point(4, value);
        }

        /**
         * 深度。在原版主世界，只有此参数会随垂直位置改变：在XZ坐标不变的情况下，Y轴坐标越高，该值越高。格式与 temperature 相同。
         */
        public ParameterPointBuilder depth(float min, float max) {
            return span(4, min, max);
        }

        /**
         * 怪异度。在原版主世界，用于生物群系崎岖程度。格式与 temperature 相同。
         */
        public ParameterPointBuilder weirdness(float value) {
            return point(5, value);
        }

        /**
         * 怪异度。在原版主世界，用于生物群系崎岖程度。格式与 temperature 相同。
         */
        public ParameterPointBuilder weirdness(float min, float max) {
            return span(5, min, max);
        }

        ParameterPointBuilder point(int i, float value) {
            values[i] = Climate.Parameter.point(value);
            return this;
        }

        ParameterPointBuilder span(int i, float min, float max) {
            values[i] = Climate.Parameter.span(min, max);
            return this;
        }

        /**
         * @param offset 偏移。取值为 0.0 到 1.0 的闭区间。类似于其他参数，但是 offset 在任何地方都是 0，因此若其他参数都相等，将这个参数设为靠近 0 的值会使其占更多优势。
         */
        public Climate.ParameterPoint build(long offset) {
            return new Climate.ParameterPoint(values[0], values[1], values[2], values[3], values[4], values[5], offset);
        }

        public Climate.ParameterPoint build() {
            return new Climate.ParameterPoint(values[0], values[1], values[2], values[3], values[4], values[5], 0);
        }
    }
}
