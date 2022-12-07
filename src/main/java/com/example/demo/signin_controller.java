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
    private RadioButton lakiRB;
    @FXML
    private RadioButton perempuanRB;
    @FXML
    private ToggleGroup jenisKelaminRBGroup;
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

        String query = "insert into LoginDataPelanggan values (?,?,?,?)";
        String query1 = "Insert into Pelanggan (Username, Nama, JenisKelamin, NoTelp) values (?,?,?,?)";


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

                    ResultSet execute1 = null;
                    PreparedStatement sqlStatement1 = connectDB.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                    sqlStatement1.setString(1,username.getText().trim());
                    sqlStatement1.setString(2,nama.getText().trim());
                    String value;
                    if (jenisKelaminRBGroup.getSelectedToggle().toString().contains("laki")) {
                        value = "L";
                    } else {
                        value = "P";
                    }
                    sqlStatement1.setString(3,value.trim());
                    sqlStatement1.setString(4,noHp.getText().trim());

                    sqlStatement1.execute();
                    execute1 = sqlStatement1.getGeneratedKeys();
                    while (execute.next()&&execute1.next()){
                        connectNow.MyAlert("info", "Informasi","Data berhasil disimpan!");
                        HelloApplication.setRoot("FrontEnd/tes");
                    }
                }
            }
        }catch (Exception e){
            connectNow.MyAlert("warning", "Peringatan",String.valueOf(e));
        }
    }
}
