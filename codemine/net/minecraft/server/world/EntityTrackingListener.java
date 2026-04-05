/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.server.world;

import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * A listener to entity tracking within threaded anvil chunk storage.
 */
public interface EntityTrackingListener {
    public ServerPlayerEntity getPlayer();

    public void sendPacket(Packet<?> var1);
}

