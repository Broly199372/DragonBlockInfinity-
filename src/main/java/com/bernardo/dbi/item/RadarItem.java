package com.bernardo.dbi.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RadarItem extends Item {

    public RadarItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            // Simple radar functionality - in a real implementation, this would scan for Dragon Balls
            user.sendMessage(Text.literal("Radar ativado! Procurando Dragon Balls..."), true);
            // For now, just a placeholder message
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true; // Make it glow
    }
}