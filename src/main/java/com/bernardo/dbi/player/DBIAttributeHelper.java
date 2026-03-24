package com.bernardo.dbi.player;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class DBIAttributeHelper {

    private static final UUID DAMAGE_UUID  = UUID.fromString("a3b4c5d6-e7f8-1234-abcd-000000000001");
    private static final UUID DEFENSE_UUID = UUID.fromString("a3b4c5d6-e7f8-1234-abcd-000000000002");

    private static final String DAMAGE_NAME  = "dbi_damage_modifier";
    private static final String DEFENSE_NAME = "dbi_defense_modifier";

    public static void applyModifiers(PlayerEntity player) {
        float damage  = DBIPlayerData.getFinalDamage(player);
        float defense = DBIPlayerData.getFinalDefense(player);

        // Dano de ataque
        var attackAttr = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (attackAttr != null) {
            attackAttr.removeModifier(DAMAGE_UUID);
            attackAttr.addTemporaryModifier(new EntityAttributeModifier(
                DAMAGE_UUID,
                DAMAGE_NAME,
                damage - 1.0f,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
            ));
        }

        // Armadura (defesa)
        var armorAttr = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
        if (armorAttr != null) {
            armorAttr.removeModifier(DEFENSE_UUID);
            armorAttr.addTemporaryModifier(new EntityAttributeModifier(
                DEFENSE_UUID,
                DEFENSE_NAME,
                defense - 1.0f,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
            ));
        }

        // Força a atualização da hitbox de tamanho
        player.calculateDimensions();
    }

    public static void removeModifiers(PlayerEntity player) {
        var attackAttr = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (attackAttr != null) attackAttr.removeModifier(DAMAGE_UUID);

        var armorAttr = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
        if (armorAttr != null) armorAttr.removeModifier(DEFENSE_UUID);
    }
}
