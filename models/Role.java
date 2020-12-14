package models;

public class Role {
    
    private Integer roleId;
    private String roleName;

    public Integer getRoleId() {
        return this.roleId;
    }

    public Role setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
}
