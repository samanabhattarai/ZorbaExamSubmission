package com.hibernateproject.Execution;

import com.hibernateproject.Entity.Department;
import com.hibernateproject.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

        //inserting employee data to table
        Employee employee = new Employee();
        employee.setEmpName("Jack");
        employee.setEmpSalary(11605.3d);

        Employee employee1 = new Employee();
        employee1.setEmpName("Don");
        employee1.setEmpSalary(9747.3d);

        try {
            tx = session.beginTransaction();
            //insert the data to employee table
            session.persist(employee);
            session.persist(employee1);
            tx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        //Create new Session
        Session session4 = sessionFactory.openSession();

        //Declare transaction Object
        Transaction tx2 = null;
        //insert department data
        Department department = new Department();
        department.setDeptName("Science");

        Department dept1 = new Department();
        dept1.setDeptName("Arts");

        try {
            tx2 = session4.beginTransaction();
            //insert the data to department table
            session4.persist(department);
            session4.persist(dept1);
            tx2.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            tx2.rollback();
        } finally {
            if (session4 != null) {
                session4.close();
            }
        }

        Employee receivedEmpObj = new Employee();
        //fetch data from database
        Session session1 = sessionFactory.openSession();
        try {
            receivedEmpObj = session1.get(Employee.class, 4); // select * from employee where emp_id = 1;
            System.out.println(receivedEmpObj.getEmpName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session1 != null) {
                session1.close();
            }
        }

        //Delete data from database
        Session session2 = sessionFactory.openSession();
        Transaction tx1 = null;
        try {
            tx1 = session2.beginTransaction();
            //session2.delete(receivedEmpObj);
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session2 != null) {
                session2.close();
            }
        }
    }
}