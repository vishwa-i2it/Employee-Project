package com.i2i.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.i2i.dto.EmployeeDTO;
import com.i2i.model.Employee;
import com.i2i.util.DateUtil;

/** 
 * The Mapper class contains the following methods 
 * to convert the DTO object to Entity objcet and
 * vice versa.
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 17 Aug 2022
 */
public class EmployeeMapper {
    /**
     * <p> This method is used to convert the DTO object 
     *  to Entity object
     * </p>
     *
     * @param EmployeeDTO employeeDTO 
     *         For which employeeDTO(object) needs to convert as employee(object).
     * @return Employee
               Return as employee object.
     */
    public static Employee dtoToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setGender(employeeDTO.getGender());
        employee.setAddress(employeeDTO.getAddress());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setRole(employeeDTO.getRole());
        employee.setEmailId(employeeDTO.getEmailId());
        employee.setMobileNo(employeeDTO.getMobileNo());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setDateOfJoin(employeeDTO.getDateOfJoin());
        employee.setPreviousExperience(employeeDTO.getPreviousExperience());
        employee.setSpecialization(employeeDTO.getSpecialization());
        employee.setTrainingExperience(employeeDTO.getTrainingExperience());
        employee.setNoOfTrainee(employeeDTO.getNoOfTrainee());
        employee.setTrainerName(employeeDTO.getTrainerName());
        employee.setLearnedSkills(employeeDTO.getLearnedSkills());
        employee.setTrainingPeriod(employeeDTO.getTrainingPeriod());
        return employee;
    }

    /**
     * <p> This method is used to convert the Entity object 
     *  to DTO object
     * </p>
     *
     * @param Employee employee 
     *         For which employee(object) needs to convert as employeeDTO(object).
     * @return EmployeeDTO
               Return as employeeDTO object.
     */
    public static EmployeeDTO entityToDto(Employee employee) {
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setAge(DateUtil.calculateYears(dateFormater.format(employee.getDateOfBirth())));
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setDesignation(employee.getDesignation());
        employeeDTO.setRole(employee.getRole());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setMobileNo(employee.getMobileNo());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setDateOfJoin(employee.getDateOfJoin());
        employeeDTO.setExperience(employee.getPreviousExperience() 
                + DateUtil.calculateYearsMonths(dateFormater.format(employee.getDateOfJoin())));
        
        employeeDTO.setSpecialization(employee.getSpecialization());
        employeeDTO.setTrainingExperience(employee.getTrainingExperience());
        employeeDTO.setNoOfTrainee(employee.getNoOfTrainee());
        employeeDTO.setTrainerName(employee.getTrainerName());
        employeeDTO.setLearnedSkills(employee.getLearnedSkills());
        employeeDTO.setTrainingPeriod(employee.getTrainingPeriod());
        return employeeDTO;
    }

    /**
     * <p> This method is used to convert the set of Entity objects 
     *  to collection of DTO object.
     * </p>
     *
     * @param Map<String, Employee> employees 
     *         For which employee(object) needs to convert as employeeDTO(object).
     * @return List<EmployeeDTO>
               Return as employeeDTO objects.
     */
    public static List<EmployeeDTO> entityListToDtoList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
                employeeDTOList.add(EmployeeMapper.entityToDto(employee));
        }
        return employeeDTOList;
    }
}