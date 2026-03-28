package com.bernardo.dbi.player;

public enum Race {
    // Race(sizeMultiplier, eyeHeightMultiplier, damageMultiplier, defenseMultiplier)
    SAIYAN      (1.00f, 1.00f, 1.20f, 1.05f),
    HALF_SAIYAN (0.95f, 0.98f, 1.15f, 1.08f),
    NAMEKIAN    (1.08f, 1.05f, 1.05f, 1.25f),
    ARCOSIAN    (0.92f, 0.92f, 1.15f, 1.15f); // menor que Saiyan, estilo Broly/Freeza

    public final float sizeMultiplier;
    public final float eyeHeightMultiplier;
    public final float damageMultiplier;
    public final float defenseMultiplier;

    Race(float size, float eyeHeight, float damage, float defense) {
        this.sizeMultiplier = size;
        this.eyeHeightMultiplier = eyeHeight;
        this.damageMultiplier = damage;
        this.defenseMultiplier = defense;
    }
}
