package com.bernardo.dbi.mixin;

import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEyeHeightMixin {

    @Inject(method = "getEyeHeight(Lnet/minecraft/entity/EntityPose;Lnet/minecraft/entity/EntityDimensions;)F", at = @At("RETURN"), cancellable = true)
    private void dbi_applyPlayerEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> cir) {
        if (!((Object) this instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity)(Object) this;
        float eyeHeightScale = DBIPlayerData.getEyeHeightScale(player);
        if (eyeHeightScale == 1.0f) return;

        cir.setReturnValue(cir.getReturnValue() * eyeHeightScale);
    }
}
