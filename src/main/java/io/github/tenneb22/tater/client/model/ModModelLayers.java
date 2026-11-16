package io.github.tenneb22.tater.client.model;

import io.github.tenneb22.tater.TaterMod;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    public static final ModelLayerLocation TATER = createMain("tater");
    public static final ModelLayerLocation TATER_KNIFE = register("tater", "tater_knife"); // this also works if you just do createMain("tater_knife") ... why? or why shouldn't it?

    private static ModelLayerLocation register(String model, String layer) {
        return new ModelLayerLocation(TaterMod.id(model), layer);
    }

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(TaterMod.id(name), "main");
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(TATER, TaterModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(TATER_KNIFE, TaterKnifeModel::getTexturedModelData);
    }
}
