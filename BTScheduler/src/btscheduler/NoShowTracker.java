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

public class NoShowTracker {
    private LinkedList<Patient> noShowList; // Stores last 5 no-shows

    public NoShowTracker() {
        this.noShowList = new LinkedList<>(FileIOManager.loadNoShows()); // Load from file
    }

    // Get the no-show patients for display
    public List<Patient> getNoShows() {
        return new ArrayList<>(noShowList);
    }
}
