package com.i2i.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

import com.i2i.enumerator.Gender;
import com.i2i.model.Role;

/**
 * The Employee class contains the attributes of both Trainer and Trainee
 * and used to store & reterive the values by using Setter and Getter.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "designation")
    private String designation;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_join")
    private Date dateOfJoin;

    @Column(name = "previous_experience")
    private float previousExperience;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "training_experience")
    private float trainingExperience;

    @Column(name = "no_of_trainee")
    private int noOfTrainee;

    @ElementCollection
    @CollectionTable(name = "trainers_name", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "name")
    private List<String> trainersName;

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "skill")
    private Set<String> learnedSkills;

    @Column(name = "training_period")
    private int trainingPeriod;

    @ManyToMany
    @JoinTable(
        name = "employee_role", 
        joinColumns = @JoinColumn(name = "employee_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Employee() { }
    
    public Employee(String employeeId, String name, Gender gender, 
                    String address, String designation,
                    String emailId, String mobileNo, Date dateOfBirth,
                    Date dateOfJoin, float previousExperience, 
                    String specialization, float trainingExperience,
                    int noOfTrainee, List<String> trainersName,  
                    Set<String> learnedSkills, int trainingPeriod) {

        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.designation = designation;
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

    public List<String> getTrainersName() {
        if(trainersName == null) {
            trainersName = new ArrayList<String>();
        }
        return trainersName;
    }

    public void setTrainersName(List<String> trainersName) {
        this.trainersName = trainersName;
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

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<Role>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}