package com.jdbc.examples;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class RailwayReservationSystemDemoExam {

    public static  void main(String[] args) throws SQLException, IOException {


        String url = null;
        String username = null;
        String password = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creating properties object
            Properties properties = new Properties();


            FileReader fileReader = new FileReader("C:\\zorba_intellije\\JDBCExamples\\src\\main\\resources\\application.properties");
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            properties.load(bufferedReader);

            username = (String) properties.get("username");
            System.out.println(username);
            password = (String) properties.get("password");
            System.out.println(password);
            url = (String) properties.get("url");
            System.out.println(url);


            // creating table
            String railwayReservation = "create table railwayreservation ( passenger_name varchar(20), passenger_age int(6), choosen_seat varchar(10)," +
                    " reservation_type varchar(6), is_senior_citizen boolean, amount_paid decimal(10, 2))";

            connection = null;
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.execute(railwayReservation);
            System.out.println("ReservationSystemTable is created");


            PreparedStatement preparedStatement = connection.prepareStatement("insert into  railwayreservation values(?, ?, ?, ?, ?, ?) ");


            Scanner scanner = null;
            ModelRailwayReservation modelRailwayReservation = setReservation(scanner);
            preparedStatement.setString(1, modelRailwayReservation.getPassengerName());
            preparedStatement.setInt(2, modelRailwayReservation.getPassengerAge());
            preparedStatement.setString(3, modelRailwayReservation.getChoosenSeat());
            preparedStatement.setString(4, modelRailwayReservation.getReservationType());
            preparedStatement.setBoolean(5, modelRailwayReservation.isSeniorCitizen());
            preparedStatement.setDouble(6, modelRailwayReservation.getAmountPaid());
            preparedStatement.executeUpdate();

            String query = "select * from railwayreservation";
            ResultSet resultSet = statement.executeQuery(query);

            List<ModelRailwayReservation> touristSpotDetailsList = new ArrayList<>();

            while (resultSet.next()) {
                     ModelRailwayReservationSystemDemoExam= new   ModelRailwayReservationSystemDemoExam();


            }

            resultSet = statement.executeQuery(query);
            System.out.println(resultSet);




        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection Closed...");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }


              

            }    }
        }

    private static ModelRailwayReservation setReservation(Scanner scanner) {

        ModelRailwayReservation modelRailwayReservation = new ModelRailwayReservation();
        modelRailwayReservation.setPassengerName(scanner.next());
        modelRailwayReservation.setPassengerAge(scanner.nextInt());
        modelRailwayReservation.setChoosenSeat(scanner.next());
        modelRailwayReservation.setReservationType(scanner.next());
        modelRailwayReservation.setSeniorCitizen(scanner.nextBoolean());
        modelRailwayReservation.setAmountPaid(scanner.nextDouble());
        return modelRailwayReservation;
    }
}



