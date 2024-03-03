package org.harryiz205.economyapi;

import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.harryiz205.economyapi.commands.*;
import org.harryiz205.economyapi.listener.EventListener;
import org.harryiz205.economyapi.provider.SqliteProvider;

import java.util.ArrayList;
import java.util.List;

public final class EconomyAPI extends JavaPlugin {

    private static SqliteProvider database;

    @Override
    public void onEnable() {
        EconomyAPI.database = new SqliteProvider();
        EconomyAPI.getDatabase().init();
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.saveDefaultConfig();

        List<Command> commandList = new ArrayList<>();
        commandList.add(new MyMoney("mymoney"));
        commandList.add(new AddMoney("addmoney"));
        commandList.add(new ReduceMoney("reducemoney"));

        this.getServer().getCommandMap().registerAll("economyapi", commandList);
    }

    public static SqliteProvider getDatabase() {
        return EconomyAPI.database;
    }
}
