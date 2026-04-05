package com.bernardo.dbi.mixin;

import com.bernardo.dbi.entity.FlyNinbus;
import com.bernardo.dbi.entity.DarkFlyNinbus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin para KeyboardInput que adiciona funcionalidade de desmontar da nuvem com Ctrl+Left.
 */
@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin extends Input {

    @Inject(method = "tick", at = @At("TAIL"))
    private void dbi_handleNimbusDismount(boolean slowDown, float f, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.player.getVehicle() != null) {
            if (client.player.getVehicle() instanceof FlyNinbus || client.player.getVehicle() instanceof DarkFlyNinbus) {
                // Check if Ctrl+Left is pressed
                boolean ctrlPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                                     GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
                boolean leftPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS;

                if (ctrlPressed && leftPressed) {
                    client.player.stopRiding();
                }
            }
        }
    }
}
    