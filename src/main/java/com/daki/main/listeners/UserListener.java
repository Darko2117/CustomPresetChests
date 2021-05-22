package com.daki.main.listeners;

import com.daki.main.managers.UserManager;
import com.daki.main.objects.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {

        UserManager.addUser(new User(event.getPlayer()));

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {

        UserManager.removeUser(UserManager.getUserFromPlayer(event.getPlayer()));

    }

}