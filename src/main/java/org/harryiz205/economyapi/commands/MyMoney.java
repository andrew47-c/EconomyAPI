package org.harryiz205.economyapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.harryiz205.economyapi.EconomyAPI;
import org.harryiz205.economyapi.user.User;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class MyMoney extends Command {
    public MyMoney(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            try {
                User user = EconomyAPI.getDatabase().getUser(player.getName());
                player.sendMessage("Current money: " + user.getEconomy());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;

        }
        return false;
    }
}
