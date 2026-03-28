package com.bernardo.dbi.player;

public enum AgeStage {
    // Age(modelSize, eyeHeightMultiplier, damageMultiplier, defenseMultiplier)
    CHILD (0.45f, 0.40f, 0.60f, 1.00f),
    YOUNG (0.80f, 0.75f, 1.20f, 1.00f),
    ADULT (1.00f, 1.00f, 1.00f, 1.00f),
    OLD   (0.95f, 0.95f, 0.80f, 1.20f);

    public final float sizeMultiplier;
    public final float eyeHeightMultiplier;
    public final float damageMultiplier;
    public final float defenseMultiplier;

    AgeStage(float size, float eyeHeight, float damage, float defense) {
        this.sizeMultiplier = size;
        this.eyeHeightMultiplier = eyeHeight;
        this.damageMultiplier = damage;
        this.defenseMultiplier = defense;
    }
}
