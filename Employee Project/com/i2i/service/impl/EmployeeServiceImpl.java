package com.i2i.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.i2i.dao.EmployeeDAO;
import com.i2i.dao.impl.EmployeeDAOImpl;
import com.i2i.enumerator.Gender;
import com.i2i.exception.EmployeeException;
import com.i2i.model.Employee;
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
    EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();

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
        int id = employeeDAOImpl.getEmployeeId();
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
        employee.setRole(ConstantUtil.TRAINER);
        return employeeDAOImpl.insertEmployee(employee);
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
     * @return boolean value true if the updation is success, otherwise false.
     *
     * @throws EmployeeException.
     */
    public boolean updateTrainerById(String id, Employee employee) throws EmployeeException {
        List<Employee> trainers = employeeDAOImpl.retrieveTrainerById(id); 
        if (!trainers.isEmpty()) {
            if (trainers.get(0).getId().equals(id)) {
                Employee updatableEmployee = trainers.get(0);
                String name = employee.getName();
                Gender gender = employee.getGender();
                String address = employee.getAddress();
                String designation = employee.getDesignation();
                String role = employee.getRole();
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

                if (role != null && !role.isEmpty()) {
                    updatableEmployee.setRole(role);
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

                if (previousExperience != updatableEmployee.getPreviousExperience()) {
                    updatableEmployee.setPreviousExperience(previousExperience);
                }

                if (specialization != null && !specialization.isEmpty()) {
                    updatableEmployee.setSpecialization(specialization);
                }

                if (trainingExperience != updatableEmployee.getTrainingExperience()) {
                    updatableEmployee.setTrainingExperience(trainingExperience);
                }

                if (noOfTrainee != updatableEmployee.getNoOfTrainee()) {
                    updatableEmployee.setNoOfTrainee(noOfTrainee);
                }
                return employeeDAOImpl.updateEmployeeById(id, updatableEmployee);
            } else {
                throw new EmployeeException(ConstantUtil.ID_NOT_FOUND);
            }
        } else {
            throw new EmployeeException(ConstantUtil.EMPTY_REOCRDS);
        }
    }

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
    public List<Employee> searchTrainerById(String id) throws EmployeeException  {
        return employeeDAOImpl.retrieveTrainerById(id);
    }

    /**
     * <p>
     * This method will send the entire trainers for the called method.
     * </p>
     * 
     * @return employeesMap entryset.
     *
     * @throws EmployeeException.
     */
    public List<Employee> getTrainers()  throws EmployeeException {
        return employeeDAOImpl.retrieveTrainers();
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
        return (employeeDAOImpl.deleteEmployeeById(id));
    }

    /**
     * <p>
     * This method checks whether trainers exist or not
     * </p>
     * 
     * @return the boolean value true if the data exist, otherwise false.
     *
     * @throws EmployeeException.
     */
    public boolean isTrainersEmpty() throws EmployeeException {
        return employeeDAOImpl.isTrainersEmpty();
    }

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
    public boolean isTrainerExist(String id) throws EmployeeException {
        return employeeDAOImpl.isTrainerExist(id);
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
        employee.setRole(ConstantUtil.TRAINEE);
        return employeeDAOImpl.insertEmployee(employee);
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
     * @return boolean value true if the updation is success, otherwise false.
     *
     * @throws EmployeeException.
     */
    public boolean updateTraineeById(String id, Employee employee) throws EmployeeException {
        List<Employee> trainees = employeeDAOImpl.retrieveTraineeById(id);
        if (!trainees.isEmpty()) {
            if (trainees.get(0).getId().equals(id)) {
                Employee updatableEmployee = trainees.get(0);
                String name = employee.getName();
                Gender gender = employee.getGender();
                String address = employee.getAddress();
                String designation = employee.getDesignation();
                String role = employee.getRole();
                String emailId = employee.getEmailId();
                String mobileNo = employee.getMobileNo();
                Date dateOfBirth = employee.getDateOfBirth();
                Date dateOfJoin = employee.getDateOfJoin();
                Float previousExperience = employee.getPreviousExperience();
                List<String> trainerName = employee.getTrainerName();
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

                if (role != null && !role.isEmpty()) {
                    updatableEmployee.setRole(role);
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

                if (previousExperience != updatableEmployee.getPreviousExperience()) {
                    updatableEmployee.setPreviousExperience(previousExperience);
                }

                if (trainerName != null && !trainerName.isEmpty()) {
                    updatableEmployee.setTrainerName(trainerName);
                }

                if (learnedSkills != null && !learnedSkills.isEmpty()) {
                    updatableEmployee.setLearnedSkills(learnedSkills);
                }

                if (trainingPeriod != updatableEmployee.getTrainingPeriod()) {
                    updatableEmployee.setTrainingPeriod(trainingPeriod);
                }
                return employeeDAOImpl.updateEmployeeById(id, updatableEmployee);
            } else {
                throw new EmployeeException(ConstantUtil.ID_NOT_FOUND);
            }
        } else {
            throw new EmployeeException(ConstantUtil.EMPTY_REOCRDS);
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
    public List<Employee> searchTraineeById(String id) throws EmployeeException  {
        return employeeDAOImpl.retrieveTraineeById(id);
    }

    /**
     * <p>
     * This method will send the entire trainees for the called method.
     * </p>
     * 
     * @return the employees as list.
     *
     * @throws EmployeeException.
     */
    public List<Employee> getTrainees()  throws EmployeeException {
        return employeeDAOImpl.retrieveTrainees();
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
        return (employeeDAOImpl.deleteEmployeeById(id));
    }

    /**
     * <p>
     * This method checks whether trainers exist or not
     * </p>
     * 
     * @return the boolean value true if the data exist, otherwise false.
     *
     * @throws EmployeeException.
     */
    public boolean isTraineesEmpty() throws EmployeeException {
        return employeeDAOImpl.isTraineesEmpty();
    }

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
    public boolean isTraineeExist(String id) throws EmployeeException {
        return employeeDAOImpl.isTraineeExist(id);
    }
}