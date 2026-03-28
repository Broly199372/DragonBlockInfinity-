package com.bernardo.dbi.client.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class DBIPlayerModel<T extends LivingEntity> extends BipedEntityModel<T> {

    // Usando tamanho padrão do Minecraft (64x64)
    // As camadas (hair, eyes, etc) serão texturas 2D customizadas
    public static final int TEX_W = 64;
    public static final int TEX_H = 64;

    public DBIPlayerModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        // HEAD — tamanho e UV padrão
        ModelPartData head = root.addChild(EntityModelPartNames.HEAD,
            ModelPartBuilder.create()
                .uv(0, 0).cuboid(-4, -8, -4, 8, 8, 8, new Dilation(0f)),
            ModelTransform.pivot(0, 0, 0));

        // HAT — com overlay
        root.addChild(EntityModelPartNames.HAT,
            ModelPartBuilder.create()
                .uv(32, 0).cuboid(-4, -8, -4, 8, 8, 8, new Dilation(0.5f)),
            ModelTransform.pivot(0, 0, 0));

        // BODY — torso
        root.addChild(EntityModelPartNames.BODY,
            ModelPartBuilder.create()
                .uv(16, 16).cuboid(-4, 0, -2, 8, 12, 4, new Dilation(0f)),
            ModelTransform.pivot(0, 0, 0));

        // RIGHT_ARM — braço direito
        root.addChild(EntityModelPartNames.RIGHT_ARM,
            ModelPartBuilder.create()
                .uv(40, 16).cuboid(-3, -2, -2, 4, 12, 4, new Dilation(0f)),
            ModelTransform.pivot(-5, 2, 0));

        // LEFT_ARM — braço esquerdo
        root.addChild(EntityModelPartNames.LEFT_ARM,
            ModelPartBuilder.create()
                .uv(32, 48).cuboid(-1, -2, -2, 4, 12, 4, new Dilation(0f)),
            ModelTransform.pivot(5, 2, 0));

        // RIGHT_LEG — perna direita
        root.addChild(EntityModelPartNames.RIGHT_LEG,
            ModelPartBuilder.create()
                .uv(0, 16).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0f)),
            ModelTransform.pivot(-1.9f, 12, 0));

        // LEFT_LEG — perna esquerda
        root.addChild(EntityModelPartNames.LEFT_LEG,
            ModelPartBuilder.create()
                .uv(16, 48).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0f)),
            ModelTransform.pivot(1.9f, 12, 0));

        return TexturedModelData.of(modelData, TEX_W, TEX_H);
    }
}
