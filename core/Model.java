package core;

import java.sql.PreparedStatement;

import db.AppDatabase;

public abstract class Model {

    protected String tableName;
    
    protected PreparedStatement execQuery(String query) {
        return AppDatabase.connect().executeQuery(query);
    }

    protected PreparedStatement execQueryWithKeys(String query) {
        return AppDatabase.connect().executeQueryWithKeys(query);
    }
}
