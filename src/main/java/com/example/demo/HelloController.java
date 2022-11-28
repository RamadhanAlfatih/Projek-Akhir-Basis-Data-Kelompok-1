package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController{
    @FXML
    private Button pegawai;
    @FXML
    private Button pelanggan;
    @FXML
    private void clickPegawai() throws IOException {
        HelloApplication.setRoot("BackEnd/loginPegawai");
    }
    @FXML
    private void clickPelanggan() throws IOException {
        HelloApplication.setRoot("FrontEnd/tes");
    }
//    public void ganti() {
//        kePegawai.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    HelloApplication.setRoot("BackEnd/loginPegawai.fxml");
////                    DBUtils.changeScene(event, "BackEnd/loginPegawai.fxml", "Laundry Badak");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        kePelanggan.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    DBUtils.changeScene(event, "FrontEnd/tes.fxml", "Laundry Badak");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
}

