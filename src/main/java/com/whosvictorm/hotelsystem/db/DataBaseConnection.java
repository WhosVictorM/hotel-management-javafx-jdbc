package com.whosvictorm.hotelsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    public static Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "hotelmanagement";
        String databaseUser = "root";
        String databasePassword = "1234567";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
