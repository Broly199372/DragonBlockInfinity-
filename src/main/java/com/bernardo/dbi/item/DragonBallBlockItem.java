package com.bernardo.dbi.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class DragonBallBlockItem extends BlockItem {

    private final int ballNumber;

    public DragonBallBlockItem(Block block, Settings settings, int ballNumber) {
        super(block, settings);
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