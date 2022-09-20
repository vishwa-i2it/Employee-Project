package com.i2i.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.i2i.dto.EmployeeDTO;
import com.i2i.enumerator.Gender;
import com.i2i.exception.EmployeeException;
import com.i2i.exception.ExperienceException;
import com.i2i.mapper.EmployeeMapper;
import com.i2i.model.Employee;
import com.i2i.service.impl.EmployeeServiceImpl;
import com.i2i.service.EmployeeService;
import com.i2i.util.CommonUtil;
import com.i2i.util.ConstantUtil;
import com.i2i.util.DateUtil;
import com.i2i.util.StringUtil;

/**
 * This class is a controller class
 * for performing the CRUD operations for the
 * employee.
 *
 * @author Vishwaeaswaran M
 * @version 1.0
 * @since 22/07/2022
 *
 */
class EmployeeController {
    EmployeeService employeeService = new EmployeeServiceImpl();
    static Logger log = LogManager.getLogger(EmployeeController.class);
    
    public static void main (String[] args) {
        EmployeeController employeeController = new EmployeeController();
        Scanner scanner = new Scanner(System.in);
        int givenProfileAccess = 0;
        boolean validInput = false;

        do {
            System.out.println("\nWhich profile you going to access?\n" 
                           + "1. Trainer \n2. Trainee \n3. Exit");
            givenProfileAccess = employeeController.getNumber();
            switch (givenProfileAccess) {
                case 1:
                    employeeController.trainerOperation();
                    break;

                case 2:
                    employeeController.traineeOperation();
                    break;

                case 3:
                    givenProfileAccess = employeeController.terminateApp();
                    break;

                default:
                    System.out.println(ConstantUtil.INPUT_RANGE);
                    break;
            } 
        } while(givenProfileAccess != 3);
    }

    /**
     * <p>
     * This method is used to perform the trainer operation 
     * </p>
     *
     */
    private void trainerOperation() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int givenTrainerOperation = 0;

        do {
            System.out.print("\nWhich Operation Going to do?" 
                               + "\n1. Add Details\n2. Update Details\n3. Search\n4. View All\n5. Delete\n6. Go Back\n---> ");
            givenTrainerOperation = getNumber();
            switch (givenTrainerOperation) {
                case 1:
                    System.out.println("");
                    readTrainer();
                    break;

                case 2:
                    updateTrainerById();
                    break;

                case 3:
                    searchTrainerById();
                    break;

                case 4:
                    showTrainers();
                    break;

                case 5:
                    deleteTrainerById();
                    break;

                case 6:
                    System.out.println(ConstantUtil.MAIN_MENU);
                    break;

                default:
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    break;
            }
        } while (givenTrainerOperation !=6 );
    }

    /**
     * <p>
     * This method is used to perform the trainee operation 
     * </p>
     *
     */
    private void traineeOperation() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int givenTraineeOperation = 0;

        do {
            System.out.print("\nWhich Operation Going to do?" 
                               + "\n1. Add Details\n2. Update Details\n3. Search\n4. View All\n5. Delete\n6. Go Back\n---> ");
            givenTraineeOperation = getNumber();
            switch (givenTraineeOperation) {
                case 1:
                    System.out.println("");
                    readTrainee();
                    break;

                case 2:
                    updateTraineeById();
                    break;

                case 3:
                    searchTraineeById();
                    break;

                case 4:
                    showTrainees();
                    break;

                case 5:
                    deleteTraineeById();
                    break;

                case 6:
                    System.out.println(ConstantUtil.MAIN_MENU);
                    break;

                default:
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    break;
            }
        } while (givenTraineeOperation !=6 );
    }

    /**
     * <p>
     * This method is used to exit the application 
     * </p>
     *
     */
    private int terminateApp() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int terminateStateValue = 0;
        do {
            System.out.print("Are you want to exit (y/n) ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if ("y".equals(confirmation)) {
                System.out.println(ConstantUtil.THANK_YOU_MESSAGE);
                terminateStateValue = 3;
                validInput = true;
            } else if ("n".equals(confirmation)) {
                terminateStateValue = 0;
                validInput = true;
            } else {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                validInput = false;
            }
        } while (!validInput);
        return terminateStateValue;
    }

    /**
     * <p>
     * This method is used to get the input for the following fields
     * and pass it to the addTrainer() method.
     * </p>
     *
     */
    private void readTrainer() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = false;

        System.out.println("\nEnter the values for the Trainer: \n * These Fields are mandatory");

        try {
            String id = employeeService.generateEmployeeId();
            System.out.println("ID                    : " + id);
            employeeDTO.setEmployeeId(id);
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.INSERT_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.INSERT_ERROR);
        }

        employeeDTO.setName(getNameMandatory());
        employeeDTO.setGender(getGenderMandatory());
        employeeDTO.setAddress(getAddress());
        employeeDTO.setDesignation(getDesignation());
        employeeDTO.setEmailId(getEmailIdMandatory());
        employeeDTO.setMobileNo(getMobileNoMandatory());

        Date dateOfBirth = getDateOfBirthMandatory();
        employeeDTO.setDateOfBirth(dateOfBirth);

        Date dateOfJoin = getDateOfJoinMandatory(dateOfBirth);
        employeeDTO.setDateOfJoin(dateOfJoin);

        employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
        employeeDTO.setSpecialization(getSpecialization());
        employeeDTO.setTrainingExperience(getTrainingExperience(dateOfBirth));
        employeeDTO.setNoOfTrainee(getNoOfTrainee());

        try {
            if (employeeService.addTrainer(EmployeeMapper.dtoToEntity(employeeDTO))) {
                System.out.println(ConstantUtil.INSERT_SUCCESS);
            } else {
                System.out.println(ConstantUtil.INSERT_FAILED);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.INSERT_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.INSERT_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to get the input for the following fields
     * and pass it to the updateEmployeeById() method.
     * </p>
     *
     */
    private void updateTrainerById() {
        try{
            EmployeeDTO employeeDTO = new EmployeeDTO();
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            boolean isValidInput = true;

            System.out.print("Enter the Employee Id: ");
            String employeeId = scanner.nextLine().trim();
            Employee updatableEmployee = employeeService.searchTrainerById(employeeId);
            if (updatableEmployee != null) {
                System.out.println("Please enter the value for corresponding fields!"
                        + "\nIf you want to skip any field just hit the Enter button");
                employeeDTO.setName(getName());
                employeeDTO.setGender(getGender());
                employeeDTO.setAddress(getAddress());
                employeeDTO.setDesignation(getDesignation());
                employeeDTO.setEmailId(getEmailId());
                employeeDTO.setMobileNo(getMobileNo());
                Date dateOfBirth = getDateOfBirth();
                if (dateOfBirth != null) {
                    employeeDTO.setDateOfBirth(dateOfBirth);
                } else {
                    dateOfBirth = updatableEmployee.getDateOfBirth();
                }

                Date dateOfJoin = getDateOfJoin(dateOfBirth);
                if (dateOfJoin != null) {
                    employeeDTO.setDateOfJoin(dateOfJoin);
                } else {
                    dateOfJoin = updatableEmployee.getDateOfJoin();
                }

                employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
                employeeDTO.setSpecialization(getSpecialization());
                employeeDTO.setTrainingExperience(getTrainingExperience(dateOfBirth));
                employeeDTO.setNoOfTrainee(getNoOfTrainee());

                try {
                    Employee employee = employeeService.updateTrainer(EmployeeMapper.dtoToEntity(employeeDTO), updatableEmployee);
                    if (employee != null) {
                        System.out.println(ConstantUtil.CHANGES_DONE);
                        System.out.println(EmployeeMapper.entityToDto(employee));
                    } else {
                        System.out.println(ConstantUtil.CHANGES_FAILED);
                    }
                } catch (EmployeeException employeeException) {
                    log.error(ConstantUtil.UPDATE_ERROR + employeeException.toString());
                    System.err.println(ConstantUtil.UPDATE_ERROR);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.UPDATE_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.UPDATE_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to display trainer for the given ID.
     * </p>
     *
     */
    private void searchTrainerById() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Employee Id: ");
            String employeeId = scanner.nextLine().trim();
            Employee employee = employeeService.searchTrainerById(employeeId);
            if (employee != null) {
                System.out.println(EmployeeMapper.entityToDto(employee));
            } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.SEARCH_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.SEARCH_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to display set of trainers 
     * </p>
     *
     */
    private void showTrainers() {
        Scanner scanner = new Scanner(System.in);
        int startPosition = 0;
        boolean stop = false;
        int currentPage = 1;
        try {
            int totalPage = employeeService.getTrainerPageCount();
            while (!stop) {
                List<Employee> employees = employeeService.getTrainers(startPosition);
                if (totalPage != 0 && !employees.isEmpty()){
                    System.out.println("Page Number: "+ currentPage);
                    System.out.println("----------------------------"
                                       +"---------------------");
                    for (Employee employee : employees) {
                        System.out.println(EmployeeMapper.entityToDto(employee));
                    }
                    System.out.println("----------------------------"
                                       +"---------------------");
                    System.out.println("\n1. Next \t2. Previous \t3. End");
                    int givenOption = getNumber();
                    if (givenOption == 1) {
                        if (totalPage != currentPage) {
                            currentPage++;
                            startPosition += 5;
                        } else {
                            System.out.println("Your in last page!!");
                            System.out.println("-------------------");
                        }
                    } else if (givenOption == 2) {
                        if (currentPage != 1) {
                            currentPage--;
                            startPosition -= 5;
                        } else {
                            System.out.println("Your in first page!!");
                            System.out.println("--------------------");
                        }
                    } else if (givenOption == 3) {
                        stop = true;
                    } else {
                        System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    }
                } else {
                    System.out.println("There is no records to show. Please insert the record first!");
                }
            }        
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.DISPLAY_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.DISPLAY_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to delete trainer for the given ID.
     * </p>
     *
     */
    private void deleteTrainerById() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Employee Id: ");
            String employeeId = scanner.nextLine().trim();
            if (employeeService.deleteTrainerById(employeeId)) {
                System.out.println(ConstantUtil.DELETE_SUCCESS);
            } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.DELETE_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.DELETE_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to get the input for the following fields
     * and pass it to the addEmployee() method.
     * </p>
     *
     */
    private void readTrainee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = false;

        System.out.println("\nEnter the values for the Trainee: \n * These Fields are mandatory");

        try {
            String id = employeeService.generateEmployeeId();
            System.out.println("ID                    : " + id);
            employeeDTO.setEmployeeId(id);
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.INSERT_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.INSERT_ERROR);
        }

        employeeDTO.setName(getNameMandatory());
        employeeDTO.setGender(getGenderMandatory());
        employeeDTO.setAddress(getAddress());
        employeeDTO.setDesignation(getDesignation());
        employeeDTO.setEmailId(getEmailIdMandatory());
        employeeDTO.setMobileNo(getMobileNoMandatory());

        Date dateOfBirth = getDateOfBirthMandatory();
        employeeDTO.setDateOfBirth(dateOfBirth);

        Date dateOfJoin = getDateOfJoin(dateOfBirth);
        employeeDTO.setDateOfJoin(dateOfJoin);

        employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
        employeeDTO.setTrainersName(getTrainersName());
        employeeDTO.setLearnedSkills(getLearnedSkills());
        employeeDTO.setTrainingPeriod(getTrainingPeriod());

        try {
            if (employeeService.addTrainee(EmployeeMapper.dtoToEntity(employeeDTO))) {
                System.out.println(ConstantUtil.INSERT_SUCCESS);
            } else {
                System.out.println(ConstantUtil.INSERT_FAILED);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.INSERT_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.INSERT_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to get the input for the following fields
     * and pass it to a method in service for updating the fields.
     * </p>
     *
     */
    private void updateTraineeById() {
        try {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            boolean isValidInput = true;

            System.out.print("Enter the Employee Id: ");
            String employeeId = scanner.nextLine().trim();
            Employee updatableEmployee = employeeService.searchTraineeById(employeeId);
            if (updatableEmployee != null) {
                System.out.println("Please enter the value for corresponding fields!"
                         + "\nIf you want to skip any field just hit the Enter button");
                employeeDTO.setName(getName());
                employeeDTO.setGender(getGender());
                employeeDTO.setAddress(getAddress());
                employeeDTO.setDesignation(getDesignation());
                employeeDTO.setEmailId(getEmailId());
                employeeDTO.setMobileNo(getMobileNo());
                    
                Date dateOfBirth = getDateOfBirth();
                if (dateOfBirth != null) {
                    employeeDTO.setDateOfBirth(dateOfBirth);
                } else {
                    dateOfBirth = updatableEmployee.getDateOfBirth();
                }

                Date dateOfJoin = getDateOfJoin(dateOfBirth);
                if (dateOfJoin != null) {
                    employeeDTO.setDateOfJoin(dateOfJoin);
                } else {
                    dateOfJoin = updatableEmployee.getDateOfJoin();
                }

                employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
                employeeDTO.setTrainersName(getTrainersName());
                employeeDTO.setLearnedSkills(getLearnedSkills());
                employeeDTO.setTrainingPeriod(getTrainingPeriod());

                try {
                    Employee employee = employeeService.updateTrainee(EmployeeMapper.dtoToEntity(employeeDTO), updatableEmployee);
                    if (employee != null) {
                        System.out.println(ConstantUtil.CHANGES_DONE);
                        System.out.println(EmployeeMapper.entityToDto(employee));
                    } else {
                        System.out.println(ConstantUtil.CHANGES_FAILED);
                    }
                } catch (EmployeeException employeeException) {
                    log.error(ConstantUtil.UPDATE_ERROR + employeeException.toString());
                    System.err.println(ConstantUtil.UPDATE_ERROR);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.UPDATE_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.UPDATE_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to display trainee for the given ID.
     * </p>
     *
     */
    private void searchTraineeById() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Employee Id: ");
            String employeeId = scanner.nextLine().trim();
            Employee employee = employeeService.searchTraineeById(employeeId);
            if (employee != null) {
                System.out.println(EmployeeMapper.entityToDto(employee));
            } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
            }
        } catch(EmployeeException employeeException) {
            log.error(ConstantUtil.SEARCH_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.SEARCH_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to display set of trainees 
     * </p>
     *
     */
    private void showTrainees() {
        Scanner scanner = new Scanner(System.in);
        int startPosition = 0;
        boolean stop = false;
        int currentPage = 1;
        try {
            int totalPage = employeeService.getTraineePageCount();
            while (!stop) {
                List<Employee> employees = employeeService.getTrainees(startPosition);
                if (totalPage != 0 && !employees.isEmpty()){
                    System.out.println("Page Number: "+ currentPage);
                    System.out.println("----------------------------"
                                       +"---------------------");
                    for (Employee employee : employees) {
                        System.out.println(EmployeeMapper.entityToDto(employee));
                    }
                    System.out.println("----------------------------"
                                       +"---------------------");
                    System.out.println("\n1. Next \t2. Previous \t3. End");
                    int givenOption = getNumber();
                    if (givenOption == 1) {
                        if (totalPage != currentPage) {
                            currentPage++;
                            startPosition += 5;
                        } else {
                            System.out.println("Your in last page!!");
                            System.out.println("-------------------");
                        }
                    } else if (givenOption == 2) {
                        if (currentPage != 1) {
                            currentPage--;
                            startPosition -= 5;
                        } else {
                            System.out.println("Your in first page!!");
                            System.out.println("--------------------");
                        }
                    } else if (givenOption == 3) {
                        stop = true;
                    } else {
                        System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    }
                } else {
                    System.out.println("There is no records to show. Please insert the record first!");
                }
            }        
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.DISPLAY_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.DISPLAY_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to delete trainee for the given ID.
     * </p>
     *
     */
    private void deleteTraineeById() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Employee Id: ");
            String employeeId = scanner.nextLine().trim();
            if (employeeService.deleteTraineeById(employeeId)) {
                System.out.println(ConstantUtil.DELETE_SUCCESS);
            } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
            }
        } catch (EmployeeException employeeException) {
            log.error(ConstantUtil.DELETE_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.DELETE_ERROR);
        }
    }

    /**
     * <p>
     * This method is used to get the numeric value form the console.
     * </p>
     *
     * @return givenNumeber as int
     */
    private int getNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int givenNumber = 0;
        do {
            String number = scanner.nextLine().trim();
            if (CommonUtil.isNumeric(number)) {
                givenNumber = Integer.parseInt(number);
                validInput = true;
            } else {
                System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                validInput = false;
            }
        } while (!validInput);
        return givenNumber;
    }

    /**
     * <p>
     * This method is used read the name from user.
     * </p>
     *
     * @return String
     *     return the name as string.
     */
    private String getNameMandatory() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String name;
        do {
            System.out.print("Name*(Minimum 3 character): ");
            name = scanner.nextLine();
            if (StringUtil.isValidName(name)) {
                isValidInput = true;
            } else {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return name;
    }

    /**
     * <p>
     * This method is used read the gender from user.
     * </p>
     *
     * @return Gender
     *     return the enumGender as Gender.
     */
    private Gender getGenderMandatory() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        Gender enumGender = null;
        do {
            System.out.print("Gender*(Male, Female, Others): ");
            String gender = scanner.nextLine().toUpperCase();
            if (StringUtil.isValidGender(gender)) {
                enumGender = Gender.valueOf(gender);
                isValidInput = true;
            } else {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return enumGender;
    }

    /**
     * <p>
     * This method is used read the email-id from user.
     * </p>
     *
     * @return String
     *     return the emailId as string.
     */
    private String getEmailIdMandatory() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String emailId;
        do {
            System.out.print("E-Mail Id*            : ");
            emailId = scanner.nextLine();
            if (StringUtil.isValidEmailId(emailId)) {
                isValidInput = true;
            } else {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return emailId;
    }

    /**
     * <p>
     * This method is used read the mobile no from user.
     * </p>
     *
     * @return String
     *     return the mobileNo as string.
     */
    private String getMobileNoMandatory() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String mobileNo;
        do {
            System.out.print("Mobile No*            : ");
            mobileNo = scanner.nextLine();
            if (CommonUtil.isValidMobileNumber(mobileNo) && !mobileNo.isEmpty()) {
                isValidInput = true;
            } else {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
            }
        } while (!isValidInput);
        return mobileNo;
    }

    /**
     * <p>
     * This method is used read the date of birth from user.
     * </p>
     *
     * @return Date
     *     return the givenDateOfBirth as Date.
     */
    private Date getDateOfBirthMandatory() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        Date givenDateOfBirth = null;
        do {
            System.out.print("Date of Birth*(DD/MM/YYYY): ");
            String dateOfBirth = scanner.nextLine();
            try {
                givenDateOfBirth = DateUtil.checkValidDate(dateOfBirth);
                int age = DateUtil.calculateYears(givenDateOfBirth);
                if (age >= ConstantUtil.MIN_AGE && age <= ConstantUtil.MAX_AGE) {
                isValidInput = true;
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } catch (EmployeeException employeeException) {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return givenDateOfBirth;
    }

    /**
     * <p>
     * This method is used read the date of join from user.
     * </p>
     *
     * @return Date
     *     return the givenDataOfJoin as Date.
     */
    private Date getDateOfJoinMandatory(Date dateOfBirth) {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        Date validDateOfJoin = null;
        do {
            System.out.print("Date of Join*(DD/MM/YYYY): ");
            String dateOfJoin = scanner.nextLine();
            try {
                Date givenDateOfJoin = DateUtil.checkValidDate(dateOfJoin);
                validDateOfJoin = DateUtil.checkValidDateOfJoin(givenDateOfJoin, dateOfBirth);
                isValidInput = true;
            } catch (EmployeeException employeeException) {
                log.warn(employeeException);
                System.out.print(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return validDateOfJoin;
    }

    /**
     * <p>
     * This method is used read the name from user.
     * </p>
     *
     * @return String
     *     return the name as string.
     */
    private String getName() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String name;
        do {
            System.out.print("Name                  : ");
            name = scanner.nextLine();
            if (!name.isEmpty()) {
                if (StringUtil.isValidName(name)) {
                    isValidInput = true;
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return name;
    }

    /**
     * <p>
     * This method is used read the gender from user.
     * </p>
     *
     * @return Gender
     *     return the enumGender as Gender.
     */
    private Gender getGender() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        Gender enumGender = null;
        do {
            System.out.print("Gender (Male, Female, Others): ");
            String gender = scanner.nextLine().toUpperCase();
            if (!gender.isEmpty()) {
                if (StringUtil.isValidGender(gender)) {
                    enumGender = Gender.valueOf(gender);
                    isValidInput = true;
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return enumGender;
    }

    /**
     * <p>
     * This method is used read the address from user.
     * </p>
     *
     * @return String
     *     return the address as string.
     */
    private String getAddress() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String address;
        do {
            System.out.print("Address               : ");
            address = scanner.nextLine();
            isValidInput = (address.length()> ConstantUtil.MIN_ADDRESS_LENGTH 
                        && address.length() < ConstantUtil.MAX_ADDRESS_LENGTH);
            if (!address.isEmpty()) {
                if (!isValidInput) {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                address = null;
                isValidInput = true;
            }
        } while (!isValidInput);
        return address;
    }

    /**
     * <p>
     * This method is used read the designation from user.
     * </p>
     *
     * @return String
     *     return the designation as string.
     */
    private String getDesignation() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String designation;
        do {
            System.out.print("Designation           : ");
            designation = scanner.nextLine();
            isValidInput = StringUtil.isValidWord(designation);
            if (!designation.isEmpty()) {
                if (!isValidInput) {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                designation = null;
                isValidInput = true;
            }
        } while (!isValidInput);
        return designation;
    }

    /**
     * <p>
     * This method is used read the email-id from user.
     * </p>
     *
     * @return String
     *     return the emailId as string.
     */
    private String getEmailId() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String emailId;
        do {
            System.out.print("E-Mail Id             : ");
            emailId = scanner.nextLine();
            if (!emailId.isEmpty()) {
                if (StringUtil.isValidEmailId(emailId)) {
                    isValidInput = true;
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return emailId;
    }

    /**
     * <p>
     * This method is used read the mobile no from user.
     * </p>
     *
     * @return String
     *     return the mobileNo as string.
     */
    private String getMobileNo() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String mobileNo;
        do {
            System.out.print("Mobile No             : ");
            mobileNo = scanner.nextLine();
            if (!mobileNo.isEmpty()) {
                if (CommonUtil.isValidMobileNumber(mobileNo)) {
                    isValidInput = true;
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return mobileNo;
    }

    /**
     * <p>
     * This method is used read the date of birth from user.
     * </p>
     *
     * @return Date
     *     return the givenDateOfBirth as Date.
     */
    private Date getDateOfBirth() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        Date givenDateOfBirth = null;
        do {
            System.out.print("Date of Birth(DD/MM/YYYY): ");
            String dateOfBirth = scanner.nextLine();
            if (!dateOfBirth.isEmpty()) {
                try {
                    givenDateOfBirth = DateUtil.checkValidDate(dateOfBirth);
                    int age = DateUtil.calculateYears(givenDateOfBirth);
                    if (age >= ConstantUtil.MIN_AGE && age <= ConstantUtil.MAX_AGE) {
                        isValidInput = true;
                    } else {
                        System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                        isValidInput = false;
                    }
                } catch (EmployeeException employeeException) {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return givenDateOfBirth;
    }

    /**
     * <p>
     * This method is used read the date of join from user.
     * </p>
     *
     * @return Date
     *     return the givenDataOfJoin as String.
     */
    private Date getDateOfJoin(Date dateOfBirth) {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        Date validDateOfJoin = null;
        do {
            System.out.print("Date of Join (DD/MM/YYYY): ");
            String dateOfJoin = scanner.nextLine();
            if (!dateOfJoin.isEmpty()) {
                try {
                    Date givenDateOfJoin = DateUtil.checkValidDate(dateOfJoin);
                    validDateOfJoin = DateUtil.checkValidDateOfJoin(givenDateOfJoin, dateOfBirth);
                    isValidInput = true;
                } catch (EmployeeException employeeException) {
                    log.warn(employeeException);
                    System.out.print(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return validDateOfJoin;
    }


    /**
     * <p>
     * This method is used read the previous experience from user.
     * </p>
     *
     * @return float
     *     return the previousExperience as float.
     */
    private float getPreviousExperience(Date dateOfJoin, Date dateOfBirth) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        float givenPreviousExperience = 0;
        do {
            System.out.print("Previous Experience   : ");
            String previousExperience = scanner.nextLine();
            if (!previousExperience.isEmpty()) {
                try {
                    givenPreviousExperience = Float.parseFloat(previousExperience);
                    float age = DateUtil.calculateYearsMonths(dateOfBirth);
                    float experience = DateUtil.calculateYearsMonths(dateOfJoin);
                    isValidInput = givenPreviousExperience >= 0.0 && givenPreviousExperience 
                                    <= (age - experience) 
                                    - ((float)ConstantUtil.MIN_AGE);
                    if (!isValidInput) {
                        System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    }
                } catch (NumberFormatException | EmployeeException exception) {
                    log.warn(exception.toString());
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return givenPreviousExperience;
    }

    /**
     * <p>
     * This method is used read the specialization from user.
     * </p>
     *
     * @return String
     *     return the specialization as string.
     */
    private String getSpecialization() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        String specialization;
        do {
            System.out.print("Specialization        : ");
            specialization = scanner.nextLine();
            if (!specialization.isEmpty()) {
                if (specialization.length()> ConstantUtil.MIN_WORDS_LENGTH 
                        && specialization.length() < ConstantUtil.MAX_WORDS_LENGTH) {
                    isValidInput = true;
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                specialization = null;
                isValidInput = true;
            }
        } while (!isValidInput);
        return specialization;
    }

    /**
     * <p>
     * This method is used read the training experience from user.
     * </p>
     *
     * @return float
     *     return the givenTrainingExperience as float.
     */
    private float getTrainingExperience(Date dateOfBirth) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        float givenTrainingExperience = 0;
        do {
            System.out.print("Experience in Training(Years): ");
            String trainingExperience = scanner.nextLine();
            if (!trainingExperience.isEmpty()) {
                try {
                    givenTrainingExperience = Float.parseFloat(trainingExperience);
                    int age = DateUtil.calculateYears(dateOfBirth);
                    isValidInput = (givenTrainingExperience >= 0 && givenTrainingExperience 
                            <= ((float) age - ConstantUtil.MIN_AGE));
                    if (!isValidInput) {
                        givenTrainingExperience = 0;
                        System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    }
                } catch (NumberFormatException | EmployeeException exception) {
                    log.warn(exception.toString());
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true; 
            }
        } while (!isValidInput);
        return givenTrainingExperience;
    }

    /**
     * <p>
     * This method is used read the number of trainee from user.
     * </p>
     *
     * @return int
     *     return the givenNoOfTrainee as int.
     */
    private int getNoOfTrainee() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        int givenNoOfTrainee = 0;
        do {
            System.out.print("Number of Trainees    : ");
            String noOfTrainee = scanner.nextLine();
            if (!noOfTrainee.isEmpty()) {
                try {
                    givenNoOfTrainee = Integer.parseInt(noOfTrainee);
                    if (givenNoOfTrainee >= 0 && givenNoOfTrainee <= 100) {
                        isValidInput = true;
                    } else {
                        isValidInput = false;
                        givenNoOfTrainee = 0;
                        System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    }
                } catch (NumberFormatException numberFormatException) {
                    log.warn(numberFormatException.toString());
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return givenNoOfTrainee;
    }

    /**
     * <p>
     * This method is used read the trainer name from user.
     * </p>
     *
     * @return List<String>
     *     return the givenTrainerName as List<String>.
     */
    private List<String> getTrainersName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        List<String> givenTrainersName = new ArrayList<String>();
        do {
            System.out.print("Trainer name          : ");
            String trainersName = scanner.nextLine();
            if (!trainersName.isEmpty()) {
                if (StringUtil.isValidWord(trainersName)) {
                    isValidInput = true;
                    givenTrainersName = new ArrayList<String>(Arrays.asList(trainersName.split(", ")));
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                } 
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return givenTrainersName;
    }

    /**
     * <p>
     * This method is used read the learned skills from user.
     * </p>
     *
     * @return Set<String>
     *     return the givenLearnedSkills as Set<String>.
     */
    private Set<String> getLearnedSkills() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        Set<String> learnedSkills = new LinkedHashSet<String>();
        System.out.print("Learned Skills        : ");
        String skill = scanner.nextLine();
        if (!skill.isEmpty()) {
            learnedSkills = new LinkedHashSet<String>(Arrays.asList(skill.split(", ")));
        }
        return learnedSkills;
    }

    /**
     * <p>
     * This method is used read the training period from user.
     * </p>
     *
     * @return int
     *     return the givenTrainingPeriod as int.
     */
    private int getTrainingPeriod() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        int givenTrainingPeriod = 0;
        do {
            System.out.print("Training period(Months): ");
            String trainingPeriod = scanner.nextLine();
            if (!trainingPeriod.isEmpty()) {
                try {
                    givenTrainingPeriod = Integer.parseInt(trainingPeriod);
                    if ((givenTrainingPeriod >=1 && givenTrainingPeriod <=6)) {
                        isValidInput = true;
                    } else {
                        isValidInput = false;
                        givenTrainingPeriod = 0;
                        System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    }
                } catch (NumberFormatException numberFormatException) {
                    log.warn(numberFormatException.toString());
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                }
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return givenTrainingPeriod;
    }
}