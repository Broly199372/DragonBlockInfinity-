package com.bernardo.dbi.entity;

import com.bernardo.dbi.alinhamento.AlinhamentoHelper;
import com.bernardo.dbi.registry.DBIItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public abstract class DarkFlyNinbus extends Entity {

    int maxSpeed = 20;
    int minSpeed = 0;
    boolean canFly = true;

    public DarkFlyNinbus(EntityType<? extends DarkFlyNinbus> type, World world) {
        super(type, world);
    }

    public ActionResult interact(PlayerEntity player, Hand hand, EntityHitResult hit) {
        if (!this.getWorld().isClient) {
            if (AlinhamentoHelper.isBom(player)) {
                player.startRiding(this);
            } else {
                dropItem(DBIItems.NIMBUS);
                this.remove(RemovalReason.DISCARDED);
            }
        }
        return ActionResult.SUCCESS;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed > 20) {
            maxSpeed = 20;
        }
        this.maxSpeed = maxSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        if (minSpeed < 0) {
            minSpeed = 0;
        }
        this.minSpeed = minSpeed;
    }
}
