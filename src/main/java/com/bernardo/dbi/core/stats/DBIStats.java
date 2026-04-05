package com.bernardo.dbi.core.stats;

import net.minecraft.nbt.NbtCompound;
import java.util.EnumMap;
import java.util.Map;

public class DBIStats {

    private static final int DEFAULT_VALUE = 10;

    private final Map<Stat, Integer> current = new EnumMap<>(Stat.class);
    private final Map<Stat, Integer> base    = new EnumMap<>(Stat.class);
    private int potentialLevel = 0;

    public DBIStats() {
        for (Stat s : Stat.values()) {
            current.put(s, DEFAULT_VALUE);
            base.put(s, DEFAULT_VALUE);
        }
    }

    public int get(Stat stat)     { return current.getOrDefault(stat, 0); }
    public int getBase(Stat stat) { return base.getOrDefault(stat, 0); }

    // MND ignora potencial, sempre usa o valor total
    public int getEffective(Stat stat) {
        if (stat == Stat.MND) return get(stat);
        return (int) (get(stat) * getPotentialMultiplier());
    }

    public int   getPotentialLevel()      { return potentialLevel; }
    public float getPotentialMultiplier() { return 0.5f + potentialLevel * 0.05f; }

    public void set(Stat stat, int value) {
        current.put(stat, Math.max(0, value));
    }

    // Treino permanente: sobe stat e base junto
    public void add(Stat stat, int amount) {
        current.put(stat, get(stat) + amount);
        base.put(stat, getBase(stat) + amount);
    }

    public void setPotentialLevel(int level) {
        this.potentialLevel = Math.max(0, Math.min(10, level));
    }

    public void unlockNextPotential() {
        setPotentialLevel(potentialLevel + 1);
    }

    // Bônus para DBIAttributeHelper
    public float getDamageBonus()    { return 1.0f + (getEffective(Stat.STR) - DEFAULT_VALUE) * 0.02f;  }
    public float getDefenseBonus()   { return 1.0f + (getEffective(Stat.DEX) - DEFAULT_VALUE) * 0.015f; }
    public float getMaxHealthBonus() { return (getEffective(Stat.CON) - DEFAULT_VALUE) * 2.0f; }

    public void writeToNbt(NbtCompound nbt) {
        NbtCompound tag = new NbtCompound();
        for (Stat s : Stat.values()) {
            tag.putInt(s.shortName,           current.get(s));
            tag.putInt(s.shortName + "_base", base.get(s));
        }
        nbt.put("dbi_stats", tag);
        nbt.putInt("dbi_potential", potentialLevel);
    }

    public void readFromNbt(NbtCompound nbt) {
        if (!nbt.contains("dbi_stats")) return;
        NbtCompound tag = nbt.getCompound("dbi_stats");
        for (Stat s : Stat.values()) {
            if (tag.contains(s.shortName))           current.put(s, tag.getInt(s.shortName));
            if (tag.contains(s.shortName + "_base")) base.put(s, tag.getInt(s.shortName + "_base"));
        }
        if (nbt.contains("dbi_potential")) potentialLevel = nbt.getInt("dbi_potential");
    }
}
