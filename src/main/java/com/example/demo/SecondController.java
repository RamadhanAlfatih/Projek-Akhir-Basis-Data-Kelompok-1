package com.example.demo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SecondController{
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private Label loginMessage;
    @FXML
    private Button kembali_button;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/hello-view");
    }
    @FXML
    private void clickLogin() {
        if (!username.getText().trim().isBlank() && !password.getText().trim().isBlank()){
            validateLogin();
        }else{
            loginMessage.setText("Masukkan username dan password Anda!");
        }
    }
    @FXML
    private void enterPassword(){
        if (!username.getText().isBlank() && !password.getText().isBlank()){
            validateLogin();
        }else{
            loginMessage.setText("Masukkan username dan password Anda!");
        }
    }

    @FXML
    private void clickSignin() throws IOException {
        HelloApplication.setRoot("FrontEnd/signin");
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String verifyLogin = "Select count(1) from LoginDataPelanggan Where Username = '"+username.getText()+"' and Password = '"+password.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    HelloApplication.setRoot("FrontEnd/menuAwal");
                    PreparedStatement deleteSt = connectDB.prepareStatement("Execute dbo.DeleteTempUser");
                    deleteSt.execute();
                    String tempQuery = "Execute dbo.GetTempUser '"+username.getText()+"'";
                    PreparedStatement sqlStatement;
                    try {
                        sqlStatement = connectDB.prepareStatement(tempQuery);
                            sqlStatement.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    connectNow.MyAlert("warning", "Warning", "Username atau password salah!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
