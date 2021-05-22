package com.daki.main.managers;

import com.daki.main.CustomPresetChests;
import com.daki.main.Methods;
import com.daki.main.objects.ChestPreset;
import com.daki.main.objects.PresetItem;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    public static void initialize() {

        CustomPresetChests.getInstance().saveDefaultConfig();
        if (new Methods().checkConfig()) {
            CustomPresetChests.getInstance().reloadConfig();
        } else {
            Bukkit.getPluginManager().disablePlugin(CustomPresetChests.getInstance());
            CustomPresetChests.getInstance().getLogger().info("There is an error in the config. To prevent spigot from deleting it the plugin will be disabled.");
            return;
        }

    }

    public void writeChestPresetToConfig(ChestPreset chestPreset) {

        CustomPresetChests.getInstance().reloadConfig();
        CustomPresetChests.getInstance().getConfig().set("ChestPreset." + chestPreset.getName(), chestPreset.toString());
        CustomPresetChests.getInstance().saveConfig();

    }

    public ChestPreset getChestPresetFromConfig(String name) {

        CustomPresetChests.getInstance().reloadConfig();
        if (CustomPresetChests.getInstance().getConfig().getString("ChestPreset." + name) == null) {
            return new ChestPreset(name, new ArrayList<PresetItem>());
        }
        ChestPreset chestPreset = new ChestPreset(CustomPresetChests.getInstance().getConfig().getString("ChestPreset." + name));
        return chestPreset;

    }

    public void writeSetChestToConfig(String name, List<String> locations) {

        CustomPresetChests.getInstance().reloadConfig();
        CustomPresetChests.getInstance().getConfig().set("SetChest." + name, locations);
        CustomPresetChests.getInstance().saveConfig();

    }

    public List<String> getSetChestLocations(String name) {

        CustomPresetChests.getInstance().reloadConfig();
        return CustomPresetChests.getInstance().getConfig().getStringList("SetChest." + name);

    }

    public List<String> getAllChestPresetNames() {

        CustomPresetChests.getInstance().reloadConfig();

        List<String> names = new ArrayList<>();
        for (String name : CustomPresetChests.getInstance().getConfig().getKeys(true)) {
            if (name.startsWith("SetChest.")) {
                names.add(name.substring(name.indexOf("SetChest.") + 9));
            }
        }

        return names;

    }

    public List<String> getAllChestLocations(String name){

        CustomPresetChests.getInstance().reloadConfig();
        return CustomPresetChests.getInstance().getConfig().getStringList("SetChest." + name);

    }

}
