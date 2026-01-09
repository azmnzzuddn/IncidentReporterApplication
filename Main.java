package src;

import src.data.*;
import src.model.*;
import java.util.*;

public class Main {
    private static CSVRepository repo = new CSVRepository();
    private static List<Incident> incidents = new ArrayList<>();

    public static void main(String[] args) {
        try {
            incidents = repo.loadIncidents();
        } catch (DataException e) {
            System.out.println("No previous data found or load error.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Community Reporter ---");
            System.out.println("1. Report Safety Incident");
            System.out.println("2. Report Maintenance Incident");
            System.out.println("3. List All Incidents");
            System.out.println("4. Exit");
            System.out.print("Select: ");
            String choice = scanner.nextLine();

            if (choice.equals("4")) {
                try {
                    repo.saveIncidents(incidents);
                    System.out.println("Data saved. Goodbye.");
                } catch (DataException e) {
                    System.out.println("Error saving: " + e.getMessage());
                }
                break;
            }

            handleInput(choice, scanner);
        }
    }

    private static void handleInput(String choice, Scanner scanner) {
        if (choice.equals("3")) {
            incidents.forEach(System.out::println);
            return;
        }

        System.out.print("Location: ");
        String loc = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        String id = String.valueOf(System.currentTimeMillis() % 10000); // Simple ID generation
        
        // Constructing a temporary CSV line to pass to Factory (Strict UML adherence)
        // Format: id,TYPE,loc,desc,OPEN,extra
        
        IncidentFactory factory = new IncidentFactory();
        Incident newIncident = null;

        if (choice.equals("1")) {
            System.out.print("Severity (Low/Med/High): ");
            String sev = scanner.nextLine();
            String rawData = id + ",SAFETY," + loc + "," + desc + ",OPEN," + sev;
            newIncident = factory.createIncident("SAFETY", rawData);

        } else if (choice.equals("2")) {
            System.out.print("Est. Cost: ");
            String cost = scanner.nextLine();
            String rawData = id + ",MAINTENANCE," + loc + "," + desc + ",OPEN," + cost;
            newIncident = factory.createIncident("MAINTENANCE", rawData);
        }

        if (newIncident != null) {
            incidents.add(newIncident);
            System.out.println("Incident Logged!");
        }
    }
}