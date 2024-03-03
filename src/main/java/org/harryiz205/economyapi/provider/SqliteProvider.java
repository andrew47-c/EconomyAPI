package org.harryiz205.economyapi.provider;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.harryiz205.economyapi.user.User;
import java.sql.*;

public class SqliteProvider {

    Connection connection = null;
    Statement stmt = null;

    public void init() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (username string, eco integer)");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void createUser(String username) throws SQLException {
        FileConfiguration config = this.getLoader().getConfig();
        int default_money = config.getInt("default-money");
        String sql = "INSERT INTO users (username, eco) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, username);
        pstmt.setInt(2, default_money);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public User getUser(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";

        PreparedStatement pstmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, username);

        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            return new User(username, resultSet.getInt("eco"));
        } else {
            return null;
        }
    }

    public void saveUser(User user) throws SQLException {
        String query = "UPDATE users SET eco = ? WHERE username = ?";

        PreparedStatement pstmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, user.getEconomy());
        pstmt.setString(2, user.getUsername());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public Plugin getLoader() {
        return Bukkit.getPluginManager().getPlugin("EconomyAPI");
    }
}
