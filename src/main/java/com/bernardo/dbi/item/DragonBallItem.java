package com.bernardo.dbi.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DragonBallItem extends Item {

    private final int ballNumber;

    public DragonBallItem(Settings settings, int ballNumber) {
        super(settings);
        this.ballNumber = ballNumber;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true; // Dragon Balls glow
    }

    public int getBallNumber() {
        return ballNumber;
    }
}