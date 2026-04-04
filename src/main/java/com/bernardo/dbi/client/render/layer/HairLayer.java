package com.bernardo.dbi.client.render.layer;

import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HairLayer<T extends AbstractClientPlayerEntity, M extends PlayerEntityModel<T>>
        extends FeatureRenderer<T, M> {

    private final HairVegetaModel<T> vegeta;
    private final HairGokuModel<T>   goku;
    private final HairTrunksModel<T> trunks;

    public HairLayer(FeatureRendererContext<T, M> context,
                     ModelPart vegetaRoot,
                     ModelPart gokuRoot,
                     ModelPart trunksRoot) {
        super(context);
        this.vegeta = new HairVegetaModel<>(vegetaRoot);
        this.goku   = new HairGokuModel<>(gokuRoot);
        this.trunks = new HairTrunksModel<>(trunksRoot);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, T entity, float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress, float headYaw, float headPitch) {

        Identifier hairTex = DBIPlayerData.getHairTexture(entity);
        if (hairTex == null) return;

        String path = hairTex.getPath();

        int col = DBIPlayerData.getHairColor(entity);
        float r = ((col >> 16) & 0xFF) / 255f;
        float g = ((col >>  8) & 0xFF) / 255f;
        float b = (col         & 0xFF) / 255f;

        if (path.contains("vegeta")) {
            this.getContextModel().head.copyTransform(this.vegeta.head);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.vegeta.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        } else if (path.contains("goku")) {
            this.getContextModel().head.copyTransform(this.goku.head);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.goku.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        } else if (path.contains("trunks")) {
            this.getContextModel().head.copyTransform(this.trunks.head);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.trunks.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        }
    }
}
