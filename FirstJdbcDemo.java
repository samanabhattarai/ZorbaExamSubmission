package com.jdbc.examples;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class FirstJdbcDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/zorba_1015";
        String userName = "root";
        String password = "root";

        Connection connection = null;
        try {


            connection = null;

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver loaded");

            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("connection established");

            //Statement statement = connection.createStatement();
            String query = "select * from employee where empl_id= ? and employee_name=?";

            PreparedStatement prepareStatement = connection.prepareStatement(query);

            prepareStatement.setInt(1, 1001);
            prepareStatement.setString(2, "sam");

            ResultSet resultSet = prepareStatement.executeQuery();

            //ResultSetMetaData metaData = resultSet.getMetaData();
            //// DatabaseMetaData metadata = connection.getMetaData();
            //metadata.getTables(null, null, null, null);;
            //int count = metaData.getColumnCount();
            // System.out.println(count);
            //int id = Integer.parseInt(metaData.getColumnName(1));
            //System.out.println(id);

//create table
            Statement statement = connection.createStatement();
            // String queries = "create table sales(sales_id int, name varchar(20), price double)";
            //  statement.executeUpdate(queries);

            //  String querie = "alter table sales add primary key(sales_id)";
            // statement.executeUpdate(querie);

            //truncate table
           // String str1 = "truncate table products";
           // statement.executeUpdate(str1);


           PreparedStatement prepareStatement1 = connection.prepareStatement("insert into products values(?, ?, ?)");
            prepareStatement1.setInt(1, 1004);
            prepareStatement1.setString(2, "wallet");
            prepareStatement1.setDouble(3, 100.23);


            prepareStatement1.executeUpdate();


//delete from table
            //  PreparedStatement preparedSt =  connection.prepareStatement("delete from products where id=? ");
            // preparedSt.setInt(1,1004);
            // preparedSt.executeUpdate();

          /* while(resultSet.next()){
                int empId = resultSet.getInt(1);
                String empName = resultSet.getString(2);
               double salary = resultSet.getDouble(3);
               int age = resultSet.getInt(4);
               String date =resultSet.getString(5);



                System.out.println(empId + " " + " " + salary + " " + age + " "
                + date);

                System.out.println(resultSet.getString(empId));



            }
*/

        } catch (ClassNotFoundException | SQLException e) {
            System.err.print(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("connection closed");
                } catch (Exception e) {
                    System.err.println(e.getMessage());

                }
            }
        }
    }
}
