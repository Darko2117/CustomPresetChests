package com.daki.main.managers;

import com.daki.main.CustomPresetChests;
import com.daki.main.listeners.InteractWithChestListener;
import com.daki.main.listeners.InventoryCloseListener;
import com.daki.main.listeners.UserListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager extends JavaPlugin {

    public static void registerEvents() {

        registerEvents(
                new InventoryCloseListener(),
                new UserListener(),
                new InteractWithChestListener()
        );


    }

    private static void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            CustomPresetChests.getInstance().getServer().getPluginManager().registerEvents(listener, CustomPresetChests.getInstance());
        }
    }

}
