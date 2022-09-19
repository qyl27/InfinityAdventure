package cx.rain.infadv.world.gen;

import cx.rain.infadv.InfAdv;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACEMENT_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, InfAdv.MODID);

    private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    public static void register(IEventBus bus) {
        PLACEMENT_FEATURES.register(bus);
    }

    public static final RegistryObject<PlacedFeature> SILVER_ORE = PLACEMENT_FEATURES.register("silver_ore", () -> new PlacedFeature(ModConfiguredFeatures.SILVER_ORE.getHolder().get(), commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)))));
    public static final RegistryObject<PlacedFeature> MITHRIL_ORE = PLACEMENT_FEATURES.register("mithril_ore", () -> new PlacedFeature(ModConfiguredFeatures.MITHRIL_ORE.getHolder().get(), commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(40)))));
    public static final RegistryObject<PlacedFeature> ADAMANTINE_ORE = PLACEMENT_FEATURES.register("adamantine_ore", () -> new PlacedFeature(ModConfiguredFeatures.ADAMANTINE_ORE.getHolder().get(), commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)))));
    public static final RegistryObject<PlacedFeature> RUBY_ORE = PLACEMENT_FEATURES.register("ruby_ore", () -> new PlacedFeature(ModConfiguredFeatures.RUBY_ORE.getHolder().get(), commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)))));
    public static final RegistryObject<PlacedFeature> AQUAMARINE_ORE = PLACEMENT_FEATURES.register("aquamarine_ore", () -> new PlacedFeature(ModConfiguredFeatures.AQUAMARINE_ORE.getHolder().get(), commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(100), VerticalAnchor.top()))));
}
