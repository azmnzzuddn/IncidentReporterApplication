package src.data;

import src.model.Incident;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVRepository {
    private final String FILE_PATH = "data/incidents.csv";
    private IncidentFactory factory;

    public CSVRepository() {
        this.factory = new IncidentFactory();
    }

    public void saveIncidents(List<Incident> incidents) throws DataException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Incident i : incidents) {
                writer.println(i.toCSV());
            }
        } catch (IOException e) {
            throw new DataException("Failed to save data: " + e.getMessage());
        }
    }

    public List<Incident> loadIncidents() throws DataException {
        List<Incident> list = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String type = parts[1]; // Type is at index 1 in CSV
                    list.add(factory.createIncident(type, line));
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new DataException("Error loading data: " + e.getMessage());
        }
        return list;
    }
}
