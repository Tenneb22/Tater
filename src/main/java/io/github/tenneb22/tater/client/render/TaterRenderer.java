package io.github.tenneb22.tater.client.render;

import io.github.tenneb22.tater.TaterMod;
import io.github.tenneb22.tater.client.model.ModModelLayers;
import io.github.tenneb22.tater.client.model.TaterModel;
import io.github.tenneb22.tater.client.render.state.TaterRenderState;
import io.github.tenneb22.tater.entity.Tater;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class TaterRenderer extends MobRenderer<Tater, TaterRenderState, TaterModel> {
    private static final Identifier TATER_TEXTURE = TaterMod.id("textures/entity/tater.png");

    public TaterRenderer(EntityRendererProvider.Context context) {
        super(context, new TaterModel(context.bakeLayer(ModModelLayers.TATER)), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(TaterRenderState state) {
        return TATER_TEXTURE;
    }

    @Override
    public TaterRenderState createRenderState() {
        return new TaterRenderState();
    }
}
