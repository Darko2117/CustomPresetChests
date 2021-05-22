package com.daki.main.managers;

import com.daki.main.objects.User;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    static List<User> users = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    public static void removeUser(User user){
        users.remove(user);
    }

    public static User getUserFromPlayer(Player player) {
        for (User user : users) {
            if (user.getPlayer().equals(player)) {
                return user;
            }
        }
        return null;
    }

}
