package com.bernardo.dbi.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ScouterItem extends Item implements Equipment {

    public ScouterItem(Settings settings) {
        super(settings);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD; // Scouter is worn on head
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true; // Make it glow
    }
}