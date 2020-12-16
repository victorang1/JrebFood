package models;

public class Role {
    
    private Integer roleId;
    private String attributes;

    public Role(Integer roleId, String attributes) {
        this.roleId = roleId;
        this.attributes = attributes;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public Role setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getAttributes() {
        return attributes;
    }

    public Role setAttributes(String attributes) {
        this.attributes = attributes;
        return this;
    }
}
