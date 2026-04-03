package com.bernardo.dbi.alinhamento;

import net.minecraft.entity.player.PlayerEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AlinhamentoHelper {

    private static final Map<UUID, PlayerData> dados = new HashMap<>();

    public static void registrar(PlayerEntity player, PlayerData data) {
        dados.put(player.getUuid(), data);
    }

    public static String getAlinhamento(PlayerEntity player) {
        PlayerData data = dados.get(player.getUuid());
        if (data == null) return "indefinido";
        return data.getAlinhamento();
    }

    public static boolean isBom(PlayerEntity player) {
        return getAlinhamento(player).equals("bom");
    }
}
