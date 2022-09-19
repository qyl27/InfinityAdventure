package cx.rain.infadv.world.gen;

import com.google.common.base.Suppliers;
import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, InfAdv.MODID);

    private static Supplier<List<OreConfiguration.TargetBlockState>> stoneReplacement(Supplier<? extends Block> block) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, block.get().defaultBlockState())));
    }

    private static Supplier<List<OreConfiguration.TargetBlockState>> deepslateReplacement(Supplier<? extends Block> block) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, block.get().defaultBlockState())));
    }

    private static Supplier<List<OreConfiguration.TargetBlockState>> overworldReplacement(Supplier<? extends Block> block, Supplier<? extends Block> deepslate) {
        return Suppliers.memoize(() ->
                List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, block.get().defaultBlockState()),
                        OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, deepslate.get().defaultBlockState())));
    }

    public static void register(IEventBus bus) {
        InfAdv.getInstance().getLogger().info("Register InfAdv Configured Features.");
        CONFIGURED_FEATURES.register(bus);
    }

    private static final Supplier<List<OreConfiguration.TargetBlockState>> SILVER_REPLACEMENT = stoneReplacement(ModBlocks.SILVER_ORE);
    private static final Supplier<List<OreConfiguration.TargetBlockState>> MITHRIL_REPLACEMENT = overworldReplacement(ModBlocks.MITHRIL_ORE, ModBlocks.DEEPSLATE_MITHRIL_ORE);
    private static final Supplier<List<OreConfiguration.TargetBlockState>> ADAMANTINE_REPLACEMENT = deepslateReplacement(ModBlocks.DEEPSLATE_ADAMANTINE_ORE);
    private static final Supplier<List<OreConfiguration.TargetBlockState>> RUBY_REPLACEMENT = stoneReplacement(ModBlocks.RUBY_ORE);
    private static final Supplier<List<OreConfiguration.TargetBlockState>> AQUAMARINE_REPLACEMENT = stoneReplacement(ModBlocks.AQUAMARINE_ORE);

    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE = CONFIGURED_FEATURES.register("silver_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SILVER_REPLACEMENT.get(), 8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MITHRIL_ORE = CONFIGURED_FEATURES.register("mithril_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MITHRIL_REPLACEMENT.get(), 6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ADAMANTINE_ORE = CONFIGURED_FEATURES.register("adamantine_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ADAMANTINE_REPLACEMENT.get(), 5)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_ORE = CONFIGURED_FEATURES.register("ruby_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(RUBY_REPLACEMENT.get(), 5)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> AQUAMARINE_ORE_MOUNTAIN = CONFIGURED_FEATURES.register("aquamarine_ore_mountain", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(AQUAMARINE_REPLACEMENT.get(), 4)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> AQUAMARINE_ORE_OCEAN = CONFIGURED_FEATURES.register("aquamarine_ore_ocean", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(AQUAMARINE_REPLACEMENT.get(), 3)));
}
