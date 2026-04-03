package com.bernardo.dbi.alinhamento;

public class PlayerData {

    private String name;
    private int good;
    private int neutral;
    private int evil;

    public PlayerData(String name, int good, int neutral, int evil) {
        int total = good + neutral + evil;
        if (total > 100) {
            throw new IllegalArgumentException(
                "A soma de good, neutral e evil nao pode exceder 100"
            );
        }
        this.name    = name;
        this.good    = good;
        this.neutral = neutral;
        this.evil    = evil;
    }

    public String getAlinhamento() {
        int total = good + neutral + evil;
        if (total == 0) return "indefinido";

        float goodPct = (float) good / total;

        if (goodPct >= 0.75f) {
            return "bom";
        } else if (goodPct >= 0.45f) {
            return "neutro";
        } else {
            return "mal";
        }
    }

    public String getName()    { return name; }
    public int    getGood()    { return good; }
    public int    getNeutral() { return neutral; }
    public int    getEvil()    { return evil; }
}
