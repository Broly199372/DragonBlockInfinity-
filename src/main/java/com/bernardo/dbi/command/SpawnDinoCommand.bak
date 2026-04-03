package com.bernardo.dbi.command;

import com.bernardo.dbi.entity.VelociraptorEntity;
import com.bernardo.dbi.registry.DBIEntities;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class SpawnDinoCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(CommandManager.literal("spawndino")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(context -> spawnDino(context)));
    }

    private static int spawnDino(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        Vec3d pos = source.getPosition();

        Entity dino = new VelociraptorEntity(DBIEntities.VELOCIRAPTOR, source.getWorld());
        dino.setPosition(pos.x, pos.y, pos.z);
        source.getWorld().spawnEntity(dino);
        source.sendMessage(Text.literal("Spawned Dino at your location!"));
        return 1;
    }
}