package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FrontEnd/hello-view.fxml"));
//        scene = new Scene(fxmlLoader.load(), 651, 422);
        scene = new Scene(loadFXML("FrontEnd/hello-view"), 651,422);
        stage.setTitle("Laundry Badak");
        stage.setScene(scene);
        stage.show();
    }
    //"FrontEnd/hello-view.fxml"
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        //
    }
}