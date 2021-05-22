package com.daki.main.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChestPreset {

    String name;
    List<PresetItem> items;

    public ChestPreset(String name, List<PresetItem> items) {
        this.name = name;
        this.items = items;
    }

    public ChestPreset(String chestPresetString) {

        this.name = chestPresetString.substring(chestPresetString.indexOf("Name:") + 5, chestPresetString.indexOf("Items:"));

        List<String> presetItemStringList = new ArrayList<>(Arrays.asList(chestPresetString.substring(chestPresetString.indexOf("Items:") + 6).split(",")));
        List<PresetItem> items = new ArrayList<>();
        try {
            for (String string : presetItemStringList) {
                items.add(new PresetItem(string));
            }
        } catch (Throwable ignored) {
            items.clear();
        }
        this.items = items;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PresetItem> getItems() {
        return items;
    }

    public void setItems(List<PresetItem> items) {
        this.items = items;
    }

    public void addItem(PresetItem item) {
        this.items.add(item);
    }

    public String toString() {

        String string = "";

        string = string.concat("Name:" + name);
        string = string.concat("Items:");
        for (Integer i = 0; i < items.size(); i++) {
            string = string.concat(items.get(i).toString());
            if (i != items.size() - 1) {
                string = string.concat(",");
            }
        }

        return string;

    }

}