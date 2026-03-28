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

public class NoseLayer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private final DBIPlayerModel<AbstractClientPlayerEntity> noseModel;

    public NoseLayer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> ctx) {
        super(ctx);
        this.noseModel = new DBIPlayerModel<>(
            DBIPlayerModel.getTexturedModelData().createModel()
        );
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, AbstractClientPlayerEntity player,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {

        Identifier texture = DBIPlayerData.getNoseTexture(player);
        if (texture == null) return;

        // Sincroniza o modelo com as animações do player
        noseModel.setAngles(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        noseModel.handSwingProgress = getContextModel().handSwingProgress;
        noseModel.riding = getContextModel().riding;
        noseModel.child = getContextModel().child;

        var vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
        noseModel.head.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
    }
}
