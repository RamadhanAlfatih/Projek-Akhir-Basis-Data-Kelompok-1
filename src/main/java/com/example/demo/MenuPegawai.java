package com.example.demo;

import javafx.fxml.FXML;

import java.io.IOException;

public class MenuPegawai {
    @FXML
    private void clickPegawai() throws IOException {
        HelloApplication.setRoot("BackEnd/dataPegawai");
    }
    @FXML
    private void clickPelanggan() throws IOException {
        HelloApplication.setRoot("BackEnd/dataPelanggan");
    }
    @FXML
    private void clickLaundry() throws IOException {
        HelloApplication.setRoot("BackEnd/dataLaundry");
    }
    @FXML
    private void clickPembayaran() throws IOException {
        HelloApplication.setRoot("BackEnd/dataPembayaran");
    }
    @FXML
    private void clickIsiberat() throws IOException {
        HelloApplication.setRoot("BackEnd/isiBeratLaundry");
    }
    @FXML
    private void clickHarga() throws IOException {
        HelloApplication.setRoot("BackEnd/dataHarga");
    }
}
