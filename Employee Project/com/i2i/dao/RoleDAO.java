package com.i2i.dao;

import java.util.List;

import com.i2i.exception.EmployeeException;
import com.i2i.model.Role;

/**
 * This interface is contains the methods to perform the database operations
 * for Role details. 
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 17 Sep 2022
 */ 
public interface RoleDAO {

    /**
     * <p>
     * This method is used to send a trainer for the given id from 
     * the employee table to called method
     * </p>
     * 
     * @return role as Role object if the role is available in table
     *        otherwise return null.
     *
     * @throws EmployeeException
     */
    Role retrieveRoleByRoleName(String roleName) throws EmployeeException;
}