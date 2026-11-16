package io.github.tenneb22.tater.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class TaterRenderState extends LivingEntityRenderState {
    public final AnimationState useKnifeAnimationState = new AnimationState();
    public boolean isAngry;
}
