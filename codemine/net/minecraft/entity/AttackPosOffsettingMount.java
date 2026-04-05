/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.entity;

/**
 * A mount which offsets the attack position of its passengers.
 * @see net.minecraft.entity.LivingEntity#getAttackPos
 */
public interface AttackPosOffsettingMount {
    /**
     * {@return an offset to the Y co-ordinate of passengers' positions, for use in their attack positions}
     * @see net.minecraft.entity.LivingEntity#getAttackPos
     */
    public double getPassengerAttackYOffset();
}

