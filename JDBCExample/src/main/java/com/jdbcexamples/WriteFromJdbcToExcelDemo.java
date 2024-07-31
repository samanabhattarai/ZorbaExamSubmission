package com.jdbcexamples;

import com.model.CustomerDetails;
import com.model.VehicleDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WriteFromJdbcToExcelDemo {

    public static Connection connection = null;
    static Statement statement = null;
    static  PreparedStatement preparedStatement = null;

    public WriteFromJdbcToExcelDemo() throws SQLException {
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {


        String dbUrl = "jdbc:mysql://localhost:3306/Zorba_1015";
        String userName = "root";
        String password = "root";
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\\n"); // delimiter needed for scanner input with space in between
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection to mysql from jdbc
            connection = DriverManager.getConnection(dbUrl, userName, password);

            System.out.println("successful connection!");

            statement = connection.createStatement();

//            // drop tables if already exist
//            String dropTable = "drop table if exists vehicle_details, customer_details";
//            statement.execute(dropTable);
//
//            //create table customer_details
//
//            String createCustomerDetails = "create table customer_details (cust_id int, cust_name varchar(255), cust_location varchar(255), cust_email varchar(50), cust_mobile bigint, v_hire_date datetime)";
//            statement.execute(createCustomerDetails);
//
//
//            //alter table customer_details
//            String addPrimaryKeyCustomerDetails = "alter table customer_details add primary key(cust_id)";
//            statement.executeUpdate(addPrimaryKeyCustomerDetails);
//
//            //create another table vehicle_details
//            String createVehicle = "create table vehicle_details (v_id int(6), v_name varchar(255), v_registration varchar(255), v_reg_date datetime, customer_id int(5))";
//            statement.execute(createVehicle);
//
//            //alter table vehicle_details for primary key
//            String addPrimaryKeyVehicle = "alter table vehicle_details add primary key(v_id)";
//            statement.executeUpdate(addPrimaryKeyVehicle);
//            //alter table vehicle_details for foreign key
//            String addForeignKey = "alter table vehicle_details add foreign key(customer_id) references customer_details(cust_id)";
//            statement.executeUpdate(addForeignKey);


            System.out.println("how many records you want to insert into your customer_details table");
            int inputNumberOfCustomerRecords = sc.nextInt();

            for (int i = 0; i < inputNumberOfCustomerRecords; i++) {
                CustomerDetails customerDetails = fetchCustomerDetails(sc);

                preparedStatement = connection.prepareStatement("insert into customer_details(cust_id , cust_name, cust_location, cust_email, cust_mobile, v_hire_date) values(?,?,?,?,?,?)");
                preparedStatement.setInt(1, customerDetails.getCustId());
                preparedStatement.setString(2, customerDetails.getCustName());
                preparedStatement.setString(3, customerDetails.getCustLocation());
                preparedStatement.setString(4, customerDetails.getCustEmail());
                preparedStatement.setLong(5, customerDetails.getCustMobile());
                preparedStatement.setString(6, customerDetails.getvHireDate().toString());
                preparedStatement.executeUpdate();
            }

            System.out.println("how many records you want to insert into your vehicle_details table");
            int inputNumberOfVehicleRecords = sc.nextInt();
            for (int i = 0; i < inputNumberOfVehicleRecords; i++) {


                VehicleDetails vehicleDetails = fetchVehicleDetails(sc);

                preparedStatement = connection.prepareStatement("insert into vehicle_details(v_id, v_name, v_registration, v_reg_date, customer_id) values(?,?,?,?,?)");
                preparedStatement.setInt(1, vehicleDetails.getvId());
                preparedStatement.setString(2, vehicleDetails.getvName());
                preparedStatement.setString(3, vehicleDetails.getvRegistration());
                preparedStatement.setString(4, vehicleDetails.getvRegDate().toString());
                preparedStatement.setInt(5, vehicleDetails.getCustomerId());
                preparedStatement.executeUpdate();
            }


        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());

        }

        if(connection != null) {

            ResultSet resultSetCustomerDetails = statement.executeQuery("select * from customer_details");

            writeToExcelFileFromJdbc(resultSetCustomerDetails, "C:\\ZorbaExamSubmission\\JDBCExample\\src\\main\\resources\\CustomerDetails.xlsx");

            ResultSet resultSetVehicleDetails = statement.executeQuery("select * from vehicle_details");

            writeToExcelFileFromJdbc(resultSetVehicleDetails, "C:\\ZorbaExamSubmission\\JDBCExample\\src\\main\\resources\\VehicleDetails.xlsx");
        }


    }

    public static CustomerDetails fetchCustomerDetails(Scanner scanner) {

        CustomerDetails customerDetails = new CustomerDetails();

        System.out.println("Enter customer id: ");
        int custId = scanner.nextInt();

        customerDetails.setCustId(custId);

        System.out.println("Enter customer name:");

        String custName = scanner.next();

        customerDetails.setCustName(custName);

        System.out.println("Enter customer location: ");
        String custLocation = scanner.next();
        customerDetails.setCustLocation(custLocation);

        System.out.println("Enter customer email: ");
        String custEmail = scanner.next();
        customerDetails.setCustEmail(custEmail);

        System.out.println("Enter customer mobile: ");
        String custMobile = scanner.next();
        customerDetails.setCustMobile(Long.parseLong(custMobile));

        System.out.println("Enter vehicle hire date(yyyy-MM-dd HH:mm:ss): ");
        String vHireDate = scanner.next();
        System.out.println(vHireDate);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse(vHireDate, dateTimeFormatter);
        System.out.println(vHireDate);
        customerDetails.setvHireDate(localDateTime);


        return customerDetails;

    }

    public static VehicleDetails fetchVehicleDetails(Scanner scanner) {

        VehicleDetails vehicleDetails = new VehicleDetails();

        System.out.println("Enter vehicle id: ");
        int vId = scanner.nextInt();

        vehicleDetails.setvId(vId);

        System.out.println("Enter vehicle name: ");
        String vName = scanner.next();

        vehicleDetails.setvName(vName);

        System.out.println("Enter vehicle registration: ");
        String vRegistration = scanner.next();

        vehicleDetails.setvRegistration(vRegistration);

        System.out.println("Enter vehicle registration date(yyyy-MM-dd HH:mm:ss): ");
        String vRegDate = scanner.next();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(vRegDate, dateTimeFormatter);
        vehicleDetails.setvRegDate(localDateTime);
        System.out.println(localDateTime);


        System.out.println("Enter customer id: ");
        int customerId = scanner.nextInt();
        vehicleDetails.setCustomerId(customerId);

        return vehicleDetails;


    }

    public static void writeToExcelFileFromJdbc(ResultSet rs, String fileName) throws SQLException, IOException {


        ResultSetMetaData rsmd = rs.getMetaData();


        List<String> columns = new ArrayList<>();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {


                    columns.add(rsmd.getColumnLabel(i));

                }
            //create sheet from row columns

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(0);

            for (int i = 0; i < columns.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(columns.get(i));

                //create other rows below header, create cell and insert values
            }

            int rowIndex = 0;

           while (rs.next()) {

                Row rows = sheet.createRow(++rowIndex);

                for (int i = 0; i < columns.size(); i++) {

                    Cell cell = rows.createCell(i);

                    String values = Objects.toString(rs.getObject(columns.get(i)), "");

                    cell.setCellValue(values.toString());
                }


            }
            try (FileOutputStream fos = new FileOutputStream(fileName) ){
                workbook.write(fos);
                System.out.println("file inseretd: " + fileName);

            }
            catch(IOException e ){
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }


    }
}

