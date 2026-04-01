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

/**
 * Mixin para PlayerEntity que adiciona dados personalizados do DBI (Dragon Block Infinity).
 * Permite armazenar raça, estágio de idade, estilo de luta e texturas faciais do jogador.
 * Os dados são persistidos no NBT do jogador para sobreviver ao logout/login.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements DBIPlayerDataAccess {

    // Valores padrão para os dados personalizados do jogador DBI
    @Unique private Race          dbi$race  = Race.SAIYAN;     // Raça do jogador (ex: Saiyan, Namekian)
    @Unique private AgeStage      dbi$age   = AgeStage.ADULT;  // Estágio de idade (ex: Criança, Adulto)
    @Unique private FightingStyle dbi$style = FightingStyle.WARRIOR; // Estilo de luta (ex: Guerreiro, Monge)
    @Unique private Identifier    dbi$bodyTexture = new Identifier("minecraft", "textures/entity/player/wide/steve.png"); // Textura do corpo
    @Unique private Identifier    dbi$bodyTextureSlim = new Identifier("minecraft", "textures/entity/player/slim/alex.png"); //textura do corpo para modelo slim (Alex)
    @Unique private Identifier    dbi$hair  = null;            // Textura do cabelo (pode ser null)
    @Unique private Identifier    dbi$eye;            // Textura dos olhos (pode ser null)
    @Unique private Identifier    dbi$nose;            // Textura do nariz (pode ser null)
    @Unique private Identifier    dbi$mouth;           // Textura da boca (pode ser null)
    @Unique private Identifier    dbi$tail;            // Textura da cauda (pode ser null)
    @Unique private int          dbi$hairColor  = 0x000000;   // Cor do cabelo (RGB)
    @Unique private int          dbi$eyePupilColor = 0x000000; // Cor da pupila do olho (RGB)
    @Unique private int          dbi$tailColor = 0x8B4513;    // Cor da cauda (RGB)
    @Unique private int          dbi$skinColor = 0xFFDBAC;    // Cor de pele (RGB)

    /**
     * Injeta no método writeCustomDataToNbt para salvar os dados DBI no NBT do jogador.
     * Isso garante que as customizações sejam persistidas quando o jogador salva o mundo.
     */
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void dbi_writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("dbi_race",  dbi$race.name());
        nbt.putString("dbi_age",   dbi$age.name());
        nbt.putString("dbi_style", dbi$style.name());
        if (dbi$hair  != null) nbt.putString("dbi_hair",  dbi$hair.toString());
        if (dbi$eye   != null) nbt.putString("dbi_eye",   dbi$eye.toString());
        if (dbi$nose  != null) nbt.putString("dbi_nose",  dbi$nose.toString());
        if (dbi$mouth != null) nbt.putString("dbi_mouth", dbi$mouth.toString());
        if (dbi$tail  != null) nbt.putString("dbi_tail",  dbi$tail.toString());
        nbt.putInt("dbi_hair_color", dbi$hairColor);
        nbt.putInt("dbi_eye_pupil_color", dbi$eyePupilColor);
        nbt.putInt("dbi_tail_color", dbi$tailColor);
        nbt.putInt("dbi_skin_color", dbi$skinColor);
    }

    /**
     * Injeta no método readCustomDataFromNbt para carregar os dados DBI do NBT do jogador.
     * Usa try-catch para ignorar erros se os dados estiverem corrompidos ou ausentes,
     * mantendo os valores padrão.
     */
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void dbi_readNbt(NbtCompound nbt, CallbackInfo ci) {
        try { dbi$race  = Race.valueOf(nbt.getString("dbi_race"));           } catch (Exception ignored) {}
        try { dbi$age   = AgeStage.valueOf(nbt.getString("dbi_age"));        } catch (Exception ignored) {}
        try { dbi$style = FightingStyle.valueOf(nbt.getString("dbi_style")); } catch (Exception ignored) {}
        if (nbt.contains("dbi_hair"))  dbi$hair  = new Identifier(nbt.getString("dbi_hair"));
        if (nbt.contains("dbi_eye"))   dbi$eye   = new Identifier(nbt.getString("dbi_eye"));
        if (nbt.contains("dbi_nose"))  dbi$nose  = new Identifier(nbt.getString("dbi_nose"));
        if (nbt.contains("dbi_mouth")) dbi$mouth = new Identifier(nbt.getString("dbi_mouth"));
        if (nbt.contains("dbi_tail"))  dbi$tail  = new Identifier(nbt.getString("dbi_tail"));
        if (nbt.contains("dbi_hair_color")) dbi$hairColor = nbt.getInt("dbi_hair_color");
        if (nbt.contains("dbi_eye_pupil_color")) dbi$eyePupilColor = nbt.getInt("dbi_eye_pupil_color");
        if (nbt.contains("dbi_tail_color")) dbi$tailColor = nbt.getInt("dbi_tail_color");
        if (nbt.contains("dbi_skin_color")) dbi$skinColor = nbt.getInt("dbi_skin_color");
    }

    // Getters e setters para acessar e modificar os dados DBI do jogador
    // Implementam a interface DBIPlayerDataAccess

    @Override public Race          dbi_getRace()         { return dbi$race;  }
    @Override public AgeStage      dbi_getAge()          { return dbi$age;   }
    @Override public FightingStyle dbi_getStyle()        { return dbi$style; }
    @Override public Identifier    dbi_getHairTexture()  { return dbi$hair;  }
    @Override public Identifier    dbi_getEyeTexture()   { return dbi$eye;   }
    @Override public Identifier    dbi_getNoseTexture()  { return dbi$nose;  }
    @Override public Identifier    dbi_getMouthTexture() { return dbi$mouth; }
    @Override public Identifier    dbi_getTailTexture()  { return dbi$tail;  }
    @Override public int           dbi_getHairColor()      { return dbi$hairColor; }
    @Override public int           dbi_getEyePupilColor()  { return dbi$eyePupilColor; }
    @Override public int           dbi_getTailColor()      { return dbi$tailColor; }
    @Override public int           dbi_getSkinColor()      { return dbi$skinColor; }

    @Override public void dbi_setRace(Race r)              { dbi$race  = r; }
    @Override public void dbi_setAge(AgeStage a)           { dbi$age   = a; }
    @Override public void dbi_setStyle(FightingStyle s)    { dbi$style = s; }
    @Override public void dbi_setHairTexture(Identifier t) { dbi$hair  = t; }
    @Override public void dbi_setEyeTexture(Identifier t)  { dbi$eye   = t; }
    @Override public void dbi_setNoseTexture(Identifier t) { dbi$nose  = t; }
    @Override public void dbi_setMouthTexture(Identifier t){ dbi$mouth = t; }
    @Override public void dbi_setTailTexture(Identifier t) { dbi$tail  = t; }
    @Override public void dbi_setHairColor(int c)         { dbi$hairColor = c; }
    @Override public void dbi_setEyePupilColor(int c)     { dbi$eyePupilColor = c; }
    @Override public void dbi_setTailColor(int c)         { dbi$tailColor = c; }
    @Override public void dbi_setSkinColor(int c)         { dbi$skinColor = c; }
}
