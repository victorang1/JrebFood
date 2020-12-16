package db;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.Statement;


public class AppDatabase {
    
    private final String DB_USERNAME = "root";
    private final String DB_PASS = "";
    private final String DB_NAME = "jrebfood";
    private final String DB_HOST = "localhost";
    private final String DB_PORT = "3306";

    private static AppDatabase mInstance;

    private Connection connection;

    private AppDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(getConnectionString(), DB_USERNAME, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database, the system will be terminated.");
            System.exit(0);
        }
    }
    
    public synchronized static AppDatabase connect() {
        if (mInstance == null) {
            mInstance = new AppDatabase();
        }
        return mInstance;
    }

    private String getConnectionString() {
        return String.format("jdbc:mysql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME);
    }

    public PreparedStatement executeQuery(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement executeQueryWithKeys(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }
}
