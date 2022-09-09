package com.i2i.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.i2i.dto.EmployeeDTO;
import com.i2i.enumerator.Gender;
import com.i2i.exception.EmployeeException;
import com.i2i.exception.ExperienceException;
import com.i2i.mapper.EmployeeMapper;
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
    EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
    static Logger log = Logger.getLogger(EmployeeController.class.getName());

    public static void main (String[] args) {
        EmployeeController employeeController = new EmployeeController();
        Scanner scanner = new Scanner(System.in);
        int givenProfileAccess = 0;
        boolean validInput = false;

        try {
            Handler fileHandler = new FileHandler("com/i2i/ExceptionLog.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            log.setUseParentHandlers(false);
            log.addHandler(fileHandler);
        } catch (IOException ioException) {
            log.warning("Internal Error\n" + ioException.toString());
        } catch (SecurityException securityException) {
            log.warning("Internal Error\n" + securityException.toString());
        }

        do {
            System.out.println("\nWhich profile you going to access?\n" 
                           + "1. Trainer \n2. Trainee \n3. Exit");
            do {
                String profileAccess = scanner.nextLine().trim();
                if (CommonUtil.isNumeric(profileAccess)) {
                    givenProfileAccess = Integer.parseInt(profileAccess);
                    validInput = true;
                } else {
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    validInput = false;
                }
            } while (!validInput);

            switch (givenProfileAccess) {
                case 1:
                    int givenTrainerOperation = 0;

                    do {
                        System.out.print("\nWhich Operation Going to do?" 
                                           + "\n1. Add Details\n2. Update Details\n3. Search\n4. View All\n5. Delete\n6. Go Back\n---> ");
                        do {
                            String trainerOperation = scanner.nextLine().trim();
                            if (CommonUtil.isNumeric(trainerOperation)) {
                                givenTrainerOperation = Integer.parseInt(trainerOperation);
                                validInput = true;
                            } else {
                                System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                validInput = false;
                            }
                        } while (!validInput);

                        switch (givenTrainerOperation) {
                            case 1:
                                System.out.println("");
                                employeeController.readTrainer();
                                break;

                            case 2:
                                employeeController.updateTrainerById();
                                break;

                            case 3:
                                employeeController.searchTrainerById();
                                break;

                            case 4:
                                employeeController.showTrainers();
                                break;

                            case 5:
                                employeeController.deleteTrainerById();
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                break;
                        }
                    } while (givenTrainerOperation !=6 );
                    break;

                case 2:
                    int givenTraineeOperation = 0;

                    do {
                        System.out.print("\nWhich Operation Going to do?" 
                                           + "\n1. Add Details\n2. Update Details\n3. Search\n4. View All\n5. Delete\n6. Go Back\n---> ");
                        do {
                            String traineeOperation = scanner.nextLine().trim();
                            if (CommonUtil.isNumeric(traineeOperation)) {
                                givenTraineeOperation = Integer.parseInt(traineeOperation);
                                validInput = true;
                            } else {
                                System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                validInput = false;
                            }
                        } while (!validInput);

                        switch (givenTraineeOperation) {
                            case 1:
                                System.out.println("");
                                employeeController.readTrainee();
                                break;

                            case 2:
                                employeeController.updateTraineeById();
                                break;

                            case 3:
                                employeeController.searchTraineeById();
                                break;

                            case 4:
                                employeeController.showTrainees();
                                break;

                            case 5:
                                employeeController.deleteTraineeById();
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                break;
                        }
                    } while (givenTraineeOperation !=6 );
                    break;

                case 3:
                    do {
                        System.out.print("Are you want to exit (y/n) ");
                        String confirmation = scanner.nextLine().trim().toLowerCase();
                        if ("y".equals(confirmation)) {
                            System.out.println(ConstantUtil.THANK_YOU_MESSAGE);
                            validInput = true;
                        } else if ("n".equals(confirmation)) {
                            givenProfileAccess = 0;
                            validInput = true;
                        } else {
                            System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                            validInput = false;
                        }
                    } while (!validInput);
                    break;
                default:
                    System.out.println(ConstantUtil.INPUT_RANGE);
                    break;
            } 
        } while(givenProfileAccess != 3);
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
            String id = employeeServiceImpl.generateEmployeeId();
            System.out.println("ID                    : " + id);
            employeeDTO.setId(id);
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.INSERT_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.INSERT_ERROR);
        }

        employeeDTO.setName(getNameMandatory());
        employeeDTO.setGender(getGenderMandatory());
        employeeDTO.setAddress(getAddress());
        employeeDTO.setDesignation(getDesignation());
        employeeDTO.setRole("Trainer");
        employeeDTO.setEmailId(getEmailIdMandatory());
        employeeDTO.setMobileNo(getMobileNoMandatory());
        String dateOfBirth = getDateOfBirthMandatory();
        try {
            employeeDTO.setDateOfBirth(new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfBirth));
        } catch (ParseException parseException) {
            log.warning(parseException.toString());
        }

        String dateOfJoin = getDateOfJoinMandatory(dateOfBirth);
        try {
            employeeDTO.setDateOfJoin(new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfJoin));
        } catch (ParseException parseException) {
            log.warning(parseException.toString());
        }
        employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
        employeeDTO.setSpecialization(getSpecialization());
        employeeDTO.setTrainingExperience(getTrainingExperience(dateOfBirth));
        employeeDTO.setNoOfTrainee(getNoOfTrainee());

        try {
            if (employeeServiceImpl.addTrainer(EmployeeMapper.dtoToEntity(employeeDTO))) {
                System.out.println(ConstantUtil.INSERT_SUCCESS);
            } else {
                System.out.println(ConstantUtil.INSERT_FAILED);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.INSERT_ERROR + employeeException.toString());
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
            if (!employeeServiceImpl.isTrainersEmpty()) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                String dateOfBirth;
                String dateOfJoin;
                boolean isValidInput = true;

                System.out.print("Enter the Employee Id: ");
                String employeeId = scanner.nextLine().trim();
                if (employeeServiceImpl.isTrainerExist(employeeId)) {
                    System.out.println("Please enter the value for corresponding fields!"
                            + "\nIf you want to skip any field just hit the Enter button");
                    do {
                        System.out.print("Name                  : ");
                        String name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            if (StringUtil.isValidName(name)) {
                                employeeDTO.setName(name);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    do {
                        System.out.print("Gender (Male, Female, Transgender): ");
                        String gender = scanner.nextLine().toUpperCase();
                        if (!gender.isEmpty()) {
                            if (StringUtil.isValidGender(gender)) {
                                Gender enumGender = Gender.valueOf(gender);
                                employeeDTO.setGender(enumGender);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    employeeDTO.setAddress(getAddress());
                    employeeDTO.setDesignation(getDesignation());

                    do {
                        System.out.print("E-Mail Id             : ");
                        String emailId = scanner.nextLine();
                        if (!emailId.isEmpty()) {
                            if (StringUtil.isValidEmailId(emailId)) {
                                employeeDTO.setEmailId(emailId);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    do {
                        System.out.print("Mobile No             : ");
                        String mobileNo = scanner.nextLine();
                        if (!mobileNo.isEmpty()) {
                            if (CommonUtil.isValidMobileNumber(mobileNo)) {
                                employeeDTO.setMobileNo(mobileNo);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    do {
                        System.out.print("Date of Birth(DD/MM/YYYY): ");
                        dateOfBirth = scanner.nextLine();
                        if (!dateOfBirth.isEmpty()) {
                            if (DateUtil.isValidDate(dateOfBirth) 
                                  && DateUtil.calculateYears(dateOfBirth) >= ConstantUtil.MIN_AGE
                                  && DateUtil.calculateYears(dateOfBirth) <= ConstantUtil.MAX_AGE) {
                                try {
                                    Date givenDateOfBirth = new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfBirth);
                                    employeeDTO.setDateOfBirth(givenDateOfBirth);
                                    isValidInput = true;
                                } catch (ParseException parseException) {
                                    log.warning(parseException.toString());
                                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                    isValidInput = false;
                                }
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    if (dateOfBirth.isEmpty()) {
                        dateOfBirth = new SimpleDateFormat(ConstantUtil.DATE_FORMAT)
                                .format(EmployeeMapper.entityToDto(employeeServiceImpl
                                .searchTrainerById(employeeId).get(0)).getDateOfBirth());
                    }

                    do {
                        System.out.print("Date of Join (DD/MM/YYYY): ");
                        dateOfJoin = scanner.nextLine();
                        if (!dateOfJoin.isEmpty()) {
                            if (DateUtil.isValidDateOfJoin(dateOfJoin, dateOfBirth)) {
                                try {
                                    Date givenDateOfJoin = new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfJoin);
                                    employeeDTO.setDateOfJoin(givenDateOfJoin);
                                    isValidInput = true;
                                } catch (ParseException parseException) {
                                    log.warning(parseException.toString());
                                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                    isValidInput = false;
                                }
                            } else {
                                System.out.print(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    if (dateOfJoin.isEmpty()) {
                        dateOfJoin = new SimpleDateFormat(ConstantUtil.DATE_FORMAT)
                                .format(EmployeeMapper.entityToDto(employeeServiceImpl
                                .searchTrainerById(employeeId).get(0)).getDateOfJoin());
                    }

                    employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
                    employeeDTO.setSpecialization(getSpecialization());
                    employeeDTO.setTrainingExperience(getTrainingExperience(dateOfBirth));
                    employeeDTO.setNoOfTrainee(getNoOfTrainee());

                    try {
                        if (employeeServiceImpl.updateTrainerById(employeeId, EmployeeMapper.dtoToEntity(employeeDTO))) {
                            System.out.println(ConstantUtil.CHANGES_DONE);
                        } else {
                            System.out.println(ConstantUtil.CHANGES_FAILED);
                        }
                    } catch (EmployeeException employeeException) {
                        log.severe(ConstantUtil.UPDATE_ERROR + employeeException.toString());
                        System.err.println(ConstantUtil.UPDATE_ERROR);
                    }
                } else {
                    System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORDS);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.UPDATE_ERROR + employeeException.toString());
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
            if (!employeeServiceImpl.isTrainersEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the Employee Id: ");
                String employeeId = scanner.nextLine().trim();
                if (!employeeServiceImpl.searchTrainerById(employeeId).isEmpty()) {
                    System.out.println(EmployeeMapper.entityToDto(employeeServiceImpl.searchTrainerById(employeeId).get(0)));
                } else {
                    System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORDS);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.SEARCH_ERROR + employeeException.toString());
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
        try {
            if (employeeServiceImpl.isTrainersEmpty()){
                System.out.println("There is no records to show. Please insert the record first!");
            } else {
                for (EmployeeDTO employee : EmployeeMapper.entityListToDtoList(employeeServiceImpl.getTrainers())) {
                    System.out.println(employee);
                }
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.DISPLAY_ERROR + employeeException.toString());
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
            if (!employeeServiceImpl.isTrainersEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the Employee Id: ");
                String employeeId = scanner.nextLine().trim();
                if (employeeServiceImpl.isTrainerExist(employeeId)) {
                    if (employeeServiceImpl.deleteTrainerById(employeeId)) {
                        System.out.println(ConstantUtil.DELETE_SUCCESS);
                    }
                } else {
                    System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORDS);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.DELETE_ERROR + employeeException.toString());
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
            String id = employeeServiceImpl.generateEmployeeId();
            System.out.println("ID                    : " + id);
            employeeDTO.setId(id);
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.INSERT_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.INSERT_ERROR);
        }

        employeeDTO.setName(getNameMandatory());
        employeeDTO.setGender(getGenderMandatory());
        employeeDTO.setAddress(getAddress());
        employeeDTO.setDesignation(getDesignation());
        employeeDTO.setRole("Trainee");
        employeeDTO.setEmailId(getEmailIdMandatory());
        employeeDTO.setMobileNo(getMobileNoMandatory());
        String dateOfBirth = getDateOfBirthMandatory();
        try {
            employeeDTO.setDateOfBirth(new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfBirth));
        } catch (ParseException parseException) {
            log.warning(parseException.toString());
        }

        String dateOfJoin = getDateOfJoinMandatory(dateOfBirth);
        try {
            employeeDTO.setDateOfJoin(new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfJoin));
        } catch (ParseException parseException) {
            log.warning(parseException.toString());
        }
        employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
        employeeDTO.setTrainerName(getTrainerName());
        employeeDTO.setLearnedSkills(getLearnedSkills());
        employeeDTO.setTrainingPeriod(getTrainingPeriod());

        try {
            if (employeeServiceImpl.addTrainee(EmployeeMapper.dtoToEntity(employeeDTO))) {
                System.out.println(ConstantUtil.INSERT_SUCCESS);
            } else {
                System.out.println(ConstantUtil.INSERT_FAILED);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.INSERT_ERROR + employeeException.toString());
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
            if (!employeeServiceImpl.isTraineesEmpty()) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                String dateOfBirth;
                String dateOfJoin;
                boolean isValidInput = true;

                System.out.print("Enter the Employee Id: ");
                String employeeId = scanner.nextLine().trim();

                if (employeeServiceImpl.isTraineeExist(employeeId)) {
                    System.out.println("Please enter the value for corresponding fields!"
                             + "\nIf you want to skip any field just hit the Enter button");
                    do {
                        System.out.print("Name                  : ");
                        String name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            if (StringUtil.isValidName(name)) {
                                employeeDTO.setName(name);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    do {
                        System.out.print("Gender (Male, Female, Transgender): ");
                        String gender = scanner.nextLine().toUpperCase();
                        if (!gender.isEmpty()) {
                            if (StringUtil.isValidGender(gender)) {
                                Gender enumGender = Gender.valueOf(gender);
                                employeeDTO.setGender(enumGender);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                        isValidInput = true;
                        }
                    } while (!isValidInput);

                    employeeDTO.setAddress(getAddress());
                    employeeDTO.setDesignation(getDesignation());

                    do {
                        System.out.print("E-Mail Id             : ");
                        String emailId = scanner.nextLine();
                        if (!emailId.isEmpty()) {
                            if (StringUtil.isValidEmailId(emailId)) {
                                employeeDTO.setEmailId(emailId);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    do {
                        System.out.print("Mobile No             : ");
                        String mobileNo = scanner.nextLine();
                        if (!mobileNo.isEmpty()) {
                            if (CommonUtil.isValidMobileNumber(mobileNo)) {
                                employeeDTO.setMobileNo(mobileNo);
                                isValidInput = true;
                            } else {
                                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    do {
                        System.out.print("Date of Birth(DD/MM/YYYY): ");
                        dateOfBirth = scanner.nextLine();
                        if (!dateOfBirth.isEmpty()) {
                            if (DateUtil.isValidDate(dateOfBirth) 
                                  && DateUtil.calculateYears(dateOfBirth) >= ConstantUtil.MIN_AGE
                                  && DateUtil.calculateYears(dateOfBirth) <= ConstantUtil.MAX_AGE) {
                                try {
                                    Date givenDateOfBirth = new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfBirth);
                                    employeeDTO.setDateOfBirth(givenDateOfBirth);
                                    isValidInput = true;
                                } catch (ParseException parseException) {
                                    System.err.println("Internal Error " + parseException.getMessage());
                                    isValidInput = false;
                                }
                            } else {
                            System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    if (dateOfBirth.isEmpty()) {
                            dateOfBirth = new SimpleDateFormat(ConstantUtil.DATE_FORMAT)
                                .format(EmployeeMapper.entityToDto(employeeServiceImpl
                                .searchTraineeById(employeeId).get(0)).getDateOfBirth());
                    }

                    do {
                        System.out.print("Date of Join (DD/MM/YYYY): ");
                        dateOfJoin = scanner.nextLine();
                        if (!dateOfJoin.isEmpty()) {
                            if (DateUtil.isValidDateOfJoin(dateOfJoin, dateOfBirth)) {
                                try {
                                    Date givenDateOfJoin = new SimpleDateFormat(ConstantUtil.DATE_FORMAT).parse(dateOfJoin);
                                    employeeDTO.setDateOfJoin(givenDateOfJoin);
                                    isValidInput = true;
                                } catch (ParseException parseException) {
                                    System.err.println("Internal Error " + parseException.getMessage());
                                    isValidInput = false;
                                }
                            } else {
                                System.out.print(ConstantUtil.INVALID_INPUT_MESSAGE);
                                isValidInput = false;
                            }
                        } else {
                            isValidInput = true;
                        }
                    } while (!isValidInput);

                    if (dateOfJoin.isEmpty()) {
                        dateOfJoin = new SimpleDateFormat(ConstantUtil.DATE_FORMAT)
                                .format(EmployeeMapper.entityToDto(employeeServiceImpl
                                .searchTraineeById(employeeId).get(0)).getDateOfJoin());
                    }

                    employeeDTO.setPreviousExperience(getPreviousExperience(dateOfJoin, dateOfBirth));
                    employeeDTO.setTrainerName(getTrainerName());
                    employeeDTO.setLearnedSkills(getLearnedSkills());
                    employeeDTO.setTrainingPeriod(getTrainingPeriod());

                    try {
                        if (employeeServiceImpl.updateTraineeById(employeeId, EmployeeMapper.dtoToEntity(employeeDTO))) {
                            System.out.println(ConstantUtil.CHANGES_DONE);
                        } else {
                            System.out.println(ConstantUtil.CHANGES_FAILED);
                        }
                    } catch (EmployeeException employeeException) {
                        log.severe(ConstantUtil.UPDATE_ERROR + employeeException.toString());
                        System.err.println(ConstantUtil.UPDATE_ERROR);
                    }
                } else {
                System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORDS);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.UPDATE_ERROR + employeeException.toString());
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
            if (!employeeServiceImpl.isTraineesEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the Employee Id: ");
                String employeeId = scanner.nextLine().trim();
                if (!employeeServiceImpl.searchTraineeById(employeeId).isEmpty()) {
                    System.out.println(EmployeeMapper.entityToDto(employeeServiceImpl.searchTraineeById(employeeId).get(0)));
                } else {
                    System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORDS);
            }
        } catch(EmployeeException employeeException) {
            log.severe(ConstantUtil.SEARCH_ERROR + employeeException.toString());
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
        try {
            if (employeeServiceImpl.isTraineesEmpty()){
                System.out.println("There is no records to show. Please insert the record first!");
            } else {
                for (EmployeeDTO employee : EmployeeMapper.entityListToDtoList(employeeServiceImpl.getTrainees())) {
                    System.out.println(employee);
                }
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.DISPLAY_ERROR + employeeException.toString());
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
            if (!employeeServiceImpl.isTraineesEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the Employee Id: ");
                String employeeId = scanner.nextLine().trim();
                if (employeeServiceImpl.isTraineeExist(employeeId)) {
                    if (employeeServiceImpl.deleteTraineeById(employeeId)) {
                        System.out.println(ConstantUtil.DELETE_SUCCESS);
                    }
                } else {
                    System.out.println(ConstantUtil.NO_RECORD_FOR_ID);
                }
            } else {
                System.out.println(ConstantUtil.NO_RECORDS);
            }
        } catch (EmployeeException employeeException) {
            log.severe(ConstantUtil.DELETE_ERROR + employeeException.toString());
            System.err.println(ConstantUtil.DELETE_ERROR);
        }
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
            System.out.print("Gender*(Male, Female, Transgender): ");
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
     *     return the givenDateOfBirth as string.
     */
    private String getDateOfBirthMandatory() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String dateOfBirth;
        do {
            System.out.print("Date of Birth*(DD/MM/YYYY): ");
            dateOfBirth = scanner.nextLine();
            if (DateUtil.isValidDate(dateOfBirth) 
                    && DateUtil.calculateYears(dateOfBirth) >= ConstantUtil.MIN_AGE
                    && DateUtil.calculateYears(dateOfBirth) <= ConstantUtil.MAX_AGE) {
                isValidInput = true;
            } else {
                System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return dateOfBirth;
    }

    /**
     * <p>
     * This method is used read the date of join from user.
     * </p>
     *
     * @return Date
     *     return the givenDataOfJoin as String.
     */
    private String getDateOfJoinMandatory(String dateOfBirth) {
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        String dateOfJoin;
        do {
            System.out.print("Date of Join*(DD/MM/YYYY): ");
            dateOfJoin = scanner.nextLine();
            if (DateUtil.isValidDateOfJoin(dateOfJoin, dateOfBirth)) {
                isValidInput = true;
            } else {
                System.out.print(ConstantUtil.INVALID_INPUT_MESSAGE);
                isValidInput = false;
            }
        } while (!isValidInput);
        return dateOfJoin;
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
     * This method is used read the previous experience from user.
     * </p>
     *
     * @return float
     *     return the previousExperience as float.
     */
    private float getPreviousExperience(String dateOfJoin, String dateOfBirth) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        float givenPreviousExperience = 0;
        do {
            System.out.print("Previous Experience   : ");
            String previousExperience = scanner.nextLine();
            if (!previousExperience.isEmpty()) {
                try {
                    givenPreviousExperience = Float.parseFloat(previousExperience);
                    isValidInput = (givenPreviousExperience >= 0.0 && givenPreviousExperience 
                                    <= (DateUtil.calculateYearsMonths(dateOfBirth) 
                                    - DateUtil.calculateYearsMonths(dateOfJoin)) 
                                    - ((float)ConstantUtil.MIN_AGE));
                    if (!isValidInput) {
                        throw new ExperienceException("Enter valid Experience");
                    }
                } catch (ExperienceException experienceException) {
                    log.warning(experienceException.toString());
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;  
                } catch (NumberFormatException numberFormatException) {
                    log.warning(numberFormatException.toString());
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
    private float getTrainingExperience(String dateOfBirth) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        float givenTrainingExperience = 0;
        do {
            System.out.print("Experience in Training(Years): ");
            String trainingExperience = scanner.nextLine();
            if (!trainingExperience.isEmpty()) {
                try {
                    givenTrainingExperience = Float.parseFloat(trainingExperience);
                    isValidInput = (givenTrainingExperience >= 0 && givenTrainingExperience 
                            <= ((float) DateUtil.calculateYears(dateOfBirth) - ConstantUtil.MIN_AGE));
                    if (!isValidInput) {
                        givenTrainingExperience = 0;
                        throw new ExperienceException("Enter Valid Experience");
                    }
                } catch (ExperienceException experienceException) {
                    log.warning(experienceException.toString());
                    System.err.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;  
                } catch (NumberFormatException numberFormatException) {
                    log.warning(numberFormatException.toString());
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
                    log.warning(numberFormatException.toString());
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
    private List<String> getTrainerName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean isValidInput = true;
        List<String> givenTrainerName = new ArrayList<String>();
        do {
            System.out.print("Trainer name          : ");
            String trainerName = scanner.nextLine();
            if (!trainerName.isEmpty()) {
                if (StringUtil.isValidWord(trainerName)) {
                    isValidInput = true;
                    givenTrainerName = new ArrayList<String>(Arrays.asList(trainerName.split(", ")));
                } else {
                    System.out.println(ConstantUtil.INVALID_INPUT_MESSAGE);
                    isValidInput = false;
                } 
            } else {
                isValidInput = true;
            }
        } while (!isValidInput);
        return givenTrainerName;
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
                    log.warning(numberFormatException.toString());
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