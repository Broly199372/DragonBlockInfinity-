package com.bernardo.dbi.mixin;

import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin para PlayerEntity que ajusta as dimensões físicas do jogador baseado na escala de tamanho DBI.
 * Isso afeta a largura e altura do hitbox do jogador, permitindo tamanhos personalizados.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntitySizeMixin {

    // Dimensões padrão do Minecraft para
    /**
     * Injeta no método getDimensions para modificar o tamanho do jogador.
     * Aplica a escala de tamanho DBI a todas as poses para consistência.
     * Se a escala for 1.0 (padrão), não modifica nada para otimização.
     */
    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void dbi_applySize(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        PlayerEntity self = (PlayerEntity)(Object) this;
        float sizeScale = DBIPlayerData.getFinalSize(self);

        // Validação: escala deve ser positiva e diferente de 1.0
        if (sizeScale <= 0.0f || sizeScale == 1.0f) return;

        // Obtém as dimensões originais
        EntityDimensions original = cir.getReturnValue();

        // Aplica a escala mantendo as proporções
        cir.setReturnValue(EntityDimensions.changing(
            original.width * sizeScale,
            original.height * sizeScale
        ));
    }

}
