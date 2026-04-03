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

    private final HairStyle1Model<T> style1Model;
    private final HairGokuModel<T>   gokuModel;

    public HairLayer(FeatureRendererContext<T, M> context,
                     ModelPart style1Root, ModelPart gokuRoot) {
        super(context);
        this.style1Model = new HairStyle1Model<>(style1Root);
        this.gokuModel   = new HairGokuModel<>(gokuRoot);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, T entity, float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress, float headYaw, float headPitch) {

        Identifier hairTex = DBIPlayerData.getHairTexture(entity);
        if (hairTex == null) return;

        int hairColorInt = DBIPlayerData.getHairColor(entity);
        float r = ((hairColorInt >> 16) & 0xFF) / 255f;
        float g = ((hairColorInt >> 8)  & 0xFF) / 255f;
        float b = (hairColorInt         & 0xFF) / 255f;

        String path = hairTex.getPath();

        if (path.contains("goku")) {
            this.getContextModel().head.copyTransform(this.gokuModel.head);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.gokuModel.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        } else {
            this.getContextModel().head.copyTransform(this.style1Model.hair);
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(hairTex));
            this.style1Model.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, r, g, b, 1f);
        }
    }
}
