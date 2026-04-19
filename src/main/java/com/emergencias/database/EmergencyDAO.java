package com.emergencias.database;

import com.emergencias.model.EmergencyEvent;
import com.emergencias.gps.GPSLocation;
import com.emergencias.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmergencyDAO {

    public void insertEmergency(EmergencyEvent e) {

        String sql = """
            INSERT INTO emergencies 
            (type, user_name, latitude, longitude, status, created_at)
            VALUES (?, ?, ?, ?, ?, NOW())
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getTipo());
            stmt.setString(2, e.getUsuario().getUsername());
            stmt.setDouble(3, e.getGps().getLatitud());
            stmt.setDouble(4, e.getGps().getLongitud());
            stmt.setString(5, "ACTIVE");

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}