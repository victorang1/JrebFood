package models.employee;

import java.util.ArrayList;
import java.util.Date;

import models.Model;

public class EmployeeImpl extends Model implements EmployeeModel {

    @Override
    public Boolean createEmployee(Integer id, Integer roleId, String name, Date dob, String email, String password,
            String status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean changeStatus(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Employee> viewAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
