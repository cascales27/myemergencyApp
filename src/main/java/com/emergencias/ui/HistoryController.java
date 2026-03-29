package com.emergencias.ui;

import com.emergencias.model.EmergencyRecord;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.util.List;

public class HistoryController {

    @FXML
    private TableView<EmergencyRecord> tableHistory;

    @FXML
    private TableColumn<EmergencyRecord, String> colUsuario;

    @FXML
    private TableColumn<EmergencyRecord, String> colTipo;

    @FXML
    private TableColumn<EmergencyRecord, String> colUbicacion;

    // ✅ NUEVA COLUMNA
    @FXML
    private TableColumn<EmergencyRecord, String> colFecha;

    @FXML
    public void initialize() {
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));

        // ✅ ESTO ES LO QUE FALTABA
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    /**
     * Recibe la lista de registros y la carga en la tabla.
     */
    public void cargarRegistros(List<EmergencyRecord> registros) {
        if (registros != null && !registros.isEmpty()) {
            tableHistory.getItems().setAll(registros);
        }
    }

    /**
     * Vuelve al menú principal.
     */
    @FXML
    private void volverMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/emergencias/ui/MainView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Sistema de Emergencias");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}