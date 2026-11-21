package io.github.tenneb22.tater.id;

import io.github.tenneb22.tater.TaterMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItemIds {
    public static final ResourceKey<Item> TATER_SPAWN_EGG = create("tater_spawn_egg");

    private static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, TaterMod.id(name));
    }
}
