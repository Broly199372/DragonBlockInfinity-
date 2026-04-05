/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.client.realms.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

@Environment(value=EnvType.CLIENT)
public interface Errable {
    public void error(Text var1);

    default public void error(String errorMessage) {
        this.error(Text.literal(errorMessage));
    }
}

