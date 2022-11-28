package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class signin_controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField nama;
    @FXML
    private TextField noHp;
    @FXML
    private Label label1;
    @FXML
    private void clickLogin() throws IOException {
        HelloApplication.setRoot("FrontEnd/tes");
    }
    @FXML
    private void clickCreate() throws IOException, SQLException {
        if (!username.getText().isBlank() && !password.getText().isBlank()){
            createLogin();
        }else{
            DatabaseConnection connectNow = new DatabaseConnection();
            connectNow.MyAlert("warning", "Peringatan","Data harus diisi!");
        }
    }

    public void createLogin() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifySignin = "Select count(1) from LoginDataPelanggan Where Username = '"+username.getText()+"'";
//        String query = "insert into LoginDataPelanggan values ('"+username.getText().trim()+"', '"+password.getText().trim()+"', '"+nama.getText().trim()+"', '"+noHp.getText().trim()+"')";
        String query = "insert into LoginDataPelanggan values (?,?,?,?)";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifySignin);


            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    connectNow.MyAlert("warning", "Peringatan","Username sudah dipakai!");
                }else{
                    ResultSet execute = null;
                    PreparedStatement sqlStatement = connectDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    sqlStatement.setString(1,username.getText().trim());
                    sqlStatement.setString(2,password.getText().trim());
                    sqlStatement.setString(3,nama.getText().trim());
                    sqlStatement.setString(4,noHp.getText().trim());
                    sqlStatement.execute();
                    execute = sqlStatement.getGeneratedKeys();
                    while (execute.next()){
                        connectNow.MyAlert("info", "Informasi","Data berhasil disimpan!");
                        HelloApplication.setRoot("FrontEnd/tes");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
