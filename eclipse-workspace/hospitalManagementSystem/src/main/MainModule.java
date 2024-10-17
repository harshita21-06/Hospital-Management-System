package main;

import dao.HospitalServiceImpl;
import entity.Appointment;
import util.DBConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class MainModule {

    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection();
             Scanner sc = new Scanner(System.in)) {

            HospitalServiceImpl service = new HospitalServiceImpl(connection);

            while (true) {
                System.out.println("\n--- Hospital Appointment System ---");
                System.out.println("1. Schedule Appointment");
                System.out.println("2. Retrieve Appointment by ID");
                System.out.println("3. View Appointments for Patient");
                System.out.println("4. View Appointments for Doctor");
                System.out.println("5. Update Appointment");
                System.out.println("6. Cancel Appointment");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                
                int choice = sc.nextInt();
                sc.nextLine(); 
                
                switch (choice) {
                    case 1:
                       
                        System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        System.out.println("Enter Description: ");
                        String description = sc.nextLine();
                       
                        int patientId = 1;  
                        int doctorId = 1;   
                        
                        Appointment newAppointment = new Appointment(patientId, doctorId, date, description);
                        boolean isScheduled = service.scheduleAppointment(newAppointment);
                        System.out.println("Appointment scheduled: " + isScheduled);
                        break;

                    case 2:
                        System.out.println("Enter Appointment ID: ");
                        int appointmentId = sc.nextInt();
                        Appointment retrievedAppointment = service.getAppointmentById(appointmentId);
                        if (retrievedAppointment != null) {
                            System.out.println("Retrieved Appointment: " + retrievedAppointment.getDescription() + " on " + retrievedAppointment.getAppointmentDate());
                        } else {
                            System.out.println("Appointment not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter Patient ID: ");
                        patientId = sc.nextInt();
                        List<Appointment> patientAppointments = service.getAppointmentsForPatient(patientId);
                        System.out.println("Appointments for Patient ID " + patientId + ":");
                        for (Appointment appointment : patientAppointments) {
                            System.out.println("Appointment ID: " + appointment.getAppointmentId() + ", Description: " + appointment.getDescription());
                        }
                        break;

                    case 4:
                        System.out.println("Enter Doctor ID: ");
                        doctorId = sc.nextInt();
                        List<Appointment> doctorAppointments = service.getAppointmentsForDoctor(doctorId);
                        System.out.println("Appointments for Doctor ID " + doctorId + ":");
                        for (Appointment appointment : doctorAppointments) {
                            System.out.println("Appointment ID: " + appointment.getAppointmentId() + ", Description: " + appointment.getDescription());
                        }
                        break;

                    case 5:
                        System.out.println("Enter Appointment ID to update: ");
                        appointmentId = sc.nextInt();
                        sc.nextLine();
                        Appointment appointmentToUpdate = service.getAppointmentById(appointmentId);
                        if (appointmentToUpdate != null) {
                            System.out.println("Enter new Description: ");
                            description = sc.nextLine();
                            appointmentToUpdate.setDescription(description);
                            boolean isUpdated = service.updateAppointment(appointmentToUpdate);
                            System.out.println("Appointment updated: " + isUpdated);
                        } else {
                            System.out.println("Appointment not found.");
                        }
                        break;

                    case 6:
                        System.out.println("Enter Appointment ID to cancel: ");
                        appointmentId = sc.nextInt();
                        boolean isCancelled = service.cancelAppointment(appointmentId);
                        System.out.println("Appointment cancelled: " + isCancelled);
                        break;

                    case 7:
                        System.out.println("Exiting the system.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
