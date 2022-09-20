package com.i2i.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;

import com.i2i.model.Employee;

/**
 * The Role class contain the attribute for employee role
 * and used to store & reterive the value by using Setter and Getter.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 02 Sep 2022
 */ 
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<Employee> employees;

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

    public List<Employee> getEmployees() {
        if(employees == null) {
            employees = new ArrayList<Employee>();
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
