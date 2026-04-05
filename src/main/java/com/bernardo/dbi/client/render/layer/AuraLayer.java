package com.bernardo.dbi.client.render.layer;

import com.bernardo.dbi.client.render.RenderUtils;
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
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class AuraLayer<T extends AbstractClientPlayerEntity, M extends PlayerEntityModel<T>>
        extends FeatureRenderer<T, M> {

    private static final Identifier AURA_TEXTURE = new Identifier("dragonblockinfinity", "textures/fx/aura/ki_aura_base.png");
    private static final int SEGMENTS = 24; // Segmentos ao redor do cilindro
    private static final float AURA_RADIUS = 0.5f;
    private static final float AURA_HEIGHT = 2.2f;

    public AuraLayer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, T entity,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {
        float pulse = RenderUtils.getPulse(animationProgress * 10f) * 1.05f;
        float[] rgb = RenderUtils.rgb(0xA4D8FF);
        float alpha = 0.25f + 0.15f * (float) Math.sin(animationProgress * 0.18f);
        alpha = Math.max(0.15f, Math.min(0.55f, alpha));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(AURA_TEXTURE));

        matrices.push();
        matrices.translate(0.0f, 0.1f, 0.0f);

        drawAuraCylinder(matrices, vertexConsumer, light, pulse, rgb[0], rgb[1], rgb[2], alpha, animationProgress);

        matrices.pop();
    }

    private static void drawAuraCylinder(MatrixStack matrices, VertexConsumer consumer,
                                         int light, float scale, float red, float green, float blue,
                                         float alpha, float animationProgress) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f position = entry.getPositionMatrix();
        Matrix3f normal = entry.getNormalMatrix();

        float radius = AURA_RADIUS * scale;
        float height = AURA_HEIGHT;
        float segmentSin, segmentCos;
        float nextSegmentSin, nextSegmentCos;

        // Desenha cilindro em espiral com múltiplas camadas para cobrir tudo
        for (int layer = 0; layer < 4; layer++) {
            float yOffset = (layer / 4.0f) * height - height / 2.0f;
            float layerRadius = radius * (1.0f - layer * 0.15f);
            float layerAlpha = alpha * (1.0f - layer * 0.2f);

            for (int i = 0; i < SEGMENTS; i++) {
                float angle1 = (float) Math.PI * 2f * i / SEGMENTS + animationProgress * 0.05f;
                float angle2 = (float) Math.PI * 2f * ((i + 1) % SEGMENTS) / SEGMENTS + animationProgress * 0.05f;

                segmentSin = (float) Math.sin(angle1);
                segmentCos = (float) Math.cos(angle1);
                nextSegmentSin = (float) Math.sin(angle2);
                nextSegmentCos = (float) Math.cos(angle2);

                float x1 = segmentCos * layerRadius;
                float z1 = segmentSin * layerRadius;
                float x2 = nextSegmentCos * layerRadius;
                float z2 = nextSegmentSin * layerRadius;

                float uStart = i / (float) SEGMENTS;
                float uEnd = (i + 1) / (float) SEGMENTS;

                // Quad vertical
                consumer.vertex(position, x1, yOffset, z1)
                        .color(red, green, blue, layerAlpha)
                        .texture(uStart, layer / 4.0f)
                        .overlay(OverlayTexture.DEFAULT_UV)
                        .light(light)
                        .normal(normal, segmentCos, 0f, segmentSin)
                        .next();

                consumer.vertex(position, x2, yOffset, z2)
                        .color(red, green, blue, layerAlpha)
                        .texture(uEnd, layer / 4.0f)
                        .overlay(OverlayTexture.DEFAULT_UV)
                        .light(light)
                        .normal(normal, nextSegmentCos, 0f, nextSegmentSin)
                        .next();

                consumer.vertex(position, x2, yOffset + height / 4.0f, z2)
                        .color(red, green, blue, layerAlpha)
                        .texture(uEnd, (layer + 1) / 4.0f)
                        .overlay(OverlayTexture.DEFAULT_UV)
                        .light(light)
                        .normal(normal, nextSegmentCos, 0f, nextSegmentSin)
                        .next();

                consumer.vertex(position, x1, yOffset + height / 4.0f, z1)
                        .color(red, green, blue, layerAlpha)
                        .texture(uStart, (layer + 1) / 4.0f)
                        .overlay(OverlayTexture.DEFAULT_UV)
                        .light(light)
                        .normal(normal, segmentCos, 0f, segmentSin)
                        .next();
            }
        }
    }
}
