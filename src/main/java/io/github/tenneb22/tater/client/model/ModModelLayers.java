package io.github.tenneb22.tater.client.model;

import io.github.tenneb22.tater.TaterMod;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    public static final ModelLayerLocation TATER = createMain("tater");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(TaterMod.id(name), "main");
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(TATER, TaterModel::getTexturedModelData);
    }
}
