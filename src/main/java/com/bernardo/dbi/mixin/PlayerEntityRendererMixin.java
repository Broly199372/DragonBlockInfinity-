package com.bernardo.dbi.mixin;

import com.bernardo.dbi.client.render.layer.HairGokuModel;
import com.bernardo.dbi.client.render.layer.HairLayer;
import com.bernardo.dbi.client.render.layer.HairStyle1Model;
import com.bernardo.dbi.client.render.layer.HairStyle3Model;
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

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin
        extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx,
        PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void dbi_addLayers(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        this.addFeature(new HairLayer<>(
            this,
            ctx.getPart(HairStyle1Model.LAYER),
            ctx.getPart(HairGokuModel.LAYER),
            ctx.getPart(HairStyle3Model.LAYER)
        ));
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void dbi_getRaceTexture(AbstractClientPlayerEntity player,
                                    CallbackInfoReturnable<Identifier> cir) {
        Race race = DBIPlayerData.getRace(player);
        switch (race) {
            case NAMEKIAN ->
                cir.setReturnValue(new Identifier("dragonblockinfinity", "textures/races/namekian.png"));
            case ARCOSIAN ->
                cir.setReturnValue(new Identifier("dragonblockinfinity", "textures/races/arconsian.png"));
            default -> {}
        }
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void dbi_applySizeScale(AbstractClientPlayerEntity player, float yaw, float tickDelta,
                                    MatrixStack matrices,
                                    net.minecraft.client.render.VertexConsumerProvider vertexConsumers,
                                    int light, CallbackInfo ci) {
        float sizeScale = DBIPlayerData.getFinalSize(player);
        if (sizeScale != 1.0f) {
            matrices.scale(sizeScale, sizeScale, sizeScale);
        }
    }
}
