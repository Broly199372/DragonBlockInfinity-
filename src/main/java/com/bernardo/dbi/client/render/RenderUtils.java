package com.bernardo.dbi.client.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

/**
 * Utilitários para melhorar a renderização de texturas de customização
 */
public class RenderUtils {

    public enum FeatureType {
        HAIR, NOSE, EYES, MOUTH, RACE_BODY
    }

    /**
     * Renderiza uma textura com melhor controle de transparência e blending
     */
    public static void renderCustomTexture(MatrixStack matrices, VertexConsumer vertexConsumer,
                                         int light, int overlay, float alpha, Runnable renderFunction) {
        matrices.push();
        renderFunction.run();
        matrices.pop();
    }

    /**
     * Cria um RenderLayer otimizado para texturas de customização
     */
    public static RenderLayer getCustomTextureLayer(Identifier texture) {
        return RenderLayer.getEntityTranslucent(texture);
    }

    /**
     * Ajusta a posição Z para layering correto das texturas faciais
     */
    public static float getFaceLayerOffset(FeatureType featureType) {
        return switch (featureType) {
            case HAIR -> 0.0f;
            case NOSE -> 0.02f;
            case EYES -> 0.01f;
            case MOUTH -> 0.025f;
            case RACE_BODY -> 0.0f;
        };
    }

    /**
     * Ajusta a posição Y para melhor alinhamento facial
     */
    public static float getFaceVerticalOffset(FeatureType featureType) {
        return switch (featureType) {
            case HAIR -> -0.25f;
            case NOSE -> -0.0625f;
            case EYES -> -0.125f;
            case MOUTH -> 0.0f;
            case RACE_BODY -> 0.0f;
        };
    }

    /**
     * Retorna o alpha ideal para cada tipo de feature
     */
    public static float getFeatureAlpha(FeatureType featureType) {
        return switch (featureType) {
            case HAIR -> 0.9f;
            case NOSE -> 0.9f;
            case EYES -> 0.95f;
            case MOUTH -> 0.85f;
            case RACE_BODY -> 0.95f;
        };
    }

    /**
     * Valor de pulso para animação (breathe/pulsar) com base em tempo.
     */
    public static float getPulse(float tick) {
        return 1.0f + 0.04f * (float) Math.sin(tick * 0.08f);
    }

    /**
     * Pequeno desvio oscilante para cabelo
     */
    public static float getHairSway(float tick) {
        return 0.0f + 0.015f * (float) Math.sin(tick * 0.12f);
    }

    /**
     * Escala de brilho para olhos, dá efeito “vivo”
     */
    public static float getEyeGlow(float tick) {
        return 0.8f + 0.2f * (float) (0.5f + 0.5f * Math.sin(tick * 0.15f));
    }

    /**
     * Normaliza uma cor para [0,1] pelo inteiro RGB
     */
    public static float[] rgb(int color) {
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;
        return new float[] { r, g, b };
    }
}