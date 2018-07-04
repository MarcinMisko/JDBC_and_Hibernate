package jdbc1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static final String DB_H2_URL = "jdbc:h2:tcp://localhost/~/test/testdb";
    private static Config instance;
    private Connection connection;

    private Config() {
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_H2_URL, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
