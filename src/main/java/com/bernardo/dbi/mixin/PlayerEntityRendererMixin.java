package com.bernardo.dbi.mixin;

import com.bernardo.dbi.client.render.layer.EyeLayer;
import com.bernardo.dbi.client.render.layer.HairLayer;
import com.bernardo.dbi.client.render.layer.MouthLayer;
import com.bernardo.dbi.client.render.layer.NoseLayer;
import com.bernardo.dbi.client.render.layer.RaceBodyLayer;
import com.bernardo.dbi.player.DBIPlayerData;
import com.bernardo.dbi.player.Race;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
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
                                     PlayerEntityModel<AbstractClientPlayerEntity> model,
                                     float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void dbi_addLayers(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        var context = (net.minecraft.client.render.entity.feature.FeatureRendererContext) this;
        this.addFeature(new RaceBodyLayer(context));
        this.addFeature(new HairLayer(context));
        this.addFeature(new EyeLayer(context));
        this.addFeature(new NoseLayer(context));
        this.addFeature(new MouthLayer(context));
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void dbi_getRaceTexture(AbstractClientPlayerEntity player, CallbackInfoReturnable<Identifier> cir) {
        Race race = DBIPlayerData.getRace(player);
        Identifier raceTexture = switch (race) {
            case NAMEKIAN -> new Identifier("dragonblockinfinity", "textures/races/namekian.png");
            case ARCOSIAN -> new Identifier("dragonblockinfinity", "textures/races/arconsian.png");
            default -> new Identifier("dragonblockinfinity", "textures/races/base.png");
        };

        // Se for custom, substitui a textura base do player (esquece a skin antiga)
        cir.setReturnValue(raceTexture);
    }
}

