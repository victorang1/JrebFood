package models;

import models.user.User;

public class Session {
    
    private static Session instance;
    private User user;
    private Boolean isLoggedIn;

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void createLoginSession(User user) {
        this.isLoggedIn = true;
        this.user = user;
    }

    public Integer getUserId() {
        return isLoggedIn ? user.getUserId() : -1;
    }

    public User getUser() {
        return user;
    }
}
