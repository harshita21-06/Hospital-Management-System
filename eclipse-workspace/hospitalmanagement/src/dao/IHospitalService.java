package dao;

import entity.Appointment;
import java.util.List;

public interface IHospitalService {
	
	//a
    Appointment getAppointmentById(int appointmentId);
    
    //b
    List<Appointment> getAppointmentsForPatient(int patientId);
    
    //c
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    
    //d
    boolean scheduleAppointment(Appointment appointment);
    
    //e
    boolean updateAppointment(Appointment appointment);
    
    //f
    boolean cancelAppointment(int appointmentId);
}