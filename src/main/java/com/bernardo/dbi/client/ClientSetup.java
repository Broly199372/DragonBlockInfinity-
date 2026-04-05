package com.bernardo.dbi.client;


import com.bernardo.dbi.screen.CaracterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.MinecraftClient;

public class ClientSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindings.initialize();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.OPEN_MENU.wasPressed()) {
                if (client.player != null) {
                    MinecraftClient.getInstance().setScreen(new CaracterScreen());
                }
            }
        });
    }
}
