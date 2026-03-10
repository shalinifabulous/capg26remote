package org.example;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class PlayersService {
    public static void main(String[] args) throws SQLException {
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cap_player",
                "root",
                "Shalini@2804"
        );

        Statement statement = connection.createStatement();

        //===========================Insert dat====================

        int rowsInserted = statement.executeUpdate(
                "INSERT INTO players values (3, 'Smith', 'Aus')"
        );

        if(rowsInserted > 0){
            System.out.println(rowsInserted + "rows Inserted");
        }
        else {
            System.out.println("insert failed");
        }

        // +++++++++++++++ Fetching+++++++++++++++++++++++++++++++++


        ResultSet resultSet = statement.executeQuery("SELECT * FROM players");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String country = resultSet.getString("playercountry");

            System.out.println(id + "|" + name + "|" + country);


            //========================update dat============================


            int rowsUpdated = statement.executeUpdate(
                    "UPDATE players SET playercountry = 'England' WHERE id = 2"
            );

            if (rowsUpdated > 0) {
                System.out.println(rowsUpdated + "row updated");
            } else {
                System.out.println("Update failed");
            }

            //========================Delete data================

            int rowsDeleted = statement.executeUpdate(
                    "DELETE FROM players WHERE id = 3"
            );

            if (rowsDeleted > 0) {
                System.out.println(rowsDeleted + "deleted");
            } else {
                System.out.println("delete failed");
            }
       }
    }
}

