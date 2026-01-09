package src.data;

import src.model.*;

public class IncidentFactory {

    // Creates an Incident from a CSV line OR raw data inputs formatted as a string
    public Incident createIncident(String type, String csvLine) {
        String[] parts = csvLine.split(",");
        
        // Expected Common Parts: ID, TYPE, LOC, DESC, STATUS ... (Extras)
        // If creating new from UI, we might construct a temporary CSV string or overload this.
        // For this design, we assume inputs come in as a comma-separated string.

        String id = parts[0];
        // parts[1] is type, redundant here but part of CSV
        String loc = parts[2];
        String desc = parts[3];
        IncidentStatus status = IncidentStatus.valueOf(parts[4]);

        if (type.equalsIgnoreCase("SAFETY")) {
            String severity = parts[5];
            return new SafetyIncident(id, loc, desc, status, severity);
        } else if (type.equalsIgnoreCase("MAINTENANCE")) {
            double cost = Double.parseDouble(parts[5]);
            return new MaintenanceIncident(id, loc, desc, status, cost);
        }
        
        throw new IllegalArgumentException("Unknown Incident Type: " + type);
    }
}