package com.bernardo.dbi.mixin;

import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntitySizeMixin {

    // Dimensões base do player: 0.6 largura, 1.8 altura
    private static final float BASE_WIDTH  = 0.6f;
    private static final float BASE_HEIGHT = 1.8f;

    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void dbi_applySize(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if (pose != EntityPose.STANDING) return;
        PlayerEntity self = (PlayerEntity)(Object) this;
        float scale = DBIPlayerData.getFinalSize(self);
        if (scale == 1.0f) return;
        cir.setReturnValue(EntityDimensions.changing(
            BASE_WIDTH  * scale,
            BASE_HEIGHT * scale
        ));
    }
}
