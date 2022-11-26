package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class HistoriLaundry {
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/menuAwal");
    }
    @FXML
    private void clickNota() throws IOException {
        HelloApplication.setRoot("FrontEnd/TampilanNota");
    }
}
