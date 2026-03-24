package com.bernardo.dbi.mixin;

import com.bernardo.dbi.player.AgeStage;
import com.bernardo.dbi.player.DBIPlayerDataAccess;
import com.bernardo.dbi.player.FightingStyle;
import com.bernardo.dbi.player.Race;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements DBIPlayerDataAccess {

    @Unique private Race          dbi$race  = Race.SAIYAN;
    @Unique private AgeStage      dbi$age   = AgeStage.ADULT;
    @Unique private FightingStyle dbi$style = FightingStyle.WARRIOR;
    @Unique private Identifier    dbi$hair  = null;
    @Unique private Identifier    dbi$eye   = null;
    @Unique private Identifier    dbi$nose  = null;

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void dbi_writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("dbi_race",  dbi$race.name());
        nbt.putString("dbi_age",   dbi$age.name());
        nbt.putString("dbi_style", dbi$style.name());
        if (dbi$hair != null) nbt.putString("dbi_hair", dbi$hair.toString());
        if (dbi$eye  != null) nbt.putString("dbi_eye",  dbi$eye.toString());
        if (dbi$nose != null) nbt.putString("dbi_nose", dbi$nose.toString());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void dbi_readNbt(NbtCompound nbt, CallbackInfo ci) {
        try { dbi$race  = Race.valueOf(nbt.getString("dbi_race"));           } catch (Exception ignored) {}
        try { dbi$age   = AgeStage.valueOf(nbt.getString("dbi_age"));        } catch (Exception ignored) {}
        try { dbi$style = FightingStyle.valueOf(nbt.getString("dbi_style")); } catch (Exception ignored) {}
        if (nbt.contains("dbi_hair")) dbi$hair = new Identifier(nbt.getString("dbi_hair"));
        if (nbt.contains("dbi_eye"))  dbi$eye  = new Identifier(nbt.getString("dbi_eye"));
        if (nbt.contains("dbi_nose")) dbi$nose = new Identifier(nbt.getString("dbi_nose"));
    }

    @Override public Race          dbi_getRace()        { return dbi$race;  }
    @Override public AgeStage      dbi_getAge()         { return dbi$age;   }
    @Override public FightingStyle dbi_getStyle()       { return dbi$style; }
    @Override public Identifier    dbi_getHairTexture() { return dbi$hair;  }
    @Override public Identifier    dbi_getEyeTexture()  { return dbi$eye;   }
    @Override public Identifier    dbi_getNoseTexture() { return dbi$nose;  }

    @Override public void dbi_setRace(Race r)           { dbi$race  = r; }
    @Override public void dbi_setAge(AgeStage a)        { dbi$age   = a; }
    @Override public void dbi_setStyle(FightingStyle s) { dbi$style = s; }
    @Override public void dbi_setHairTexture(Identifier t) { dbi$hair = t; }
    @Override public void dbi_setEyeTexture(Identifier t)  { dbi$eye  = t; }
    @Override public void dbi_setNoseTexture(Identifier t) { dbi$nose = t; }
}
