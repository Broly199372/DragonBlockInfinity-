
package com.bernardo.dbi.screen;

import com.bernardo.dbi.player.AgeStage;
import com.bernardo.dbi.player.DBIPlayerData;
import com.bernardo.dbi.player.Race;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AppearanceManager {

    // ── Opções internas (IDs) ─────────────────────────────────────────────────
    // ATENÇÃO: os índices de GLOBAL_OPTIONS e DISPLAY_OPTIONS devem corresponder!
    private static final Map<String, List<String>> GLOBAL_OPTIONS = Map.of(
        "race",       Arrays.asList("SAIYAN", "HALF_SAIYAN", "NAMEKIAN", "ARCOSIAN"),
        "age",        Arrays.asList("CHILD", "YOUNG", "ADULT", "OLD"),
        "hair",       Arrays.asList("hair_base", "hair_goku", "hair_vegeta", "hair_gohan", "hair_trunks"),
        "hair_color", Arrays.asList("BLACK", "BROWN", "BLONDE", "WHITE", "RED"),
        "eyes",       Arrays.asList("eye1", "eye2", "eye_arcosian1"),
        "nose",       Arrays.asList("nose1", "nose2", "nose3"),
        "mouth",      Arrays.asList("mouth1", "mouth2", "mouth3")
    );

    // ── Opções de display (nomes legíveis) ────────────────────────────────────
    private static final Map<String, List<String>> DISPLAY_OPTIONS = Map.of(
        "race",       Arrays.asList("Saiyajin", "Meio-Saiyajin", "Namekiano", "Arcosiano"),
        "age",        Arrays.asList("Criança", "Jovem", "Adulto", "Velho"),
        "hair",       Arrays.asList("Base", "Goku", "Vegeta", "Gohan", "Trunks"),
        "hair_color", Arrays.asList("Preto", "Marrom", "Loiro", "Branco", "Vermelho"),
        "eyes",       Arrays.asList("Olho 1", "Olho 2", "Olho Arcosiano"),
        "nose",       Arrays.asList("Nariz 1", "Nariz 2", "Nariz 3"),
        "mouth",      Arrays.asList("Boca 1", "Boca 2", "Boca 3")
    );

    // ── Mapeamento textura → fileName ─────────────────────────────────────────
    private static final Map<String, String> HAIR_FILE = Map.of(
        "hair_base",   "hair_base.png",
        "hair_goku",   "hair_goku_overlay.png",
        "hair_vegeta", "hair_vegeta_overlay.png",
        "hair_gohan",  "hair_gohan_overlay.png",
        "hair_trunks", "hair_trunks_overlay.png"
    );

    public static List<String> getOptionsFor(String option, String race) {
        return GLOBAL_OPTIONS.getOrDefault(option, List.of());
    }

    public static List<String> getDisplayOptionsFor(String option, String race) {
        return DISPLAY_OPTIONS.getOrDefault(option, List.of());
    }

    // ── Retorna o índice atual de cada opção ──────────────────────────────────
    public static int getCurrentValue(PlayerEntity player, String option) {
        return switch (option) {
            case "race" -> {
                Race r = DBIPlayerData.getRace(player);
                yield r != null ? r.ordinal() : 0;
            }
            case "age" -> {
                AgeStage a = DBIPlayerData.getAge(player);
                yield a != null ? a.ordinal() : 0;
            }
            case "hair" -> {
                Identifier tex = DBIPlayerData.getHairTexture(player);
                if (tex == null) yield 1; // padrão: Goku
                String path = tex.getPath();
                List<String> keys = GLOBAL_OPTIONS.get("hair");
                for (int i = 0; i < keys.size(); i++) {
                    if (path.contains(keys.get(i).replace("hair_", ""))) yield i;
                }
                yield 1;
            }
            case "eyes" -> {
                Identifier tex = DBIPlayerData.getEyeTexture(player);
                if (tex == null) yield 0;
                String path = tex.getPath();
                List<String> keys = GLOBAL_OPTIONS.get("eyes");
                for (int i = 0; i < keys.size(); i++) {
                    if (path.contains(keys.get(i))) yield i;
                }
                yield 0;
            }
            case "nose" -> {
                Identifier tex = DBIPlayerData.getNoseTexture(player);
                if (tex == null) yield 0;
                String path = tex.getPath();
                List<String> keys = GLOBAL_OPTIONS.get("nose");
                for (int i = 0; i < keys.size(); i++) {
                    if (path.contains(keys.get(i))) yield i;
                }
                yield 0;
            }
            case "mouth" -> {
                Identifier tex = DBIPlayerData.getMouthTexture(player);
                if (tex == null) yield 0;
                String path = tex.getPath();
                List<String> keys = GLOBAL_OPTIONS.get("mouth");
                for (int i = 0; i < keys.size(); i++) {
                    if (path.contains(keys.get(i))) yield i;
                }
                yield 0;
            }
            default -> 0;
        };
    }

    // ── Atualiza a aparência do jogador ───────────────────────────────────────
    public static void updateAppearance(PlayerEntity player, String option, int value) {
        switch (option) {
            case "race" -> {
                Race[] races = Race.values();
                if (value >= 0 && value < races.length)
                    DBIPlayerData.setRace(player, races[value]);
            }
            case "age" -> {
                AgeStage[] ages = AgeStage.values();
                if (value >= 0 && value < ages.length)
                    DBIPlayerData.setAge(player, ages[value]);
            }
            case "hair" -> {
                List<String> opts = GLOBAL_OPTIONS.get("hair");
                if (value >= 0 && value < opts.size()) {
                    String id = opts.get(value);
                    String fileName = HAIR_FILE.getOrDefault(id, id + ".png");
                    DBIPlayerData.setHairTexture(player,
                        new Identifier("dragonblockinfinity", "textures/hairs/" + fileName));
                }
            }
            case "eyes" -> {
                List<String> opts = GLOBAL_OPTIONS.get("eyes");
                if (value >= 0 && value < opts.size()) {
                    DBIPlayerData.setEyeTexture(player,
                        new Identifier("dragonblockinfinity", "textures/eyes/" + opts.get(value) + ".png"));
                }
            }
            case "nose" -> {
                List<String> opts = GLOBAL_OPTIONS.get("nose");
                if (value >= 0 && value < opts.size()) {
                    DBIPlayerData.setNoseTexture(player,
                        new Identifier("dragonblockinfinity", "textures/nose/" + opts.get(value) + ".png"));
                }
            }
            case "mouth" -> {
                List<String> opts = GLOBAL_OPTIONS.get("mouth");
                if (value >= 0 && value < opts.size()) {
                    DBIPlayerData.setMouthTexture(player,
                        new Identifier("dragonblockinfinity", "textures/mouth/" + opts.get(value) + ".png"));
                }
            }
            case "hair_color" -> {
                // Reservado para futura implementação de colorização
            }
        }
    }
}
