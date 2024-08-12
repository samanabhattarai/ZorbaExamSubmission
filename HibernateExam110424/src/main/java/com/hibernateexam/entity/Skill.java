package com.hibernateexam.entity;

import java.util.HashSet;
import java.util.Set;

public class Skill {

    private int skillId;
    private String skillName;
    private Set<Employee> employees;

    public Skill(){}

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Set<Employee> getEmployees() {
        if(employees == null){
            employees = new HashSet<>();
        }
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                ", employees=" + employees +
                '}';
    }

}
