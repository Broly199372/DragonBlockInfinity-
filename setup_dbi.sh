#!/bin/bash
# =============================================================================
# Dragon Block Infinity - Setup Script
# Gera toda a estrutura de pastas e arquivos Java do mod DBI (merged com DBE)
# =============================================================================

BASE="src/main/java/com/bernardo/dbi"

# =============================================================================
# CRIAR PASTAS
# =============================================================================
mkdir -p $BASE/ki
mkdir -p $BASE/bp
mkdir -p $BASE/transformation
mkdir -p $BASE/skill
mkdir -p $BASE/alignment
mkdir -p $BASE/network
mkdir -p $BASE/player
mkdir -p $BASE/mixin
mkdir -p $BASE/client/render/layer
mkdir -p $BASE/client/hud
mkdir -p $BASE/screen
mkdir -p $BASE/registry
mkdir -p $BASE/item
mkdir -p $BASE/entity
mkdir -p $BASE/block
mkdir -p $BASE/util
echo "[1/N] Pastas criadas."

# =============================================================================
# KI SYSTEM
# =============================================================================
cat << 'EOF' > $BASE/ki/KiData.java
package com.bernardo.dbi.ki;

import net.minecraft.nbt.NbtCompound;

/** Armazena e gerencia o Ki do jogador (portado do DBE, adaptado para DBI). */
public class KiData {

    private float currentKi;
    private float maxKi;
    private float kiRegenRate;
    private boolean suppressionActive = false;
    private float suppressionLevel = 1.0f;

    public KiData() {
        this.currentKi   = 100.0f;
        this.maxKi       = 100.0f;
        this.kiRegenRate = 1.0f;
    }

    public float getCurrentKi()    { return currentKi; }
    public float getMaxKi()        { return maxKi; }
    public float getKiRegenRate()  { return kiRegenRate; }
    public float getKiPercentage() { return maxKi > 0 ? (currentKi / maxKi) : 0f; }
    public boolean isSuppressionActive() { return suppressionActive; }
    public float   getSuppressionLevel() { return suppressionLevel; }

    public void setCurrentKi(float ki)   { this.currentKi = Math.max(0f, Math.min(ki, maxKi)); }
    public void setMaxKi(float max)      { this.maxKi = Math.max(1f, max); this.currentKi = Math.min(currentKi, this.maxKi); }
    public void setKiRegenRate(float r)  { this.kiRegenRate = Math.max(0f, r); }
    public void setSuppressionActive(boolean a) { this.suppressionActive = a; }
    public void setSuppressionLevel(float l)    { this.suppressionLevel = Math.max(0.01f, Math.min(1.0f, l)); }

    public void addKi(float amount) { setCurrentKi(currentKi + amount); }

    public boolean consumeKi(float amount) {
        if (currentKi >= amount) { currentKi -= amount; return true; }
        return false;
    }

    public void tickRegen() {
        if (currentKi < maxKi) addKi(kiRegenRate);
    }

    public void writeToNbt(NbtCompound nbt) {
        nbt.putFloat("CurrentKi",      currentKi);
        nbt.putFloat("MaxKi",          maxKi);
        nbt.putFloat("KiRegen",        kiRegenRate);
        nbt.putBoolean("Suppression",  suppressionActive);
        nbt.putFloat("SuppressionLvl", suppressionLevel);
    }

    public void readFromNbt(NbtCompound nbt) {
        currentKi        = nbt.getFloat("CurrentKi");
        maxKi            = nbt.getFloat("MaxKi");
        if (maxKi <= 0f) maxKi = 100f;
        kiRegenRate      = nbt.getFloat("KiRegen");
        if (kiRegenRate <= 0f) kiRegenRate = 1.0f;
        suppressionActive = nbt.getBoolean("Suppression");
        suppressionLevel  = nbt.getFloat("SuppressionLvl");
        if (suppressionLevel <= 0f) suppressionLevel = 1.0f;
    }
}
EOF
echo "[KiData.java] criado"

# =============================================================================
# BATTLE POWER
# =============================================================================
cat << 'EOF' > $BASE/bp/BattlePower.java
package com.bernardo.dbi.bp;

import com.bernardo.dbi.player.Race;
import com.bernardo.dbi.player.AgeStage;
import com.bernardo.dbi.player.FightingStyle;
import net.minecraft.nbt.NbtCompound;

/** Calcula e armazena o Battle Power (BP) do jogador. */
public class BattlePower {

    private int   currentBP     = 0;
    private int   suppressedBP  = 0;
    private float transformMult = 1.0f;

    // ── Cálculo ───────────────────────────────────────────────────────────
    public static int calcBase(Race race, AgeStage age, FightingStyle style) {
        float base = 1000f;
        base *= race.damageMultiplier  * race.defenseMultiplier;
        base *= age.damageMultiplier   * age.defenseMultiplier;
        base *= style.damageMultiplier * style.defenseMultiplier;
        return Math.max(1, (int) base);
    }

    public void recalculate(Race race, AgeStage age, FightingStyle style,
                            float transformMult, float suppressionLevel) {
        this.transformMult = transformMult;
        int base = calcBase(race, age, style);
        this.currentBP    = (int)(base * transformMult);
        this.suppressedBP = (int)(currentBP * suppressionLevel);
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public int   getCurrentBP()   { return currentBP;    }
    public int   getSuppressedBP(){ return suppressedBP; }
    public float getTransformMult(){ return transformMult;}

    public int getDisplayBP(boolean isSuppressed) {
        return isSuppressed ? suppressedBP : currentBP;
    }

    public String getFormattedBP(boolean isSuppressed) {
        return format(getDisplayBP(isSuppressed));
    }

    public static String format(int bp) {
        if (bp >= 1_000_000_000) return String.format("%.1fB", bp / 1_000_000_000.0);
        if (bp >= 1_000_000)     return String.format("%.1fM", bp / 1_000_000.0);
        if (bp >= 1_000)         return String.format("%.1fK", bp / 1_000.0);
        return String.valueOf(bp);
    }

    // ── NBT ───────────────────────────────────────────────────────────────
    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt("CurrentBP",    currentBP);
        nbt.putInt("SuppressedBP", suppressedBP);
        nbt.putFloat("TransformMult", transformMult);
    }

    public void readFromNbt(NbtCompound nbt) {
        currentBP    = nbt.getInt("CurrentBP");
        suppressedBP = nbt.getInt("SuppressedBP");
        transformMult = nbt.getFloat("TransformMult");
        if (transformMult <= 0f) transformMult = 1.0f;
    }
}
EOF
echo "[BattlePower.java] criado"

# =============================================================================
# TRANSFORMATIONS
# =============================================================================
cat << 'EOF' > $BASE/transformation/Transformation.java
package com.bernardo.dbi.transformation;

/** Representa uma transformação (SSJ1, SSJ2, SSJ3, etc). */
public class Transformation {

    public final String id;
    public final String displayName;
    public final float  strMultiplier;   // multiplicador de dano
    public final float  defMultiplier;   // multiplicador de defesa
    public final float  bpMultiplier;    // multiplicador de BP
    public final float  activationCost;  // Ki gasto ao ativar
    public final float  drainPerSecond;  // Ki/s enquanto ativa
    public final int    maxMastery;      // nível máximo de domínio (0-10)
    public final String requiredId;      // transformação anterior obrigatória ("" = nenhuma)
    public final String auraTexture;     // caminho da textura de aura animada
    public final int    auraColor;       // cor ARGB da aura
    public final boolean hasLightning;

    private Transformation(Builder b) {
        this.id              = b.id;
        this.displayName     = b.displayName;
        this.strMultiplier   = b.strMult;
        this.defMultiplier   = b.defMult;
        this.bpMultiplier    = b.bpMult;
        this.activationCost  = b.activationCost;
        this.drainPerSecond  = b.drainPerSecond;
        this.maxMastery      = b.maxMastery;
        this.requiredId      = b.requiredId;
        this.auraTexture     = b.auraTexture;
        this.auraColor       = b.auraColor;
        this.hasLightning    = b.hasLightning;
    }

    /** Redução de drain baseada no domínio (0-10). */
    public float getDrainPerSecond(int mastery) {
        float reduction = 1.0f - (mastery * 0.05f);
        return drainPerSecond * Math.max(0.1f, reduction);
    }

    /** Bonus de multiplicador baseado no domínio. */
    public float getBpMultiplier(int mastery) {
        return bpMultiplier * (1.0f + mastery * 0.02f);
    }

    // ── Builder ───────────────────────────────────────────────────────────
    public static class Builder {
        private final String id, displayName;
        private float strMult=1f, defMult=1f, bpMult=1f;
        private float activationCost=0.2f, drainPerSecond=10f;
        private int   maxMastery=10;
        private String requiredId="";
        private String auraTexture="textures/fx/aura/ki_aura_base.png";
        private int    auraColor=0xFFFFFFFF;
        private boolean hasLightning=false;

        public Builder(String id, String displayName) { this.id=id; this.displayName=displayName; }
        public Builder str(float v)          { strMult=v;          return this; }
        public Builder def(float v)          { defMult=v;          return this; }
        public Builder bp(float v)           { bpMult=v;           return this; }
        public Builder cost(float v)         { activationCost=v;   return this; }
        public Builder drain(float v)        { drainPerSecond=v;   return this; }
        public Builder mastery(int v)        { maxMastery=v;       return this; }
        public Builder requires(String v)    { requiredId=v;       return this; }
        public Builder aura(String tex, int color){ auraTexture=tex; auraColor=color; return this; }
        public Builder lightning()           { hasLightning=true;  return this; }
        public Transformation build()        { return new Transformation(this); }
    }
}
EOF
echo "[Transformation.java] criado"

cat << 'EOF' > $BASE/transformation/TransformationRegistry.java
package com.bernardo.dbi.transformation;

import java.util.LinkedHashMap;
import java.util.Map;

/** Registra todas as transformações disponíveis. */
public class TransformationRegistry {

    private static final Map<String, Transformation> REGISTRY = new LinkedHashMap<>();

    public static void register() {
        // ── Super Saiyan 1 ─────────────────────────────────────────────────
        add(new Transformation.Builder("ssj1", "Super Saiyan")
            .str(50f).def(25f).bp(50f)
            .cost(0.2f).drain(10f)
            .aura("textures/fx/aura/ki_aura_ssj1.png", 0xFFFFDD00)
            .build());

        // ── Super Saiyan 2 ─────────────────────────────────────────────────
        add(new Transformation.Builder("ssj2", "Super Saiyan 2")
            .str(100f).def(50f).bp(100f)
            .cost(0.3f).drain(20f)
            .requires("ssj1")
            .aura("textures/fx/aura/ki_aura_ssj2.png", 0xFFFFEE00)
            .lightning()
            .build());

        // ── Super Saiyan 3 ─────────────────────────────────────────────────
        add(new Transformation.Builder("ssj3", "Super Saiyan 3")
            .str(400f).def(100f).bp(400f)
            .cost(0.4f).drain(40f)
            .requires("ssj2")
            .aura("textures/fx/aura/ki_aura_ssj3.png", 0xFFFFDD00)
            .lightning()
            .build());
    }

    private static void add(Transformation t) { REGISTRY.put(t.id, t); }

    public static Transformation get(String id)  { return REGISTRY.get(id); }
    public static Map<String, Transformation> all(){ return REGISTRY; }
}
EOF
echo "[TransformationRegistry.java] criado"

cat << 'EOF' > $BASE/transformation/TransformationManager.java
package com.bernardo.dbi.transformation;

import com.bernardo.dbi.ki.KiData;
import net.minecraft.nbt.NbtCompound;
import java.util.HashMap;
import java.util.Map;

/** Controla qual transformação está ativa e o domínio de cada uma. */
public class TransformationManager {

    private String            activeId  = "";
    private Transformation    active    = null;
    private final Map<String,Integer> mastery = new HashMap<>();

    // ── Ativar / Desativar ────────────────────────────────────────────────
    public boolean tryActivate(String id, KiData ki) {
        Transformation t = TransformationRegistry.get(id);
        if (t == null) return false;

        // Verificar transformação pré-requisito
        if (!t.requiredId.isEmpty() && !t.requiredId.equals(activeId)) return false;

        // Custo de ativação
        if (!ki.consumeKi(t.activationCost)) return false;

        this.activeId = id;
        this.active   = t;
        return true;
    }

    public void deactivate() {
        this.activeId = "";
        this.active   = null;
    }

    /**
     * Chamado todo tick no servidor. Drena Ki enquanto transformado.
     * @return false se o Ki acabou e a transformação foi cancelada
     */
    public boolean tick(KiData ki) {
        if (active == null) return true;
        float drain = active.getDrainPerSecond(getMastery(activeId)) / 20f; // por tick
        if (!ki.consumeKi(drain)) {
            deactivate();
            return false;
        }
        return true;
    }

    // ── Domínio ───────────────────────────────────────────────────────────
    public int getMastery(String id)  { return mastery.getOrDefault(id, 0); }
    public void addMastery(String id, int amount) {
        int cur = getMastery(id);
        Transformation t = TransformationRegistry.get(id);
        int max = t != null ? t.maxMastery : 10;
        mastery.put(id, Math.min(cur + amount, max));
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public boolean     isTransformed()          { return active != null; }
    public String      getActiveId()            { return activeId; }
    public Transformation getActive()           { return active; }
    public float       getBpMultiplier()        {
        if (active == null) return 1.0f;
        return active.getBpMultiplier(getMastery(activeId));
    }
    public float       getDamageMultiplier()    {
        if (active == null) return 1.0f;
        return active.strMultiplier;
    }
    public float       getDefenseMultiplier()   {
        if (active == null) return 1.0f;
        return active.defMultiplier;
    }

    // ── NBT ───────────────────────────────────────────────────────────────
    public void writeToNbt(NbtCompound nbt) {
        nbt.putString("ActiveTransform", activeId);
        NbtCompound masteryNbt = new NbtCompound();
        for (Map.Entry<String,Integer> e : mastery.entrySet()) {
            masteryNbt.putInt(e.getKey(), e.getValue());
        }
        nbt.put("Mastery", masteryNbt);
    }

    public void readFromNbt(NbtCompound nbt) {
        activeId = nbt.getString("ActiveTransform");
        active   = activeId.isEmpty() ? null : TransformationRegistry.get(activeId);
        NbtCompound masteryNbt = nbt.getCompound("Mastery");
        for (String key : masteryNbt.getKeys()) {
            mastery.put(key, masteryNbt.getInt(key));
        }
    }
}
EOF
echo "[TransformationManager.java] criado"

# =============================================================================
# SKILL SYSTEM
# =============================================================================
cat << 'EOF' > $BASE/skill/Skill.java
package com.bernardo.dbi.skill;

import net.minecraft.entity.player.PlayerEntity;

/** Base abstrata para todas as skills do DBI. */
public abstract class Skill {

    public final String id;
    public final String displayName;
    public final String description;
    public final int    maxLevel;
    public final int    baseCost;       // custo em TP para aprender
    public final int    upgradeCost;    // custo por nível adicional

    protected Skill(String id, String displayName, String description,
                    int maxLevel, int baseCost, int upgradeCost) {
        this.id          = id;
        this.displayName = displayName;
        this.description = description;
        this.maxLevel    = maxLevel;
        this.baseCost    = baseCost;
        this.upgradeCost = upgradeCost;
    }

    public int getCostForLevel(int currentLevel) {
        return upgradeCost * (currentLevel + 1);
    }

    /** Chamado quando o player usa a skill. */
    public abstract void onUse(PlayerEntity player, int level);

    /** Chamado todo tick enquanto a skill estiver ativa (override se necessário). */
    public void onTick(PlayerEntity player, int level) {}

    /** Custo de Ki por tick (0 = nenhum). */
    public float getKiCost(int level) { return 0f; }

    /** Cooldown em ticks após uso (0 = nenhum). */
    public int getCooldown(int level) { return 0; }

    /** Verifica se o player pode usar a skill agora. */
    public abstract boolean canUse(PlayerEntity player, int level);
}
EOF
echo "[Skill.java] criado"

cat << 'EOF' > $BASE/skill/SkillRegistry.java
package com.bernardo.dbi.skill;

import com.bernardo.dbi.skill.skills.FlightSkill;
import com.bernardo.dbi.skill.skills.KiBlastSkill;
import com.bernardo.dbi.skill.skills.ZenkaiSkill;
import java.util.LinkedHashMap;
import java.util.Map;

/** Registra todas as skills disponíveis no DBI. */
public class SkillRegistry {

    private static final Map<String, Skill> REGISTRY = new LinkedHashMap<>();

    public static void register() {
        add(new FlightSkill());
        add(new KiBlastSkill());
        add(new ZenkaiSkill());
    }

    private static void add(Skill s) { REGISTRY.put(s.id, s); }

    public static Skill get(String id)        { return REGISTRY.get(id); }
    public static Map<String, Skill> all()    { return REGISTRY; }
}
EOF
echo "[SkillRegistry.java] criado"

cat << 'EOF' > $BASE/skill/SkillManager.java
package com.bernardo.dbi.skill;

import net.minecraft.nbt.NbtCompound;
import java.util.HashMap;
import java.util.Map;

/** Controla as skills aprendidas e seus níveis para um jogador. */
public class SkillManager {

    private final Map<String, Integer> learned = new HashMap<>(); // id -> level

    public boolean hasSkill(String id)       { return learned.containsKey(id); }
    public int     getLevel(String id)       { return learned.getOrDefault(id, 0); }

    public void learnOrUpgrade(String id) {
        Skill s = SkillRegistry.get(id);
        if (s == null) return;
        int cur = getLevel(id);
        if (cur < s.maxLevel) learned.put(id, cur + 1);
    }

    public Map<String, Integer> getLearned() { return learned; }

    public void writeToNbt(NbtCompound nbt) {
        NbtCompound skillsNbt = new NbtCompound();
        for (Map.Entry<String,Integer> e : learned.entrySet()) {
            skillsNbt.putInt(e.getKey(), e.getValue());
        }
        nbt.put("Skills", skillsNbt);
    }

    public void readFromNbt(NbtCompound nbt) {
        NbtCompound skillsNbt = nbt.getCompound("Skills");
        for (String key : skillsNbt.getKeys()) {
            learned.put(key, skillsNbt.getInt(key));
        }
    }
}
EOF
echo "[SkillManager.java] criado"

mkdir -p $BASE/skill/skills

cat << 'EOF' > $BASE/skill/skills/FlightSkill.java
package com.bernardo.dbi.skill.skills;

import com.bernardo.dbi.mixin.PlayerEntityMixin;
import com.bernardo.dbi.skill.Skill;
import net.minecraft.entity.player.PlayerEntity;

/** Skill de voo com custo de Ki por tick. */
public class FlightSkill extends Skill {

    public FlightSkill() {
        super("flight", "Voo", "Permite voar usando Ki.", 10, 2000, 500);
    }

    @Override public float getKiCost(int level) { return Math.max(0.1f, 0.5f - level * 0.03f); }
    @Override public int   getCooldown(int level) { return 0; }

    @Override
    public boolean canUse(PlayerEntity player, int level) {
        return ((PlayerEntityMixin)(Object)player).dbi_getKiData().getCurrentKi() > 10f;
    }

    @Override
    public void onUse(PlayerEntity player, int level) {
        boolean flying = player.getAbilities().flying;
        player.getAbilities().allowFlying = !flying;
        player.getAbilities().flying      = !flying;
        player.sendAbilitiesUpdate();
    }

    @Override
    public void onTick(PlayerEntity player, int level) {
        if (!player.getAbilities().flying) return;
        boolean ok = ((PlayerEntityMixin)(Object)player).dbi_getKiData().consumeKi(getKiCost(level) / 20f);
        if (!ok) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying      = false;
            player.sendAbilitiesUpdate();
        }
    }
}
EOF
echo "[FlightSkill.java] criado"

cat << 'EOF' > $BASE/skill/skills/KiBlastSkill.java
package com.bernardo.dbi.skill.skills;

import com.bernardo.dbi.mixin.PlayerEntityMixin;
import com.bernardo.dbi.skill.Skill;
import net.minecraft.entity.player.PlayerEntity;

/** Skill de ki blast — custo de Ki ao disparar. */
public class KiBlastSkill extends Skill {

    public KiBlastSkill() {
        super("ki_blast", "Ki Blast", "Dispara um projétil de Ki.", 10, 1500, 400);
    }

    @Override public float getKiCost(int level) { return Math.max(5f, 20f - level * 1.5f); }
    @Override public int   getCooldown(int level) { return Math.max(5, 20 - level * 2); }

    @Override
    public boolean canUse(PlayerEntity player, int level) {
        return ((PlayerEntityMixin)(Object)player).dbi_getKiData().getCurrentKi() >= getKiCost(level);
    }

    @Override
    public void onUse(PlayerEntity player, int level) {
        // Consome Ki — o projétil em si é lançado pelo handler de rede
        ((PlayerEntityMixin)(Object)player).dbi_getKiData().consumeKi(getKiCost(level));
    }
}
EOF
echo "[KiBlastSkill.java] criado"

cat << 'EOF' > $BASE/skill/skills/ZenkaiSkill.java
package com.bernardo.dbi.skill.skills;

import com.bernardo.dbi.skill.Skill;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Zenkai Boost — skill passiva exclusiva de Saiyans.
 * A cada vez que o player sobrevive com menos de 20% de HP,
 * ganha um bônus permanente de BP (implementado via evento).
 */
public class ZenkaiSkill extends Skill {

    public ZenkaiSkill() {
        super("zenkai_boost", "Zenkai Boost",
              "Após quase morrer, o poder aumenta permanentemente.", 5, 3000, 1000);
    }

    @Override public boolean canUse(PlayerEntity player, int level) { return false; } // passiva
    @Override public void    onUse(PlayerEntity player, int level)  {}

    /** Retorna o multiplicador de bônus de BP para este nível de Zenkai. */
    public static float getBonusMultiplier(int level) {
        return 1.0f + level * 0.10f; // +10% por nível
    }
}
EOF
echo "[ZenkaiSkill.java] criado"

# =============================================================================
# ALIGNMENT
# =============================================================================
cat << 'EOF' > $BASE/alignment/Alignment.java
package com.bernardo.dbi.alignment;

/** Enum de alinhamento com sistema de pontos (0-100). */
public enum Alignment {
    BOM    ("Bom",    "§a", 65, 100),
    NEUTRO ("Neutro", "§e", 35,  64),
    MAL    ("Mal",    "§c",  0,  34);

    public final String name;
    public final String colorCode;
    public final int    minPts;
    public final int    maxPts;

    Alignment(String name, String colorCode, int min, int max) {
        this.name=name; this.colorCode=colorCode; this.minPts=min; this.maxPts=max;
    }

    public String getColored() { return colorCode + name; }

    public static Alignment fromPoints(int pts) {
        for (Alignment a : values()) if (pts >= a.minPts && pts <= a.maxPts) return a;
        return NEUTRO;
    }
}
EOF
echo "[Alignment.java] criado"

cat << 'EOF' > $BASE/alignment/AlignmentData.java
package com.bernardo.dbi.alignment;

import net.minecraft.nbt.NbtCompound;

/** Gerencia os pontos de alinhamento do jogador. */
public class AlignmentData {

    private int       points    = 50;   // 0-100 (50 = neutro)
    private Alignment alignment = Alignment.NEUTRO;

    public int       getPoints()    { return points; }
    public Alignment getAlignment() { return alignment; }

    public void addPoints(int amount) { setPoints(points + amount); }

    public void setPoints(int pts) {
        this.points    = Math.max(0, Math.min(100, pts));
        this.alignment = Alignment.fromPoints(this.points);
    }

    public boolean isBom()    { return alignment == Alignment.BOM;    }
    public boolean isMal()    { return alignment == Alignment.MAL;    }
    public boolean isNeutro() { return alignment == Alignment.NEUTRO; }

    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt("AlignPoints", points);
    }

    public void readFromNbt(NbtCompound nbt) {
        setPoints(nbt.getInt("AlignPoints"));
    }
}
EOF
echo "[AlignmentData.java] criado"

# =============================================================================
# PLAYER — MIXIN EXPANDIDO (Ki, BP, Transform, Skill, Alignment)
# =============================================================================
cat << 'EOF' > $BASE/mixin/PlayerEntityMixin.java
package com.bernardo.dbi.mixin;

import com.bernardo.dbi.alignment.AlignmentData;
import com.bernardo.dbi.bp.BattlePower;
import com.bernardo.dbi.ki.KiData;
import com.bernardo.dbi.player.*;
import com.bernardo.dbi.skill.SkillManager;
import com.bernardo.dbi.transformation.TransformationManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin central do DBI.
 * Contém: raça, idade, estilo, texturas (DBI original)
 *       + Ki, BP, Transformações, Skills, Alinhamento (portado do DBE).
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements DBIPlayerDataAccess {

    // ── Dados originais DBI ───────────────────────────────────────────────
    @Unique private Race          dbi$race  = Race.SAIYAN;
    @Unique private AgeStage      dbi$age   = AgeStage.ADULT;
    @Unique private FightingStyle dbi$style = FightingStyle.WARRIOR;
    @Unique private Identifier    dbi$eye;
    @Unique private Identifier    dbi$nose;
    @Unique private Identifier    dbi$mouth;
    @Unique private Identifier    dbi$tail;
    @Unique private int           dbi$eyePupilColor = 0x000000;
    @Unique private int           dbi$tailColor     = 0x8B4513;
    @Unique private int           dbi$skinColor     = 0xFFDBAC;

    // ── Dados novos (portados do DBE) ────────────────────────────────────
    @Unique private KiData              dbi$ki        = new KiData();
    @Unique private BattlePower         dbi$bp        = new BattlePower();
    @Unique private TransformationManager dbi$transform = new TransformationManager();
    @Unique private SkillManager        dbi$skills    = new SkillManager();
    @Unique private AlignmentData       dbi$alignment = new AlignmentData();
    @Unique private int                 dbi$tp        = 0;   // Training Points
    @Unique private boolean             dbi$charging  = false;

    // ── NBT Save ──────────────────────────────────────────────────────────
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void dbi_writeNbt(NbtCompound nbt, CallbackInfo ci) {
        // originais
        nbt.putString("dbi_race",  dbi$race.name());
        nbt.putString("dbi_age",   dbi$age.name());
        nbt.putString("dbi_style", dbi$style.name());
        if (dbi$eye   != null) nbt.putString("dbi_eye",   dbi$eye.toString());
        if (dbi$nose  != null) nbt.putString("dbi_nose",  dbi$nose.toString());
        if (dbi$mouth != null) nbt.putString("dbi_mouth", dbi$mouth.toString());
        if (dbi$tail  != null) nbt.putString("dbi_tail",  dbi$tail.toString());
        nbt.putInt("dbi_eye_pupil_color", dbi$eyePupilColor);
        nbt.putInt("dbi_tail_color",      dbi$tailColor);
        nbt.putInt("dbi_skin_color",      dbi$skinColor);
        // novos
        NbtCompound kiNbt = new NbtCompound(); dbi$ki.writeToNbt(kiNbt);       nbt.put("dbi_ki", kiNbt);
        NbtCompound bpNbt = new NbtCompound(); dbi$bp.writeToNbt(bpNbt);       nbt.put("dbi_bp", bpNbt);
        NbtCompound trNbt = new NbtCompound(); dbi$transform.writeToNbt(trNbt);nbt.put("dbi_transform", trNbt);
        NbtCompound skNbt = new NbtCompound(); dbi$skills.writeToNbt(skNbt);   nbt.put("dbi_skills", skNbt);
        NbtCompound alNbt = new NbtCompound(); dbi$alignment.writeToNbt(alNbt);nbt.put("dbi_alignment", alNbt);
        nbt.putInt("dbi_tp", dbi$tp);
    }

    // ── NBT Load ──────────────────────────────────────────────────────────
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void dbi_readNbt(NbtCompound nbt, CallbackInfo ci) {
        try { dbi$race  = Race.valueOf(nbt.getString("dbi_race"));          } catch (Exception ignored) {}
        try { dbi$age   = AgeStage.valueOf(nbt.getString("dbi_age"));       } catch (Exception ignored) {}
        try { dbi$style = FightingStyle.valueOf(nbt.getString("dbi_style"));} catch (Exception ignored) {}
        if (nbt.contains("dbi_eye"))   dbi$eye   = new Identifier(nbt.getString("dbi_eye"));
        if (nbt.contains("dbi_nose"))  dbi$nose  = new Identifier(nbt.getString("dbi_nose"));
        if (nbt.contains("dbi_mouth")) dbi$mouth = new Identifier(nbt.getString("dbi_mouth"));
        if (nbt.contains("dbi_tail"))  dbi$tail  = new Identifier(nbt.getString("dbi_tail"));
        if (nbt.contains("dbi_eye_pupil_color")) dbi$eyePupilColor = nbt.getInt("dbi_eye_pupil_color");
        if (nbt.contains("dbi_tail_color"))      dbi$tailColor     = nbt.getInt("dbi_tail_color");
        if (nbt.contains("dbi_skin_color"))      dbi$skinColor     = nbt.getInt("dbi_skin_color");
        if (nbt.contains("dbi_ki"))        dbi$ki.readFromNbt(nbt.getCompound("dbi_ki"));
        if (nbt.contains("dbi_bp"))        dbi$bp.readFromNbt(nbt.getCompound("dbi_bp"));
        if (nbt.contains("dbi_transform")) dbi$transform.readFromNbt(nbt.getCompound("dbi_transform"));
        if (nbt.contains("dbi_skills"))    dbi$skills.readFromNbt(nbt.getCompound("dbi_skills"));
        if (nbt.contains("dbi_alignment")) dbi$alignment.readFromNbt(nbt.getCompound("dbi_alignment"));
        dbi$tp = nbt.getInt("dbi_tp");
    }

    // ── Interface DBIPlayerDataAccess ─────────────────────────────────────
    @Override public Race             dbi_getRace()         { return dbi$race;      }
    @Override public AgeStage         dbi_getAge()          { return dbi$age;       }
    @Override public FightingStyle    dbi_getStyle()        { return dbi$style;     }
    @Override public Identifier       dbi_getEyeTexture()   { return dbi$eye;       }
    @Override public Identifier       dbi_getNoseTexture()  { return dbi$nose;      }
    @Override public Identifier       dbi_getMouthTexture() { return dbi$mouth;     }
    @Override public Identifier       dbi_getTailTexture()  { return dbi$tail;      }
    @Override public int              dbi_getEyePupilColor(){ return dbi$eyePupilColor; }
    @Override public int              dbi_getTailColor()    { return dbi$tailColor;  }
    @Override public int              dbi_getSkinColor()    { return dbi$skinColor;  }
    @Override public KiData           dbi_getKiData()       { return dbi$ki;         }
    @Override public BattlePower      dbi_getBp()           { return dbi$bp;         }
    @Override public TransformationManager dbi_getTransform(){ return dbi$transform; }
    @Override public SkillManager     dbi_getSkills()       { return dbi$skills;     }
    @Override public AlignmentData    dbi_getAlignment()    { return dbi$alignment;  }
    @Override public int              dbi_getTP()           { return dbi$tp;         }
    @Override public boolean          dbi_isCharging()      { return dbi$charging;   }

    @Override public void dbi_setRace(Race r)              { dbi$race  = r; }
    @Override public void dbi_setAge(AgeStage a)           { dbi$age   = a; }
    @Override public void dbi_setStyle(FightingStyle s)    { dbi$style = s; }
    @Override public void dbi_setEyeTexture(Identifier t)  { dbi$eye   = t; }
    @Override public void dbi_setNoseTexture(Identifier t) { dbi$nose  = t; }
    @Override public void dbi_setMouthTexture(Identifier t){ dbi$mouth = t; }
    @Override public void dbi_setTailTexture(Identifier t) { dbi$tail  = t; }
    @Override public void dbi_setEyePupilColor(int c)      { dbi$eyePupilColor = c; }
    @Override public void dbi_setTailColor(int c)          { dbi$tailColor = c;     }
    @Override public void dbi_setSkinColor(int c)          { dbi$skinColor = c;     }
    @Override public void dbi_addTP(int amount)            { dbi$tp = Math.max(0, dbi$tp + amount); }
    @Override public void dbi_setCharging(boolean v)       { dbi$charging = v;      }
}
EOF
echo "[PlayerEntityMixin.java] criado"

# =============================================================================
# DBIPlayerDataAccess — interface expandida
# =============================================================================
cat << 'EOF' > $BASE/player/DBIPlayerDataAccess.java
package com.bernardo.dbi.player;

import com.bernardo.dbi.alignment.AlignmentData;
import com.bernardo.dbi.bp.BattlePower;
import com.bernardo.dbi.ki.KiData;
import com.bernardo.dbi.skill.SkillManager;
import com.bernardo.dbi.transformation.TransformationManager;
import net.minecraft.util.Identifier;

/** Interface implementada pelo PlayerEntityMixin para expor os dados DBI. */
public interface DBIPlayerDataAccess {
    // ── Originais ─────────────────────────────────────────────────────────
    Race          dbi_getRace();
    AgeStage      dbi_getAge();
    FightingStyle dbi_getStyle();
    Identifier    dbi_getEyeTexture();
    Identifier    dbi_getNoseTexture();
    Identifier    dbi_getMouthTexture();
    Identifier    dbi_getTailTexture();
    int           dbi_getEyePupilColor();
    int           dbi_getTailColor();
    int           dbi_getSkinColor();
    void dbi_setRace(Race race);
    void dbi_setAge(AgeStage age);
    void dbi_setStyle(FightingStyle style);
    void dbi_setEyeTexture(Identifier t);
    void dbi_setNoseTexture(Identifier t);
    void dbi_setMouthTexture(Identifier t);
    void dbi_setTailTexture(Identifier t);
    void dbi_setEyePupilColor(int color);
    void dbi_setTailColor(int color);
    void dbi_setSkinColor(int color);
    // ── Novos (DBE merge) ─────────────────────────────────────────────────
    KiData              dbi_getKiData();
    BattlePower         dbi_getBp();
    TransformationManager dbi_getTransform();
    SkillManager        dbi_getSkills();
    AlignmentData       dbi_getAlignment();
    int                 dbi_getTP();
    boolean             dbi_isCharging();
    void dbi_addTP(int amount);
    void dbi_setCharging(boolean v);
}
EOF
echo "[DBIPlayerDataAccess.java] criado"

# =============================================================================
# NETWORK — Packet IDs expandido
# =============================================================================
cat << 'EOF' > $BASE/network/PacketIds.java
package com.bernardo.dbi.network;

import net.minecraft.util.Identifier;

/** IDs de todos os pacotes DBI. */
public class PacketIds {
    private static Identifier id(String path) { return new Identifier("dragonblockinfinity", path); }

    // Transformações
    public static final Identifier TRANSFORM_UP   = id("transform_up");
    public static final Identifier TRANSFORM_DOWN = id("transform_down");
    public static final Identifier POWER_DOWN     = id("power_down");

    // Ki
    public static final Identifier CHARGE_KI      = id("charge_ki");
    public static final Identifier KI_BLAST       = id("ki_blast");
    public static final Identifier KI_SUPPRESS    = id("ki_suppress");

    // Movimento
    public static final Identifier FLIGHT_TOGGLE  = id("flight_toggle");
    public static final Identifier DASH           = id("dash");

    // GUI / dados
    public static final Identifier SYNC_PLAYER    = id("sync_player");
    public static final Identifier OPEN_MENU      = id("open_menu");
}
EOF
echo "[PacketIds.java] criado"

cat << 'EOF' > $BASE/network/DBINetwork.java
package com.bernardo.dbi.network;

import com.bernardo.dbi.mixin.PlayerEntityMixin;
import com.bernardo.dbi.transformation.TransformationRegistry;
import com.bernardo.dbi.transformation.Transformation;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

/** Registra e trata todos os pacotes servidor do DBI. */
public class DBINetwork {

    public static void initialize() {
        // ── Transform Up (sobe um nível) ─────────────────────────────────
        ServerPlayNetworking.registerGlobalReceiver(PacketIds.TRANSFORM_UP, (server, player, handler, buf, sender) -> {
            server.execute(() -> handleTransformUp(player));
        });

        // ── Transform Down (desce um nível / desativa) ───────────────────
        ServerPlayNetworking.registerGlobalReceiver(PacketIds.TRANSFORM_DOWN, (server, player, handler, buf, sender) -> {
            server.execute(() -> handleTransformDown(player));
        });

        // ── Power Down (base) ─────────────────────────────────────────────
        ServerPlayNetworking.registerGlobalReceiver(PacketIds.POWER_DOWN, (server, player, handler, buf, sender) -> {
            server.execute(() -> ((PlayerEntityMixin)(Object)player).dbi_getTransform().deactivate());
        });

        // ── Charge Ki ─────────────────────────────────────────────────────
        ServerPlayNetworking.registerGlobalReceiver(PacketIds.CHARGE_KI, (server, player, handler, buf, sender) -> {
            server.execute(() -> {
                ((PlayerEntityMixin)(Object)player).dbi_setCharging(true);
            });
        });

        // ── Flight Toggle ─────────────────────────────────────────────────
        ServerPlayNetworking.registerGlobalReceiver(PacketIds.FLIGHT_TOGGLE, (server, player, handler, buf, sender) -> {
            server.execute(() -> {
                var skillMgr = ((PlayerEntityMixin)(Object)player).dbi_getSkills();
                if (skillMgr.hasSkill("flight")) {
                    var flight = com.bernardo.dbi.skill.SkillRegistry.get("flight");
                    if (flight != null) flight.onUse(player, skillMgr.getLevel("flight"));
                }
            });
        });
    }

    private static void handleTransformUp(ServerPlayerEntity player) {
        var data      = (PlayerEntityMixin)(Object)player;
        var transform = data.dbi_getTransform();
        var ki        = data.dbi_getKiData();
        String activeId = transform.getActiveId();

        // Determina o próximo nível
        String nextId = getNextTransformId(activeId);
        if (nextId == null) return;

        Transformation next = TransformationRegistry.get(nextId);
        if (next == null) return;

        transform.tryActivate(nextId, ki);
        // Recalcula BP
        data.dbi_getBp().recalculate(
            data.dbi_getRace(), data.dbi_getAge(), data.dbi_getStyle(),
            transform.getBpMultiplier(),
            ki.isSuppressionActive() ? ki.getSuppressionLevel() : 1.0f
        );
    }

    private static void handleTransformDown(ServerPlayerEntity player) {
        var data = (PlayerEntityMixin)(Object)player;
        data.dbi_getTransform().deactivate();
    }

    private static String getNextTransformId(String current) {
        // Cadeia simples de transformações
        return switch (current) {
            case ""     -> "ssj1";
            case "ssj1" -> "ssj2";
            case "ssj2" -> "ssj3";
            default     -> null;
        };
    }
}
EOF
echo "[DBINetwork.java] criado"

# =============================================================================
# KEY BINDINGS EXPANDIDO
# =============================================================================
cat << 'EOF' > $BASE/client/KeyBindings.java
package com.bernardo.dbi.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static KeyBinding OPEN_MENU;
    public static KeyBinding TRANSFORM_UP;
    public static KeyBinding TRANSFORM_DOWN;
    public static KeyBinding POWER_DOWN;
    public static KeyBinding FLIGHT_TOGGLE;
    public static KeyBinding CHARGE_KI;
    public static KeyBinding KI_BLAST;
    public static KeyBinding DASH;

    public static void initialize() {
        String CAT_DBI    = "key.categories.dbi";
        String CAT_COMBAT = "key.categories.dbi.combat";

        OPEN_MENU       = reg("key.dbi.open_menu",       GLFW.GLFW_KEY_K,            CAT_DBI);
        TRANSFORM_UP    = reg("key.dbi.transform_up",    GLFW.GLFW_KEY_R,            CAT_COMBAT);
        TRANSFORM_DOWN  = reg("key.dbi.transform_down",  GLFW.GLFW_KEY_F,            CAT_COMBAT);
        POWER_DOWN      = reg("key.dbi.power_down",      GLFW.GLFW_KEY_V,            CAT_COMBAT);
        FLIGHT_TOGGLE   = reg("key.dbi.flight",          GLFW.GLFW_KEY_G,            CAT_DBI);
        CHARGE_KI       = reg("key.dbi.charge_ki",       GLFW.GLFW_KEY_C,            CAT_COMBAT);
        KI_BLAST        = reg("key.dbi.ki_blast",        GLFW.GLFW_KEY_X,            CAT_COMBAT);
        DASH            = reg("key.dbi.dash",            GLFW.GLFW_KEY_LEFT_ALT,     CAT_DBI);
    }

    private static KeyBinding reg(String id, int key, String cat) {
        return KeyBindingHelper.registerKeyBinding(
            new KeyBinding(id, InputUtil.Type.KEYSYM, key, cat));
    }
}
EOF
echo "[KeyBindings.java] criado"

# =============================================================================
# CLIENT SETUP EXPANDIDO
# =============================================================================
cat << 'EOF' > $BASE/client/ClientSetup.java
package com.bernardo.dbi.client;

import com.bernardo.dbi.client.hud.DBIHudOverlay;
import com.bernardo.dbi.client.render.layer.AuraLayer;
import com.bernardo.dbi.network.PacketIds;
import com.bernardo.dbi.screen.CaracterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.fabricmc.fabric.impl.client.rendering.RegistrationHelper;

public class ClientSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindings.initialize();

        // ── HUD ───────────────────────────────────────────────────────────
        HudRenderCallback.EVENT.register(new DBIHudOverlay());

        // ── Registrar AuraLayer nos renderers do player ───────────────────
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, registrationHelper, ctx) -> {
            if (renderer instanceof PlayerEntityRenderer pr) {
                registrationHelper.register(new AuraLayer<>(pr));
            }
        });

        // ── Teclas → pacotes para o servidor ─────────────────────────────
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            while (KeyBindings.OPEN_MENU.wasPressed())
                client.setScreen(new CaracterScreen());

            while (KeyBindings.TRANSFORM_UP.wasPressed())
                ClientPlayNetworking.send(PacketIds.TRANSFORM_UP, net.minecraft.network.PacketByteBuf.EMPTY);

            while (KeyBindings.TRANSFORM_DOWN.wasPressed())
                ClientPlayNetworking.send(PacketIds.TRANSFORM_DOWN, net.minecraft.network.PacketByteBuf.EMPTY);

            while (KeyBindings.POWER_DOWN.wasPressed())
                ClientPlayNetworking.send(PacketIds.POWER_DOWN, net.minecraft.network.PacketByteBuf.EMPTY);

            while (KeyBindings.FLIGHT_TOGGLE.wasPressed())
                ClientPlayNetworking.send(PacketIds.FLIGHT_TOGGLE, net.minecraft.network.PacketByteBuf.EMPTY);

            while (KeyBindings.CHARGE_KI.wasPressed())
                ClientPlayNetworking.send(PacketIds.CHARGE_KI, net.minecraft.network.PacketByteBuf.EMPTY);

            while (KeyBindings.KI_BLAST.wasPressed())
                ClientPlayNetworking.send(PacketIds.KI_BLAST, net.minecraft.network.PacketByteBuf.EMPTY);
        });
    }
}
EOF
echo "[ClientSetup.java] criado"

# =============================================================================
# AURA LAYER — usa texturas animadas (256x4096 = 16 frames)
# =============================================================================
cat << 'EOF' > $BASE/client/render/layer/AuraLayer.java
package com.bernardo.dbi.client.render.layer;

import com.bernardo.dbi.client.render.RenderUtils;
import com.bernardo.dbi.mixin.PlayerEntityMixin;
import com.bernardo.dbi.transformation.Transformation;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

/**
 * Renderiza a aura de Ki/Transformação ao redor do jogador.
 *
 * TEXTURAS ANIMADAS (spritesheet vertical):
 *   ki_aura_base.png  = 256x4096 (16 frames de 256x256)
 *   ki_aura_ssj1.png  = 256x4096 (16 frames)
 *   ki_aura_ssj2.png  = 256x4096 (16 frames)
 *   ki_aura_ssj3.png  = 256x4096 (16 frames)
 *
 * O Fabric/Minecraft anima automaticamente via .mcmeta — NÃO dividimos
 * manualmente os frames aqui. Usamos a textura completa como atlas e
 * deixamos o RenderLayer.getEntityTranslucent() + mcmeta cuidarem da animação.
 */
public class AuraLayer<T extends AbstractClientPlayerEntity, M extends PlayerEntityModel<T>>
        extends FeatureRenderer<T, M> {

    // Texturas de aura — o Minecraft usa o .mcmeta para animar
    private static final Identifier AURA_BASE = new Identifier("dragonblockinfinity", "textures/fx/aura/ki_aura_base.png");
    private static final Identifier AURA_SSJ1 = new Identifier("dragonblockinfinity", "textures/fx/aura/ki_aura_ssj1.png");
    private static final Identifier AURA_SSJ2 = new Identifier("dragonblockinfinity", "textures/fx/aura/ki_aura_ssj2.png");
    private static final Identifier AURA_SSJ3 = new Identifier("dragonblockinfinity", "textures/fx/aura/ki_aura_ssj3.png");

    private static final int   SEGMENTS    = 24;
    private static final float AURA_RADIUS = 0.55f;
    private static final float AURA_HEIGHT = 2.2f;

    public AuraLayer(FeatureRendererContext<T, M> ctx) { super(ctx); }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, T entity,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {

        var data      = (PlayerEntityMixin)(Object)entity;
        var transform = data.dbi_getTransform();
        var ki        = data.dbi_getKiData();

        // Aura de Ki base (sempre visível se carregando)
        boolean isCharging    = data.dbi_isCharging();
        boolean isTransformed = transform.isTransformed();

        if (!isCharging && !isTransformed) return;

        // Escolhe textura e cor com base na transformação ativa
        Identifier auraTexture;
        int        auraColor;
        float      alpha;

        if (isTransformed) {
            Transformation t = transform.getActive();
            auraTexture = switch (t.id) {
                case "ssj1" -> AURA_SSJ1;
                case "ssj2" -> AURA_SSJ2;
                case "ssj3" -> AURA_SSJ3;
                default     -> AURA_BASE;
            };
            auraColor = t.auraColor;
            alpha     = 0.6f + 0.1f * (float) Math.sin(animationProgress * 0.3f);
        } else {
            // Aura base de carregamento
            auraTexture = AURA_BASE;
            auraColor   = 0xFFA4D8FF;
            alpha       = 0.25f + 0.15f * (float) Math.sin(animationProgress * 0.18f);
        }

        // Clamp alpha
        alpha = Math.max(0.15f, Math.min(0.75f, alpha));

        float[] rgb = RenderUtils.rgb(auraColor);
        float pulse = RenderUtils.getPulse(animationProgress * 10f);

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(auraTexture));

        matrices.push();
        matrices.translate(0.0f, 0.1f, 0.0f);
        drawCylinder(matrices, vc, light, pulse, rgb[0], rgb[1], rgb[2], alpha, animationProgress);
        matrices.pop();
    }

    /**
     * Desenha um cilindro de aura em 4 camadas concêntricas.
     * A coordenada UV vertical (v) percorre 0-1 sobre a textura completa
     * — o .mcmeta cuidará de mostrar o frame correto animado.
     */
    private static void drawCylinder(MatrixStack matrices, VertexConsumer vc,
                                     int light, float scale,
                                     float r, float g, float b, float alpha,
                                     float animProgress) {
        MatrixStack.Entry entry    = matrices.peek();
        Matrix4f          pos      = entry.getPositionMatrix();
        Matrix3f          norm     = entry.getNormalMatrix();
        float             radius   = AURA_RADIUS * scale;
        float             height   = AURA_HEIGHT;

        for (int layer = 0; layer < 4; layer++) {
            float yOff        = (layer / 4.0f) * height - height / 2.0f;
            float layerRadius = radius * (1.0f - layer * 0.15f);
            float layerAlpha  = alpha  * (1.0f - layer * 0.20f);
            float vTop        = layer       / 4.0f;
            float vBot        = (layer + 1) / 4.0f;

            for (int i = 0; i < SEGMENTS; i++) {
                float a1 = (float)(Math.PI * 2.0 *  i      / SEGMENTS) + animProgress * 0.05f;
                float a2 = (float)(Math.PI * 2.0 * (i + 1) / SEGMENTS) + animProgress * 0.05f;

                float sin1 = (float)Math.sin(a1), cos1 = (float)Math.cos(a1);
                float sin2 = (float)Math.sin(a2), cos2 = (float)Math.cos(a2);

                float x1 = cos1 * layerRadius, z1 = sin1 * layerRadius;
                float x2 = cos2 * layerRadius, z2 = sin2 * layerRadius;
                float u1 = i / (float)SEGMENTS, u2 = (i + 1) / (float)SEGMENTS;

                // Quad: dois triângulos (4 vértices)
                vc.vertex(pos, x1, yOff,          z1).color(r,g,b,layerAlpha).texture(u1,vTop).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(norm,cos1,0f,sin1).next();
                vc.vertex(pos, x2, yOff,          z2).color(r,g,b,layerAlpha).texture(u2,vTop).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(norm,cos2,0f,sin2).next();
                vc.vertex(pos, x2, yOff + height/4f, z2).color(r,g,b,layerAlpha).texture(u2,vBot).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(norm,cos2,0f,sin2).next();
                vc.vertex(pos, x1, yOff + height/4f, z1).color(r,g,b,layerAlpha).texture(u1,vBot).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(norm,cos1,0f,sin1).next();
            }
        }
    }
}
EOF
echo "[AuraLayer.java] criado"

# =============================================================================
# HUD OVERLAY
# =============================================================================
cat << 'EOF' > $BASE/client/hud/DBIHudOverlay.java
package com.bernardo.dbi.client.hud;

import com.bernardo.dbi.mixin.PlayerEntityMixin;
import com.bernardo.dbi.transformation.Transformation;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * HUD do DBI: barra de Ki, BP, TP e info de transformação ativa.
 * Usa a textura gui/life.png (512x512) para as barras.
 */
public class DBIHudOverlay implements HudRenderCallback {

    private static final Identifier LIFE_TEX = new Identifier("dragonblockinfinity", "textures/gui/life.png");
    private static final int BAR_W = 150;
    private static final int BAR_H = 10;
    private static final int PAD   = 10;

    @Override
    public void onHudRender(DrawContext ctx, RenderTickCounter ticker) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) return;

        var data = (PlayerEntityMixin)(Object)mc.player;
        var ki   = data.dbi_getKiData();
        var bp   = data.dbi_getBp();
        var tr   = data.dbi_getTransform();

        int sw = ctx.getScaledWindowWidth();
        int sh = ctx.getScaledWindowHeight();

        // ── Barra de Ki ───────────────────────────────────────────────────
        int bx = PAD, by = sh - 60;
        renderBar(ctx, bx, by, ki.getKiPercentage(), getKiColor(ki.getKiPercentage()),
                  String.format("Ki: %d/%d", (int)ki.getCurrentKi(), (int)ki.getMaxKi()), mc);

        // ── TP ────────────────────────────────────────────────────────────
        ctx.drawTextWithShadow(mc.textRenderer,
            Text.literal("§eTP: §f" + String.format("%,d", data.dbi_getTP())),
            PAD, sh - 45, 0xFFFFFF);

        // ── BP ────────────────────────────────────────────────────────────
        boolean suppressed = ki.isSuppressionActive();
        String bpStr = "§6BP: §f" + bp.getFormattedBP(suppressed) + (suppressed ? " §7(Suprimido)" : "");
        ctx.drawTextWithShadow(mc.textRenderer, Text.literal(bpStr), PAD, sh - 30, 0xFFFFFF);

        // ── Transformação ativa (centro topo) ─────────────────────────────
        if (tr.isTransformed()) {
            Transformation t     = tr.getActive();
            int            mast  = tr.getMastery(t.id);
            float          drain = t.getDrainPerSecond(mast);

            String tName  = "§6" + t.displayName + " §7(Dom: " + mast + "/10)";
            String tDrain = "§cDrain: §f" + String.format("%.1f", drain) + " Ki/s";

            int cx = sw / 2;
            ctx.drawCenteredTextWithShadow(mc.textRenderer, Text.literal(tName),  cx, 10, 0xFFFFFF);
            ctx.drawCenteredTextWithShadow(mc.textRenderer, Text.literal(tDrain), cx, 22, 0xFFFFFF);
        }
    }

    private void renderBar(DrawContext ctx, int x, int y, float pct,
                           int fillColor, String label, MinecraftClient mc) {
        // Fundo
        ctx.fill(x, y, x + BAR_W, y + BAR_H, 0x88000000);
        // Preenchimento
        ctx.fill(x, y, x + (int)(BAR_W * pct), y + BAR_H, fillColor);
        // Borda
        ctx.drawBorder(x, y, BAR_W, BAR_H, 0xFFFFFFFF);
        // Texto
        ctx.drawTextWithShadow(mc.textRenderer, Text.literal(label), x + 3, y + 1, 0xFFFFFF);
    }

    private int getKiColor(float pct) {
        if (pct > 0.7f) return 0xFF00FFFF;
        if (pct > 0.4f) return 0xFF00FF00;
        if (pct > 0.2f) return 0xFFFFFF00;
        return 0xFFFF0000;
    }
}
EOF
echo "[DBIHudOverlay.java] criado"

# =============================================================================
# Dbi.java PRINCIPAL — atualizado
# =============================================================================
cat << 'EOF' > $BASE/Dbi.java
package com.bernardo.dbi;

import com.bernardo.dbi.network.DBINetwork;
import com.bernardo.dbi.player.DBIPlayerEvents;
import com.bernardo.dbi.registry.DBIBlocks;
import com.bernardo.dbi.registry.DBICreativeTab;
import com.bernardo.dbi.registry.DBIEntities;
import com.bernardo.dbi.registry.DBIItems;
import com.bernardo.dbi.skill.SkillRegistry;
import com.bernardo.dbi.transformation.TransformationRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dbi implements ModInitializer {

    public static final String MOD_ID = "dragonblockinfinity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("=== Dragon Block Infinity — Iniciando ===");

        DBIBlocks.initialize();
        DBIItems.initialize();
        DBICreativeTab.initialize();
        DBIEntities.initialize();
        DBIPlayerEvents.initialize();

        TransformationRegistry.register();
        LOGGER.info("Transformações registradas: {}", TransformationRegistry.all().size());

        SkillRegistry.register();
        LOGGER.info("Skills registradas: {}", SkillRegistry.all().size());

        DBINetwork.initialize();

        LOGGER.info("=== Dragon Block Infinity — Pronto! ===");
    }
}
EOF
echo "[Dbi.java] criado"

echo ""
echo "============================================"
echo "  SETUP COMPLETO!"
echo "  Todos os arquivos foram gerados."
echo "============================================"
