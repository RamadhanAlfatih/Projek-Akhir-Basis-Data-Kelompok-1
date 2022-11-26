package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class MenuAwal {
    @FXML
    private void clicktambahLaundry() throws IOException {

    }
    @FXML
    private void clicklaundryJalan() throws IOException {
        HelloApplication.setRoot("FrontEnd/laundryJalan");
    }
    @FXML
    private void clicklaundryHistori() throws IOException {
        HelloApplication.setRoot("FrontEnd/historiLaundry");
    }
    @FXML
    private void clicklaundryBayar() throws IOException {
        HelloApplication.setRoot("FrontEnd/MenuPembayaran");
    }
}
