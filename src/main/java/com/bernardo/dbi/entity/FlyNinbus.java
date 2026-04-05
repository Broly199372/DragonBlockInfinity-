package com.bernardo.dbi.entity;

import com.bernardo.dbi.alinhamento.AlinhamentoHelper;
import com.bernardo.dbi.registry.DBIItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class FlyNinbus extends Entity {

    public FlyNinbus(EntityType<? extends FlyNinbus> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        // No data tracker needed for this entity
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            if (AlinhamentoHelper.isBom(player)) {
                player.startRiding(this);
            } else {
                this.dropItem(DBIItems.NIMBUS);
                this.remove(RemovalReason.DISCARDED);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient && this.hasPassengers()) {
            Entity passenger = this.getFirstPassenger();
            if (passenger instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) passenger;

                // Handle movement controls - simplified for server-side
                // Movement will be handled client-side through input detection
                // For now, just apply basic floating behavior

                // Apply gravity if not ascending (simplified)
                this.setVelocity(this.getVelocity().multiply(0.9).add(0, -0.01, 0));

                // Apply velocity
                this.move(net.minecraft.entity.MovementType.SELF, this.getVelocity());
            }
        }
    }

    public boolean canBeRiddenInWater() {
        return true;
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        // Custom data saving if needed
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        // Custom data loading if needed
    }
}
