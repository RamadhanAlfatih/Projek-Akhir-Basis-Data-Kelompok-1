package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginPegawai {
    @FXML
    private Button kembali_button;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/hello-view");
    }
    @FXML
    private void clickLogin() {
        if (!username.getText().trim().isBlank() && !password.getText().trim().isBlank()){
            validateLogin();
        }else{
            DatabaseConnection connectNow = new DatabaseConnection();
            connectNow.MyAlert("warning", "Peringatan","Masukkan username dan password Anda!");
        }
    }
    @FXML
    private void enterPassword(){
        if (!username.getText().isBlank() && !password.getText().isBlank()){
            validateLogin();
        }else{
            DatabaseConnection connectNow = new DatabaseConnection();
            connectNow.MyAlert("warning", "Peringatan","Masukkan username dan password Anda!");
        }
    }
    @FXML
    private void clickSignin() throws IOException {
        HelloApplication.setRoot("BackEnd/signinPegawai");
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String verifyLogin = "Select count(1) from LoginDataPegawai Where Username = '"+username.getText().trim()+"' and Password = '"+password.getText().trim()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    HelloApplication.setRoot("BackEnd/menuPegawai");
                }else{
                    connectNow.MyAlert("warning", "Peringatan","Username atau password salah!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
