package models;

import java.sql.PreparedStatement;

import db.AppDatabase;

public abstract class Model {
    
    protected PreparedStatement execQuery(String query) {
        return AppDatabase.connect().executeQuery(query);
    }
}
