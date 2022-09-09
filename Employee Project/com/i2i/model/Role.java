package com.i2i.model;

/**
 * The Role class contain the attribute for employee role
 * and used to store & reterive the value by using Setter and Getter.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 02 Sep 2022
 */ 
public class Role {
    private int roleId;
    private String role;

    public Role() {}

    public Role(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
