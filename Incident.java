package src.model;

public abstract class Incident {
    protected String id;
    protected String location;
    protected String description;
    protected IncidentStatus status;

    public Incident(String id, String location, String description, IncidentStatus status) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.status = status;
    }

    // Abstract Method (Polymorphism)
    public abstract String getType();

    // Common CSV formatting
    public String toCSV() {
        return id + "," + getType() + "," + location + "," + description + "," + status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s at %s (%s)", id, getType(), description, location, status);
    }
    
    // Getters
    public String getId() { return id; }
    public IncidentStatus getStatus() { return status; }
}