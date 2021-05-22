package com.daki.main.objects;

import com.daki.main.Methods;
import org.bukkit.inventory.ItemStack;

public class PresetItem {

    ItemStack itemStack;
    Integer percentage;

    public PresetItem(ItemStack itemStack, Integer percentage) {
        this.itemStack = itemStack;
        this.percentage = percentage;
    }

    public PresetItem(String presetItemString) {
        this.itemStack = new Methods().deserializeItemStack(presetItemString.substring(presetItemString.indexOf("Item:") + 5));
        this.percentage = Integer.parseInt(presetItemString.substring(presetItemString.indexOf("Percentage:") + 11, presetItemString.indexOf("Item:")));
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String toString() {

        String string = "";

        string = string.concat("Percentage:" + percentage);
        string = string.concat("Item:" + new Methods().serializeItemStack(itemStack));

        return string;

    }

}