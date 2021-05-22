package com.daki.main.listeners;

import com.daki.main.managers.ConfigManager;
import com.daki.main.objects.ChestPreset;
import com.daki.main.objects.PresetEditInventoryHolder;
import com.daki.main.objects.PresetItem;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryCloseListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent event) {

        if (!(event.getInventory().getHolder() instanceof PresetEditInventoryHolder)) return;

        if (!getAllFalseItems(event).isEmpty()) {
            for (ItemStack itemStack : getAllFalseItems(event)) {
                event.getPlayer().getInventory().addItem(itemStack);
            }
            event.getPlayer().sendMessage(ChatColor.RED + "Some items don't have the percentage at the end of their name. Parts of the preset not saved.");
        }

        List<PresetItem> presetItems = new ArrayList<>();
        for (ItemStack itemStack : getAllCorrectItems(event)) {
            PresetItem presetItem = new PresetItem(itemStack, getPercentageFromName(itemStack));
            presetItems.add(presetItem);
        }

        ChestPreset chestPreset = new ChestPreset(getPresetNameFromTitle(event), presetItems);

        new ConfigManager().writeChestPresetToConfig(chestPreset);

    }

    String getPresetNameFromTitle(InventoryCloseEvent event) {

        return event.getView().getTitle().substring(event.getView().getTitle().lastIndexOf(" ") + 1);

    }

    Integer getPercentageFromName(ItemStack itemStack) {

        String itemName = itemStack.getItemMeta().getDisplayName();

        itemName = itemName.substring(itemName.lastIndexOf(" ") + 1);

        itemName = itemName.substring(0, itemName.length() - 1);

        return Integer.parseInt(itemName);

    }

    List<ItemStack> getAllCorrectItems(InventoryCloseEvent event) {

        List<ItemStack> itemStackList = new ArrayList<>();

        for (ItemStack itemStack : event.getView().getTopInventory().getContents()) {

            if (itemStack == null) continue;

            String itemName = itemStack.getItemMeta().getDisplayName();
            if (itemName.isEmpty()) continue;

            itemName = itemName.substring(itemName.lastIndexOf(" ") + 1);
            if (!itemName.endsWith("%")) continue;

            itemName = itemName.substring(0, itemName.length() - 1);
            try {
                Integer.parseInt(itemName);
            } catch (Throwable throwable) {
                continue;
            }

            itemStackList.add(itemStack);

        }

        return itemStackList;

    }

    List<ItemStack> getAllFalseItems(InventoryCloseEvent event) {

        List<ItemStack> itemStackList = new ArrayList<>();

        for (ItemStack itemStack : event.getView().getTopInventory().getContents()) {

            if (itemStack == null) continue;

            String itemName = itemStack.getItemMeta().getDisplayName();
            if (itemName.isEmpty()) {
                itemStackList.add(itemStack);
                continue;
            }

            itemName = itemName.substring(itemName.lastIndexOf(" ") + 1);
            if (!itemName.endsWith("%")) {
                itemStackList.add(itemStack);
                continue;
            }

            itemName = itemName.substring(0, itemName.length() - 1);
            try {
                Integer.parseInt(itemName);
            } catch (Throwable throwable) {
                itemStackList.add(itemStack);
            }

        }

        return itemStackList;

    }

}
