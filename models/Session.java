package models;

import models.employee.Employee;
import models.user.User;

public class Session {
    
    private static Session instance;
    private User user;
    private Employee employee;
    private Boolean isLoggedIn;
    private Boolean isEmployee = false;

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

    public void createEmployeeLoginSession(Employee employee) {
        this.isLoggedIn = true;
        this.isEmployee = true;
        this.employee = employee;
    }

    public Integer getUserId() {
        return isLoggedIn ? user.getUserId() : -1;
    }

    public Boolean isEmployee() {
        return isEmployee;
    }

    public Integer getEmployeeRoleId() {
        return isLoggedIn ? employee.getRole().getRoleId(): -1;
    }

    public Integer getEmployeeUserId() {
        return isLoggedIn ? employee.getId() : -1;
    }

    public User getUser() {
        return user;
    }
}
