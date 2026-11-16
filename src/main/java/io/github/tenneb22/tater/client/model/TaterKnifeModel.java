package io.github.tenneb22.tater.client.model;

import io.github.tenneb22.tater.client.render.state.TaterRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TaterKnifeModel extends EntityModel<TaterRenderState> {
    public TaterKnifeModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(10, 2).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.5F, -11.0F, -3.0F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.5F, -14.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 1).addBox(-0.5F, -13.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 0).addBox(-0.5F, -12.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

}
