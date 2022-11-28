package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SigninPegawai {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField IdPegawai;
    @FXML
    private TextField noHp;
    @FXML
    private void clickLogin() throws IOException {
        HelloApplication.setRoot("BackEnd/loginPegawai");
    }
    @FXML
    private void clickCreate() throws IOException {
        if (!username.getText().isBlank() && !password.getText().isBlank()){
            createLogin();
        }else{
            DatabaseConnection connectNow = new DatabaseConnection();
            connectNow.MyAlert("warning", "Peringatan","Data harus diisi!");
        }
    }
    public void createLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifySignin = "Select count(1) from LoginDataPegawai Where Username = '"+username.getText().trim()+"'";
        String verifyIdPegawai = "Select count(1) from LoginDataPegawai Where IdPegawai = '"+IdPegawai.getText().trim()+"'";
//        String query = "insert into LoginDataPegawai values ('"+username.getText().trim()+"', '"+password.getText().trim()+"', '"+IdPegawai.getText().trim()+"', '"+noHp.getText().trim()+"')";
        String query = "insert into LoginDataPegawai values (?,?,?,?)";
        try {
            Statement statement = connectDB.createStatement();
//            ResultSet queryResult = statement.executeQuery(verifySignin);
            ResultSet queryResult = null;
            queryResult = statement.executeQuery(verifySignin);
//            ResultSet queryResult1 = statement.executeQuery(verifyIdPegawai);
//            ResultSet queryResult1 = null;
//            queryResult1 = statement.executeQuery(verifyIdPegawai);
            boolean validation = true;
//
//            while (queryResult1.next()){
//                if (queryResult1.getInt(1)==1){
//                    validation = true;
//                }else {
//                    connectNow.MyAlert("warning", "Peringatan","IdPegawai belum didaftarkan!");
//                    validation = false;
//                }
//            }
            while(queryResult.next()){
                if (queryResult.getInt(1)==1){
                    connectNow.MyAlert("warning", "Peringatan","Username sudah dipakai!");
                }else{
                    if (validation){
                        ResultSet execute = null;
                        PreparedStatement sqlStatement = connectDB.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        sqlStatement.setString(1,username.getText().trim());
                        sqlStatement.setString(2,password.getText().trim());
                        sqlStatement.setString(3,IdPegawai.getText().trim());
                        sqlStatement.setString(4,noHp.getText().trim());
                        sqlStatement.execute();
                        execute = sqlStatement.getGeneratedKeys();
                        while (execute.next()){
                            connectNow.MyAlert("info", "Informasi","Data berhasil disimpan!");
                            HelloApplication.setRoot("BackEnd/loginPegawai");
                        }
                    }
                }
            }

        }catch (Exception e){
//            e.printStackTrace();
            connectNow.MyAlert("warning", "Peringatan","IdPegawai belum didaftarkan!");
        }
    }
}
