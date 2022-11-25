package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label Connection;

    @FXML
    protected void onConnect1ButtonClick() {

        Connection.setText("DB Connected!");

    }
    @FXML
    protected void onConnect2ButtonClick() {
        Connection.setText("DB Disconnected!");
    }

}

