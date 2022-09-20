package com.i2i.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

/**
 * The EmployeeId class contain the attribute for employee id
 * generation used to store & reterive the value by using Setter and Getter.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 15 Sep 2022
 */ 
@Entity
@Table(name = "employee_id")
public class EmployeeId {

    @Id
    @Column(name = "id")
    private int id;

    public EmployeeId() {}

    public EmployeeId(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}