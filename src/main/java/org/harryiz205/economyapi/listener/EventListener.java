package org.harryiz205.economyapi.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.harryiz205.economyapi.EconomyAPI;
import org.harryiz205.economyapi.user.User;

import java.sql.SQLException;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        String username = player.getName();
        User user = EconomyAPI.getDatabase().getUser(username);
        if (user == null) {
            EconomyAPI.getDatabase().createUser(username);
        }
    }
}
