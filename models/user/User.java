package models.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import core.Model;

public class User extends Model {

    private Integer userId;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;

    public User() {
        this.tableName = "user";
    }

    public Integer getUserId() {
        return userId;
    }
    
    public User setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public Boolean createAccount(String name, String address, String email, String phoneNumber,
            String password) {
        try {
            String rawQuery = String.format("INSERT INTO %s VALUES (default, ?, ?, ?, ?, ?)", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setString(1, name);
            result.setString(2, address);
            result.setString(3, email);
            result.setString(4, phoneNumber);
            result.setString(5, password);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Model login(String email, String password) {
        try {
            String rawQuery = String.format("SELECT * FROM %s WHERE email=? AND password=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setString(1, email);
            result.setString(2, password);
            ResultSet rs = result.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.userId = rs.getInt("userId");
                user.name = rs.getString("name");
                user.address = rs.getString("address");
                user.email = rs.getString("email");
                user.phoneNumber = rs.getString("phoneNumber");
                return user;
            }
            throw new Exception();
        } catch(Exception e) {
            return null;
        }
    }

    public Model getOne(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean checkIfUserExists(String email, String phoneNumber) {
        try {
            String rawQuery = String.format("SELECT * FROM %s WHERE email=? OR phoneNumber=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setString(1, email);
            result.setString(2, phoneNumber);
            return result.executeQuery().isBeforeFirst();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
