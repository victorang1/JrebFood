package models.user;

import core.Model;

public class User extends Model {

    private Integer userId;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;

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
    
    public Boolean createAccount(Integer userId, String name, String address, String email, String phoneNumber,
            String password) {
        // TODO Auto-generated method stub
        return null;
    }

    public Model getOne(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }
}
