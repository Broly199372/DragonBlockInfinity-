/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public interface ArmorMaterial {
    public int getDurability(ArmorItem.Type var1);

    public int getProtection(ArmorItem.Type var1);

    public int getEnchantability();

    public SoundEvent getEquipSound();

    public Ingredient getRepairIngredient();

    public String getName();

    public float getToughness();

    public float getKnockbackResistance();
}

