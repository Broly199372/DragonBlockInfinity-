/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.loot;

import net.minecraft.loot.LootDataType;
import net.minecraft.util.Identifier;

public record LootDataKey<T>(LootDataType<T> type, Identifier id) {
}

