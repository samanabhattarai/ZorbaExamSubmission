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
import java.util.HashSet;
import java.util.Set;

public class EmployeeSkillExecution {

    public static void main(String[] args) throws IOException {

        //file read

        FileInputStream fileInputStream = new FileInputStream("C:\\ZorbaExamSubmission\\HibernateExam110424\\src\\main\\resources\\Exam_info.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Set<Employee> employees = new HashSet<>();
        Set<Skill> skills = new HashSet<>();

        // Read employee data
        // skip first row as header
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && row.getRowNum() > 0) {
                Employee employee = new Employee();
                employee.setEmployeeId((int) cell.getNumericCellValue());
                employee.setEmployeeName(row.getCell(1).getStringCellValue());
                employee.setEmployeeAddress(row.getCell(2).getStringCellValue());
                employee.setEmployeeEmail(row.getCell(3).getStringCellValue());
                employee.setEmployeeExperience((int) row.getCell(4).getNumericCellValue());
                System.out.println("Employee: " + employee);
                employees.add(employee);
            }
        }

        // Read skill data
        sheet = workbook.getSheetAt(1);
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && row.getRowNum() > 0) {
                Skill skill = new Skill();
                skill.setSkillId((int) cell.getNumericCellValue());
                skill.setSkillName(row.getCell(1).getStringCellValue());
                // read other columns
                System.out.println("Skill: " + skill);
                skills.add(skill);
            }
        }

        // Read employee-skill mapping data
        sheet = workbook.getSheetAt(2);
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && row.getRowNum() > 0) {
                int employeeId = (int) cell.getNumericCellValue();
                int skillId = (int) row.getCell(1).getNumericCellValue();
                System.out.println("Employee id: " + employeeId + " Skill id: " + skillId);
                for (Employee emp : employees) {
                    if (emp.getEmployeeId() == employeeId) {
                        emp.getSkills().add(skills.stream().filter(skill -> skill.getSkillId() == skillId).findFirst().get());
                    }
                }
            }

        }
        // Just see the data

        for(Employee emp : employees){
            System.out.println("Employee id " + emp.getEmployeeId() + " skills " + emp.getSkills());
        }

        // Save data to database using Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        //Create SessionFactory Object from Configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Create new Session
        Session session = sessionFactory.openSession();

        //Declare transaction Object
        Transaction tx = session.beginTransaction();
        try {

            for(Employee employee : employees){
                session.persist(employee);
            }

            for(Skill skill : skills){
                session.persist(skill);
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());


        } finally {

            session.close();
        }
    }
}