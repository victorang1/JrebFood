package models.employee;

public class Driver extends Employee {
    
    private String licensePlate;

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public Employee setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
}
