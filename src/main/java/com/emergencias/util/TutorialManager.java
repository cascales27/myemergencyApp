package com.emergencias.util;

import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TutorialManager {

    private Map<String, String> tutoriales;

    public TutorialManager(String rutaArchivo) {
        try {

            Gson gson = new Gson();

            FileReader reader = new FileReader(rutaArchivo);

            tutoriales = gson.fromJson(
                    reader,
                    new TypeToken<Map<String, String>>(){}.getType()
            );

        } catch (Exception e) {

            System.out.println("Error cargando tutoriales: " + e.getMessage());

        }
    }

    public String obtenerTutorial(String tipo) {

        if (tutoriales == null) {
            return "No hay tutorial disponible.";
        }

        String tutorial = tutoriales.get(tipo);

        if (tutorial == null) {
            return "No hay tutorial para esta emergencia.";
        }

        return tutorial;
    }
}

