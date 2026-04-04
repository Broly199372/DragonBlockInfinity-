package com.bernardo.dbi.network;

import com.bernardo.dbi.player.DBIPlayerData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class DBINetwork {

    public static void initialize() {
        ServerPlayNetworking.registerGlobalReceiver(PacketIds.SET_HAIR, (server, player, handler, buf, responseSender) -> {
            String texturePath = buf.readString(256);
            server.execute(() -> {
                if (texturePath.isEmpty()) {
                    DBIPlayerData.setHairTexture(player, null);
                } else {
                    DBIPlayerData.setHairTexture(player, new Identifier("dragonblockinfinity", texturePath));
                }
            });
        });
    }

    public static PacketByteBuf buildSetHairPacket(String texturePath) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(texturePath == null ? "" : texturePath);
        return buf;
    }
}
