package com.emergencias.history;

import com.emergencias.model.EmergencyRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmergencyHistoryManager {

    private static final String FILE_PATH = "emergency_history.json";
    private Gson gson = new Gson();

    public void saveHistory(EmergencyRecord record) {
        List<EmergencyRecord> history = loadHistory();
        if (history == null) {
            history = new ArrayList<>();
        }

        history.add(record);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(history, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EmergencyRecord> loadHistory() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<EmergencyRecord>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}


