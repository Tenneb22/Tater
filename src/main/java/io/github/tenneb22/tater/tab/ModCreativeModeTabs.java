package io.github.tenneb22.tater.tab;

import io.github.tenneb22.tater.TaterMod;
import io.github.tenneb22.tater.id.ModCreativeModeTabIds;
import io.github.tenneb22.tater.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final CreativeModeTab TATER_CREATIVE_TAB = register(
            ModCreativeModeTabIds.TATER_CREATIVE_TAB,
            () -> new ItemStack(ModItems.TINY_POTATO),
            (parameters, output) -> BuiltInRegistries.ITEM.listElements()
                    .filter(holder -> holder.key().identifier().getNamespace().equals(TaterMod.MOD_ID))
                    .map(Holder::value)
                    .forEach(output::accept)
    );

    /// Creates and registers a new creative mode tab. The respective language key is created in this format: "itemGroup.namespace.path" (retrieved from the resource key).
    private static CreativeModeTab register(ResourceKey<CreativeModeTab> resourceKey, Supplier<ItemStack> iconSupplier, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
        CreativeModeTab tab = FabricCreativeModeTab.builder()
                .icon(iconSupplier)
                .title(Component.translatable("itemGroup." + resourceKey.identifier().getNamespace() + "." + resourceKey.identifier().getPath()))
                .displayItems(displayItemsGenerator)
                .build();

        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceKey, tab);
    }

    public static void initialize() {
    }
}
