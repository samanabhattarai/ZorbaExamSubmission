package com.hibernateproject.Entity;

public class Teacher {

    private int teacherId;
    private String teacherName;
    private String   teacherQualification;
    private int experienceOfTeaching;
    private int studentId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherQualification() {
        return teacherQualification;
    }

    public void setTeacherQualification(String teacherQualification) {
        this.teacherQualification = teacherQualification;
    }

    public int getExperienceOfTeaching() {
        return experienceOfTeaching;
    }

    public void setExperienceOfTeaching(int experienceOfTeaching) {
        this.experienceOfTeaching = experienceOfTeaching;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherQualification='" + teacherQualification + '\'' +
                ", experienceOfTeaching=" + experienceOfTeaching +
                ", studentId=" + studentId +
                '}';
    }
}
