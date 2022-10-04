package cx.rain.infadv.world;

import cx.rain.infadv.InfAdv;
import cx.rain.infadv.world.feature.OreFeatureBuilder;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModFeatures {

    public static final Holder<PlacedFeature> ETHER_COAL = new OreFeatureBuilder()
            .addReplaceRule(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), Blocks.COAL_ORE)
            .addModifier(CountPlacement.of(20))
            .addModifier(HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.TOP))
            .register(InfAdv.MODID, "sky_peace_coal");

    public static final Holder<PlacedFeature> ETHER_IRON = new OreFeatureBuilder()
            .addReplaceRule(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), Blocks.IRON_ORE)
            .addModifier(BiomeFilter.biome())
            .addModifier(CountPlacement.of(20))
            .addModifier(HeightRangePlacement.uniform(VerticalAnchor.absolute(100), VerticalAnchor.TOP))
            .register(InfAdv.MODID, "sky_peace_iron");

    public static BiomeGenerationSettings peaceOres() {
        return new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ETHER_COAL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ETHER_IRON)
                .build();
    }
}
