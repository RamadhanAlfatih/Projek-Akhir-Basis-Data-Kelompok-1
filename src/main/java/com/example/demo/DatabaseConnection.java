package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String url = "jdbc:sqlserver://LAPTOP-FATIH;databaseName=FinalProject;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            databaseLink = DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
