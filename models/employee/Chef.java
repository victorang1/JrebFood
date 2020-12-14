package models.employee;

public class Chef extends Employee {
    
    private String position;

    public String getPosition() {
        return this.position;
    }

    public Employee setPosition(String position) {
        this.position = position;
        return this;
    }
}