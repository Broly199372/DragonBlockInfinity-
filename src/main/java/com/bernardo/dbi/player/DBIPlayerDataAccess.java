package com.bernardo.dbi.player;

import net.minecraft.util.Identifier;

public interface DBIPlayerDataAccess {
    Race          dbi_getRace();
    AgeStage      dbi_getAge();
    FightingStyle dbi_getStyle();
    Identifier    dbi_getBodyTexture();
    Identifier    dbi_getHairTexture();
    Identifier    dbi_getEyeTexture();
    Identifier    dbi_getNoseTexture();
    Identifier    dbi_getMouthTexture();
    Identifier    dbi_getTailTexture();
    int           dbi_getHairColor();
    int           dbi_getEyePupilColor();
    int           dbi_getTailColor();
    int           dbi_getSkinColor();

    void dbi_setRace(Race race);
    void dbi_setAge(AgeStage age);
    void dbi_setStyle(FightingStyle style);
    void dbi_setBodyTexture(Identifier texture);
    void dbi_setHairTexture(Identifier texture);
    void dbi_setEyeTexture(Identifier texture);
    void dbi_setNoseTexture(Identifier texture);
    void dbi_setMouthTexture(Identifier texture);
    void dbi_setTailTexture(Identifier texture);
    void dbi_setHairColor(int color);
    void dbi_setEyePupilColor(int color);
    void dbi_setTailColor(int color);
    void dbi_setSkinColor(int color);
}
