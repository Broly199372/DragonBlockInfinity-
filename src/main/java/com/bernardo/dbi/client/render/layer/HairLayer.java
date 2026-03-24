package com.bernardo.dbi.client.render.layer;

import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class HairLayer extends FeatureRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>> {

    public HairLayer(FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> ctx) {
        super(ctx);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, PlayerEntity player,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {

        Identifier texture = DBIPlayerData.getHairTexture(player);
        if (texture == null) return;

        var vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(texture));
        getContextModel().render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
    }
}
