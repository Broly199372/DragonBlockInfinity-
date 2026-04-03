package com.bernardo.dbi.registry;

import com.bernardo.dbi.item.NinbusItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DBIItems {

    public static final Item BLOCK_DRAGONBALL1 = registerBlockItem("block_dragonball1", DBIBlocks.BLOCK_DRAGONBALL1);
    public static final Item BLOCK_DRAGONBALL2 = registerBlockItem("block_dragonball2", DBIBlocks.BLOCK_DRAGONBALL2);
    public static final Item BLOCK_DRAGONBALL3 = registerBlockItem("block_dragonball3", DBIBlocks.BLOCK_DRAGONBALL3);
    public static final Item BLOCK_DRAGONBALL4 = registerBlockItem("block_dragonball4", DBIBlocks.BLOCK_DRAGONBALL4);
    public static final Item BLOCK_DRAGONBALL5 = registerBlockItem("block_dragonball5", DBIBlocks.BLOCK_DRAGONBALL5);
    public static final Item BLOCK_DRAGONBALL6 = registerBlockItem("block_dragonball6", DBIBlocks.BLOCK_DRAGONBALL6);
    public static final Item BLOCK_DRAGONBALL7 = registerBlockItem("block_dragonball7", DBIBlocks.BLOCK_DRAGONBALL7);

    public static final Item NAMEK_GRASS             = registerBlockItem("namek_grass",             DBIBlocks.NAMEK_GRASS);
    public static final Item NAMEK_DIRTY             = registerBlockItem("namek_dirty",             DBIBlocks.NAMEK_DIRTY);
    public static final Item DIRTY_STONE             = registerBlockItem("dirty_stone",             DBIBlocks.DIRTY_STONE);
    public static final Item DIRTY_STONE_COBBLESTONE = registerBlockItem("dirty_stone_cobblestone", DBIBlocks.DIRTY_STONE_COBBLESTONE);

    public static final Item SENZU = register("senzu",
        new Item(new Item.Settings()
            .food(new FoodComponent.Builder()
                .hunger(10)
                .saturationModifier(1.0f)
                .alwaysEdible()
                .build())));

    public static final Item DINO_MEAT = register("dino_meat",
        new Item(new Item.Settings()
            .food(new FoodComponent.Builder()
                .hunger(3)
                .saturationModifier(0.3f)
                .build())));

    public static final Item DINO_MEAT_COOKED = register("dino_meat_cooked",
        new Item(new Item.Settings()
            .food(new FoodComponent.Builder()
                .hunger(8)
                .saturationModifier(0.8f)
                .build())));

    public static final Item NIMBUS  = register("nimbus",  new NinbusItem(new Item.Settings().maxCount(1)));
    public static final Item RADAR   = register("radar",   new Item(new Item.Settings().maxCount(1)));
    public static final Item PESO    = register("peso",    new Item(new Item.Settings().maxCount(16)));
    public static final Item SCOUTER = register("scouter", new Item(new Item.Settings().maxCount(1)));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("dragonblockinfinity", name), item);
    }

    private static Item registerBlockItem(String name, Block block) {
        return register(name, new BlockItem(block, new Item.Settings()));
    }

    public static void initialize() {}
}
