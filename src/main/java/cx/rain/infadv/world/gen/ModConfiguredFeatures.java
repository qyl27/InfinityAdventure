package cx.rain.infadv.world.gen;

import com.google.common.base.Suppliers;
import cx.rain.infadv.InfAdv;
import cx.rain.infadv.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, InfAdv.MODID);

    private static Supplier<List<OreConfiguration.TargetBlockState>> stoneReplacement(Block block) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, block.defaultBlockState())));
    }

    private static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_REPLACEMENT = stoneReplacement(ModBlocks.AQUAMARINE_ORE.get());


}
