package io.github.tenneb22.tater.id;

import io.github.tenneb22.tater.TaterMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeIds {
    public static final ResourceKey<EntityType<?>> TATER = create("tater");

    private static ResourceKey<EntityType<?>> create(String name) {
        Identifier id = TaterMod.id(name);
        return ResourceKey.create(Registries.ENTITY_TYPE, id);
    }
}
