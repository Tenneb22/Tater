package io.github.tenneb22.tater.client;

import io.github.tenneb22.tater.client.model.ModModelLayers;
import io.github.tenneb22.tater.client.render.TaterRenderer;
import io.github.tenneb22.tater.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class TaterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelLayers.registerModelLayers();
        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
        EntityRenderers.register(ModEntityTypes.TATER, TaterRenderer::new);
    }
}
