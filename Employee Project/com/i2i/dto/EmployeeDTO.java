package com.i2i.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import com.i2i.enumerator.Gender;

/**
 * The EmployeeDTO class contains the attributes of both Trainer and Trainee
 * and used to store & reterive the values by using Setter and Getter.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class EmployeeDTO {
    private String employeeId;
    private String name;
    private Gender gender;
    private String address;
    private String designation;
    private String role;
    private String emailId;
    private String mobileNo;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private int age;
    private float previousExperience;
    private float experience;
    private String specialization;
    private float trainingExperience;
    private int noOfTrainee;
    private List<String> trainersName;
    private Set<String> learnedSkills;
    private int trainingPeriod;
    
    public EmployeeDTO() { }
    
    public EmployeeDTO(String employeeId, String name, Gender gender,
                       String address, String designation, 
                       String role, String emailId, 
                       String mobileNo, Date dateOfBirth, 
                       Date dateOfJoin, float previousExperience,
                       String specialization, float trainingExperience,
                       int noOfTrainee, List<String> trainersName, 
                       Set<String> learnedSkills, 
                       int trainingPeriod) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.designation = designation;
        this.role = role;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        this.previousExperience = previousExperience;
        this.specialization = specialization;
        this.trainingExperience = trainingExperience;
        this.noOfTrainee = noOfTrainee;
        this.trainersName = trainersName;
        this.learnedSkills = learnedSkills;
        this.trainingPeriod = trainingPeriod;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getPreviousExperience() {
        return previousExperience;
    }

    public void setPreviousExperience(float previousExperience) {
        this.previousExperience = previousExperience;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public float getTrainingExperience() {
        return trainingExperience;
    }

    public void setTrainingExperience(float trainingExperience) {
        this.trainingExperience = trainingExperience;
    }

    public int getNoOfTrainee() {
        return noOfTrainee;
    }

    public void setNoOfTrainee(int noOfTrainee) {
        this.noOfTrainee = noOfTrainee;
    }

    public List<String> getTrainersName() {
        return trainersName;
    }

    public void setTrainersName(List<String> trainersName) {
        this.trainersName = trainersName;
    }

    public Set<String> getLearnedSkills() {
        return learnedSkills;
    }

    public void setLearnedSkills(Set<String> learnedSkills) {
        this.learnedSkills = learnedSkills;
    }

    public int getTrainingPeriod() {
        return trainingPeriod;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public String toString() {
        SimpleDateFormat simpleDateFormater = new SimpleDateFormat("dd/MM/yyyy");
        String returnString = "";

        if ("TRAINER".equals(this.role)) {
            returnString = ("\nID                    : " + this.employeeId 
                    + "\nName                  : " + this.name
                    + "\nGender                : " + this.gender 
                    + "\nAddress               : " + Optional.ofNullable(this.address).orElse("")
                    + "\nAge                   : " + this.age 
                    + "\nTotal Experience      : " + this.experience + " Years"
                    + "\nDesignation           : " + Optional.ofNullable(this.designation).orElse("") 
                    + "\nDate of Join          : " + simpleDateFormater.format(this.dateOfJoin)
                    + "\nSpecialization        : " + Optional.ofNullable(this.specialization).orElse("")
                    + "\nExperience in Training: " + trainingExperience 
                    + "\nNumber of Trainee     : " + noOfTrainee);
        } else if ("TRAINEE".equals(this.role)) {
            returnString = ("\nID                    : " + this.employeeId 
                    + "\nName                  : " + this.name
                    + "\nGender                : " + this.gender 
                    + "\nAddress               : " + Optional.ofNullable(this.address).orElse("")
                    + "\nAge                   : " + this.age 
                    + "\nTotal Experience      : " + this.experience + " Years"
                    + "\nDesignation           : " + Optional.ofNullable(this.designation).orElse("") 
                    + "\nDate of Join          : " + simpleDateFormater.format(this.dateOfJoin)
                    + "\nTrainers Name         : " + String.join(", ", this.trainersName) 
                    + "\nLearned Skills        : " + String.join(", ", this.learnedSkills) 
                    + "\nTraining period       : " + trainingPeriod); 
        }
        return returnString;
    }
}