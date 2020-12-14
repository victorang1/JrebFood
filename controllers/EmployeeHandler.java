package controllers;

import java.util.ArrayList;
import java.util.Date;

import core.Controller;
import core.Model;
import core.View;

public class EmployeeHandler extends Controller {

    private static EmployeeHandler instance;

    public static EmployeeHandler getInstance() {
        if (instance != null) {
            instance = new EmployeeHandler();
        }
        return instance;
    }
    
    public Boolean createEmployee(Integer roleId, String name, Date dob, String email, String password,
            String status) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean changeStatus(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean validateFields(String name, Date dob, String email) {
        return null;
    }

    public ArrayList<Model> viewAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public View viewManageEmployeeForm() {
        return null;
    }
}
