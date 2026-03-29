package com.emergencias.ui;

import com.emergencias.controller.EmergencyManager;
import com.emergencias.model.HealthCenter;
import com.emergencias.model.UserData;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.util.HealthCenterLoader;
import com.emergencias.util.HealthCenterUtils;
import com.emergencias.gps.GPSLocation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;

import java.util.List;

public class MainController {

    private List<HealthCenter> centros;
    private EmergencyManager emergencyManager;

    @FXML
    public void initialize() {
        emergencyManager = new EmergencyManager();
        centros = HealthCenterLoader.loadFromFile();
    }

    @FXML
    private void iniciarEmergencia() {

        TextInputDialog tipoDialog = new TextInputDialog("Emergencia médica");
        tipoDialog.setTitle("Nueva emergencia");
        tipoDialog.setHeaderText("Introduce el tipo de emergencia:");
        tipoDialog.setContentText("Tipo:");

        String tipo = tipoDialog.showAndWait().orElse("Emergencia médica");

        TextInputDialog ubicacionDialog = new TextInputDialog("Madrid");
        ubicacionDialog.setTitle("Nueva emergencia");
        ubicacionDialog.setHeaderText("Introduce la ubicación:");
        ubicacionDialog.setContentText("Ubicación:");

        String ubicacion = ubicacionDialog.showAndWait().orElse("Madrid");

        UserData usuario = new UserData(
                "user1",
                "Usuario Prueba",
                "600123123",
                List.of("Contacto1", "Contacto2"),
                List.of()
        );

        // 🚀 GPS SIMULADO (aquí debería ir GPS real en el futuro)
        GPSLocation gps = new GPSLocation(
                40.4168,
                -3.7038,
                5.0
        );

        emergencyManager.activarEmergenciaManual(
                tipo,
                ubicacion,
                usuario,
                gps
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Emergencia");
        alert.setHeaderText(null);
        alert.setContentText("Emergencia registrada correctamente.");
        alert.showAndWait();
    }

    @FXML
    private void verHistorial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/emergencias/ui/HistoryView.fxml")
            );
            Parent root = loader.load();

            HistoryController controller = loader.getController();
            List<EmergencyRecord> registros = emergencyManager.getHistorial();
            controller.cargarRegistros(registros);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Historial de Emergencias");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verTutorial() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tutorial de Primeros Auxilios");
        alert.setHeaderText(null);
        alert.setContentText(
                "- Caída: Revisar respiración.\n" +
                "- Atragantamiento: Heimlich.\n"
        );
        alert.showAndWait();
    }

    @FXML
    private void centroMasCercano() {
        double userLat = 40.4168;
        double userLng = -3.7038;

        HealthCenter cercano =
                HealthCenterUtils.getCentroMasCercano(userLat, userLng, centros);

        if (cercano != null) {
            String info =
                    "Nombre: " + cercano.getMU_NOMBRE() + "\n" +
                    "Denominación: " + cercano.getDenominacion() + "\n" +
                    "Tipo: " + cercano.getTipo() + "\n" +
                    "Coordenadas: " + cercano.getLatitude() + ", " + cercano.getLongitude();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Centro de Salud Más Cercano");
            alert.setHeaderText(null);
            alert.setContentText(info);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Centro de Salud");
            alert.setHeaderText(null);
            alert.setContentText("No se encontró ningún centro.");
            alert.showAndWait();
        }
    }
}