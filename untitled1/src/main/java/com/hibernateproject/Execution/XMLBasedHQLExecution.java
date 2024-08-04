package com.hibernateproject.Execution;

import com.hibernateproject.Entity.Employee;
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
        String selectQuery = "from Employee";
        try {
            Query query = session.createQuery(selectQuery);
            List<Employee> employeeList = query.list();
            System.out.println(employeeList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //Session object creation
        Session session1 = sessionFactory.openSession();
        String selectSpecificEmp = "select empName from Employee where empId=:id";
        try {
            Query query1 = session1.createQuery(selectSpecificEmp);
            query1.setParameter("id", 2);
            String recEmp = (String) query1.getSingleResult();
            System.out.println(recEmp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session1 != null) {
                session1.close();
            }
        }

        //Session object creation
        Session session3 = sessionFactory.openSession();
        Transaction tx = null;
        String updateEmp = "update Employee set empName = :newName where empId = :empId";
        try {
            tx = session3.beginTransaction();
            Query query4 = session3.createQuery(updateEmp);
            query4.setParameter("newName", "Anil");
            query4.setParameter("empId", 2);
            int noOfRows = query4.executeUpdate();
            System.out.println(noOfRows + " row updated");
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session3 != null) {
                session3.close();
            }
        }

        //Session object creation
        Session session2 = sessionFactory.openSession();
        String selectEmp = "select empName from Employee";
        try {
            Query query2 = session2.createQuery(selectEmp);
            List<String> allEmpNames = query2.list();
            System.out.println(allEmpNames);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session2 != null) {
                session2.close();
            }
        }
    }
}
