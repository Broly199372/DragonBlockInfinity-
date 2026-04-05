package com.bernardo.dbi.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class DarkNimbusEntity extends DarkFlyNinbus {

    public DarkNimbusEntity(EntityType<? extends DarkFlyNinbus> type, World world) {
        super(type, world);
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