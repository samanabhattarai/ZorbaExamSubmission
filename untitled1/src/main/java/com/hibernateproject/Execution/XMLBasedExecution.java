package com.hibernateproject.Execution;

import com.hibernateproject.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class XMLBasedExecution {
    public static void main(String[] args) {
        //Read the Configuration file
        Configuration configuration = new Configuration();
        configuration.configure("hibernate-mapping/hibernate.cfg.xml");

        //Create SessionFactory Object from Configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Create new Session
        Session session = sessionFactory.openSession();

        //Declare transaction Object
        Transaction tx = null;

        // insert subject data to table
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of subjects:");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numSubjects; i++) {
            System.out.println("Enter subject name:");
            String subName = scanner.nextLine();

            Subject subject = new Subject();
            subject.setSubjectName(subName);

            try {
                tx = session.beginTransaction();
                //insert the data to employee table
                session.persist(subject);
                tx.commit();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                tx.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }

        }

        //insert student data to table
         scanner = new Scanner(System.in);
        Session session1 = sessionFactory.openSession();

        System.out.println("Enter the number of students:");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

       // List<Subject> subjects = session.createQuery("FROM Subject", Subject.class).list();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student name:");
            String studName = scanner.nextLine();

            System.out.println("Enter student roll number:");
            int studRollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

//            System.out.println("Available subjects:");
//            for (int j = 0; j < subjects.size(); j++) {
//                System.out.println((j + 1) + ". " + subjects.get(j).getSubjectName());
//            }

            System.out.println("Choose a subject (enter the number):");
            int subjectChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Student student = new Student();
            student.setStudentName(studName);
            student.setStudentRollNumber(studRollNumber);
        /*    student.setSubjectId(subjectChoice);*/

            try {
                tx = session.beginTransaction();
                session1.persist(student);
                tx.commit();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                tx.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }

        }

            //insert into teacher table
             scanner = new Scanner(System.in);

        Session session2 = sessionFactory.openSession();

            System.out.println("Enter the number of teachers:");
            int numTeachers = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            List<Subject> subject = session.createQuery("FROM Subject", Subject.class).list();

            for (int k = 0; k < numTeachers; k++) {
                System.out.println("Enter teacher name:");
                String teacherName = scanner.nextLine();

                System.out.println("Enter teacher qualification:");
                String teacherQualification = scanner.nextLine();

                System.out.println("Enter teacher experience of teaching:");
                int experienceOfTeaching = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

//                System.out.println("Available subjects:");
//                for (int j = 0; j < subjects.size(); j++) {
//                    System.out.println((j + 1) + ". " + subjects.get(j).getSubjectName());
//                }

                System.out.println("Choose a subject (enter the number):");
                int subjectChoice1 = scanner.nextInt();
                scanner.nextLine();

                Teacher teacher = new Teacher();
                teacher.setTeacherName(teacherName);
                teacher.setTeacherQualification(teacherQualification);
                teacher.setExperienceOfTeaching(experienceOfTeaching);
                //teacher.setStudentId(subjectChoice1);

                try {
                    tx = session.beginTransaction();
                    //insert the data to employee table
                    session2.persist(teacher);
                    tx.commit();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    tx.rollback();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }

            }
            }
        }

