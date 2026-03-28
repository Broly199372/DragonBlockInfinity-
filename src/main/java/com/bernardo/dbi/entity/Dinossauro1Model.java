package com.bernardo.dbi.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class Dinossauro1Model<T extends Entity> extends EntityModel<T> {

    public static final EntityModelLayer LAYER = new EntityModelLayer(
            new Identifier("dragonblockinfinity", "dinossauro1"), "main");

    private final ModelPart Root;
    private final ModelPart Corpo;
    private final ModelPart Corpo2;
    private final ModelPart Head;
    private final ModelPart Eye;
    private final ModelPart Eye2;
    private final ModelPart LegRigth;
    private final ModelPart Base;
    private final ModelPart Canela;
    private final ModelPart Joelho;
    private final ModelPart Coxa;
    private final ModelPart finger3;
    private final ModelPart Claw;
    private final ModelPart LegLeft;
    private final ModelPart Base2;
    private final ModelPart Canela2;
    private final ModelPart Joelho2;
    private final ModelPart Coxa2;
    private final ModelPart finger4;
    private final ModelPart Claw2;
    private final ModelPart ArmRight;
    private final ModelPart Arm1;
    private final ModelPart forearm;
    private final ModelPart finger;
    private final ModelPart ArmLeft;
    private final ModelPart Arm2;
    private final ModelPart forearm2;
    private final ModelPart finger2;
    private final ModelPart Tail;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;

    public Dinossauro1Model(ModelPart root) {
        this.Root      = root.getChild("Root");
        this.Corpo     = this.Root.getChild("Corpo");
        this.Corpo2    = this.Corpo.getChild("Corpo2");
        this.Head      = this.Root.getChild("Head");
        this.Eye       = this.Head.getChild("Eye");
        this.Eye2      = this.Head.getChild("Eye2");
        this.LegRigth  = this.Root.getChild("LegRigth");
        this.Base      = this.LegRigth.getChild("Base");
        this.Canela    = this.LegRigth.getChild("Canela");
        this.Joelho    = this.LegRigth.getChild("Joelho");
        this.Coxa      = this.LegRigth.getChild("Coxa");
        this.finger3   = this.LegRigth.getChild("finger3");
        this.Claw      = this.finger3.getChild("Claw");
        this.LegLeft   = this.Root.getChild("LegLeft");
        this.Base2     = this.LegLeft.getChild("Base2");
        this.Canela2   = this.LegLeft.getChild("Canela2");
        this.Joelho2   = this.LegLeft.getChild("Joelho2");
        this.Coxa2     = this.LegLeft.getChild("Coxa2");
        this.finger4   = this.LegLeft.getChild("finger4");
        this.Claw2     = this.finger4.getChild("Claw2");
        this.ArmRight  = this.Root.getChild("ArmRight");
        this.Arm1      = this.ArmRight.getChild("Arm1");
        this.forearm   = this.ArmRight.getChild("forearm");
        this.finger    = this.ArmRight.getChild("finger");
        this.ArmLeft   = this.Root.getChild("ArmLeft");
        this.Arm2      = this.ArmLeft.getChild("Arm2");
        this.forearm2  = this.ArmLeft.getChild("forearm2");
        this.finger2   = this.ArmLeft.getChild("finger2");
        this.Tail      = this.Root.getChild("Tail");
        this.tail1     = this.Tail.getChild("tail1");
        this.tail2     = this.Tail.getChild("tail2");
        this.tail3     = this.Tail.getChild("tail3");
        this.tail4     = this.Tail.getChild("tail4");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData partData = modelData.getRoot();

        ModelPartData Root = partData.addChild("Root",
                ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 0.5F, -16.8333F));

        ModelPartData Corpo = Root.addChild("Corpo", ModelPartBuilder.create()
                .uv(0, 0).cuboid(-11.0F, -8.0F, -9.0F, 22.0F, 19.0F, 17.0F, new Dilation(0.0F))
                .uv(0, 72).cuboid(-10.0F, -7.0F, 8.0F, 20.0F, 17.0F, 17.0F, new Dilation(0.0F)),
                ModelTransform.pivot(3.0F, -6.5F, 18.8333F));

        ModelPartData Corpo2 = Corpo.addChild("Corpo2", ModelPartBuilder.create()
                .uv(75, 37).cuboid(-8.0F, -4.157F, -16.7409F, 15.0F, 13.0F, 21.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -3.0F, -16.0F, -0.6981F, 0.0F, 0.0F));

        Corpo2.addChild("Corpo2_r1", ModelPartBuilder.create()
                .uv(0, 107).cuboid(-8.0F, -7.5F, -3.5F, 15.0F, 12.0F, 14.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 6.4819F, -24.0425F, 0.3491F, 0.0F, 0.0F));

        Corpo2.addChild("Corpo2_r2", ModelPartBuilder.create()
                .uv(0, 37).cuboid(-10.0F, -2.0F, -2.0F, 19.0F, 16.0F, 18.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -3.4691F, -3.14F, 0.3491F, 0.0F, 0.0F));

        ModelPartData Head = Root.addChild("Head", ModelPartBuilder.create()
                .uv(119, 169).cuboid(-6.4818F, -5.4366F, 9.6761F, 13.0F, 11.0F, 7.0F, new Dilation(0.7F))
                .uv(140, 121).cuboid(-6.4818F, -4.4366F, 1.6761F, 13.0F, 1.0F, 7.0F, new Dilation(0.7F))
                .uv(140, 103).cuboid(-6.4818F, -2.4366F, -6.3239F, 13.0F, 2.0F, 15.0F, new Dilation(0.7F))
                .uv(116, 133).cuboid(-6.4818F, 0.5634F, -6.3239F, 13.0F, 3.0F, 15.0F, new Dilation(0.7F))
                .uv(0, 134).cuboid(-6.9818F, 4.4634F, -5.8239F, 14.0F, 1.0F, 15.0F, new Dilation(0.2F)),
                ModelTransform.pivot(2.4818F, -22.0634F, -37.8427F));

        Head.addChild("head11_r1", ModelPartBuilder.create().uv(82, 165).cuboid(-7.0F, -0.5F, -0.5F, 14.0F, 1.0F, 2.0F, new Dilation(0.2F)), ModelTransform.of(0.0182F, -1.8707F, -7.1915F, -2.0071F, 0.0F, 0.0F));
        Head.addChild("head10_r1", ModelPartBuilder.create().uv(82, 161).cuboid(-7.0F, -0.5F, -0.5F, 14.0F, 1.0F, 2.0F, new Dilation(0.2F)), ModelTransform.of(0.0182F, 0.1326F, -7.9081F, -1.6581F, 0.0F, 0.0F));
        Head.addChild("head9_r1",  ModelPartBuilder.create().uv(148, 67).cuboid(-7.0F, -0.5F, -0.5F, 14.0F, 1.0F, 2.0F, new Dilation(0.2F)), ModelTransform.of(0.0182F, 2.0628F, -7.8094F, -1.0908F, 0.0F, 0.0F));
        Head.addChild("head8_r1",  ModelPartBuilder.create().uv(142, 96).cuboid(-7.0F, -0.5F, -0.5F, 14.0F, 1.0F, 2.0F, new Dilation(0.2F)), ModelTransform.of(0.0182F, 3.9371F, -6.6564F, -0.829F, 0.0F, 0.0F));
        Head.addChild("head6_r1",  ModelPartBuilder.create().uv(112, 30).cuboid(-7.5F, -1.0F, -1.5F, 13.0F, 1.0F, 3.0F, new Dilation(0.6F)), ModelTransform.of(0.9182F, -4.12F, 7.2721F, 0.2182F, 0.0F, 0.0F));
        Head.addChild("head5_r1",  ModelPartBuilder.create().uv(79, 30).cuboid(-7.5F, -1.0F, -1.5F, 13.0F, 1.0F, 3.0F, new Dilation(0.6F)), ModelTransform.of(0.9182F, -2.701F, -0.0426F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Eye = Head.addChild("Eye", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Eye.addChild("eye2_r1", ModelPartBuilder.create().uv(181, 121).cuboid(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -1.0F, 9.0F, 0.3927F, 0.0F, 0.0F));
        Eye.addChild("eye_r1",  ModelPartBuilder.create().uv(179, 173).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 2.0F, 5.0F, new Dilation(0.2F)), ModelTransform.of(8.0F, -0.4F, 9.9946F, 0.3927F, 0.0F, 0.0F));
        Eye.addChild("eye1_r1", ModelPartBuilder.create().uv(179, 165).cuboid(-3.0F, -2.0F, -1.4163F, 4.0F, 2.0F, 5.0F, new Dilation(0.2F)), ModelTransform.of(7.0F, -4.6824F, 7.3923F, 0.3927F, 0.0F, 0.0F));

        ModelPartData Eye2 = Head.addChild("Eye2", ModelPartBuilder.create(), ModelTransform.pivot(-14.0F, 0.0F, -1.0F));
        Eye2.addChild("eye3_r1", ModelPartBuilder.create().uv(181, 121).cuboid(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, -1.0F, 9.0F, 0.3927F, 0.0F, 0.0F));
        Eye2.addChild("eye_r2",  ModelPartBuilder.create().uv(179, 173).cuboid(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 5.0F, new Dilation(0.2F)), ModelTransform.of(8.0F, -0.4F, 8.9946F, 0.3927F, 0.0F, 0.0F));
        Eye2.addChild("eye2_r2", ModelPartBuilder.create().uv(179, 165).cuboid(-3.0F, -2.0F, -2.4163F, 4.0F, 2.0F, 5.0F, new Dilation(0.2F)), ModelTransform.of(9.0F, -4.6824F, 8.3923F, 0.3927F, 0.0F, 0.0F));

        // ── LEG RIGHT ────────────────────────────────────────────────────────
        ModelPartData LegRigth = Root.addChild("LegRigth", ModelPartBuilder.create(), ModelTransform.pivot(18.0F, 21.0F, 17.3333F));
        LegRigth.addChild("Base", ModelPartBuilder.create().uv(148, 50).cuboid(-5.0F, -0.5F, 0.0F, 13.0F, 3.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Canela = LegRigth.addChild("Canela", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Canela.addChild("canela1_r1", ModelPartBuilder.create().uv(45, 161).cuboid(-2.0F, -12.0F, -6.0F, 9.0F, 14.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -0.5F, 8.0F, -0.3054F, 0.0F, 0.0F));

        ModelPartData Joelho = LegRigth.addChild("Joelho", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Joelho.addChild("joelho_r1", ModelPartBuilder.create().uv(142, 72).cuboid(-3.0F, -5.0F, -6.0F, 11.0F, 9.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -13.263F, 6.0427F, -0.3054F, 0.0F, 0.0F));

        ModelPartData Coxa = LegRigth.addChild("Coxa", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Coxa.addChild("cube_r1", ModelPartBuilder.create().uv(148, 24).cuboid(-5.0F, -14.0F, -5.0F, 13.0F, 16.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -15.0F, -2.0F, -0.5236F, 0.0F, 0.0F));

        ModelPartData finger3 = LegRigth.addChild("finger3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        finger3.addChild("finger3_r1", ModelPartBuilder.create().uv(184, 96).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(7.1794F, 1.3026F, -0.4511F, -0.3054F, -0.3927F, 0.0F));
        finger3.addChild("finger3_r2", ModelPartBuilder.create().uv(175, 96).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(3.1794F, 1.3026F, -0.4511F, -0.3054F, 0.0F, 0.0F));
        finger3.addChild("finger3_r3", ModelPartBuilder.create().uv(45, 151).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-0.8206F, 1.3026F, -0.4511F, -0.3054F, 0.0F, 0.0F));
        finger3.addChild("finger3_r4", ModelPartBuilder.create().uv(59, 127).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-4.8206F, 1.3026F, -0.4511F, -0.3054F, 0.3927F, 0.0F));

        ModelPartData Claw = finger3.addChild("Claw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Claw.addChild("claw2_r1", ModelPartBuilder.create().uv(0, 186).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(-5.2786F, 0.2862F, -1.7374F, 1.8762F, 0.3927F, 0.0F));
        Claw.addChild("claw3_r1", ModelPartBuilder.create().uv(18, 186).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(7.6374F, 0.2862F, -1.7374F, 1.8762F, -0.3927F, 0.0F));
        Claw.addChild("claw2_r2", ModelPartBuilder.create().uv(9, 186).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(3.1294F, 0.2862F, -1.8156F, 1.8762F, 0.0F, 0.0F));
        Claw.addChild("claw1_r1", ModelPartBuilder.create().uv(68, 185).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(-0.8706F, 0.2862F, -1.8156F, 1.8762F, 0.0F, 0.0F));

        // ── LEG LEFT ─────────────────────────────────────────────────────────
        ModelPartData LegLeft = Root.addChild("LegLeft", ModelPartBuilder.create(), ModelTransform.pivot(-14.0F, 21.0F, 17.3333F));
        LegLeft.addChild("Base2", ModelPartBuilder.create().uv(116, 152).cuboid(-5.0F, -0.5F, 0.0F, 13.0F, 3.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Canela2 = LegLeft.addChild("Canela2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Canela2.addChild("canela2_r1", ModelPartBuilder.create().uv(82, 169).cuboid(-2.0F, -12.0F, -6.0F, 9.0F, 14.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -0.5F, 8.0F, -0.3054F, 0.0F, 0.0F));

        ModelPartData Joelho2 = LegLeft.addChild("Joelho2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Joelho2.addChild("joelho_r2", ModelPartBuilder.create().uv(144, 0).cuboid(-3.0F, -2.0F, -6.0F, 11.0F, 9.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -16.263F, 7.0427F, -0.3054F, 0.0F, 0.0F));

        ModelPartData Coxa2 = LegLeft.addChild("Coxa2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Coxa2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 151).cuboid(-5.0F, -14.0F, -5.0F, 13.0F, 16.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -15.0F, -2.0F, -0.5236F, 0.0F, 0.0F));

        ModelPartData finger4 = LegLeft.addChild("finger4", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 0.0F, 0.0F));
        finger4.addChild("finger4_r1", ModelPartBuilder.create().uv(137, 188).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(7.1794F, 1.3026F, -0.4511F, -0.3054F, -0.3927F, 0.0F));
        finger4.addChild("finger4_r2", ModelPartBuilder.create().uv(128, 188).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(3.1794F, 1.3026F, -0.4511F, -0.3054F, 0.0F, 0.0F));
        finger4.addChild("finger4_r3", ModelPartBuilder.create().uv(119, 188).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-0.8206F, 1.3026F, -0.4511F, -0.3054F, 0.0F, 0.0F));
        finger4.addChild("finger4_r4", ModelPartBuilder.create().uv(27, 186).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.3F)), ModelTransform.of(-4.8206F, 1.3026F, -0.4511F, -0.3054F, 0.3927F, 0.0F));

        ModelPartData Claw2 = finger4.addChild("Claw2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        Claw2.addChild("claw3_r2", ModelPartBuilder.create().uv(68, 191).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(-5.2786F, 0.2862F, -1.7374F, 1.8762F, 0.3927F, 0.0F));
        Claw2.addChild("claw4_r1", ModelPartBuilder.create().uv(188, 154).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(7.6374F, 0.2862F, -1.7374F, 1.8762F, -0.3927F, 0.0F));
        Claw2.addChild("claw3_r3", ModelPartBuilder.create().uv(188, 148).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(3.1294F, 0.2862F, -1.8156F, 1.8762F, 0.0F, 0.0F));
        Claw2.addChild("claw2_r3", ModelPartBuilder.create().uv(146, 188).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.25F)), ModelTransform.of(-0.8706F, 0.2862F, -1.8156F, 1.8762F, 0.0F, 0.0F));

        // ── ARM RIGHT ────────────────────────────────────────────────────────
        ModelPartData ArmRight = Root.addChild("ArmRight", ModelPartBuilder.create(), ModelTransform.pivot(10.0F, -5.0F, -8.6667F));
        ModelPartData Arm1 = ArmRight.addChild("Arm1", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, 0.0F, 0.0F));
        Arm1.addChild("Arm1_r1", ModelPartBuilder.create().uv(169, 152).cuboid(-3.0F, -2.0F, -1.0F, 5.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 3.2958F, 10.6568F, -0.3927F, 0.0F, 0.0F));

        ModelPartData forearm = ArmRight.addChild("forearm", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 0.0F, 0.0F));
        forearm.addChild("ForeArm_r1", ModelPartBuilder.create().uv(173, 139).cuboid(-2.0F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 11.3496F, 11.8594F, 1.0472F, 0.0F, 0.0F));
        forearm.addChild("ForeArm_r2", ModelPartBuilder.create().uv(173, 130).cuboid(-2.0F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 9.4238F, 10.0947F, 0.4363F, 0.0F, 0.0F));

        ModelPartData finger = ArmRight.addChild("finger", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 0.0F, 0.0F));
        finger.addChild("finger3_r5", ModelPartBuilder.create()
                .uv(179, 181).cuboid(0.0F, -2.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(59, 117).cuboid(2.0F, -2.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(59, 107).cuboid(4.0F, -2.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(5.0F, 12.3392F, 15.2304F, 0.2618F, 0.0F, 0.0F));

        // ── ARM LEFT ─────────────────────────────────────────────────────────
        ModelPartData ArmLeft = Root.addChild("ArmLeft", ModelPartBuilder.create(), ModelTransform.pivot(-12.0F, 3.0F, 4.3333F));
        ModelPartData Arm2 = ArmLeft.addChild("Arm2", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, -8.0F, -13.0F));
        Arm2.addChild("Arm2_r1", ModelPartBuilder.create().uv(160, 169).cuboid(-2.5F, -4.0F, -2.0F, 5.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, 5.5262F, 10.8153F, -0.3927F, 0.0F, 0.0F));

        ModelPartData forearm2 = ArmLeft.addChild("forearm2", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -8.0F, -13.0F));
        forearm2.addChild("ForeArm_r3", ModelPartBuilder.create().uv(19, 177).cuboid(-2.0F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 11.3496F, 11.8594F, 1.0472F, 0.0F, 0.0F));
        forearm2.addChild("ForeArm_r4", ModelPartBuilder.create().uv(0, 177).cuboid(-2.0F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 9.4238F, 10.0947F, 0.4363F, 0.0F, 0.0F));

        ModelPartData finger2 = ArmLeft.addChild("finger2", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -8.0F, -13.0F));
        finger2.addChild("finger4_r5", ModelPartBuilder.create()
                .uv(53, 185).cuboid(0.0F, -2.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(38, 185).cuboid(2.0F, -2.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(160, 182).cuboid(4.0F, -2.0F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(5.0F, 12.3392F, 15.2304F, 0.2618F, 0.0F, 0.0F));

        // ── TAIL ─────────────────────────────────────────────────────────────
        ModelPartData Tail = Root.addChild("Tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -5.0F, 50.0F));
        Tail.addChild("tail1", ModelPartBuilder.create().uv(75, 72).cuboid(-6.0F, -7.0F, -7.0F, 18.0F, 15.0F, 15.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 0.8333F));
        Tail.addChild("tail2", ModelPartBuilder.create().uv(79, 0).cuboid(-5.0F, -6.0F, -7.0F, 16.0F, 13.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 15.8333F));
        Tail.addChild("tail3", ModelPartBuilder.create().uv(75, 103).cuboid(-4.0F, -5.0F, -7.0F, 14.0F, 11.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 31.8333F));
        Tail.addChild("tail4", ModelPartBuilder.create().uv(59, 133).cuboid(-2.0F, -4.0F, -7.0F, 10.0F, 9.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, 49.8333F));

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
