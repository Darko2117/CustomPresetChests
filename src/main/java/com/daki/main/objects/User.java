package com.daki.main.objects;

import org.bukkit.entity.Player;

public class User {

    Player player;
    Boolean isSetting = false;
    Boolean isUnsetting = false;
    String settingName = "";

    public User(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getSetting() {
        return isSetting;
    }

    public void setSetting(Boolean setting) {
        isSetting = setting;
    }

    public Boolean getUnsetting() {
        return isUnsetting;
    }

    public void setUnsetting(Boolean unsetting) {
        isUnsetting = unsetting;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }
}
