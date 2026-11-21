package io.github.tenneb22.tater;

import io.github.tenneb22.tater.block.ModBlocks;
import io.github.tenneb22.tater.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class TaterModelProvider extends FabricModelProvider {
    protected TaterModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createNonTemplateHorizontalBlock(ModBlocks.TINY_POTATO);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(ModItems.TATER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
