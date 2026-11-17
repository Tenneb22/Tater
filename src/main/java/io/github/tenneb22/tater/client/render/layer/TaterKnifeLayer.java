package io.github.tenneb22.tater.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tenneb22.tater.ModConfig;
import io.github.tenneb22.tater.TaterMod;
import io.github.tenneb22.tater.client.model.ModModelLayers;
import io.github.tenneb22.tater.client.model.TaterKnifeModel;
import io.github.tenneb22.tater.client.model.TaterModel;
import io.github.tenneb22.tater.client.render.state.TaterRenderState;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class TaterKnifeLayer extends RenderLayer<TaterRenderState, TaterModel> {
    private static final Identifier TATER_KNIFE_TEXTURE = TaterMod.id("textures/entity/tater_knife.png");
    private final TaterKnifeModel model;

    public TaterKnifeLayer(RenderLayerParent<TaterRenderState, TaterModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new TaterKnifeModel(modelSet.bakeLayer(ModModelLayers.TATER_KNIFE));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TaterRenderState state, float yRot, float xRot) {
        if (!state.isAngry || !ModConfig.renderTaterKnife) return;

        poseStack.pushPose();
        poseStack.translate(-0.4, -0.25, 0);

        int overlayCoords = LivingEntityRenderer.getOverlayCoords(state, 0.0F); // ?
        submitNodeCollector.submitModel(
                model, state, poseStack, RenderTypes.entityCutout(TATER_KNIFE_TEXTURE), lightCoords, overlayCoords, state.outlineColor
        );

        poseStack.popPose();
    }
}
