package com.daki.main.managers;

public class StartupManager {

    public static void initialize(){

        ListenerManager.registerEvents();
        CommandManager.registerCommands();
        ConfigManager.initialize();

    }

}
