package com.daki.main;

import com.daki.main.managers.StartupManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPresetChests extends JavaPlugin {

    public static CustomPresetChests instance;

    public static CustomPresetChests getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        CustomPresetChests.getInstance().getLogger().info("--------------------------------------------------");

        StartupManager.initialize();

        CustomPresetChests.getInstance().getLogger().info("CustomPresetChests started!");
        CustomPresetChests.getInstance().getLogger().info("--------------------------------------------------");

    }

}
