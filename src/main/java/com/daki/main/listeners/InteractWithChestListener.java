package com.daki.main.listeners;

import com.daki.main.Methods;
import com.daki.main.managers.ConfigManager;
import com.daki.main.managers.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class InteractWithChestListener implements Listener {

    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (!event.getClickedBlock().getType().equals(Material.CHEST)) return;
        if (!UserManager.getUserFromPlayer(event.getPlayer()).getSetting()) return;

        String name = UserManager.getUserFromPlayer(event.getPlayer()).getSettingName();

        List<String> locations = new ConfigManager().getSetChestLocations(name);
        String newLocation = new Methods().getBetterLocationString(event.getClickedBlock().getLocation());

        if (!locations.contains(newLocation)) locations.add(newLocation);

        new ConfigManager().writeSetChestToConfig(name, locations);

        UserManager.getUserFromPlayer(event.getPlayer()).setSetting(false);
        UserManager.getUserFromPlayer(event.getPlayer()).setSettingName("");

        event.getPlayer().sendMessage(ChatColor.GREEN + "Chest set!");
        event.setCancelled(true);

    }

}
