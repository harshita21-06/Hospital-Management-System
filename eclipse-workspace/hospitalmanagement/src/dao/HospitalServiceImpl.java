package dao;

import entity.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements IHospitalService {

    private Connection connection;

    public HospitalServiceImpl(Connection connection) {
        this.connection = connection;
    }

    // a
    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                appointment = new Appointment(
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
                appointment.setAppointmentId(rs.getInt("appointmentId"));
            }
        } catch (SQLException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        return appointment;
    }

    // b
    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment WHERE patientId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
                appointment.setAppointmentId(rs.getInt("appointmentId"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        return appointments;
    }

    // c
    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment WHERE doctorId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
                appointment.setAppointmentId(rs.getInt("appointmentId"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        return appointments;
    }

    // d
    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        String query = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientId()); 
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getDescription());
            
            int rowsInserted = stmt.executeUpdate();
            
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedAppointmentId = generatedKeys.getInt(1);
                        appointment.setAppointmentId(generatedAppointmentId);
                        System.out.println("Appointment scheduled with ID: " + generatedAppointmentId);
                    }
                }
            }
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    // e
    @Override
    public boolean updateAppointment(Appointment appointment) {
        String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getDescription());
            stmt.setInt(5, appointment.getAppointmentId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    // f
    @Override
    public boolean cancelAppointment(int appointmentId) {
        String query = "DELETE FROM Appointment WHERE appointmentId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
