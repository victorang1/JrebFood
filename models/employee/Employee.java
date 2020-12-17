package models.employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public Employee() {
        tableName = "employee";
    }

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
    public Boolean createEmployee(Integer roleId, String name, Date dob, String email, String password,
            String status) {
                try {
                    String rawQuery = String.format("INSERT INTO %s VALUES (default, ?, ?, ?, ?, ?, ?)", tableName);
                    PreparedStatement result = execQuery(rawQuery);
                    result.setInt(1, roleId);
                    result.setString(2, name);
                    result.setDate(3, new java.sql.Date(dob.getTime()));
                    result.setString(4, email);
                    result.setString(5, password);
                    result.setString(6, status);
                    result.executeUpdate();
                    return true;
                } catch(Exception e) {
                    e.printStackTrace();
                    return false;
                }
    }

    @Override
    public Model loginAsEmployee(String email, String password) {
        try {
            String rawQuery = String.format("SELECT * FROM %s a LEFT JOIN %s b ON a.id = b.employeeId LEFT JOIN %s c ON a.id = c.employeeId WHERE email=? AND password=?", tableName, "driver", "chef");
            PreparedStatement result = execQuery(rawQuery);
            result.setString(1, email);
            result.setString(2, password);
            ResultSet rs = result.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.id = rs.getInt("id");
                Integer roleId = rs.getInt("roleId");
                if (roleId == 1) {
                    employee.role = new Role(roleId, rs.getString("licensePlate"));
                }
                else if (roleId == 2) {
                    employee.role = new Role(roleId, rs.getString("position"));
                }
                else employee.role = new Role(roleId, "");
                employee.name = rs.getString("name");
                employee.dob = rs.getDate("dob");
                employee.email = rs.getString("email");
                employee.status = rs.getString("status");
                return employee;
            }
            throw new Exception();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean changeStatus(Integer id) {
        try {
            String rawQuery = String.format("UPDATE %s SET status='fired' WHERE id=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, id);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vector<Model> viewAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
