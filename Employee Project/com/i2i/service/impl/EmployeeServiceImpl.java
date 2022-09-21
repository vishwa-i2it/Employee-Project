package com.i2i.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.i2i.dao.EmployeeDAO;
import com.i2i.dao.RoleDAO;
import com.i2i.dao.impl.EmployeeDAOImpl;
import com.i2i.dao.impl.RoleDAOImpl;
import com.i2i.enumerator.Gender;
import com.i2i.exception.EmployeeException;
import com.i2i.model.Employee;
import com.i2i.model.Role;
import com.i2i.service.EmployeeService;
import com.i2i.util.ConstantUtil;

/**
 *
 * EmployeeServiceImpl.java
 * This class contains the following method declarations for the
 * employee CRUD operations.
 *
 * @author    Vishwaeaswaran M
 * @version   1.0 10 Aug 2022
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    RoleDAO roleDAO = new RoleDAOImpl();

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
    public String generateEmployeeId() throws EmployeeException {
        int id = employeeDAO.getEmployeeId();
        return "I" + id;
    }

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
    public boolean addTrainer(Employee employee) throws EmployeeException {
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleDAO.retrieveRoleByRoleName(ConstantUtil.TRAINER));
        employee.setRoles(roles);
        return employeeDAO.insertEmployee(employee);
    }

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
     * @return Employee objcet if the updation is success, otherwise null.
     *
     * @throws EmployeeException.
     */
    public Employee updateTrainer(Employee employee, Employee updatableEmployee) throws EmployeeException {
        if (updatableEmployee != null) {
            String name = employee.getName();
            Gender gender = employee.getGender();
            String address = employee.getAddress();
            String designation = employee.getDesignation();
            String emailId = employee.getEmailId();
            String mobileNo = employee.getMobileNo();
            Date dateOfBirth = employee.getDateOfBirth();
            Date dateOfJoin = employee.getDateOfJoin();
            Float previousExperience = employee.getPreviousExperience();
            String specialization = employee.getSpecialization();
            Float trainingExperience = employee.getTrainingExperience();
            Integer noOfTrainee = employee.getNoOfTrainee();

            if (name != null && !name.isEmpty()) {
                updatableEmployee.setName(name);
            }

            if (gender != null) {
                updatableEmployee.setGender(gender);
            }

            if (address != null && !address.isEmpty()) {
                updatableEmployee.setAddress(address);
            }

            if (designation != null && !designation.isEmpty()) {
                updatableEmployee.setDesignation(designation);
            }

            if (emailId != null && !emailId.isEmpty()) {
                updatableEmployee.setEmailId(emailId);
            }

            if (mobileNo != null && !mobileNo.isEmpty()) {
                updatableEmployee.setMobileNo(mobileNo);
            }

            if (dateOfBirth != null) {
                updatableEmployee.setDateOfBirth(dateOfBirth);
            }
 
            if (dateOfJoin != null) {
                updatableEmployee.setDateOfJoin(dateOfJoin);
            }

            if (previousExperience != null) {
                updatableEmployee.setPreviousExperience(previousExperience);
            }

            if (specialization != null && !specialization.isEmpty()) {
                updatableEmployee.setSpecialization(specialization);
            }

            if (trainingExperience != null) {
                updatableEmployee.setTrainingExperience(trainingExperience);
            }

            if (noOfTrainee != null) {
                updatableEmployee.setNoOfTrainee(noOfTrainee);
            }
            return employeeDAO.updateEmployee(updatableEmployee);
        } else {
            throw new EmployeeException(ConstantUtil.ID_NOT_FOUND);
        }
    }

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
    public Employee searchTrainerById(String id) throws EmployeeException  {
        return employeeDAO.retrieveEmployeeByIdAndRole(id, ConstantUtil.TRAINER);
    }

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
    public List<Employee> getTrainers(int startValue)  throws EmployeeException {
        return employeeDAO.retrieveEmployeesByRoleName(ConstantUtil.TRAINER, startValue);
    }

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
    public boolean deleteTrainerById(String id)  throws EmployeeException {
        return (employeeDAO.deleteTrainerById(id));
    }

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
    public boolean addTrainee(Employee employee) throws EmployeeException  {
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleDAO.retrieveRoleByRoleName(ConstantUtil.TRAINEE));
        employee.setRoles(roles);
        return employeeDAO.insertEmployee(employee);
    }

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
    public Employee updateTrainee(Employee employee, Employee updatableEmployee) throws EmployeeException {
        if (updatableEmployee != null) {
            String name = employee.getName();
            Gender gender = employee.getGender();
            String address = employee.getAddress();
            String designation = employee.getDesignation();
            String emailId = employee.getEmailId();
            String mobileNo = employee.getMobileNo();
            Date dateOfBirth = employee.getDateOfBirth();
            Date dateOfJoin = employee.getDateOfJoin();
            Float previousExperience = employee.getPreviousExperience();
            List<String> trainersName = employee.getTrainersName();
            Set<String> learnedSkills = employee.getLearnedSkills();
            Integer trainingPeriod = employee.getTrainingPeriod();

            if (name != null && !name.isEmpty()) {
                updatableEmployee.setName(name);
            }

            if (gender !=null) {
                updatableEmployee.setGender(gender);
            }

            if (address != null && !name.isEmpty()) {
                updatableEmployee.setAddress(address);
            }

            if (designation != null && !designation.isEmpty()) {
                updatableEmployee.setDesignation(designation);
            }

            if (emailId != null && !emailId.isEmpty()) {
                updatableEmployee.setEmailId(emailId);
            }

            if (mobileNo != null && !mobileNo.isEmpty()) {
                updatableEmployee.setMobileNo(mobileNo);
            }

            if (dateOfBirth != null) {
                updatableEmployee.setDateOfBirth(dateOfBirth);
            }
 
            if (dateOfJoin != null) {
                updatableEmployee.setDateOfJoin(dateOfJoin);
            }

            if (previousExperience != null) {
                updatableEmployee.setPreviousExperience(previousExperience);
            }

            if (trainersName != null && !trainersName.isEmpty()) {
                updatableEmployee.setTrainersName(trainersName);
            }

            if (learnedSkills != null && !learnedSkills.isEmpty()) {
                updatableEmployee.setLearnedSkills(learnedSkills);
            }

            if (trainingPeriod != null) {
                updatableEmployee.setTrainingPeriod(trainingPeriod);
            }
            return employeeDAO.updateEmployee(updatableEmployee);
        } else {
            throw new EmployeeException(ConstantUtil.ID_NOT_FOUND);
        }
    }

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
    public Employee searchTraineeById(String id) throws EmployeeException  {
        return employeeDAO.retrieveEmployeeByIdAndRole(id, ConstantUtil.TRAINEE);
    }

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
    public List<Employee> getTrainees(int startValue)  throws EmployeeException {
        return employeeDAO.retrieveEmployeesByRoleName(ConstantUtil.TRAINEE, startValue);
    }

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
    public boolean deleteTraineeById(String id) throws EmployeeException  {
        return (employeeDAO.deleteTrainerById(id));
    }

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
    public int getTrainerPageCount() throws EmployeeException {
        long noOfTrainers = employeeDAO.getNoOfEmployeeByRoleName(ConstantUtil.TRAINER);
        int pageCount = 0;
        if (noOfTrainers != 0) {
            pageCount = (int) noOfTrainers / 5;
            if ((noOfTrainers % 5) != 0) {
                pageCount++;
            }
        }
        return pageCount;
    }

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
    public int getTraineePageCount() throws EmployeeException {
        long noOfTrainees = employeeDAO.getNoOfEmployeeByRoleName(ConstantUtil.TRAINEE);
        int pageCount = 0;
        if (noOfTrainees != 0) {
            pageCount = (int) noOfTrainees / 5;
            if ((noOfTrainees % 5) != 0) {
                pageCount++;
            }
        }
        return pageCount;
    }
}