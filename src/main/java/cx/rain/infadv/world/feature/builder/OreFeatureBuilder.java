package cx.rain.infadv.world.feature.builder;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * A builder for ore generator
 * <p>This util used to build feature use {@link Feature#ORE}</p>
 */
public class OreFeatureBuilder extends BaseFeatureBuilder<OreConfiguration, OreFeatureBuilder> {

    private final List<OreConfiguration.TargetBlockState> targets = new LinkedList<>();
    private float discardChanceOnAirExposure;
    private int size;

    public OreFeatureBuilder() {
        super(Feature.ORE);
    }

    public OreFeatureBuilder size(int size) {
        this.size = size;
        return this;
    }

    public OreFeatureBuilder discardChanceOnAirExposure(float chance) {
        this.discardChanceOnAirExposure = chance;
        return this;
    }

    // Target

    /**
     * Add a rule to replace existed block to ore
     *
     * @param target replace rule
     * @return this builder
     */
    public OreFeatureBuilder addReplaceRule(OreConfiguration.TargetBlockState target) {
        targets.add(target);
        return this;
    }

    /**
     * Add a rule to replace existed block to ore
     *
     * @param rule     replace rule
     * @param replaced new block
     * @return this builder
     */
    public OreFeatureBuilder addReplaceRule(RuleTest rule, BlockState replaced) {
        return addReplaceRule(OreConfiguration.target(rule, replaced));
    }

    /**
     * Add a rule to replace existed block to ore
     *
     * @param rule     replace rule
     * @param replaced new block
     * @return this builder
     */
    public OreFeatureBuilder addReplaceRule(RuleTest rule, Block replaced) {
        return addReplaceRule(OreConfiguration.target(rule, replaced.defaultBlockState()));
    }

    /**
     * Add a rule to replace existed block to ore
     *
     * @param rule     replace rule
     * @param replaced new block
     * @return this builder
     */
    public OreFeatureBuilder addReplaceRule(RuleTest rule, Supplier<Block> replaced) {
        return addReplaceRule(rule, replaced.get());
    }

    /**
     * A simple method for stone ore
     *
     * @param ore ore block
     * @return this builder
     */
    public OreFeatureBuilder addStoneOre(Supplier<? extends Block> ore) {
        return addReplaceRule(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ore.get().defaultBlockState());
    }

    @Override
    public OreFeatureBuilder fromConfiguration(OreConfiguration parent) {
        discardChanceOnAirExposure = parent.discardChanceOnAirExposure;
        size = parent.size;
        return this;
    }

    @Override
    protected OreConfiguration buildConfiguration() {
        return new OreConfiguration(targets, size, discardChanceOnAirExposure);
    }
}
