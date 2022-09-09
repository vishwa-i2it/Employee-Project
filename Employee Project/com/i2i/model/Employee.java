package com.i2i.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import com.i2i.enumerator.Gender;

/**
 * The Employee class contains the attributes of both Trainer and Trainee
 * and used to store & reterive the values by using Setter and Getter.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class Employee {
    private String id;
    private String name;
    private Gender gender;
    private String address;
    private String designation;
    private String role;
    private String emailId;
    private String mobileNo;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private float previousExperience;
    private String specialization;
    private float trainingExperience;
    private int noOfTrainee;
    private List<String> trainerName;
    private Set<String> learnedSkills;
    private int trainingPeriod;

    public Employee() { }
    
    public Employee(String id, String name, Gender gender, 
                    String address, String designation, String role,
                    String emailId, String mobileNo, Date dateOfBirth,
                    Date dateOfJoin, float previousExperience, 
                    String specialization, float trainingExperience,
                    int noOfTrainee, List<String> trainerName,  
                    Set<String> learnedSkills, int trainingPeriod) {

        this.id = id;
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
        this.trainerName = trainerName;
        this.learnedSkills = learnedSkills;
        this.trainingPeriod = trainingPeriod;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public float getPreviousExperience() {
        return previousExperience;
    }

    public void setPreviousExperience(float previousExperience) {
        this.previousExperience = previousExperience;
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

    public List<String> getTrainerName() {
        if(trainerName == null) {
        trainerName = new ArrayList<String>();
        }
        return trainerName;
    }

    public void setTrainerName(List<String> trainerName) {
        this.trainerName = trainerName;
    }

    public Set<String> getLearnedSkills() {
        if(learnedSkills == null) {
        learnedSkills = new HashSet<String>();
        }
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
}