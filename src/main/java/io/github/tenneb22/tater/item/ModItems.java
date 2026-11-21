package io.github.tenneb22.tater.item;

import io.github.tenneb22.tater.block.ModBlocks;
import io.github.tenneb22.tater.entity.ModEntityTypes;
import io.github.tenneb22.tater.id.ModBlockItemIds;
import io.github.tenneb22.tater.id.ModItemIds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {
    public static final Item TATER_SPAWN_EGG = register(
            ModItemIds.TATER_SPAWN_EGG,
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(ModEntityTypes.TATER)
    );

    public static final Item TINY_POTATO = register(
            ModBlockItemIds.TINY_POTATO,
            ModBlocks.TINY_POTATO,
            BlockItem::new,
            new Item.Properties().equippableUnswappable(EquipmentSlot.HEAD)
    );

    private static Item register(BlockItemId blockItemId, Block block, BiFunction<Block, Item.Properties, Item> itemFactory, Item.Properties properties) {
        return register(blockItemId.item(), p -> itemFactory.apply(block, p), properties.useBlockDescriptionPrefix().requiredFeatures(block.requiredFeatures()));
    }

    private static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(itemKey));

        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    public static void initialize() {
    }
}
