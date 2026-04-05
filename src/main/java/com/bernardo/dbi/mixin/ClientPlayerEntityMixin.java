package com.bernardo.dbi.mixin;

import com.bernardo.dbi.entity.FlyNinbus;
import com.bernardo.dbi.entity.DarkFlyNinbus;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin para ClientPlayerEntity que adiciona controles de movimento para as nuvens voadoras.
 */
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void dbi_handleNimbusMovement(CallbackInfo ci) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (player.getVehicle() instanceof FlyNinbus || player.getVehicle() instanceof DarkFlyNinbus) {
            // Movement is handled by the entity itself, but we can add client-side effects here if needed
            // The entity tick method handles the actual movement based on player input
        }
    }
}