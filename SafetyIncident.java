package src.model;

public class SafetyIncident extends Incident {
    private String severityLevel; // e.g., Low, Medium, High

    public SafetyIncident(String id, String location, String description, IncidentStatus status, String severityLevel) {
        super(id, location, description, status);
        this.severityLevel = severityLevel;
    }

    @Override
    public String getType() {
        return "SAFETY";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + severityLevel;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Severity: " + severityLevel + "]";
    }
}