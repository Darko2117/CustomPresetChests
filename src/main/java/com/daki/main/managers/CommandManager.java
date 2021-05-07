package com.daki.main.managers;

import com.daki.main.commands.CPCCommand;
import com.daki.main.CustomPresetChests;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager extends JavaPlugin {

    public static void registerCommands(){

        CustomPresetChests.getInstance().getCommand("cpc").setExecutor(new CPCCommand());

    }

}
