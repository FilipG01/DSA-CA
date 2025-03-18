/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package btscheduler;
import java.util.List;
import btscheduler.BloodTestScheduler; 
import btscheduler.Patient; 

/**
 *
 * @author Filip
 */
public class SchedulerGUI extends javax.swing.JFrame {
    private BloodTestScheduler scheduler;
    private NoShowTracker noShowTracker;
    private AddPatientForm addPatientForm; // Store instance of Joption pane

    
    public SchedulerGUI() {
    scheduler = new BloodTestScheduler(); // Initialize scheduler
    noShowTracker = new NoShowTracker();// Initialize no-show tracker
    addPatientForm = new AddPatientForm(this); // Initialize Joption pane but keep it hidden
    
    initComponents();
    
    displayAllPatients(); // Load and display all patients when project is ran
    displayNextPatient(); // Show highest-priority patient in NextPatientTA
}
    
    public void displayAllPatients() {
    List<Patient> patients = scheduler.getAllPatients(); // Get patients from priority queue
    StringBuilder sb = new StringBuilder();

    for (Patient p : patients) {
        sb.append(p.getName()).append(" - ")
          .append(p.getPriority()).append(" - ")
          .append("Age: ").append(p.getAge()).append(" - ")
          .append(p.isFromHospital() ? "Hospital" : "GP").append("\n");
    }

    // Update AllPatientsTA TextArea
    AllPatientsTA.setText(sb.toString());
}
    
    public void displayNextPatient() {
    Patient next = scheduler.peekNextPatient(); // Get highest-priority patient

    if (next != null) {
        // Display patient details in NextPatientTA
        NextPatientTA.setText(next.getName() + " - " + next.getPriority() + 
            " - Age: " + next.getAge() + " - " + 
            (next.isFromHospital() ? "Hospital" : "GP"));
    } else {
        NextPatientTA.setText("No patients in queue.");
    }
}
    
    private void displayNoShows() {
    List<Patient> noShows = noShowTracker.getNoShows(); // Get no-show patients
    StringBuilder sb = new StringBuilder();

    for (Patient p : noShows) {
        sb.append(p.getName()).append(" - ")
          .append(p.getPriority()).append(" - ")
          .append("Age: ").append(p.getAge()).append(" - ")
          .append(p.isFromHospital() ? "Hospital" : "GP").append("\n");
    }

    // Update AllPatientsTA textarea
    AllPatientsTA.setText(sb.length() > 0 ? sb.toString() : "No no-show patients.");
}
    
    private void displayLastProcessedPatients() {
    String ProcessedPatients = scheduler.getLastProcessedPatients();
    if (ProcessedPatients.isEmpty()) {
        AllPatientsTA.setText("No patients have been Processed yet.");
    } else {
        AllPatientsTA.setText("Last Patients Processed:\n" + ProcessedPatients);
    }
}
    
    private void processNextPatient() {
    Patient removedPatient = scheduler.getNextPerson(); // Remove highest-priority patient

    if (removedPatient != null) {
        // Display next patient (if available)
        displayNextPatient();

        // Update patient list in AllPatientsTA
        displayAllPatients();
    } else {
        // If no patients are left, show a message
        NextPatientTA.setText("No more patients in queue.");
    }
}
    
    private void openAddPatientForm() {
    addPatientForm.setVisible(true); // Make pop-up window visible when button is clicked
}
    
    public BloodTestScheduler getScheduler() {
    return scheduler;
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        TitleLBL = new javax.swing.JLabel();
        Sep1 = new javax.swing.JSeparator();
        NextPatientLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        NextPatientTA = new javax.swing.JTextArea();
        AllPatientsLBL = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AllPatientsTA = new javax.swing.JTextArea();
        NextPatientBTN = new javax.swing.JButton();
        ShowAllPatientsBTN = new javax.swing.JButton();
        ShowNoShowBTN = new javax.swing.JButton();
        AddPatientBTN = new javax.swing.JButton();
        LastProcessedPatientsBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 196, 196));

        MainPanel.setBackground(new java.awt.Color(255, 204, 204));

        TitleLBL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TitleLBL.setText("Blood Test Scheduler");

        Sep1.setBackground(new java.awt.Color(0, 0, 0));
        Sep1.setForeground(new java.awt.Color(255, 255, 255));
        Sep1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        NextPatientLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NextPatientLBL.setText("Next Patient:");

        NextPatientTA.setEditable(false);
        NextPatientTA.setColumns(20);
        NextPatientTA.setRows(5);
        jScrollPane1.setViewportView(NextPatientTA);

        AllPatientsLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AllPatientsLBL.setText("All Patients:");

        AllPatientsTA.setEditable(false);
        AllPatientsTA.setColumns(20);
        AllPatientsTA.setRows(5);
        jScrollPane2.setViewportView(AllPatientsTA);

        NextPatientBTN.setText("Patient Processed");
        NextPatientBTN.setToolTipText("Deletes Selected Patient");
        NextPatientBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPatientBTNActionPerformed(evt);
            }
        });

        ShowAllPatientsBTN.setText("Show All Patients");
        ShowAllPatientsBTN.setToolTipText("Displays all users in pqueue");
        ShowAllPatientsBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllPatientsBTNActionPerformed(evt);
            }
        });

        ShowNoShowBTN.setText("Show last 5 No Shows");
        ShowNoShowBTN.setToolTipText("Displays the last 5 no show patients");
        ShowNoShowBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowNoShowBTNActionPerformed(evt);
            }
        });

        AddPatientBTN.setText("Add Patient");
        AddPatientBTN.setToolTipText("Opens Add Patient window");
        AddPatientBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPatientBTNActionPerformed(evt);
            }
        });

        LastProcessedPatientsBTN.setText("Show last processed patients");
        LastProcessedPatientsBTN.setToolTipText("Shows last processes patients");
        LastProcessedPatientsBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastProcessedPatientsBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sep1))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ShowAllPatientsBTN)
                            .addComponent(ShowNoShowBTN)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(NextPatientBTN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AddPatientBTN))
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NextPatientLBL, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AllPatientsLBL, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(LastProcessedPatientsBTN))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGap(0, 135, Short.MAX_VALUE)
                .addComponent(TitleLBL)
                .addGap(127, 127, 127))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(TitleLBL)
                .addGap(18, 18, 18)
                .addComponent(Sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(NextPatientLBL)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPatientBTN)
                    .addComponent(AddPatientBTN))
                .addGap(25, 25, 25)
                .addComponent(AllPatientsLBL)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ShowAllPatientsBTN)
                .addGap(18, 18, 18)
                .addComponent(ShowNoShowBTN)
                .addGap(18, 18, 18)
                .addComponent(LastProcessedPatientsBTN)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowNoShowBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowNoShowBTNActionPerformed
        displayNoShows();//displays no show patients
    }//GEN-LAST:event_ShowNoShowBTNActionPerformed

    private void ShowAllPatientsBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllPatientsBTNActionPerformed
        displayAllPatients();//displays all patients
    }//GEN-LAST:event_ShowAllPatientsBTNActionPerformed

    private void NextPatientBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPatientBTNActionPerformed
        processNextPatient();//removes currently displayed patient
    }//GEN-LAST:event_NextPatientBTNActionPerformed

    private void AddPatientBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPatientBTNActionPerformed
        openAddPatientForm();//opens joption pane for adding patient details to txtfile and program
    }//GEN-LAST:event_AddPatientBTNActionPerformed

    private void LastProcessedPatientsBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastProcessedPatientsBTNActionPerformed
        displayLastProcessedPatients();//Displays last patients to be processed (removed)
    }//GEN-LAST:event_LastProcessedPatientsBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchedulerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPatientBTN;
    private javax.swing.JLabel AllPatientsLBL;
    private javax.swing.JTextArea AllPatientsTA;
    private javax.swing.JButton LastProcessedPatientsBTN;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton NextPatientBTN;
    private javax.swing.JLabel NextPatientLBL;
    private javax.swing.JTextArea NextPatientTA;
    private javax.swing.JSeparator Sep1;
    private javax.swing.JButton ShowAllPatientsBTN;
    private javax.swing.JButton ShowNoShowBTN;
    private javax.swing.JLabel TitleLBL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
