package io.github.tenneb22.tater.id;

import io.github.tenneb22.tater.TaterMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class ModCreativeModeTabIds {
    public static final ResourceKey<CreativeModeTab> TATER_CREATIVE_TAB = create("tater");

    private static ResourceKey<CreativeModeTab> create(String name) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, TaterMod.id(name));
    }
}
