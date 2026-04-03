package com.bernardo.dbi.item;

import com.bernardo.dbi.alinhamento.AlinhamentoHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class NinbusItem extends Item {

    public NinbusItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            if (AlinhamentoHelper.isBom(player)) {
                player.sendMessage(Text.literal("A Nuvem Voadora responde ao seu chamado!"), true);
            } else {
                player.sendMessage(Text.literal("A Nuvem Voadora rejeita você..."), true);
                stack.decrement(1);
            }
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}
