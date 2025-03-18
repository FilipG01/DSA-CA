/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btscheduler;

import java.util.*;

/**
 *
 * @author Filip
 */

public class BloodTestScheduler {
    private PriorityQueue<Patient> queue; // Priority Queue to manage patient order
    private List<Patient> allPatients; // Keeps track of all patients (for saving to file)
    private Stack<Patient> lastProcessedPatients; // Stack to store last 5 served patients

    public BloodTestScheduler() {
        // Initialize Priority Queue with sorting
        queue = new PriorityQueue<>(new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                // Priority setting Urgent - Medium - Low
                int priorityCompare = p2.getPriority().compareTo(p1.getPriority());
                if (priorityCompare != 0) return priorityCompare;

                // Compare ages, oldest first
                if (p1.getAge() != p2.getAge()) {
                    return Integer.compare(p2.getAge(), p1.getAge()); // Higher age = higher priority
                }

                // Comparison of patient status, hospital patients go before GP patients
                return Boolean.compare(p2.isFromHospital(), p1.isFromHospital());
            }
        });

        // Load patients from file and add to queue
        allPatients = FileIOManager.loadPatients();
        queue.addAll(allPatients);
        lastProcessedPatients = new Stack<>(); // Initialize stack
    }

    // Get all patients in priority order (for displaying in TextArea)
    public List<Patient> getAllPatients() {
        return new ArrayList<>(queue); // Returns priority queue as a list
    }

    // Get the next patient in the queue (without removing)
    public Patient peekNextPatient() {
        return queue.peek();
    }

    // Remove and return the next patient, storing them in the stack
    public Patient getNextPerson() {
        Patient next = queue.poll(); // Remove highest-priority patient from queue

        if (next != null) {
            allPatients.remove(next); // Remove from the list
            FileIOManager.savePatients(allPatients); // Update patients.txt

            // Keep only the last 5 served patients in the stack
            if (lastProcessedPatients.size() == 5) {
                lastProcessedPatients.remove(0); // Remove the oldest served patient
            }
            lastProcessedPatients.push(next); // Add new patient to stack
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

    // Recursively retrieve the last Processed patients
    public String getLastProcessedPatients() {
        return displayStackRecursive(lastProcessedPatients, lastProcessedPatients.size() - 1);
    }

    // Recursive function to format stack contents for display
    private String displayStackRecursive(Stack<Patient> stack, int index) {
        if (index < 0) return ""; // Base case: No more patients
        Patient p = stack.get(index);
        return p.getName() + " - " + p.getPriority() + " - Age: " + p.getAge() + " - "
               + (p.isFromHospital() ? "Hospital" : "GP") + "\n"
               + displayStackRecursive(stack, index - 1); // Recursive call
    }
}