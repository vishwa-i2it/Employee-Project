package com.i2i.service;

import java.util.List;

import com.i2i.exception.EmployeeException;
import com.i2i.model.Employee;

/**
 *
 * EmployeeService.java
 * This interface contains the following method declarations for the
 * employee CRUD operations.
 *
 * @author    Vishwaeaswaran M
 * @version   1.0 10 Aug 2022
 */
public interface EmployeeService {

    /**
     * <p>
     * This method is used to generate the employee ID
     * by getting the id from dao.
     * </p>
     *
     * @return String as id.
     *
     * @throws EmployeeException.
     */
    public String generateEmployeeId() throws EmployeeException;

    /**
     * <p>
     * Get the Trainer object and call the insertTrainer() 
     * method to store the object by passing.
     * </p>
     * 
     * @param String id
     *         The id is needs to added in DAO as key.
     * @param Employee employee
     *         The trainer is needs to added in DAO.
     *
     * @throws EmployeeException.
     */
    boolean addTrainer(Employee employee) throws EmployeeException;

    /**
     * <p>
     * Get the following parameter and update the trainer by passing
     * the parameter to that setter.
     * </p>
     * 
     * @param String id
     *         ID of the employee which needs to access trainer.
     * @param Employee trainer
     *         The trainer is needs to updated in DAO.
     * 
     * @return Employee object if the updation is success, otherwise null.
     *
     * @throws EmployeeException.
     */
    Employee updateTrainer(Employee employee, Employee updatableEmployee) throws EmployeeException;

    /**
     * <p>
     * This method will send the particular trainer for the given id.
     * </p>
     * 
     * @param String id
     *         for which id needs to find out the existing object.
     * @return trainer as Employee object if the object exist, otherwise return null.
     *
     * @throws EmployeeException.
     */
    Employee searchTrainerById(String id) throws EmployeeException;

    /**
     * <p>
     * This method will send the entire trainers for the called method.
     * </p>
     *
     * @param int startValue 
     *         for whicg value is used to diplay the record form this position 
     * @return the employees as list.
     *
     * @throws EmployeeException.
     */
    List<Employee> getTrainers(int startValue)  throws EmployeeException;

    /**
     * <p>
     * Delete the particular trainer for the given id as parameter. 
     * </p>
     * 
     * @param String id
     *         for which id needs to delete the existing entryset.
     * @return boolean value true if the entryset deleted, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean deleteTrainerById(String id) throws EmployeeException;

    /**
     * <p>
     * Get the Employee object and call the insertEmployee() 
     * method to store the object by passing.
     * </p>
     * 
     * @param String id
     *         The id is needs to added in DAO as key.
     * @param Employee employee
     *         The trainee is needs to added in DAO.
     *
     * @throws EmployeeException.
     */
    boolean addTrainee(Employee employee) throws EmployeeException;

    /**
     * <p>
     * Get the following parameter and update the trainee by passing
     * the parameter to that setter.
     * </p>
     * 
     * @param String id
     *         ID of the employee which needs to access the trainee.
     * @param Employee employee
     *         The trainee is needs to updated in DAO.
     *
     * @return Employee object if the updation is success, otherwise null.
     *
     * @throws EmployeeException.
     */
    Employee updateTrainee(Employee employee, Employee updatableEmployee) throws EmployeeException, EmployeeException;

    /**
     * <p>
     * This method will send the particular trainee for the given id.
     * </p>
     * 
     * @param String id
     *         for which id needs to find out the existing object.
     * @return trainee as a Employee object if the object exist, otherwise return null.
     *
     * @throws EmployeeException.
     */
    Employee searchTraineeById(String id) throws EmployeeException;

    /**
     * <p>
     * This method will send the entire trainees for the called method.
     * </p>
     *
     * @param int startValue 
     *         for whicg value is used to diplay the record form this position 
     * @return the employees as list.
     *
     * @throws EmployeeException.
     */
    List<Employee> getTrainees(int startValue)  throws EmployeeException;

    /**
     * <p>
     * Delete the particular trainee for the given id as parameter. 
     * </p>
     * 
     * @param String id
     *         for which id needs to delete the existing entryset.
     * @return boolean value true if the entryset deleted, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean deleteTraineeById(String id) throws EmployeeException;

    /**
     * <p>
     * This method is used to get the page count for displaying the 
     *  trainer from dao
     * </p>
     * 
     * @return pageCount as integer
     *
     * @throws EmployeeException
     */
    public int getTrainerPageCount() throws EmployeeException;

    /**
     * <p>
     * This method is used to get the page count for displaying the 
     *  trainee from dao
     * </p>
     * 
     * @return pageCount as integer
     *
     * @throws EmployeeException
     */
    public int getTraineePageCount() throws EmployeeException;
}