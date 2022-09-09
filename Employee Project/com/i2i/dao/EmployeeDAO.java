package com.i2i.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.i2i.enumerator.EmployeeException;
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
     * @param String id 
     *         for which id is needs to access particular employee.
     * @param Employee employee
     *         for which the employee needs to be updated in table.
     * @return boolean value true if updated, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean updateEmployeeById(String id, Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method is used to delete a employee for the given id from 
     * the table.
     * </p>
     * 
     * @param String id 
     *         for which id is needs to delete the particular employee.
     * @return boolean value true if deleted, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean deleteEmployeeById(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to check whether the trainers is available or not in table.
     * </p>
     * 
     * @return boolean value true if avaliable, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean isTrainersEmpty() throws EmployeeException;

    /**
     * <p>
     * This method is used to check whether the trainer exist or not for given id.
     * </p>
     * 
     * @return boolean value true if exist, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean isTrainerExist(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to send the entire trainers as list for 
     * called method
     * </p>
     * 
     * @return trainers list if the trainers are available in table
     *        otherwise return empty list.
     *
     * @throws EmployeeException
     */
    List<Employee> retrieveTrainers() throws EmployeeException;

    /**
     * <p>
     * This method is used to send a trainer for the given id from 
     * the employee table as a list to called method
     * </p>
     * 
     * @return trainers list if the trainer is available in table
     *        otherwise return empty list.
     *
     * @throws EmployeeException
     */
    List<Employee> retrieveTrainerById(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to check whether the trainees is available or not in table.
     * </p>
     * 
     * @return boolean value true if avaliable, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean isTraineesEmpty() throws EmployeeException;

    /**
     * <p>
     * This method is used to check whether the trainee exist or not for given id.
     * </p>
     * 
     * @return boolean value true if exist, otherwise false.
     *
     * @throws EmployeeException
     */
    boolean isTraineeExist(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to send the entire trainees as a list for 
     * called method
     * </p>
     * 
     * @return trainees list if the trainees are available in table
     *        otherwise return empty list.
     *
     * @throws EmployeeException
     */
    List<Employee> retrieveTrainees() throws EmployeeException;

    /**
     * <p>
     * This method is used to send a trainee for the given key from 
     * the employee table to called method
     * </p>
     * 
     * @return trainees list if the trainee is available in table
     *        otherwise return empty list.
     *
     * @throws EmployeeException
     */
    List<Employee> retrieveTraineeById(String id) throws EmployeeException;
}