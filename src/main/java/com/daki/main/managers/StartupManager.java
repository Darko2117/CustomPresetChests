package com.daki.main.managers;

import com.daki.main.managers.CommandManager;
import com.daki.main.managers.ListenerManager;

public class StartupManager {

    public static void initialize(){

        ListenerManager.registerEvents();
        CommandManager.registerCommands();

    }

}
