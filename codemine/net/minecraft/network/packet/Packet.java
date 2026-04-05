/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.network.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;

public interface Packet<T extends PacketListener> {
    public void write(PacketByteBuf var1);

    public void apply(T var1);

    /**
     * Returns whether a throwable in writing of this packet allows the
     * connection to simply skip the packet's sending than disconnecting.
     */
    default public boolean isWritingErrorSkippable() {
        return false;
    }
}

