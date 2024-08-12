package com.hibernateexam.entity;

import java.util.HashSet;
import java.util.Set;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeAddress;

    private String employeeEmail;
    private int employeeExperience;

    private Set<Skill> skills;

    public Employee(){}

    public Employee(String employeeName, String employeeAddress, String employeeEmail, int employeeExperience) {
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeEmail = employeeEmail;
        this.employeeExperience = employeeExperience;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public int getEmployeeExperience() {
        return employeeExperience;
    }

    public void setEmployeeExperience(int employeeExperience) {
        this.employeeExperience = employeeExperience;
    }

    public Set<Skill> getSkills() {
        if(skills == null){
            skills = new HashSet<>();
        }
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeExperience=" + employeeExperience +
                '}';
    }
}

