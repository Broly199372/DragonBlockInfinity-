/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.util;

import net.minecraft.util.JsonSerializer;

public class JsonSerializableType<T> {
    private final JsonSerializer<? extends T> jsonSerializer;

    public JsonSerializableType(JsonSerializer<? extends T> jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public JsonSerializer<? extends T> getJsonSerializer() {
        return this.jsonSerializer;
    }
}

