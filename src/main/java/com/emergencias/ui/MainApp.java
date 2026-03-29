package com.emergencias.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/emergencias/ui/MainView.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);
            stage.setScene(scene);
            stage.setTitle("Sistema de Emergencias");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}