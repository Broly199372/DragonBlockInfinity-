package com.bernardo.dbi.client.render.layer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class HairStyle1Model<T extends LivingEntity> extends EntityModel<T> {

    public static final EntityModelLayer LAYER = new EntityModelLayer(
        new Identifier("dragonblockinfinity", "hair_style1"), "main");

    public final ModelPart hair;

    public HairStyle1Model(ModelPart root) {
        this.hair = root.getChild("hair");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData hair = root.addChild("hair",
            ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -2.0F, -3.0F, 8, 2, 7, new Dilation(0.0F)),
            ModelTransform.pivot(0.0F, -8.0F, 0.0F));

        hair.addChild("cube_r1",
            ModelPartBuilder.create().uv(26, 25).cuboid(-1.8F, -3.0F, -1.0F, 3, 5, 3, new Dilation(0.1F)),
            ModelTransform.of(-3.0F, 2.0F, -5.0F, 0.0F, 0.0F, 0.7854F));

        hair.addChild("cube_r2",
            ModelPartBuilder.create().uv(14, 25).cuboid(-1.0F, -3.0F, -1.0F, 3, 5, 3, new Dilation(0.1F)),
            ModelTransform.of(2.0F, 2.0F, -5.0F, 0.0F, 0.0F, -0.7854F));

        hair.addChild("cube_r3",
            ModelPartBuilder.create().uv(28, 9).cuboid(-2.0F, -4.0F, -2.0F, 3, 4, 3, new Dilation(0.1F)),
            ModelTransform.of(-3.3695F, -1.0931F, -0.9F, 0.0F, 0.0F, -2.2689F));

        hair.addChild("cube_r4",
            ModelPartBuilder.create().uv(20, 33).cuboid(-0.9679F, -4.0F, -1.0F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(0.4679F, 2.4618F, 5.9F, -2.7576F, 0.0F, 0.0F));

        hair.addChild("cube_r5",
            ModelPartBuilder.create().uv(16, 33).cuboid(-0.2679F, -4.0F, -1.0F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(2.4679F, 2.4618F, 5.9F, -2.7576F, 0.0F, 0.0F));

        hair.addChild("cube_r6",
            ModelPartBuilder.create().uv(12, 33).cuboid(-0.2679F, -4.0F, -1.0F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(-2.5321F, 2.4618F, 5.9F, -2.7576F, 0.0F, 0.0F));

        hair.addChild("cube_r7",
            ModelPartBuilder.create().uv(8, 33).cuboid(0.0F, -4.0F, -1.0F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(6.4679F, 2.4618F, -2.1F, 0.0F, 0.0F, 2.5744F));

        hair.addChild("cube_r8",
            ModelPartBuilder.create().uv(4, 33).cuboid(0.0F, -4.0F, -0.4F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(6.4679F, 2.4618F, -0.1F, 0.0F, 0.0F, 2.5744F));

        hair.addChild("cube_r9",
            ModelPartBuilder.create().uv(0, 33).cuboid(0.0F, -4.0F, -1.0F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(6.4679F, 2.4618F, 2.9F, 0.0F, 0.0F, 2.5744F));

        hair.addChild("cube_r10",
            ModelPartBuilder.create().uv(32, 16).cuboid(0.0F, -4.0F, -1.0F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(-5.5321F, 2.4618F, 0.9F, 0.0F, 0.0F, -2.5744F));

        hair.addChild("cube_r11",
            ModelPartBuilder.create().uv(30, 0).cuboid(0.0F, -4.0F, -1.4F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(-5.5321F, 2.4618F, 3.9F, 0.0F, 0.0F, -2.5744F));

        hair.addChild("cube_r12",
            ModelPartBuilder.create().uv(28, 16).cuboid(0.0F, -4.0F, -1.4F, 1, 5, 1, new Dilation(0.4F)),
            ModelTransform.of(-5.5321F, 2.4618F, -1.1F, 0.0F, 0.0F, -2.5744F));

        hair.addChild("cube_r13",
            ModelPartBuilder.create().uv(0, 25).cuboid(-2.0F, -3.0F, -3.0F, 3, 4, 4, new Dilation(0.1F)),
            ModelTransform.of(3.1F, -0.4503F, 4.1356F, -1.5708F, -0.6981F, -1.5708F));

        hair.addChild("cube_r14",
            ModelPartBuilder.create().uv(14, 17).cuboid(-2.0F, -3.0F, -2.0F, 3, 4, 4, new Dilation(0.1F)),
            ModelTransform.of(-1.9F, -0.4503F, 4.1356F, -1.5708F, -0.6981F, -1.5708F));

        hair.addChild("cube_r15",
            ModelPartBuilder.create().uv(0, 17).cuboid(-2.0F, -3.0F, -3.0F, 3, 4, 4, new Dilation(0.1F)),
            ModelTransform.of(3.4928F, 0.3158F, -1.1F, 0.0F, 0.0F, 2.2689F));

        hair.addChild("cube_r16",
            ModelPartBuilder.create().uv(14, 9).cuboid(-2.0F, -3.0F, -3.0F, 3, 4, 4, new Dilation(0.1F)),
            ModelTransform.of(3.4928F, 0.3158F, 2.9F, 0.0F, 0.0F, 2.2689F));

        hair.addChild("cube_r17",
            ModelPartBuilder.create().uv(0, 9).cuboid(-2.0F, -3.0F, -3.0F, 3, 4, 4, new Dilation(0.1F)),
            ModelTransform.of(-4.1356F, -0.4503F, 2.9F, 0.0F, 0.0F, -2.2689F));

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
