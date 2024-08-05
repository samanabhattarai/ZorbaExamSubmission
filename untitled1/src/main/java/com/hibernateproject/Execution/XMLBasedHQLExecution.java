package com.hibernateproject.Execution;

import com.hibernateproject.Entity.Employee;
import com.hibernateproject.Entity.Student;
import com.hibernateproject.Entity.Subject;
import com.hibernateproject.Entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class XMLBasedHQLExecution {
    public static void main(String[] args) {
        //Reads the Configuration file from hibernate mapping xml file
        Configuration configuration = new Configuration();
        configuration.configure("hibernate-mapping/hibernate.cfg.xml");

        //Create SessionFactory Object from Configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Creates Session object
        Session session = sessionFactory.openSession();
        String selectQuery = "from Student";
        try {
            Query query = session.createQuery(selectQuery);
            List<Student> studentList = query.list();
            System.out.println(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        Session session1 = sessionFactory.openSession();
        String selectQuery1 = "from Teacher";
        try {
            Query query1= session.createQuery(selectQuery);
            List<Teacher> teacherList = query1.list();
            System.out.println(teacherList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        Session session2 = sessionFactory.openSession();
        String selectQuery2 = "from Subject";
        try {
            Query query1= session.createQuery(selectQuery);
            List<Subject> teacherList = query1.list();
            System.out.println(teacherList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }
}
