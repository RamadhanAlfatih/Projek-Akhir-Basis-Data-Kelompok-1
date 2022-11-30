package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class IsiBeratLaundry {
    @FXML
    private TextField beratcucianText;

    @FXML
    private Button kembali;

    @FXML
    private TextField nocucianText;

    @FXML
    private Button tambah;

    @FXML
    private void clickTambah() throws IOException {
        tambahLaundry();
    }
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
    public void tambahLaundry(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "Update Pesanan Set BeratCucian = '"+beratcucianText.getText()+"' where NomorCucian = '"+nocucianText.getText()+"'";

        try {
            PreparedStatement sqlStatement = connectDB.prepareStatement(query);
            sqlStatement.execute();
            connectNow.MyAlert("info", "Informasi", "Data berhasil ditambahkan!");
            HelloApplication.setRoot("BackEnd/menuPegawai");
        }catch (Exception e){
            e.printStackTrace();
            connectNow.MyAlert("warning", "Warning", String.valueOf(e));
        }
    }
}
