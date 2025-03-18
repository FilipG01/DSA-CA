/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btscheduler;

/**
 *
 * @author Filip
 */
import java.io.*;
import java.util.*;

public class FileIOManager {
    private static final String FILE_PATH = "src/btscheduler/patientInfo/patients.txt";
    private static final String NOSHOW_FILE_PATH = "src/btscheduler/patientInfo/noshow.txt";

    //Load patients from file
    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("patients.txt not found.");
            return patients; // Return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patients.add(Patient.fromFileString(line)); // Convert text to Patient object
                System.out.println("Loaded patient: " + line); // Debugging output
            }
        } catch (IOException e) {
            System.err.println("Error loading patient data: " + e.getMessage());
        }
        return patients;
    }

    //Save patients to file (To be used later)
    public static void savePatients(List<Patient> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Patient patient : patients) {
                writer.write(patient.toFileString()); // Convert Patient object to text format
                writer.newLine();
            }
            System.out.println("Patients saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving patient data: " + e.getMessage());
        }
    }
    
    // Load No-Show Patients
    public static List<Patient> loadNoShows() {
        List<Patient> noShows = new ArrayList<>();
        File file = new File(NOSHOW_FILE_PATH);

        if (!file.exists()) {
            System.out.println("noshow.txt not found");
            return noShows;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(NOSHOW_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                noShows.add(Patient.fromFileString(line)); // Convert text to Patient object
            }
        } catch (IOException e) {
            System.err.println("Error loading no-show data: " + e.getMessage());
        }
        return noShows;
    }
}