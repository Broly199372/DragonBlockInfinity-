package com.bernardo.dbi.client.render.layer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class HairGokuModel<T extends LivingEntity> extends EntityModel<T> {

    public static final EntityModelLayer LAYER = new EntityModelLayer(
        new Identifier("dragonblockinfinity", "hair_goku"), "main");

    public final ModelPart head;

    public HairGokuModel(ModelPart root) {
        this.head = root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData head = root.addChild("head",
            ModelPartBuilder.create(),
            ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData bone7 = head.addChild("bone7",
            ModelPartBuilder.create(),
            ModelTransform.of(-20.0F, -28.0F, 0.0F, 0.0F, 0.0F, 2.4871F));

        bone7.addChild("bone7_r1",
            ModelPartBuilder.create()
                .uv(0, 23).cuboid(20.5F, -28.0F, -3.0F, 4, 4, 6, new Dilation(-1.0F))
                .uv(20, 22).cuboid(20.5F, -26.0F, -3.0F, 4, 4, 6, new Dilation(-0.7F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

        bone7.addChild("bone7_r2",
            ModelPartBuilder.create().uv(0, 12).cuboid(23.3F, -21.0F, -3.0F, 4, 5, 6, new Dilation(-0.3F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

        ModelPartData bone6 = head.addChild("bone6",
            ModelPartBuilder.create(),
            ModelTransform.pivot(1.0F, 22.0F, 0.0F));

        bone6.addChild("bone6_r1",
            ModelPartBuilder.create().uv(48, 52).cuboid(-28.2F, -2.0F, -2.0F, 2, 2, 4, new Dilation(-0.3F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.7453F));

        bone6.addChild("bone6_r2",
            ModelPartBuilder.create().uv(0, 43).cuboid(-26.3F, -10.0F, -2.0F, 2, 3, 4, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.3963F));

        ModelPartData bone4 = head.addChild("bone4",
            ModelPartBuilder.create(),
            ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        bone4.addChild("bone4_r1",
            ModelPartBuilder.create().uv(40, 42).cuboid(26.6F, -26.0F, -3.0F, 3, 4, 6, new Dilation(-0.5F)),
            ModelTransform.of(-17.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        bone4.addChild("bone4_r2",
            ModelPartBuilder.create().uv(40, 31).cuboid(21.0F, -29.0F, -3.0F, 3, 5, 6, new Dilation(-0.3F)),
            ModelTransform.of(-17.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        bone4.addChild("bone4_r3",
            ModelPartBuilder.create().uv(40, 20).cuboid(15.0F, -30.0F, -3.0F, 3, 5, 6, new Dilation(0.0F)),
            ModelTransform.of(-17.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

        ModelPartData bone5 = head.addChild("bone5",
            ModelPartBuilder.create(),
            ModelTransform.pivot(-1.0F, 24.0F, 0.0F));

        bone5.addChild("bone5_r1",
            ModelPartBuilder.create()
                .uv(12, 51).cuboid(-26.3F, -9.0F, -2.0F, 2, 3, 4, new Dilation(-0.3F))
                .uv(0, 50).cuboid(-26.3F, -8.0F, -2.0F, 2, 3, 4, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.4835F));

        ModelPartData bone2 = head.addChild("bone2",
            ModelPartBuilder.create(),
            ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        bone2.addChild("bone2_r1",
            ModelPartBuilder.create()
                .uv(40, 0).cuboid(26.7F, -18.0F, -3.0F, 4, 4, 6, new Dilation(-1.0F))
                .uv(0, 33).cuboid(26.7F, -16.0F, -3.0F, 4, 4, 6, new Dilation(-0.7F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.3526F));

        bone2.addChild("bone2_r2",
            ModelPartBuilder.create()
                .uv(20, 32).cuboid(23.0F, -20.0F, -3.0F, 4, 4, 6, new Dilation(-0.5F))
                .uv(20, 42).cuboid(23.0F, -18.0F, -3.0F, 4, 3, 6, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

        ModelPartData bone3 = bone2.addChild("bone3",
            ModelPartBuilder.create(),
            ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        bone3.addChild("bone3_r1",
            ModelPartBuilder.create().uv(20, 11).cuboid(28.5F, 3.0F, -3.0F, 4, 5, 6, new Dilation(-1.3F)),
            ModelTransform.of(-17.0F, -9.0F, 0.0F, 0.0F, 0.0F, -1.4835F));

        bone3.addChild("bone3_r2",
            ModelPartBuilder.create().uv(0, 0).cuboid(27.5F, -11.0F, -3.0F, 4, 6, 6, new Dilation(-0.9F)),
            ModelTransform.of(-17.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        bone3.addChild("bone3_r3",
            ModelPartBuilder.create().uv(20, 0).cuboid(24.0F, -16.0F, -3.0F, 4, 5, 6, new Dilation(-0.5F)),
            ModelTransform.of(-17.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

        bone3.addChild("bone3_r4",
            ModelPartBuilder.create().uv(40, 10).cuboid(23.0F, -19.0F, -3.0F, 4, 4, 6, new Dilation(0.0F)),
            ModelTransform.of(-17.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        ModelPartData bone = head.addChild("bone",
            ModelPartBuilder.create(),
            ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        bone.addChild("bone_r1",
            ModelPartBuilder.create()
                .uv(36, 52).cuboid(21.0F, -17.0F, -2.0F, 2, 3, 4, new Dilation(-0.3F))
                .uv(24, 51).cuboid(21.0F, -16.0F, -2.0F, 2, 3, 4, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1781F));

        head.addChild("pelo1",
            ModelPartBuilder.create().uv(10, 58).cuboid(-3.5F, -10.0F, -4.6F, 1, 2, 2, new Dilation(0.0F)),
            ModelTransform.of(-2.7F, 2.2F, -3.6F, -0.2104F, 0.3108F, 0.3259F));

        head.addChild("pelo2",
            ModelPartBuilder.create().uv(58, 24).cuboid(-3.3F, -10.0F, -4.6F, 1, 2, 2, new Dilation(0.0F)),
            ModelTransform.of(-1.0F, 4.0F, -4.3F, -0.2834F, 0.2464F, 0.0619F));

        head.addChild("pelo5",
            ModelPartBuilder.create().uv(0, 57).cuboid(-1.3F, -1.25F, -1.0F, 2, 2, 2, new Dilation(0.0F)),
            ModelTransform.of(-0.45F, -7.494F, -3.9192F, -0.37F, -0.051F, -0.7659F));

        head.addChild("pelo6",
            ModelPartBuilder.create().uv(18, 58).cuboid(-3.3F, -10.0F, -4.6F, 1, 2, 2, new Dilation(0.0F)),
            ModelTransform.of(6.5F, 1.8F, -4.4F, -0.3625F, 0.0913F, -0.3995F));

        head.addChild("pelo7",
            ModelPartBuilder.create().uv(12, 43).cuboid(-3.5F, -10.0F, -4.6F, 2, 2, 2, new Dilation(0.0F)),
            ModelTransform.of(10.4F, -2.6F, -3.6F, -0.37F, -0.051F, -0.7659F));

        head.addChild("pelo8",
            ModelPartBuilder.create().uv(58, 20).cuboid(-3.37F, -9.8201F, -5.0656F, 1, 2, 2, new Dilation(0.0F)),
            ModelTransform.of(10.0F, 1.2F, -3.6F, -0.3687F, 0.06F, -0.4812F));

        return TexturedModelData.of(modelData, 128, 128);
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
