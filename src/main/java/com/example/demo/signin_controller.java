package com.example.demo;

import javafx.fxml.FXML;

import javax.swing.*;
import java.io.IOException;

public class signin_controller {
    @FXML
    private void clickLogin() throws IOException {
        HelloApplication.setRoot("FrontEnd/tes");
    }
    @FXML
    private void clickCreate() throws IOException {
        JOptionPane.showMessageDialog(null, "Masih error");
    }
}
