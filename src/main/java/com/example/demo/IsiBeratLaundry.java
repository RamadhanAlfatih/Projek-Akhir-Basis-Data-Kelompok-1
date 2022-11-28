package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class IsiBeratLaundry {
    @FXML
    private void clickTambah() throws IOException {
    }
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
}
