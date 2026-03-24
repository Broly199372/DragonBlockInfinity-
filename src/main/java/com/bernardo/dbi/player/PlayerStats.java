package com.bernardo.dbi.player;

public class PlayerStats {

    public static float getDamage(Race race, AgeStage age, FightingStyle style) {
        return race.damageMultiplier * age.damageMultiplier * style.damageMultiplier;
    }

    public static float getDefense(Race race, AgeStage age, FightingStyle style) {
        return race.defenseMultiplier * age.defenseMultiplier * style.defenseMultiplier;
    }

    public static float getSize(AgeStage age) {
        return age.sizeMultiplier;
    }
}
