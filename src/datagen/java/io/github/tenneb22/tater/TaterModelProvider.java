package io.github.tenneb22.tater;

import io.github.tenneb22.tater.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

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

    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
