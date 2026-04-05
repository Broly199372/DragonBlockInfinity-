/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package net.minecraft.advancement;

import java.util.Collection;

public interface CriterionMerger {
    public static final CriterionMerger AND = criteriaNames -> {
        String[][] strings = new String[criteriaNames.size()][];
        int i = 0;
        for (String string : criteriaNames) {
            strings[i++] = new String[]{string};
        }
        return strings;
    };
    public static final CriterionMerger OR = String[][]::new;

    public String[][] createRequirements(Collection<String> var1);
}

