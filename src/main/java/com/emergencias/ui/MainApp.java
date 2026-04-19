package com.emergencias.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            URL fxml = getClass().getResource("/com/emergencias/ui/MainView.fxml");

            if (fxml == null) {
                throw new RuntimeException("❌ No se encuentra MainView.fxml en /com/emergencias/ui/");
            }

            FXMLLoader loader = new FXMLLoader(fxml);
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Sistema de Emergencias");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}