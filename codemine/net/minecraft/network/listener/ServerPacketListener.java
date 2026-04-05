/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.network.listener;

import net.minecraft.network.listener.PacketListener;

/**
 * The base interface for serverbound packet listeners.
 * 
 * @implNote Serverbound packet listeners log any uncaught exceptions
 * without crashing.
 */
public interface ServerPacketListener
extends PacketListener {
    @Override
    default public boolean shouldCrashOnException() {
        return false;
    }
}

