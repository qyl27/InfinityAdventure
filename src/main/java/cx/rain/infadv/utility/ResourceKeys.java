package cx.rain.infadv.utility;

import cx.rain.infadv.InfAdv;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public interface ResourceKeys {

    default String modid() {
        return InfAdv.MODID;
    }

    default ResourceLocation modLoc(String name) {
        return new ResourceLocation(modid(), name);
    }

    default ResourceLocation mcLoc(String name) {
        return new ResourceLocation(name);
    }

    default <T> ResourceKey<T> modKey(ResourceKey<? extends Registry<T>> type, String name) {
        return ResourceKey.create(type, modLoc(name));
    }

    default <T> ResourceKey<T> mcKey(ResourceKey<? extends Registry<T>> type, String name) {
        return ResourceKey.create(type, mcLoc(name));
    }
}
