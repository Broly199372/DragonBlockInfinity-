/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.loot.provider.number;

import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class LootNumberProviderType
extends JsonSerializableType<LootNumberProvider> {
    public LootNumberProviderType(JsonSerializer<? extends LootNumberProvider> jsonSerializer) {
        super(jsonSerializer);
    }
}

