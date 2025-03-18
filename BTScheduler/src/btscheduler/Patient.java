/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btscheduler;

/**
 *
 * @author Filip
 */
import java.io.Serializable;

public class Patient implements Serializable {
    private String name;
    private String priority;
    private int age;
    private boolean fromHospital;
    private String gp;

    // Constructor class
    public Patient(String name, String priority, int age, boolean fromHospital, String gp) {
        this.name = name;
        this.priority = priority;
        this.age = age;
        this.fromHospital = fromHospital;
        this.gp = gp;
    }

    // Converts Patient object to a String for saving to file
    public String toFileString() {
        return name + "," + priority + "," + age + "," + fromHospital + "," + gp;
    }

    // Parses a line from file and converts it back into a Patient object
    public static Patient fromFileString(String line) {
        String[] data = line.split(",");
        return new Patient(
            data[0], 
            data[1], 
            Integer.parseInt(data[2]), 
            Boolean.parseBoolean(data[3]), 
            data[4]
        );
    }

    // Getters for vars
    public String getName() { return name; }
    public String getPriority() { return priority; }
    public int getAge() { return age; }
    public boolean isFromHospital() { return fromHospital; }
    public String getGp() { return gp; }

    // Override toString() for debugging
    @Override
    public String toString() {
        return name + " - " + priority + " - Age: " + age + " - " + (fromHospital ? "Hospital" : "GP");
    }
}