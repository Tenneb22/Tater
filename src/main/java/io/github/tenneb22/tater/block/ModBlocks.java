package io.github.tenneb22.tater.block;

import io.github.tenneb22.tater.id.ModBlockItemIds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {
    public static final Block TINY_POTATO = register(
            ModBlockItemIds.TINY_POTATO,
            TinyPotatoBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .instabreak()
                    .noOcclusion()
                    .sound(SoundType.CROP)
    );

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = register(id.block(), factory, properties);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }

    private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(id));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {
    }
}
