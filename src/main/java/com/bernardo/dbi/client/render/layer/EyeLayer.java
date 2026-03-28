package com.bernardo.dbi.client.render.layer;

import com.bernardo.dbi.client.render.DBIPlayerModel;
import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EyeLayer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    // Olhos são 128x128
    private final DBIPlayerModel<AbstractClientPlayerEntity> eyeModel;

    public EyeLayer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> ctx) {
        super(ctx);
        this.eyeModel = new DBIPlayerModel<>(
            DBIPlayerModel.getTexturedModelData().createModel()
        );
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, AbstractClientPlayerEntity player,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {

        Identifier texture = DBIPlayerData.getEyeTexture(player);
        if (texture == null) return;

        // Sincroniza o modelo com as animações do player
        eyeModel.setAngles(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        eyeModel.handSwingProgress = getContextModel().handSwingProgress;
        eyeModel.riding = getContextModel().riding;
        eyeModel.child = getContextModel().child;

        var vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
        eyeModel.head.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
    }
}
