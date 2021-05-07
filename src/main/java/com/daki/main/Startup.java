package com.daki.main;

public class Startup {

    public static void initialize(){

        ListenerManager.registerEvents();
        CommandManager.registerCommands();

    }

}
