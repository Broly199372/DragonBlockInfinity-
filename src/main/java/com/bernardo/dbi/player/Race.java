package com.bernardo.dbi.player;

public enum Race {
    SAIYAN    (1.20f, 1.05f),
    HALF_SAIYAN(1.20f, 1.05f),
    NAMEKIAN  (1.05f, 1.25f),
    ARCOSIAN  (1.15f, 1.15f);

    public final float damageMultiplier;
    public final float defenseMultiplier;

    Race(float damage, float defense) {
        this.damageMultiplier  = damage;
        this.defenseMultiplier = defense;
    }
}
