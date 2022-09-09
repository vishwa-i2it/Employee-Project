package com.i2i.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.i2i.dao.EmployeeDAO;
import com.i2i.database.DatabaseConnection;
import com.i2i.enumerator.Gender;
import com.i2i.exception.EmployeeException;
import com.i2i.model.Employee;

/**
 * This class is used to perform the database operations
 * for Employee details. 
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class EmployeeDAOImpl implements EmployeeDAO {

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
    public Integer getEmployeeId() throws EmployeeException {
        try {
            int id;
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement employeeIdStatement = connection.prepareStatement("select max(id) from employee_id;");
            ResultSet resultSet = employeeIdStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
            if (id == 0) {
                id = 1;
            PreparedStatement employeeIdUpdateStatement = connection.prepareStatement("insert into employee_id values(?);");
            employeeIdUpdateStatement.setInt(1, id);
            employeeIdUpdateStatement.executeUpdate();
            employeeIdUpdateStatement.close();
            } else {
                id++;
            PreparedStatement employeeIdUpdateStatement = connection.prepareStatement("update employee_id set id = ? ;");
            employeeIdUpdateStatement.setInt(1, id);
            employeeIdUpdateStatement.executeUpdate();
            employeeIdUpdateStatement.close();
            }
            employeeIdStatement.close();
            connection.close();
            return id;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public boolean insertEmployee(Employee employee) throws EmployeeException {
        try {
            Connection connection = DatabaseConnection.connectDatabase();
            int roleId;
            PreparedStatement getRoleIdStatement = connection.prepareStatement("select role_id from role where role_name = ? ;");
            getRoleIdStatement.setString(1, employee.getRole());
            ResultSet resultSet = getRoleIdStatement.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getInt(1);
            } else {
                throw new SQLException("The given role not in table");
            }
            PreparedStatement insertEmployeeStatement = connection.prepareStatement("insert into employee (`employee_id`, "
                   + "`name`, `gender`, `address`, `designation`, `emailId`, `mobileNo`, `dateOfBirth`, `dateOfJoin`, "
                   + "`specialization`, `trainingExperience`, `noOfTrainee`, `trainerName`, `learnedSkill`, "
                   + "`trainingPeriod`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insertEmployeeStatement.setString(1, employee.getId());
            insertEmployeeStatement.setString(2, employee.getName());
            insertEmployeeStatement.setString(3, employee.getGender().toString());
            insertEmployeeStatement.setString(4, employee.getAddress());
            insertEmployeeStatement.setString(5, employee.getDesignation());
            insertEmployeeStatement.setString(6, employee.getEmailId());
            insertEmployeeStatement.setString(7, employee.getMobileNo());
            insertEmployeeStatement.setDate(8, new java.sql.Date(employee.getDateOfBirth().getTime()));
            insertEmployeeStatement.setDate(9, new java.sql.Date(employee.getDateOfJoin().getTime()));
            insertEmployeeStatement.setString(10, employee.getSpecialization());
            insertEmployeeStatement.setFloat(11, employee.getTrainingExperience());
            insertEmployeeStatement.setInt(12, employee.getNoOfTrainee());
            if (employee.getTrainerName().isEmpty()) {
                insertEmployeeStatement.setString(13, null);
            } else {
                insertEmployeeStatement.setString(13, String.join(", ", employee.getTrainerName()));
            }
            if (employee.getLearnedSkills().isEmpty()) {
                insertEmployeeStatement.setString(14, null);
            } else {
                insertEmployeeStatement.setString(14, String.join(", ", employee.getLearnedSkills()));
            }
            insertEmployeeStatement.setInt(15, employee.getTrainingPeriod());

            PreparedStatement insertEmployeeRoleStatement = connection.prepareStatement("insert into employee_role ("
                    + "`employee_id`,`role_id`) values(?, ?)");
            insertEmployeeRoleStatement.setString(1, employee.getId());
            insertEmployeeRoleStatement.setInt(2, roleId); 
            int insertEmployeeCount = insertEmployeeStatement.executeUpdate();
            int insertEmployeeRoleCount = insertEmployeeRoleStatement.executeUpdate();
            insertEmployeeStatement.close();
            insertEmployeeRoleStatement.close();
            connection.close();
            if ((insertEmployeeCount == 1) && (insertEmployeeRoleCount == 1)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public boolean updateEmployeeById(String id, Employee employee) throws EmployeeException {
        try {
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement updateEmployeeStatement = connection.prepareStatement("update employee set "
                   + "`name` = ?, `gender` = ?, `address` = ?, `designation` = ?, `emailId` = ?, `mobileNo` = ?, `dateOfBirth` = ?, `dateOfJoin` = ?, "
                   + "`specialization` = ?, `trainingExperience` = ?, `noOfTrainee` = ?, `trainerName` = ?, `learnedSkill` = ?, "
                   + "`trainingPeriod` = ? where employee_id = ? ;");
            updateEmployeeStatement.setString(1, employee.getName());
            updateEmployeeStatement.setString(2, employee.getGender().toString());
            updateEmployeeStatement.setString(3, employee.getAddress());
            updateEmployeeStatement.setString(4, employee.getDesignation());
            updateEmployeeStatement.setString(5, employee.getEmailId());
            updateEmployeeStatement.setString(6, employee.getMobileNo());
            updateEmployeeStatement.setDate(7, new java.sql.Date(employee.getDateOfBirth().getTime()));
            updateEmployeeStatement.setDate(8, new java.sql.Date(employee.getDateOfJoin().getTime()));
            updateEmployeeStatement.setString(9, employee.getSpecialization());
            updateEmployeeStatement.setFloat(10, employee.getTrainingExperience());
            updateEmployeeStatement.setInt(11, employee.getNoOfTrainee());
            if (employee.getTrainerName().isEmpty()) {
                updateEmployeeStatement.setString(12, null);
            } else {
                updateEmployeeStatement.setString(12, String.join(", ", employee.getTrainerName()));
            }
            if (employee.getLearnedSkills().isEmpty()) {
                updateEmployeeStatement.setString(13, null);
            } else {
                updateEmployeeStatement.setString(13, String.join(", ", employee.getLearnedSkills()));
            }
            updateEmployeeStatement.setInt(14, employee.getTrainingPeriod());
            updateEmployeeStatement.setString(15, id);
            int updatedCount = updateEmployeeStatement.executeUpdate();
            updateEmployeeStatement.close();
            connection.close();
            if (updatedCount == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public boolean deleteEmployeeById(String id) throws EmployeeException {
        try {
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement deleteEmployeeStatement = connection.prepareStatement("delete from employee where employee_id = ? ;");
            deleteEmployeeStatement.setString(1,id);
            int result = deleteEmployeeStatement.executeUpdate();
            deleteEmployeeStatement.close();
            connection.close();
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to check whether the trainers is available or not in table.
     * </p>
     * 
     * @return boolean value true if avaliable, otherwise false.
     *
     * @throws EmployeeException
     */
    public boolean isTrainersEmpty() throws EmployeeException {
        try {
            boolean flag = true;
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement trainersEmptyStatement = connection.prepareStatement("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where " 
                    + "employee_role.role_id = 1");
            ResultSet resultSet = trainersEmptyStatement.executeQuery();
            if (resultSet.next()) {
                flag = false;
            } 
            trainersEmptyStatement.close();
            connection.close();
            return flag;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to check whether the trainer exist or not for given id.
     * </p>
     * 
     * @return boolean value true if exist, otherwise false.
     *
     * @throws EmployeeException
     */
    public boolean isTrainerExist(String id) throws EmployeeException {
        try {
            boolean flag = false;
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement trainerExistStatement = connection.prepareCall("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where "
                    + "employee_role.role_id = 1 and employee.employee_id = ?");
            trainerExistStatement.setString(1, id);
            ResultSet resultSet = trainerExistStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            } 
            trainerExistStatement.close();
            connection.close();
            return flag;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public List<Employee> retrieveTrainers() throws EmployeeException {
        try {
            List<Employee> trainers = new ArrayList<Employee>();
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement retrieveTrainersStatement = connection.prepareStatement("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where " 
                    + "employee_role.role_id = 1 ;");
            ResultSet resultSet = retrieveTrainersStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString(2));
                employee.setName(resultSet.getString(3));
                employee.setGender(Gender.valueOf(resultSet.getString(4)));
                employee.setAddress(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employee.setEmailId(resultSet.getString(7));
                employee.setMobileNo(resultSet.getString(8));
                employee.setDateOfBirth(resultSet.getDate(9));
                employee.setDateOfJoin(resultSet.getDate(10));
                employee.setSpecialization(resultSet.getString(11));
                employee.setTrainingExperience(resultSet.getFloat(12));
                employee.setNoOfTrainee(resultSet.getInt(13));
                employee.setRole(resultSet.getString(17));
                trainers.add(employee);
            } 
            retrieveTrainersStatement.close();
            connection.close();
            return trainers;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public List<Employee> retrieveTrainerById(String id) throws EmployeeException {
        try {
            List<Employee> trainers = new ArrayList<Employee>();
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement retrieveTrainerStatement = connection.prepareStatement("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where "
                    + "employee_role.role_id = 1 and employee.employee_id= ? ;");
            retrieveTrainerStatement.setString(1, id);
            ResultSet resultSet = retrieveTrainerStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString(2));
                employee.setName(resultSet.getString(3));
                employee.setGender(Gender.valueOf(resultSet.getString(4)));
                employee.setAddress(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employee.setEmailId(resultSet.getString(7));
                employee.setMobileNo(resultSet.getString(8));
                employee.setDateOfBirth(resultSet.getDate(9));
                employee.setDateOfJoin(resultSet.getDate(10));
                employee.setSpecialization(resultSet.getString(11));
                employee.setTrainingExperience(resultSet.getFloat(12));
                employee.setNoOfTrainee(resultSet.getInt(13));
                employee.setRole(resultSet.getString(17));
                trainers.add(employee);
            } 
            retrieveTrainerStatement.close();
            connection.close();
            return trainers;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to check whether the trainees is available or not in table.
     * </p>
     * 
     * @return boolean value true if avaliable, otherwise false.
     *
     * @throws EmployeeException
     */
    public boolean isTraineesEmpty() throws EmployeeException {
        try {
            boolean flag = true;
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement trainersEmptyStatement = connection.prepareStatement("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where " 
                    + "employee_role.role_id = 2 ;");
            ResultSet resultSet = trainersEmptyStatement.executeQuery();
            if (resultSet.next()) {
                flag = false;
            } 
            trainersEmptyStatement.close();
            connection.close();
            return flag;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to check whether the trainee exist or not for given id.
     * </p>
     * 
     * @return boolean value true if exist, otherwise false.
     *
     * @throws EmployeeException
     */
    public boolean isTraineeExist(String id) throws EmployeeException {
        try {
            boolean flag = false;
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement trainerExistStatement = connection.prepareCall("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where "
                    + "employee_role.role_id = 2 and employee.employee_id = ? ;");
            trainerExistStatement.setString(1, id);
            ResultSet resultSet = trainerExistStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            } 
            trainerExistStatement.close();
            connection.close();
            return flag;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public List<Employee> retrieveTrainees() throws EmployeeException {
        try {
            List<Employee> trainees = new ArrayList<Employee>();
            boolean flag = true;
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement retrieveTraineesStatement = connection.prepareStatement("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where " 
                    + "employee_role.role_id = 2 ;");
            ResultSet resultSet = retrieveTraineesStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString(2));
                employee.setName(resultSet.getString(3));
                employee.setGender(Gender.valueOf(resultSet.getString(4)));
                employee.setAddress(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employee.setEmailId(resultSet.getString(7));
                employee.setMobileNo(resultSet.getString(8));
                employee.setDateOfBirth(resultSet.getDate(9));
                employee.setDateOfJoin(resultSet.getDate(10));
                if (resultSet.getString(14) != null) {
                    List<String> trainerNames = new ArrayList<String>(Arrays.asList(resultSet.getString(14).split(", ")));
                    employee.setTrainerName(trainerNames);
                } else {
                    employee.setTrainerName(null);
                }
                if (resultSet.getString(15) != null) {
                    Set<String> learnedSkills = new LinkedHashSet<String>(Arrays.asList(resultSet.getString(15).split(", ")));
                    employee.setLearnedSkills(learnedSkills);
                } else {
                    employee.setLearnedSkills(null);
                }
                employee.setTrainingPeriod(resultSet.getInt(16));
                employee.setRole(resultSet.getString(17));
                trainees.add(employee);
            } 
            retrieveTraineesStatement.close();
            connection.close();
            return trainees;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }

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
    public List<Employee> retrieveTraineeById(String id) throws EmployeeException {
        try {
            List<Employee> trainees = new ArrayList<Employee>();
            Connection connection = DatabaseConnection.connectDatabase();
            PreparedStatement retrieveTraineeStatement = connection.prepareStatement("SELECT employee.*, "
                    + "role.role_name from employee join employee_role on employee_role.employee_id = "
                    + "employee.employee_id join role on role.role_id=employee_role.role_id where "
                    + "employee_role.role_id = 2 and employee.employee_id = ? ;");
            retrieveTraineeStatement.setString(1, id);
            ResultSet resultSet = retrieveTraineeStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString(2));
                employee.setName(resultSet.getString(3));
                employee.setGender(Gender.valueOf(resultSet.getString(4)));
                employee.setAddress(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employee.setEmailId(resultSet.getString(7));
                employee.setMobileNo(resultSet.getString(8));
                employee.setDateOfBirth(resultSet.getDate(9));
                employee.setDateOfJoin(resultSet.getDate(10));
                if (resultSet.getString(14) != null) {
                    List<String> trainerNames = new ArrayList<String>(Arrays.asList(resultSet.getString(14).split(", ")));
                    employee.setTrainerName(trainerNames);
                } else {
                    employee.setTrainerName(null);
                }
                if (resultSet.getString(15) != null) {
                    Set<String> learnedSkills = new LinkedHashSet<String>(Arrays.asList(resultSet.getString(15).split(", ")));
                    employee.setLearnedSkills(learnedSkills);
                } else {
                    employee.setLearnedSkills(null);
                }
                employee.setTrainingPeriod(resultSet.getInt(16));
                employee.setRole(resultSet.getString(17));
                trainees.add(employee);
            } 
            retrieveTraineeStatement.close();
            connection.close();
            return trainees;
        } catch (SQLException sqlException) {
            throw new EmployeeException(sqlException.getMessage());
        }
    }
}