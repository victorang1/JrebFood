package models.employee;

import java.util.Date;
import java.util.Vector;

import core.Model;

public interface EmployeeModel {

    public Boolean createEmployee(Integer id, Integer roleId, String name, Date dob, String email, String password, String status);
    public Boolean changeStatus(Integer id);
    public Model loginAsEmployee(String email, String password);
    public Vector<Model> viewAll();
}
