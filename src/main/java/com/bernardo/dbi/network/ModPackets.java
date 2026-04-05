package com.broly.dragonblockinfinity.network;

import com.broly.dragonblockinfinity.DragonBlockInfinity;
import com.broly.dragonblockinfinity.ki.KiComponent;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier KI_SYNC_ID = new Identifier(DragonBlockInfinity.MOD_ID, "ki_sync");

    public static void registerC2SPackets() {
        // Nenhum pacote C2S (Client to Server) para Ki por enquanto, o Ki é autoritário no servidor.
    }

    public static void registerS2CPackets() {
        // No cliente, registra um handler para receber o pacote de sincronização de Ki
        ClientPlayNetworking.registerGlobalReceiver(KI_SYNC_ID, (client, handler, buf, responseSender) -> {
            int currentKi = buf.readInt();
            int maxKi = buf.readInt();
            // Executa no thread principal do cliente
            client.execute(() -> {
                if (client.player != null) {
                    KiComponent kiComponent = DragonBlockInfinity.KI_COMPONENT.get(client.player);
                    // Atualiza o Ki no cliente sem notificar o servidor
                    kiComponent.setKi(currentKi); // Isso vai chamar setKi, que não deve enviar de volta
                    // Se você quiser atualizar o maxKi também, precisaria de um setter público na interface ou cast
                    // ((PlayerKiComponent)kiComponent).setMaxKi(maxKi); // Cuidado com casts, melhor ter um setter na interface
                }
            });
        });
    }

    /**
     * Envia o Ki atual do jogador para o cliente.
     * Deve ser chamado sempre que o Ki do jogador mudar no servidor.
     */
    public static void sendKiSyncPacket(ServerPlayerEntity player, KiComponent kiComponent) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(kiComponent.getCurrentKi());
        buf.writeInt(kiComponent.getMaxKi());
        ServerPlayNetworking.send(player, KI_SYNC_ID, buf);
    }
}
