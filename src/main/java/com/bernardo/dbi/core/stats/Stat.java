package com.bernardo.dbi.core.stats;

public enum Stat {
    STR("Strength", "str", "Força bruta — dano físico"),
    DEX("Dexterity", "dex", "Defesa + Velocidade (corrida e voo)"),
    CON("Constitution", "con", "Vida máxima + Stamina máxima"),
    WILL("Willpower", "will", "Dano de Ki + Power de Ki"),
    MND("Mind", "mnd", "Aumenta ganho de TP em batalha"),
    SPI("Spirit", "spi", "Ki máximo + Opressão de Ki");

    public final String displayName;
    public final String shortName;
    public final String description;

    Stat(String displayName, String shortName, String description) {
        this.displayName = displayName;
        this.shortName   = shortName;
        this.description = description;
    }
}
