package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DataPembayaran {
    @FXML
    private TextField cariText;

    @FXML
    private Button edit;

    @FXML
    private Button hapus;

    @FXML
    private TableColumn<?, ?> idpegawaiCol;

    @FXML
    private TextField idpegawaiText;

    @FXML
    private TableColumn<?, ?> jenispembayaranCol;

    @FXML
    private TextField jenispembayaranText;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<?, ?> nocucianCol;

    @FXML
    private TextField nocucianText;

    @FXML
    private TableColumn<?, ?> nopembayaranCol;

    @FXML
    private TextField nopembayaranText;

    @FXML
    private TableView<?> pembayaranTabel;

    @FXML
    private Button tambah;

    @FXML
    private TableColumn<?, ?> totalhargaCol;

    @FXML
    private TextField totalhargaText;

    @FXML
    private void clickTambah() throws IOException {

    }
    @FXML
    private void clickTabel() throws IOException {

    }
    @FXML
    private void clickEdit() throws IOException {

    }
    @FXML
    private void clickHapus() throws IOException {
    }
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
}
