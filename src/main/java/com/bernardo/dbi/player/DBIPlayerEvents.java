package com.bernardo.dbi.player;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class DBIPlayerEvents {

    public static void initialize() {

        // Quando o player conecta/entra no mundo
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            DBIAttributeHelper.applyModifiers(handler.getPlayer());
        });

        // Quando o player respawna (morte ou cama)
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            // Copia os dados do player antigo para o novo
            DBIPlayerData.setRace(newPlayer,  DBIPlayerData.getRace(oldPlayer));
            DBIPlayerData.setAge(newPlayer,   DBIPlayerData.getAge(oldPlayer));
            DBIPlayerData.setStyle(newPlayer, DBIPlayerData.getStyle(oldPlayer));
            DBIAttributeHelper.applyModifiers(newPlayer);
        });
    }
}
