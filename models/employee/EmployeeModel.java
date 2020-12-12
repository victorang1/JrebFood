package models.employee;

import java.util.ArrayList;
import java.util.Date;

public interface EmployeeModel {
    
    public Boolean createEmployee(Integer id, Integer roleId, String name, Date dob, String email, String password, String status);
    public Boolean changeStatus(Integer id);
    public ArrayList<Employee> viewAll();
}
