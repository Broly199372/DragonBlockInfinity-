package com.bernardo.dbi.client.render.layer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class HairModel<T extends LivingEntity> extends EntityModel<T> {

    public static final EntityModelLayer LAYER = new EntityModelLayer(
        new Identifier("dragonblockinfinity", "hair"), "main");

    public final ModelPart head;

    public HairModel(ModelPart root) {
        this.head = root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData partData = modelData.getRoot();

        partData.addChild("head",
            ModelPartBuilder.create()
                .uv(0, 0).cuboid(-4f, -8f, -4f, 8, 8, 8, new Dilation(0.6f))
                .uv(32, 0).cuboid(-4f, -8f, -4f, 8, 8, 8, new Dilation(0.7f)),
            ModelTransform.pivot(0, 0, 0));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay,
                       float red, float green, float blue, float alpha) {
        head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
