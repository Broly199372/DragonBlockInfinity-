package com.bernardo.dbi.player;

public class PlayerStats {

    public static float getDamage(Race race, AgeStage age, FightingStyle style) {
        return race.damageMultiplier * age.damageMultiplier * style.damageMultiplier;
    }

    public static float getDefense(Race race, AgeStage age, FightingStyle style) {
        return race.defenseMultiplier * age.defenseMultiplier * style.defenseMultiplier;
    }

    /**
     * Tamanho combinado: Raça × Idade
     * Exemplo: Saiyan Criança = 1.00 × 0.45 = 0.45
     */
    public static float getSize(Race race, AgeStage age) {
        return race.sizeMultiplier * age.sizeMultiplier;
    }

    /**
     * Altura dos olhos combinada: Raça × Idade
     * Exemplo: Namekiano Jovem = 1.05 × 0.75 = 0.7875
     */
    public static float getEyeHeight(Race race, AgeStage age) {
        return race.eyeHeightMultiplier * age.eyeHeightMultiplier;
    }
}
