package com.hibernateexam.execution;

import com.hibernateexam.entity.Employee;
import com.hibernateexam.entity.Skill;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeSkillExecution {

    public static void main(String[] args) throws IOException {

        //file read

        FileInputStream fileInputStream = new FileInputStream("employees.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Set<Employee> employees = new HashSet<>();
        List<Skill> skills = new ArrayList<>();
        List<Skill> employeeSkills = new ArrayList<>();

        // Read employee data
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null) {
                Employee employee = new Employee();
                employee.setEmployeeId((int) cell.getNumericCellValue());
                employee.setEmployeeName(row.getCell(1).getStringCellValue());
                employee.setEmployeeAddress(row.getCell(2).getStringCellValue());
                employee.setEmployeeEmail(row.getCell(3).getStringCellValue());
                employee.setEmployeeExperience((int) cell.getNumericCellValue());

            }
        }

        // Read skill data
        sheet = workbook.getSheetAt(1);
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null) {
                Skill skill = new Skill();
                skill.setSkillId((int) cell.getNumericCellValue());
                skill.setSkillName(row.getCell(1).getStringCellValue());
                skills.add(skill);
            }
        }


        // Save data to database using Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        //Create SessionFactory Object from Configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Create new Session
        Session session = sessionFactory.openSession();

        //Declare transaction Object
        Transaction tx = null;

        tx = session.beginTransaction();
        try {
            for (Employee employee : employees) {
                session.persist(employee);
            }

            for (Skill skill : skills) {
                session.persist(skill);

                session.getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());


        }

        finally {

                session.close();}
    }

}