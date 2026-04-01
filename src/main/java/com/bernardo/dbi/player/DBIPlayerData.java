package com.bernardo.dbi.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class DBIPlayerData {

    public static Race          getRace(PlayerEntity p)  { return ((DBIPlayerDataAccess)p).dbi_getRace();         }
    public static AgeStage      getAge(PlayerEntity p)   { return ((DBIPlayerDataAccess)p).dbi_getAge();          }
    public static FightingStyle getStyle(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getStyle();        }
    public static Identifier    getHairTexture(PlayerEntity p)  { return ((DBIPlayerDataAccess)p).dbi_getHairTexture();  }
    public static Identifier    getEyeTexture(PlayerEntity p)   { return ((DBIPlayerDataAccess)p).dbi_getEyeTexture();   }
    public static Identifier    getNoseTexture(PlayerEntity p)  { return ((DBIPlayerDataAccess)p).dbi_getNoseTexture();  }
    public static Identifier    getMouthTexture(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getMouthTexture(); }

    public static void setRace(PlayerEntity p, Race r)           { ((DBIPlayerDataAccess)p).dbi_setRace(r);          }
    public static void setAge(PlayerEntity p, AgeStage a)        { ((DBIPlayerDataAccess)p).dbi_setAge(a);           }
    public static void setStyle(PlayerEntity p, FightingStyle s) { ((DBIPlayerDataAccess)p).dbi_setStyle(s);         }
    public static void setHairTexture(PlayerEntity p, Identifier t)  { ((DBIPlayerDataAccess)p).dbi_setHairTexture(t);  }
    public static void setEyeTexture(PlayerEntity p, Identifier t)   { ((DBIPlayerDataAccess)p).dbi_setEyeTexture(t);   }
    public static void setNoseTexture(PlayerEntity p, Identifier t)  { ((DBIPlayerDataAccess)p).dbi_setNoseTexture(t);  }
    public static void setMouthTexture(PlayerEntity p, Identifier t) { ((DBIPlayerDataAccess)p).dbi_setMouthTexture(t); }

    public static float getFinalDamage(PlayerEntity p)  { return PlayerStats.getDamage(getRace(p),  getAge(p), getStyle(p)); }
    public static float getFinalDefense(PlayerEntity p) { return PlayerStats.getDefense(getRace(p), getAge(p), getStyle(p)); }
    public static float getFinalSize(PlayerEntity p)    { return PlayerStats.getSize(getRace(p), getAge(p)); }
    public static float getEyeHeightScale(PlayerEntity p) { return PlayerStats.getEyeHeight(getRace(p), getAge(p)); }
    public static Identifier    getTailTexture(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getTailTexture(); }
    public static void setTailTexture(PlayerEntity p, Identifier t) { ((DBIPlayerDataAccess)p).dbi_setTailTexture(t); }
    public static int getHairColor(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getHairColor(); }
    public static int getEyePupilColor(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getEyePupilColor(); }
    public static int getTailColor(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getTailColor(); }
    public static int getSkinColor(PlayerEntity p) { return ((DBIPlayerDataAccess)p).dbi_getSkinColor(); }
    public static void setHairColor(PlayerEntity p, int c) { ((DBIPlayerDataAccess)p).dbi_setHairColor(c); }
    public static void setEyePupilColor(PlayerEntity p, int c) { ((DBIPlayerDataAccess)p).dbi_setEyePupilColor(c); }
    public static void setTailColor(PlayerEntity p, int c) { ((DBIPlayerDataAccess)p).dbi_setTailColor(c); }
    public static void setSkinColor(PlayerEntity p, int c) { ((DBIPlayerDataAccess)p).dbi_setSkinColor(c); }
}
