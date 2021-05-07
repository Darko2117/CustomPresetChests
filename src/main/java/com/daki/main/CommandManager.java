package com.daki.main;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager extends JavaPlugin {

    public static void registerCommands(){

        CustomPresetChests.getInstance().getCommand("cpc").setExecutor(new CPCCommand());

    }

}
