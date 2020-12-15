package models;

public class Session {
    
    private static Session instance;
    private Integer userId;
    private Boolean isLoggedIn;

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void createLoginSession(Integer userId) {
        this.isLoggedIn = true;
        this.userId = userId;
    }

    public Integer getUserId() {
        return isLoggedIn ? userId : -1;
    }
}
