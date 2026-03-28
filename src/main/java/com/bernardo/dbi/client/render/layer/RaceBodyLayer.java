package com.bernardo.dbi.client.render.layer;

import com.bernardo.dbi.client.render.DBIPlayerModel;
import com.bernardo.dbi.player.DBIPlayerData;
import com.bernardo.dbi.player.Race;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RaceBodyLayer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private final DBIPlayerModel<AbstractClientPlayerEntity> raceModel;

    private static final Identifier RACE_SAIYAN      = new Identifier("dragonblockinfinity", "textures/races/base.png");
    private static final Identifier RACE_HALF_SAIYAN = new Identifier("dragonblockinfinity", "textures/races/base.png");
    private static final Identifier RACE_NAMEKIAN    = new Identifier("dragonblockinfinity", "textures/races/namekian.png");
    private static final Identifier RACE_ARCOSIAN    = new Identifier("dragonblockinfinity", "textures/races/arconsian.png");

    public RaceBodyLayer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> ctx) {
        super(ctx);
        this.raceModel = new DBIPlayerModel<>(
            DBIPlayerModel.getTexturedModelData().createModel()
        );
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, AbstractClientPlayerEntity player,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {

        Race race = DBIPlayerData.getRace(player);

        Identifier texture = switch (race) {
            case NAMEKIAN    -> RACE_NAMEKIAN;
            case ARCOSIAN    -> RACE_ARCOSIAN;
            default          -> RACE_SAIYAN;
        };

        // Sincroniza pose do modelo de raça com o modelo do player
        raceModel.setAngles(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        raceModel.handSwingProgress = getContextModel().handSwingProgress;
        raceModel.riding             = getContextModel().riding;
        raceModel.child              = getContextModel().child;

        var vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
        raceModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
    }
}
