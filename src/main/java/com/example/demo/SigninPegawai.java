package com.example.demo;

import javafx.fxml.FXML;

import javax.swing.*;
import java.io.IOException;

public class SigninPegawai {
    @FXML
    private void clickLogin() throws IOException {
        HelloApplication.setRoot("BackEnd/loginPegawai");
    }
    @FXML
    private void clickCreate() throws IOException {
        JOptionPane.showMessageDialog(null, "Masih error");
    }

}
