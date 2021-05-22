package com.daki.main.commands;

import com.daki.main.CustomPresetChests;
import com.daki.main.Methods;
import com.daki.main.managers.ConfigManager;
import com.daki.main.managers.UserManager;
import com.daki.main.objects.ChestPreset;
import com.daki.main.objects.PresetEditInventoryHolder;
import com.daki.main.objects.PresetItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CPCCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) return true;
        //if (strings.length < 2) return true;

        Player player = (Player) commandSender;

        if (strings[0].equals("edit")) {
            String chestName = strings[1];
            openEditUI(player, chestName);
        } else if (strings[0].equals("setchest")) {
            String chestName = strings[1];
            UserManager.getUserFromPlayer(player).setSetting(true);
            UserManager.getUserFromPlayer(player).setSettingName(chestName);
            player.sendMessage(ChatColor.GOLD + "Right click a chest to set it as the preset: " + chestName);
        } else if (strings[0].equals("loadchests")) {
            String chestName = strings[1];
            loadChests(chestName);
        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (!(sender instanceof Player)) return null;

        if (args.length == 1) {
            return new ArrayList<String>(Arrays.asList("edit", "setchest", "loadchests"));
        }

        if (args[0].equals("edit") && args.length == 2) {
            return new ConfigManager().getAllChestPresetNames();
        } else if (args[0].equals("setchest") && args.length == 2) {
            return new ConfigManager().getAllChestPresetNames();
        } else if (args[0].equals("loadchests") && args.length == 2) {
            return new ConfigManager().getAllChestPresetNames();
        }

        return null;

    }

    void openEditUI(Player player, String chestName) {

        ChestPreset chestPreset = new ConfigManager().getChestPresetFromConfig(chestName);

        String inventoryTitle = "Editing chest preset: " + chestPreset.getName();

        Inventory UI = Bukkit.createInventory(new PresetEditInventoryHolder(), 54, inventoryTitle);

        for (PresetItem presetItem : chestPreset.getItems()) {
            ItemStack itemStack = presetItem.getItemStack();
            UI.addItem(itemStack);
        }

        player.openInventory(UI);

    }

    void loadChests(String name) {

        List<Location> locations = new ArrayList<>();

        for (String location : new ConfigManager().getAllChestLocations(name)) {
            locations.add(new Methods().getLocationFromBetterLocationString(location));
        }

        for (Location location : locations) {

            if (!Bukkit.getWorld(location.getWorld().getName()).getBlockAt(location).getType().equals(Material.CHEST)) {
                CustomPresetChests.getInstance().getLogger().warning("Block at " + location + " is no longer a chest, items not loaded.");
                continue;
            }

            Chest chest = (Chest) Bukkit.getWorld(location.getWorld().getName()).getBlockAt(location).getState();

            ChestPreset chestPreset = new ConfigManager().getChestPresetFromConfig(name);

            for (PresetItem presetItem : chestPreset.getItems()) {
                if (presetItem.getPercentage() >= new Random().nextInt(100) + 1) {
                    chest.getBlockInventory().addItem(presetItem.getItemStack());
                }
            }

        }

    }


}
