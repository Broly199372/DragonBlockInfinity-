package com.bernardo.dbi.client;

import com.bernardo.dbi.client.render.layer.HairGokuModel;
import com.bernardo.dbi.client.render.layer.HairVegetaModel;
import com.bernardo.dbi.client.render.layer.HairTrunksModel;
import com.bernardo.dbi.screen.CaracterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.MinecraftClient;

public class ClientSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindings.initialize();

        EntityModelLayerRegistry.registerModelLayer(HairVegetaModel.LAYER, HairVegetaModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HairGokuModel.LAYER,   HairGokuModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HairTrunksModel.LAYER, HairTrunksModel::getTexturedModelData);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.OPEN_MENU.wasPressed()) {
                if (client.player != null) {
                    MinecraftClient.getInstance().setScreen(new CaracterScreen());
                }
            }
        });
    }
}
