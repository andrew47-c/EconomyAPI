package org.harryiz205.economyapi.user;

import org.harryiz205.economyapi.EconomyAPI;

import java.sql.SQLException;

public class User {

    private final String username;
    private int economy;

    public User(String username, int economy) {
        this.username = username;
        this.economy = economy;
    }

    public int getEconomy() {
        return this.economy;
    }

    public String getUsername() {
        return this.username;
    }

    public void addEconomy(int value) throws SQLException {
        this.economy += value;
        EconomyAPI.getDatabase().saveUser(this);
    }

    public void reduceEconomy(int value) throws SQLException {
        this.economy -= value;
        EconomyAPI.getDatabase().saveUser(this);
    }
}
