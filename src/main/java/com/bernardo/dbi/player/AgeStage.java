package com.bernardo.dbi.player;

public enum AgeStage {
    CHILD (0.50f, 0.60f, 1.00f),
    YOUNG (0.75f, 1.20f, 1.00f),
    ADULT (1.00f, 1.00f, 1.00f),
    OLD   (1.00f, 1.00f, 0.70f);

    public final float sizeMultiplier;
    public final float damageMultiplier;
    public final float defenseMultiplier;

    AgeStage(float size, float damage, float defense) {
        this.sizeMultiplier    = size;
        this.damageMultiplier  = damage;
        this.defenseMultiplier = defense;
    }
}
