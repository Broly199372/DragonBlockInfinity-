package com.bernardo.dbi.player;

public enum FightingStyle {
    MARTIAL_ARTIST(1.15f, 1.10f),
    WARRIOR       (1.20f, 0.95f),
    SPIRITUALIST  (0.90f, 1.20f);

    public final float damageMultiplier;
    public final float defenseMultiplier;

    FightingStyle(float damage, float defense) {
        this.damageMultiplier  = damage;
        this.defenseMultiplier = defense;
    }
}
