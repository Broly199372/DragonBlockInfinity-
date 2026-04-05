/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.loot.condition;

import net.minecraft.loot.condition.LootCondition;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class LootConditionType
extends JsonSerializableType<LootCondition> {
    public LootConditionType(JsonSerializer<? extends LootCondition> jsonSerializer) {
        super(jsonSerializer);
    }
}

