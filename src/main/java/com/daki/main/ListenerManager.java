package com.daki.main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager extends JavaPlugin {

    public static void registerEvents() {



    }

    private static void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            CustomPresetChests.getInstance().getServer().getPluginManager().registerEvents(listener, CustomPresetChests.getInstance());
        }
    }

}
