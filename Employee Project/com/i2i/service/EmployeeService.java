package com.i2i.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * @return boolean value true if the updation is success, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean updateTrainerById(String id, Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method will send the particular trainer for the given id.
     * </p>
     * 
     * @param String id
     *         for which id needs to find out the existing object.
     * @return List of Employee if the object exist, otherwise return empty list.
     *
     * @throws EmployeeException.
     */
    List<Employee> searchTrainerById(String id) throws EmployeeException;

    /**
     * <p>
     * This method will send the entire trainers for the called method.
     * </p>
     * 
     * @return employeesMap entryset.
     *
     * @throws EmployeeException.
     */
    List<Employee> getTrainers() throws EmployeeException;

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
     * This method checks whether trainers exist or not
     * </p>
     * 
     * @return the boolean value true if the data exist, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean isTrainersEmpty() throws EmployeeException;

    /**
     * <p>
     * Get the id number and check it whether the trainer is exist or not for given id
     * </p>
     * 
     * @param String id
     *         for which id needs to check whether it exist.
     * @return the boolean value true if the data exist, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean isTrainerExist(String id) throws EmployeeException;

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
     * @return boolean value true if the updation is success, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean updateTraineeById(String id, Employee trainee) throws EmployeeException, EmployeeException;

    /**
     * <p>
     * This method will send the particular trainee for the given id.
     * </p>
     * 
     * @param String id
     *         for which id needs to find out the existing object.
     * @return List of Employee if the object exist, otherwise return empty list.
     *
     * @throws EmployeeException.
     */
    List<Employee> searchTraineeById(String id) throws EmployeeException;

    /**
     * <p>
     * This method will send the entire trainees for the called method.
     * </p>
     * 
     * @return the employees as list.
     *
     * @throws EmployeeException.
     */
    List<Employee> getTrainees() throws EmployeeException;

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
     * This method checks whether trainers exist or not
     * </p>
     * 
     * @return the boolean value true if the data exist, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean isTraineesEmpty() throws EmployeeException;

    /**
     * <p>
     * Get the id number and check it whether the trainee is exist or not
     * </p>
     * 
     * @param String id
     *         for which id needs to check whether it exist.
     * @return the boolean value true if the data exist, otherwise false.
     *
     * @throws EmployeeException.
     */
    boolean isTraineeExist(String id) throws EmployeeException;
}