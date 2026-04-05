package com.bernardo.dbi.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PesoItem extends Item {

    public PesoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            // Training weight functionality
            user.sendMessage(Text.literal("Peso de treinamento equipado! Aumenta sua força ao carregar."), true);
            // In a real implementation, this could apply slowness effect or increase stats
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
    }
}