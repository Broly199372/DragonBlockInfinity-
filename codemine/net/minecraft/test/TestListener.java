/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.test;

import net.minecraft.test.GameTestState;

public interface TestListener {
    public void onStarted(GameTestState var1);

    public void onPassed(GameTestState var1);

    public void onFailed(GameTestState var1);
}

