package models.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import core.Model;
import models.Role;

public class Employee extends Model implements EmployeeModel {

    private Integer id;
    private Role role;
    private String name;
    private Date dob;
    private String email;
    private String status;

    public Integer getId() {
        return this.id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public Role getRole() {
        return this.role;
    }

    public Employee setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Date getDob() {
        return this.dob;
    }

    public Employee setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public Employee setStatus(String status) {
        this.status = status;
        return this;
    }

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
    public Vector<Model> viewAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
