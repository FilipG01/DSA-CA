/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btscheduler;

/**
 *
 * @author Filip
 */
import java.util.*;

public class BloodTestScheduler {
    private PriorityQueue<Patient> queue; // Priority Queue to manage patient order
    private List<Patient> allPatients; // Keeps track of all patients (for saving to file later)

    public BloodTestScheduler() {
        //Initialize Priority Queue with sorting
        queue = new PriorityQueue<>(new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                // Priority setting Urgent - medium - lwo
                int priorityCompare = p2.getPriority().compareTo(p1.getPriority());
                if (priorityCompare != 0) return priorityCompare;

                // Comapre ages, oldest first
                if (p1.getAge() != p2.getAge()) {
                    return Integer.compare(p2.getAge(), p1.getAge()); // Higher age = higher priority
                }

                // Comparison of patient status, hospital patients go before gp patients
                return Boolean.compare(p2.isFromHospital(), p1.isFromHospital());
            }
        });

        // Load patients from file and add to queue
        allPatients = FileIOManager.loadPatients();
        queue.addAll(allPatients);
    }

    // Get all patients in priority order (for displaying in TextArea)
    public List<Patient> getAllPatients() {
        return new ArrayList<>(queue); // Returns priority queue as a list
    }

    // Get the next patient in the queue
    public Patient peekNextPatient() {
        return queue.peek();
    }

    // Remove and return the next patient (for late application)
    public Patient getNextPerson() {
    Patient next = queue.poll(); // Remove highest-priority patient from queue

    if (next != null) {
        allPatients.remove(next); // Remove from the list
        FileIOManager.savePatients(allPatients); // Update patients.txt
    }
    return next;
}
    
     // Add a new patient to the system
    public void addPerson(String name, String priority, int age, boolean fromHospital, String gp) {
        Patient newPatient = new Patient(name, priority, age, fromHospital, gp);
        queue.add(newPatient); // Add to priority queue
        allPatients.add(newPatient); // Add to list for saving
        FileIOManager.savePatients(allPatients); // Save updated list to file
    }
}