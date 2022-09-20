package com.i2i.dao;

import java.util.List;

import com.i2i.exception.EmployeeException;
import com.i2i.model.Employee;

/**
 * This interface is contains the methods to perform the database operations
 * for Employee details. 
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public interface EmployeeDAO {

    /**
     * <p>
     * This mehtod is used to get the id from the employee table
     * </p>
     * 
     * @return Integer id
     *         for which the id needs to be access.
     *
     * @throws EmployeeException
     */
    Integer getEmployeeId() throws EmployeeException ;

    /**
     * <p>
     * Get the employee and store it into the employee table
     * </p>
     * 
     * @param Employee employee
     *         for which the employee details to be inserted in database.
     * @return boolean
     *         returns boolean true if the employee inserted in table othrwise false.
     *
     * @throws EmployeeException
     */
    boolean insertEmployee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method is used to update a employee for the given id in employee
     * table.
     * </p>
     * 
     * @param Employee employee
     *         for which the employee needs to be updated in table.
     * @return employee object if updated, otherwise null.
     *
     * @throws EmployeeException
     */
    Employee updateEmployee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method is used to send the entire employees as a list for 
     * called method
     * </p>
     * 
     * @param String roleName
     *         for which roleName is used to retrieve the employee base on role
     * @param int startValue
     *         for which startValue is used to set the start value of the employee
     * @return employees list if the employees are available in table
     *        otherwise return empty list.
     *
     * @throws EmployeeException
     */
    List<Employee> retrieveEmployeesByRoleName(String roleName, int startValue) throws EmployeeException;

    /**
     * <p>
     * This method is used to delete a trainer for the given id from 
     * the table.
     * </p>
     * 
     * @param String id 
     *         for which id is needs to delete the particular trainer.
     * @return boolean value true if deleted, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean deleteTrainerById(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to delete a trainee for the given id from 
     * the table.
     * </p>
     * 
     * @param String id 
     *         for which id is needs to delete the particular trainee.
     * @return boolean value true if deleted, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean deleteTraineeById(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to send a employee for the given id from 
     * the employee table to called method
     * </p>
     * 
     * @return Employee object if the employee is available in table
     *        otherwise return null.
     *
     * @throws EmployeeException
     */
    Employee retrieveEmployeeByIdAndRole(String id, String roleName) throws EmployeeException;

    /**
     * <p>
     * This method is used to send the count of the 
     *  employees for called method
     * </p>
     * 
     * @return noOfRecords as long
     *
     * @throws EmployeeException
     */
    public long getNoOfEmployeeByRoleName(String roleName) throws EmployeeException;
}