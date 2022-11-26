package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPegawai {
    @FXML
    private Button kembali_button;
    @FXML
    private void clickKembali() throws IOException {
        HelloApplication.setRoot("FrontEnd/hello-view");
    }
    @FXML
    private void clickLogin() throws IOException {
        HelloApplication.setRoot("BackEnd/menuPegawai");
    }
    @FXML
    private void clickSignin() throws IOException {
        HelloApplication.setRoot("BackEnd/signinPegawai");
    }
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        kembali_button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    HelloApplication.setRoot("FrontEnd/hello-view.fxml");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
}
