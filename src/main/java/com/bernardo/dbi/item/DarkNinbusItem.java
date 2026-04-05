package com.bernardo.dbi.item;

import com.bernardo.dbi.alinhamento.AlinhamentoHelper;
import com.bernardo.dbi.entity.DarkNimbusEntity;
import com.bernardo.dbi.registry.DBIEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DarkNinbusItem extends Item {

    public DarkNinbusItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            // Dark Ninbus rejects good-aligned players
            if (!AlinhamentoHelper.isBom(player)) {
                DarkNimbusEntity darkNimbus = new DarkNimbusEntity(DBIEntities.DARK_NIMBUS, world);
                darkNimbus.setPosition(player.getPos().add(0, 0.5, 0));
                world.spawnEntity(darkNimbus);
                stack.decrement(1);
                player.sendMessage(Text.literal("A Nuvem Voadora das Trevas responde ao seu chamado!"), true);
            } else {
                player.sendMessage(Text.literal("A Nuvem Voadora das Trevas rejeita você..."), true);
                stack.decrement(1);
            }
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}
