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

    private final HairStyle1Model<T> style1;
    private final HairGokuModel<T>   goku;
    private final HairStyle3Model<T> style3;

    public HairLayer(FeatureRendererContext<T, M> context,
                     ModelPart style1Root, ModelPart gokuRoot, ModelPart style3Root) {
        super(context);
        this.style1 = new HairStyle1Model<>(style1Root);
        this.goku   = new HairGokuModel<>(gokuRoot);
        this.style3 = new HairStyle3Model<>(style3Root);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, T entity, float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress, float headYaw, float headPitch) {

        Identifier hairTex = DBIPlayerData.getHairTexture(entity);
        if (hairTex == null) return;

        int col = DBIPlayerData.getHairColor(entity);
        float r = ((col >> 16) & 0xFF) / 255f;
        float g = ((col >>  8) & 0xFF) / 255f;
        float b = (col         & 0xFF) / 255f;

        String path = hairTex.getPath();

        if (path.contains("goku")) {
            this.getContextModel().head.copyTransform(this.goku.head);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.goku.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        } else if (path.contains("hair3")) {
            this.getContextModel().head.copyTransform(this.style3.hair);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.style3.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        } else {
            this.getContextModel().head.copyTransform(this.style1.hair);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.style1.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        }
                       }
}
