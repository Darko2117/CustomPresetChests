package com.daki.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class Methods {

    public String serializeItemStack(ItemStack itemStack) {

        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(1);
            dataOutput.writeObject(itemStack);
            dataOutput.close();

            return Base64Coder.encodeLines(outputStream.toByteArray());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;

    }

    public ItemStack deserializeItemStack(String string) {

        try {

            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(string));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();

            return items[0];

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;

    }

    public Boolean checkConfig() {

        try {
            InputStream inputStream = new FileInputStream(CustomPresetChests.getInstance().getDataFolder() + "/config.yml");
            Yaml config = new Yaml();
            config.load(inputStream);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }

        return true;

    }

    public String getBetterLocationString(Location location) {

        String worldName = location.getWorld().getName();

        String dimension = location.getWorld().getEnvironment().toString();

        String X = String.valueOf(location.getBlockX());
        String Y = String.valueOf(location.getBlockY());
        String Z = String.valueOf(location.getBlockZ());

        String message = "";
        message = message.concat("World: ");
        message = message.concat(worldName);
        message = message.concat(" Dimension: ");
        message = message.concat(dimension);
        message = message.concat(" X:");
        message = message.concat(X);
        message = message.concat(" Y:");
        message = message.concat(Y);
        message = message.concat(" Z:");
        message = message.concat(Z);

        return message;

    }

    public Location getLocationFromBetterLocationString(String string) {

        string = string.substring(7);

        String worldName = string.substring(0, string.indexOf(" "));

        string = string.substring(string.indexOf(" ") + 1);
        string = string.substring(12);

        String dimension = string.substring(0, string.indexOf(" "));

        string = string.substring(string.indexOf(" ") + 1);
        string = string.substring(2);

        String X = string.substring(0, string.indexOf(" "));

        string = string.substring(string.indexOf(" ") + 1);
        string = string.substring(2);

        String Y = string.substring(0, string.indexOf(" "));

        string = string.substring(string.indexOf(" ") + 1);
        string = string.substring(2);

        String Z = string;

        return new Location(Bukkit.getWorld(worldName), Double.parseDouble(X), Double.parseDouble(Y), Double.parseDouble(Z));

    }

}