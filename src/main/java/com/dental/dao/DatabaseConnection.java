package com.dental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    // scenario.db sits in the project root (next to pom.xml)
    private static final String URL = "jdbc:sqlite:scenario.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /** Creates the table if it does not exist yet. */
    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS patients ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "patient_name TEXT NOT NULL, "
                + "treatment TEXT NOT NULL CHECK (treatment IN ('CLEANING','EXTRACTION','FILLING')), "
                + "amount_due REAL NOT NULL, "
                + "payment TEXT NOT NULL CHECK (payment IN ('PAID','PENDING')))";
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }
}
