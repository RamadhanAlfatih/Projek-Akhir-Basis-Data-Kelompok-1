package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class cobadulu {
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/menuAwal");
    }
    @FXML
    private void clickLaundry() throws IOException {
        HelloApplication.setRoot("FrontEnd/coba2");
    }
}
