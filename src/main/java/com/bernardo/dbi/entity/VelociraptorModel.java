package com.bernardo.dbi.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class VelociraptorModel<T extends Entity> extends EntityModel<T> {

    public static final EntityModelLayer LAYER = new EntityModelLayer(
            new Identifier("dragonblockinfinity", "velociraptor"), "main");

    private final ModelPart Root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart bone19;
    private final ModelPart bone21;
    private final ModelPart bone22;
    private final ModelPart bone20;
    private final ModelPart rightArm;
    private final ModelPart bone15;
    private final ModelPart bone16;
    private final ModelPart bone17;
    private final ModelPart bone18;
    private final ModelPart leftArm;
    private final ModelPart bone14;
    private final ModelPart bone11;
    private final ModelPart bone12;
    private final ModelPart bone13;
    private final ModelPart rightLeg;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone8;
    private final ModelPart bone9;
    private final ModelPart bone10;
    private final ModelPart leftLeg;
    private final ModelPart bone5;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart rightItem;

    public VelociraptorModel(ModelPart root) {
        this.Root      = root.getChild("Root");
        this.body      = this.Root.getChild("body");
        this.head      = this.Root.getChild("head");
        this.bone19    = this.head.getChild("bone19");
        this.bone21    = this.bone19.getChild("bone21");
        this.bone22    = this.bone19.getChild("bone22");
        this.bone20    = this.head.getChild("bone20");
        this.rightArm  = this.Root.getChild("rightArm");
        this.bone15    = this.rightArm.getChild("bone15");
        this.bone16    = this.bone15.getChild("bone16");
        this.bone17    = this.bone15.getChild("bone17");
        this.bone18    = this.bone15.getChild("bone18");
        this.leftArm   = this.Root.getChild("leftArm");
        this.bone14    = this.leftArm.getChild("bone14");
        this.bone11    = this.bone14.getChild("bone11");
        this.bone12    = this.bone14.getChild("bone12");
        this.bone13    = this.bone14.getChild("bone13");
        this.rightLeg  = this.Root.getChild("rightLeg");
        this.bone6     = this.rightLeg.getChild("bone6");
        this.bone7     = this.bone6.getChild("bone7");
        this.bone8     = this.bone6.getChild("bone8");
        this.bone9     = this.bone6.getChild("bone9");
        this.bone10    = this.bone6.getChild("bone10");
        this.leftLeg   = this.Root.getChild("leftLeg");
        this.bone5     = this.leftLeg.getChild("bone5");
        this.bone      = this.bone5.getChild("bone");
        this.bone2     = this.bone5.getChild("bone2");
        this.bone3     = this.bone5.getChild("bone3");
        this.bone4     = this.bone5.getChild("bone4");
        this.rightItem = this.Root.getChild("rightItem");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData partData = modelData.getRoot();

        ModelPartData Root = partData.addChild("Root",
                ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = Root.addChild("body", ModelPartBuilder.create()
                .uv(0, 33).cuboid(-8.0F, -9.0F, 1.0F, 16.0F, 16.0F, 15.0F, new Dilation(0.0F))
                .uv(48, 105).cuboid(-7.0F, -8.0F, 16.0F, 14.0F, 14.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-6.0F, -7.0F, 22.0F, 12.0F, 12.0F, 12.0F, new Dilation(0.0F))
                .uv(62, 33).cuboid(-5.0F, -6.0F, 34.0F, 10.0F, 10.0F, 15.0F, new Dilation(0.0F))
                .uv(66, 0).cuboid(-4.0F, -5.0F, 49.0F, 8.0F, 8.0F, 16.0F, new Dilation(0.0F))
                .uv(62, 58).cuboid(-3.0F, -4.0F, 65.0F, 6.0F, 6.0F, 19.0F, new Dilation(0.0F))
                .uv(96, 83).cuboid(-2.0F, -3.0F, 84.0F, 4.0F, 4.0F, 18.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-7.0F, -8.0F, -18.0F, 14.0F, 14.0F, 19.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -24.0F, 0.0F));

        body.addChild("body_r1", ModelPartBuilder.create()
                .uv(114, 0).cuboid(-4.0F, -5.0F, -9.0F, 8.0F, 10.0F, 6.0F, new Dilation(0.0F))
                .uv(112, 24).cuboid(-5.0F, -6.0F, -3.0F, 10.0F, 12.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -2.0F, -21.0F, -0.2094F, 0.0F, 0.0F));

        ModelPartData head = Root.addChild("head",
                ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

        ModelPartData bone19 = head.addChild("bone19", ModelPartBuilder.create()
                .uv(48, 83).cuboid(-4.0F, -6.0F, -16.0F, 8.0F, 6.0F, 16.0F, new Dilation(0.0F))
                .uv(82, 136).cuboid(2.0F, -5.0F, -16.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(90, 136).cuboid(-4.0F, -5.0F, -16.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(104, 31).cuboid(-1.0F, 0.0F, -15.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(30, 133).cuboid(3.0F, 0.0F, -7.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(104, 24).cuboid(3.0F, -1.0F, -5.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(50, 135).cuboid(-3.0F, -1.0F, -5.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(30, 137).cuboid(-3.0F, 0.0F, -7.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(134, 138).cuboid(3.0F, 0.0F, -10.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(134, 138).cuboid(3.0F, 0.0F, -7.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(138, 138).cuboid(-3.0F, 0.0F, -10.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 139).cuboid(3.0F, 0.0F, -13.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(4, 139).cuboid(-3.0F, 0.0F, -13.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -3.0F, -29.0F));

        bone19.addChild("bone19_r1", ModelPartBuilder.create()
                .uv(12, 139).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(-2.0F, 1.0F, -14.0F, 0.0F, -0.9076F, 0.0F));
        bone19.addChild("bone19_r2", ModelPartBuilder.create()
                .uv(8, 139).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(2.0F, 1.0F, -14.0F, 0.0F, 0.9076F, 0.0F));

        ModelPartData bone21 = bone19.addChild("bone21",
                ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -5.0F, -6.0F));
        bone21.addChild("bone21_r1", ModelPartBuilder.create()
                .uv(34, 108).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.2618F, 0.2094F, 0.0F));
        bone21.addChild("bone21_r2", ModelPartBuilder.create()
                .uv(98, 130).cuboid(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 4.0F, new Dilation(0.4F)),
                ModelTransform.of(-1.0F, 2.0F, 0.0F, 0.2618F, 0.2094F, 0.0F));

        ModelPartData bone22 = bone19.addChild("bone22",
                ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, -5.0F, -6.0F));
        bone22.addChild("bone22_r1", ModelPartBuilder.create()
                .uv(34, 114).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(1.0F, 0.0F, 0.0F, 0.2618F, -0.2094F, 0.0F));
        bone22.addChild("bone22_r2", ModelPartBuilder.create()
                .uv(106, 130).cuboid(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 4.0F, new Dilation(0.4F)),
                ModelTransform.of(1.0F, 2.0F, 0.0F, 0.2618F, -0.2094F, 0.0F));

        head.addChild("bone20", ModelPartBuilder.create()
                .uv(48, 105).cuboid(-4.0F, 0.0F, -16.0F, 8.0F, 4.0F, 16.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -3.0F, -29.0F));

        // ── RIGHT ARM ────────────────────────────────────────────────────────
        ModelPartData rightArm = Root.addChild("rightArm",
                ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, -20.0F, 0.0F));
        ModelPartData bone15 = rightArm.addChild("bone15",
                ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData bone16 = bone15.addChild("bone16",
                ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 0.0F, -10.0F));
        bone16.addChild("bone16_r1", ModelPartBuilder.create()
                .uv(48, 108).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(114, 16).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
        ModelPartData bone17 = bone15.addChild("bone17",
                ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 4.0F, -8.0F));
        bone17.addChild("bone17_r1", ModelPartBuilder.create()
                .uv(48, 64).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));
        ModelPartData bone18 = bone15.addChild("bone18",
                ModelPartBuilder.create(), ModelTransform.of(-2.0F, 9.0F, -12.0F, -0.3142F, 0.0F, 0.0F));
        bone18.addChild("bone18_r1", ModelPartBuilder.create()
                .uv(140, 92).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(90, 140).cuboid(-2.0F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 1.0F, -1.0472F, 0.0F, 0.0F));
        bone18.addChild("bone18_r2", ModelPartBuilder.create()
                .uv(138, 115).cuboid(-1.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(2.0F, 0.0F, 1.0F, -1.7104F, 0.0F, 0.0F));
        bone18.addChild("bone18_r3", ModelPartBuilder.create()
                .uv(130, 16).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -2.0F, -1.0F, -1.0472F, 0.0F, 0.0F));

        // ── LEFT ARM ─────────────────────────────────────────────────────────
        ModelPartData leftArm = Root.addChild("leftArm",
                ModelPartBuilder.create(), ModelTransform.pivot(6.0F, -20.0F, 0.0F));
        ModelPartData bone14 = leftArm.addChild("bone14",
                ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData bone11 = bone14.addChild("bone11",
                ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 0.0F, -10.0F));
        bone11.addChild("bone11_r1", ModelPartBuilder.create()
                .uv(66, 125).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(114, 16).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
        ModelPartData bone12 = bone14.addChild("bone12",
                ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 4.0F, -8.0F));
        bone12.addChild("bone12_r1", ModelPartBuilder.create()
                .uv(48, 72).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));
        ModelPartData bone13 = bone14.addChild("bone13",
                ModelPartBuilder.create(), ModelTransform.of(2.0F, 9.0F, -12.0F, -0.3142F, 0.0F, 0.0F));
        bone13.addChild("bone13_r1", ModelPartBuilder.create()
                .uv(140, 96).cuboid(-1.5F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 140).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(1.0F, 0.0F, 1.0F, -1.0472F, 0.0F, 0.0F));
        bone13.addChild("bone13_r2", ModelPartBuilder.create()
                .uv(138, 121).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(-2.0F, 0.0F, 1.0F, -1.7104F, 0.0F, 0.0F));
        bone13.addChild("bone13_r3", ModelPartBuilder.create()
                .uv(82, 130).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -2.0F, -1.0F, -1.0472F, 0.0F, 0.0F));

        // ── RIGHT LEG ────────────────────────────────────────────────────────
        ModelPartData rightLeg = Root.addChild("rightLeg",
                ModelPartBuilder.create(), ModelTransform.pivot(-10.0F, -23.0F, 8.0F));
        ModelPartData bone6 = rightLeg.addChild("bone6",
                ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create()
                .uv(66, 24).cuboid(-3.0F, -1.0F, 1.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 21.0F, -3.0F));
        bone7.addChild("bone7_r1", ModelPartBuilder.create()
                .uv(82, 125).cuboid(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.0F, 0.0F, -1.0F, -0.4538F, -0.4538F, -0.2618F));
        bone7.addChild("bone7_r2", ModelPartBuilder.create()
                .uv(0, 133).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(3.0F, 0.0F, 0.0F, -0.4538F, -0.4538F, -0.2618F));
        bone7.addChild("bone7_r3", ModelPartBuilder.create()
                .uv(66, 135).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.1F))
                .uv(58, 135).cuboid(-3.0F, -2.0F, -1.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.1F)),
                ModelTransform.of(1.0F, 1.0F, 0.0F, 0.4887F, 0.0F, 0.0F));
        bone7.addChild("bone7_r4", ModelPartBuilder.create()
                .uv(106, 135).cuboid(0.0F, -1.0F, -2.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.2F)),
                ModelTransform.of(-4.0F, 0.0F, 1.0F, 0.5236F, 0.384F, 0.2094F));
        ModelPartData bone8 = bone6.addChild("bone8",
                ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 17.0F, 3.0F));
        bone8.addChild("bone8_r1", ModelPartBuilder.create()
                .uv(48, 80).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.2F)),
                ModelTransform.of(3.0F, 2.0F, -1.0F, 2.9147F, 1.2915F, -2.2166F));
        bone8.addChild("bone8_r2", ModelPartBuilder.create()
                .uv(10, 133).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.3F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -2.9671F, 1.2915F, -2.2166F));
        bone8.addChild("bone8_r3", ModelPartBuilder.create()
                .uv(122, 115).cuboid(-1.0F, -6.0F, -2.0F, 3.0F, 12.0F, 5.0F, new Dilation(0.0F)),
                ModelTransform.of(-2.0F, -2.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
        ModelPartData bone9 = bone6.addChild("bone9",
                ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 8.0F, -2.0F));
        bone9.addChild("bone9_r1", ModelPartBuilder.create()
                .uv(112, 44).cuboid(-2.0F, -3.0F, -6.0F, 5.0F, 6.0F, 12.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
        ModelPartData bone10 = bone6.addChild("bone10",
                ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 2.0F, -1.0F));
        bone10.addChild("bone10_r1", ModelPartBuilder.create()
                .uv(88, 105).cuboid(-3.0F, -8.0F, -5.0F, 7.0F, 15.0F, 10.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

        // ── LEFT LEG ─────────────────────────────────────────────────────────
        ModelPartData leftLeg = Root.addChild("leftLeg",
                ModelPartBuilder.create(), ModelTransform.pivot(2.0F, -12.0F, 0.0F));
        ModelPartData bone5 = leftLeg.addChild("bone5",
                ModelPartBuilder.create(), ModelTransform.pivot(8.0F, -11.0F, 8.0F));
        ModelPartData bone = bone5.addChild("bone", ModelPartBuilder.create()
                .uv(122, 105).cuboid(-2.0F, -1.0F, 1.0F, 5.0F, 3.0F, 7.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 21.0F, -3.0F));
        bone.addChild("bone_r1", ModelPartBuilder.create()
                .uv(138, 127).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-3.0F, 0.0F, -2.0F, -0.4538F, 0.4538F, 0.2618F));
        bone.addChild("bone_r2", ModelPartBuilder.create()
                .uv(20, 133).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(-3.0F, 0.0F, 0.0F, -0.4538F, 0.4538F, 0.2618F));
        bone.addChild("bone_r3", ModelPartBuilder.create()
                .uv(98, 135).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.1F))
                .uv(74, 135).cuboid(2.0F, -2.0F, -1.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.1F)),
                ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.4887F, 0.0F, 0.0F));
        bone.addChild("bone_r4", ModelPartBuilder.create()
                .uv(114, 135).cuboid(-1.0F, -1.0F, -2.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.2F)),
                ModelTransform.of(4.0F, 0.0F, 1.0F, 0.5236F, -0.384F, -0.2094F));
        ModelPartData bone2 = bone5.addChild("bone2",
                ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 17.0F, 3.0F));
        bone2.addChild("bone2_r1", ModelPartBuilder.create()
                .uv(54, 80).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.2F)),
                ModelTransform.of(-3.0F, 2.0F, -1.0F, 2.9147F, -1.2915F, 2.2166F));
        bone2.addChild("bone2_r2", ModelPartBuilder.create()
                .uv(134, 132).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.3F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -2.9671F, -1.2915F, 2.2166F));
        bone2.addChild("bone2_r3", ModelPartBuilder.create()
                .uv(34, 125).cuboid(-2.0F, -6.0F, -2.0F, 3.0F, 12.0F, 5.0F, new Dilation(0.0F)),
                ModelTransform.of(2.0F, -2.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
        ModelPartData bone3 = bone5.addChild("bone3",
                ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 8.0F, -2.0F));
        bone3.addChild("bone3_r1", ModelPartBuilder.create()
                .uv(112, 62).cuboid(-3.0F, -3.0F, -6.0F, 5.0F, 6.0F, 12.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
        ModelPartData bone4 = bone5.addChild("bone4",
                ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 2.0F, -1.0F));
        bone4.addChild("bone4_r1", ModelPartBuilder.create()
                .uv(0, 108).cuboid(-4.0F, -8.0F, -5.0F, 7.0F, 15.0F, 10.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

        Root.addChild("rightItem",
                ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, -15.0F, 1.0F));

        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay,
                       float red, float green, float blue, float alpha) {
        Root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
