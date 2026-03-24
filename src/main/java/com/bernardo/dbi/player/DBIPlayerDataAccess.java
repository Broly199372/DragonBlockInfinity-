package com.bernardo.dbi.player;

import net.minecraft.util.Identifier;

public interface DBIPlayerDataAccess {
    Race          dbi_getRace();
    AgeStage      dbi_getAge();
    FightingStyle dbi_getStyle();
    Identifier    dbi_getHairTexture();
    Identifier    dbi_getEyeTexture();
    Identifier    dbi_getNoseTexture();

    void dbi_setRace(Race race);
    void dbi_setAge(AgeStage age);
    void dbi_setStyle(FightingStyle style);
    void dbi_setHairTexture(Identifier texture);
    void dbi_setEyeTexture(Identifier texture);
    void dbi_setNoseTexture(Identifier texture);
}
