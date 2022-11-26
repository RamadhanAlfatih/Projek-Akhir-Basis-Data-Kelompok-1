package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;


public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title) throws IOException {

        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 651, 422));
        stage.show();
    }
}
