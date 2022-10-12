package cx.rain.infadv.world.feature.builder;

import cx.rain.infadv.utility.Self;
import cx.rain.infadv.world.feature.ModConfiguredFeatures;
import cx.rain.infadv.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A builder for feature
 * <p>This used to build placed feature</p>
 */
public abstract class BaseFeatureBuilder<C extends FeatureConfiguration, SELF extends BaseFeatureBuilder<C, SELF>> implements Self<SELF> {

    protected final Feature<C> feature;
    protected final List<PlacementModifier> modifiers = new LinkedList<>();

    private volatile RegistryObject<PlacedFeature> holder = null;
    private volatile RegistryObject<ConfiguredFeature<C, Feature<C>>> configured = null;

    public BaseFeatureBuilder(Feature<C> feature) {
        this.feature = feature;
    }

    /**
     * A modifier can set generated base position by range, times, etc...
     *
     * @param modifier generate modifier
     * @return this builder
     */
    public SELF addModifier(PlacementModifier modifier) {
        modifiers.add(modifier);
        return self();
    }

    /**
     * Add a modifier and remove same type of other modifiers
     *
     * @param modifier generate modifier
     * @return this builder
     */
    public SELF replaceModifier(PlacementModifier modifier) {
        modifiers.removeIf(modifier.getClass()::isInstance);
        modifiers.add(modifier);
        return self();
    }

    /**
     * Copy modifiers from another feature
     *
     * @param parent base feature
     * @return this builder
     */
    public SELF fromModifier(PlacedFeature parent) {
        modifiers.addAll(parent.placement());
        return self();
    }

    /**
     * Create a configuration base on parent
     *
     * @param parent base configuration
     * @return this builder
     */
    public abstract SELF fromConfiguration(C parent);

    /**
     * Create a configuration
     *
     * @return configuration
     */
    protected abstract C buildConfiguration();

    /**
     * Create a {@link PlacedFeature} and register it.
     *
     * @param name default name of configured feature and placed feature
     */
    public SELF register(String name) {
        synchronized (this) {
            if (holder == null) {
                configured = ModConfiguredFeatures.CONFIGURED_FEATURES.register(name, () -> new ConfiguredFeature<>(feature, buildConfiguration()));
                holder = ModPlacedFeatures.PLACEMENT_FEATURES.register(name, () -> new PlacedFeature(Holder.hackyErase(configured.getHolder().get()), modifiers));
            }
        }
        return self();
    }

    /**
     * Create a {@link PlacedFeature} and register it, name is modid:name
     *
     * @param id name
     */
    public SELF register(ResourceLocation id) {
        return register(id.toString());
    }

    public RegistryObject<ConfiguredFeature<C, Feature<C>>> configuredObj() {
        return Objects.requireNonNull(configured, "Please register the feature first.");
    }

    public ConfiguredFeature<C, Feature<C>> configured() {
        return configuredObj().get();
    }

    public Holder<ConfiguredFeature<C, Feature<C>>> configuredHolder() {
        return configuredObj().getHolder().get();
    }

    public Optional<Holder<ConfiguredFeature<C, Feature<C>>>> configuredHolderOpt() {
        return configuredObj().getHolder();
    }

    public RegistryObject<PlacedFeature> object() {
        return Objects.requireNonNull(holder, "Please register the feature first.");
    }

    public PlacedFeature get() {
        return object().get();
    }

    public Holder<PlacedFeature> holder() {
        return object().getHolder().get();
    }

    public Optional<Holder<PlacedFeature>> holderOpt() {
        return object().getHolder();
    }
}
