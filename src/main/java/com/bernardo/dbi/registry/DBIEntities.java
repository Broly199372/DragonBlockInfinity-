package com.bernardo.dbi.registry;

import com.bernardo.dbi.entity.NimbusEntity;
import com.bernardo.dbi.entity.DarkNimbusEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DBIEntities {

    public static final EntityType<NimbusEntity> NIMBUS = register("nimbus",
        FabricEntityTypeBuilder.<NimbusEntity>create(SpawnGroup.MISC, NimbusEntity::new)
            .dimensions(EntityDimensions.fixed(0.6f, 0.4f))
            .build());

    public static final EntityType<DarkNimbusEntity> DARK_NIMBUS = register("dark_nimbus",
        FabricEntityTypeBuilder.<DarkNimbusEntity>create(SpawnGroup.MISC, DarkNimbusEntity::new)
            .dimensions(EntityDimensions.fixed(0.6f, 0.4f))
            .build());

    public static void initialize() {
    }

    private static <T extends net.minecraft.entity.Entity> EntityType<T> register(String name, EntityType<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier("dragonblockinfinity", name), type);
    }
}