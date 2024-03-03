package org.harryiz205.economyapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.harryiz205.economyapi.EconomyAPI;
import org.harryiz205.economyapi.user.User;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class AddMoney extends Command {
    public AddMoney(@NotNull String name) {
        super(name);
        this.setPermission("economyapi.addmoney");
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length < 2) {
                player.sendMessage("Usage: /addmoney <user> <amount>");
                return true;
            }

            String target = args[0];
            int amount = Integer.parseInt(args[1]);
            try {
                User user = EconomyAPI.getDatabase().getUser(target);
                if (user == null) {
                    player.sendMessage("User not found!");
                    return true;
                } else {
                    user.addEconomy(amount);
                    player.sendMessage("Added " + amount + "$ to " + target);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }
}
