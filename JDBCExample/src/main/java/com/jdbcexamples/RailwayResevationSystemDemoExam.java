package com.jdbcexamples;

import com.model.RailwayReservation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RailwayResevationSystemDemoExam {

    public static  void main(String[] args) throws SQLException, IOException {


        String url = null;
        String userName = null;
        String password = null;
        Connection connection = null;
        Statement statement = null;
        Scanner scanner = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creating properties object
            Properties properties = new Properties();

            //reading properties file using FileReader class, its method and BufferReader parameterized constructor for inserting connections data.
            FileReader fileReader = new FileReader("C:\\ZorbaExamSubmission\\JDBCExample\\src\\main\\resources\\application.properties");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

           //loading properties in the bufferedReader object, using properties load method.
            properties.load(bufferedReader);

            userName = (String) properties.get("userName");
            password = (String) properties.get("password");
            url = (String) properties.get("url");
            System.out.println("properties loaded successfully");

            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            //drop table if already exist
            String dropTable ="drop table if exists railwayreservation";
            statement.execute(dropTable);

            // creating table
            String railwayReservationTable = "create table railwayreservation( passenger_name varchar(20), passenger_age int(6), choosen_seat varchar(10)," +
                    " reservation_type varchar(6), is_senior_citizen boolean, amount_paid decimal(10, 2))";


            statement.execute(railwayReservationTable);
            System.out.println("railwayReservation table is created");

             //inserting data
            PreparedStatement preparedStatement = connection.prepareStatement("insert into  railwayreservation values(?, ?, ?, ?, ?, ?) ");

         scanner = new Scanner(System.in);


            System.out.println("Insert number of data") ;

           int inputRecords = scanner.nextInt();

           for(int i=0; i<inputRecords; i++) {


               //inserting data using RailwayReservation parameterized constructor and passing values using preparedStatement
               RailwayReservation railwayReservation = new RailwayReservation(scanner);

               preparedStatement.setString(1, railwayReservation.getPassengerName());
               preparedStatement.setInt(2, railwayReservation.getPassengerAge());
               preparedStatement.setString(3, railwayReservation.getChoosenSeat());
               preparedStatement.setString(4, railwayReservation.getReservationType());
               preparedStatement.setBoolean(5, railwayReservation.isSeniorCitizen());
               preparedStatement.setDouble(6, railwayReservation.getAmountPaid());

               preparedStatement.executeUpdate();
           }   //execute query and getting resultSet to retrieve data
            String query = "select * from railwayreservation";
            ResultSet resultSet = statement.executeQuery(query);

            //getting data from resultSet from each column
            /*while (resultSet.next()) {

                String passengerNameResult = resultSet.getString(1);
                int passengerAgeResult = resultSet.getInt(2);
                String choosenSeatResult = resultSet.getString(3);
                String reservationTypeResult = resultSet.getString(4);
                boolean isSeniorCitizenResult = resultSet.getBoolean(5);
                double amountPaidResult = resultSet.getDouble(6);

                System.out.println(passengerNameResult + " " + passengerAgeResult + " " + choosenSeatResult + " " + reservationTypeResult + " " + isSeniorCitizenResult + " " + amountPaidResult);

            }*/



        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        }

    }


