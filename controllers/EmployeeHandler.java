package controllers;

import java.util.ArrayList;
import java.util.Date;

import core.Controller;
import core.Model;
import core.View;
import models.Session;
import models.employee.Employee;
import models.employee.EmployeeModel;
import views.employee.ChefLandingView;
import views.employee.DriverLandingView;
import views.employee.ManageEmployeeFormView;
import views.employee.ManagerLandingView;

public class EmployeeHandler extends Controller {

    private static EmployeeHandler instance;
    private EmployeeModel model;

    private EmployeeHandler() {
        model = new Employee();
    }
    
    public static EmployeeHandler getInstance() {
        if (instance == null) {
            instance = new EmployeeHandler();
        }
        return instance;
    }
    
    public Boolean createEmployee(Integer roleId, String name, Date dob, String email, String password,
            String status) {
        return model.createEmployee(roleId, name, dob, email, password, status);
    }

    public Boolean changeStatus(Integer id) {
        return model.changeStatus(id);
    }

    public Boolean validateFields(String name, Date dob, String email) {
        return null;
    }

    public ArrayList<Model> viewAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean loginAsEmployee(String email, String password) {
        Employee result = (Employee) model.loginAsEmployee(email, password);
        if (result != null) {
            Session.getInstance().createEmployeeLoginSession(result);
        }
        return result != null;
    }

    public View viewManageEmployeeForm() {
        return new ManageEmployeeFormView();
    }

    public View viewLandingView() {
        switch (Session.getInstance().getEmployeeRoleId()) {
            case 1:
                return new DriverLandingView();
            case 2:
                return new ChefLandingView();
            default:
                return new ManagerLandingView();
        }
    }
}
