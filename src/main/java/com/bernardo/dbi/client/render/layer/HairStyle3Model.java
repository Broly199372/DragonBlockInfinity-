package com.bernardo.dbi.client.render.layer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class HairStyle3Model<T extends LivingEntity> extends EntityModel<T> {

    public static final EntityModelLayer LAYER = new EntityModelLayer(
        new Identifier("dragonblockinfinity", "hair_style3"), "main");

    public final ModelPart hair;

    public HairStyle3Model(ModelPart root) {
        this.hair = root.getChild("hair");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData hair = root.addChild("hair",
            ModelPartBuilder.create()
                .uv(7, 7).cuboid(-4f, -10f, -3f, 8, 2, 7),
            ModelTransform.pivot(0f, 0f, 0f));

        hair.addChild("cube_r2",
            ModelPartBuilder.create().uv(0, 9).cuboid(-1.0f, -3.0f, -3.0f, 3, 4, 4, new Dilation(0.1f)),
            ModelTransform.of(4.1356f, -8.4503f, 2.9f, 0.0f, 0.0f, -2.2689f));

        hair.addChild("cube_r3",
            ModelPartBuilder.create().uv(14, 9).cuboid(-1.0f, -3.0f, -3.0f, 3, 4, 4, new Dilation(0.1f)),
            ModelTransform.of(-3.4928f, -7.6842f, 2.9f, 0.0f, 0.0f, 2.2689f));

        hair.addChild("cube_r4",
            ModelPartBuilder.create().uv(0, 17).cuboid(-1.0f, -3.0f, -3.0f, 3, 4, 4, new Dilation(0.1f)),
            ModelTransform.of(-3.4928f, -7.6842f, -1.1f, 0.0f, 0.0f, 2.2689f));

        hair.addChild("cube_r5",
            ModelPartBuilder.create().uv(14, 17).cuboid(-1.0f, -3.0f, -2.0f, 3, 4, 4, new Dilation(0.1f)),
            ModelTransform.of(1.9f, -8.4503f, 4.1356f, 1.5708f, 0.6981f, -1.5708f));

        hair.addChild("cube_r6",
            ModelPartBuilder.create().uv(0, 25).cuboid(-1.0f, -3.0f, -3.0f, 3, 4, 4, new Dilation(0.1f)),
            ModelTransform.of(-3.1f, -8.4503f, 4.1356f, 1.5708f, 0.6981f, -1.5708f));

        hair.addChild("cube_r7",
            ModelPartBuilder.create().uv(28, 16).cuboid(-1.0f, -4.0f, -1.4f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(5.5321f, -5.5382f, -1.1f, 0.0f, 0.0f, -2.5744f));

        hair.addChild("cube_r8",
            ModelPartBuilder.create().uv(30, 0).cuboid(-1.0f, -4.0f, -1.4f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(5.5321f, -5.5382f, 3.9f, 0.0f, 0.0f, -2.5744f));

        hair.addChild("cube_r9",
            ModelPartBuilder.create().uv(32, 16).cuboid(-1.0f, -4.0f, -1.0f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(5.5321f, -5.5382f, 0.9f, 0.0f, 0.0f, -2.5744f));

        hair.addChild("cube_r10",
            ModelPartBuilder.create().uv(0, 33).cuboid(-1.0f, -4.0f, -1.0f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(-6.4679f, -5.5382f, 2.9f, 0.0f, 0.0f, 2.5744f));

        hair.addChild("cube_r11",
            ModelPartBuilder.create().uv(4, 33).cuboid(-1.0f, -4.0f, -0.4f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(-6.4679f, -5.5382f, -0.1f, 0.0f, 0.0f, 2.5744f));

        hair.addChild("cube_r12",
            ModelPartBuilder.create().uv(8, 33).cuboid(-1.0f, -4.0f, -1.0f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(-6.4679f, -5.5382f, -2.1f, 0.0f, 0.0f, 2.5744f));

        hair.addChild("cube_r13",
            ModelPartBuilder.create().uv(12, 33).cuboid(-0.7321f, -4.0f, -1.0f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(2.5321f, -5.5382f, 5.9f, 2.7576f, 0.0f, 0.0f));

        hair.addChild("cube_r14",
            ModelPartBuilder.create().uv(16, 33).cuboid(-0.7321f, -4.0f, -1.0f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(-2.4679f, -5.5382f, 5.9f, 2.7576f, 0.0f, 0.0f));

        hair.addChild("cube_r15",
            ModelPartBuilder.create().uv(20, 33).cuboid(-0.0321f, -4.0f, -1.0f, 1, 5, 1, new Dilation(0.4f)),
            ModelTransform.of(-0.4679f, -5.5382f, 5.9f, 2.7576f, 0.0f, 0.0f));

        hair.addChild("cube_r16",
            ModelPartBuilder.create().uv(28, 9).cuboid(-1.0f, -4.0f, -2.0f, 3, 4, 3, new Dilation(0.1f)),
            ModelTransform.of(3.3695f, -9.0931f, -0.9f, 0.0f, 0.0f, -2.2689f));

        hair.addChild("cube_r17",
            ModelPartBuilder.create().uv(14, 25).cuboid(-2f, -3f, -1f, 3, 5, 3, new Dilation(0.1f)),
            ModelTransform.of(-2f, -6f, -5f, 0.0f, 0.0f, -0.7854f));

        hair.addChild("cube_r18",
            ModelPartBuilder.create().uv(26, 25).cuboid(-1.2f, -3f, -1f, 3, 5, 3, new Dilation(0.1f)),
            ModelTransform.of(3f, -6f, -5f, 0.0f, 0.0f, 0.7854f));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay,
                       float red, float green, float blue, float alpha) {
        hair.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
