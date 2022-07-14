package net.sydokiddo.endlessencore.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class CoreRegistry<T> {
    private final ResourceKey<Registry<T>> key;
    private final String modId;
    private final Registry<T> registry;
    private boolean isPresent;

    @SuppressWarnings("all")
    public CoreRegistry(ResourceKey<Registry<T>> key, String modId) {
        this.key = key;
        this.modId = modId;
        this.isPresent = false;
        this.registry = Registry.REGISTRY.get((ResourceKey)key);
    }

    public static <T> CoreRegistry<T> create(ResourceKey<Registry<T>> key, String modId) {
        return new CoreRegistry<>(key, modId);
    }

    public <E extends T> E register(String key, E entry) {
        Registry.register(this.registry, new ResourceLocation(this.modId, key), entry);
        return entry;
    }

    public void register() {
        if (this.isPresent) throw new IllegalStateException("Duplication of Registry: " + this.key);
        this.isPresent = true;
    }
}