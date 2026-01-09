package model;

public class MaintenanceIncident extends Incident {
    private double estimatedCost;

    public MaintenanceIncident(String id, String location, String description, IncidentStatus status, double estimatedCost) {
        super(id, location, description, status);
        this.estimatedCost = estimatedCost;
    }

    @Override
    public String getType() {
        return "MAINTENANCE";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + estimatedCost;
    }

    @Override
    public String toString() {
        return super.toString() + " [Est. Cost: $" + estimatedCost + "]";
    }
}