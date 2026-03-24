package com.bernardo.dbi;

import com.bernardo.dbi.player.DBIPlayerEvents;
import com.bernardo.dbi.registry.DBIBlocks;
import com.bernardo.dbi.registry.DBICreativeTab;
import com.bernardo.dbi.registry.DBIItems;
import net.fabricmc.api.ModInitializer;

public class Dbi implements ModInitializer {

    public static final String MOD_ID = "dragonblockinfinity";

    @Override
    public void onInitialize() {
        DBIBlocks.initialize();
        DBIItems.initialize();
        DBICreativeTab.initialize();
        DBIPlayerEvents.initialize();
    }
}
