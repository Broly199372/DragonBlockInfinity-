package com.bernardo.dbi.mixin;

import com.bernardo.dbi.player.DBIPlayerData;
import com.bernardo.dbi.player.Race;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin para PlayerEntityRenderer que adiciona camadas de renderização customizadas
 * e ajusta a textura e escala do jogador baseado nos dados DBI.
 */
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx,
                                     PlayerEntityModel<AbstractClientPlayerEntity> model,
                                     float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void dbi_addLayers(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        // Removido: camada RaceBodyLayer conforme pedido
    }

    /**
     * Sobrescreve a textura do jogador baseada na raça DBI.
     * Isso substitui a skin padrão do Minecraft por texturas customizadas.
     */
    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void dbi_getRaceTexture(AbstractClientPlayerEntity player, CallbackInfoReturnable<Identifier> cir) {
        Race race = DBIPlayerData.getRace(player);
        Identifier raceTexture = switch (race) {
            case NAMEKIAN -> new Identifier("dragonblockinfinity", "textures/races/namekian.png");
            case ARCOSIAN -> new Identifier("dragonblockinfinity", "textures/races/arconsian.png");
            default -> new Identifier("dragonblockinfinity", "textures/races/base.png");
        };
        cir.setReturnValue(raceTexture);
    }

    /**
     * Aplica escala ao modelo do jogador baseada no tamanho DBI.
     * Isso garante que a renderização visual corresponda ao hitbox ajustado.
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void dbi_applySizeScale(AbstractClientPlayerEntity player, float yaw, float tickDelta, MatrixStack matrices, net.minecraft.client.render.VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        float sizeScale = DBIPlayerData.getFinalSize(player);
        if (sizeScale != 1.0f) {
            matrices.scale(sizeScale, sizeScale, sizeScale);
        }
    }
}

