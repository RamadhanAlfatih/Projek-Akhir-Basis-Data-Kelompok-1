package com.example.demo;
import javafx.scene.control.Alert;

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
    public void MyAlert(String type, String title, String content){
        Alert alert;
        if (type.equalsIgnoreCase("warning")){
            alert = new Alert(Alert.AlertType.WARNING);
        }else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
