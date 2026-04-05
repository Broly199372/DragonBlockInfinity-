/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.loot.function;

import net.minecraft.loot.function.LootFunction;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class LootFunctionType
extends JsonSerializableType<LootFunction> {
    public LootFunctionType(JsonSerializer<? extends LootFunction> jsonSerializer) {
        super(jsonSerializer);
    }
}

