/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public interface JsonSerializer<T> {
    public void toJson(JsonObject var1, T var2, JsonSerializationContext var3);

    public T fromJson(JsonObject var1, JsonDeserializationContext var2);
}

