package entity;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
    private String description;

    public Appointment() {}

    public Appointment(int patientId, int doctorId, String appointmentDate, String description) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public int getAppointmentId() { 
    	return appointmentId; 
    }
    public void setAppointmentId(int appointmentId) { 
    	this.appointmentId = appointmentId; 
    }
    public int getPatientId() { 
    	return patientId; 
    }
    public void setPatientId(int patientId) { 
    	this.patientId = patientId; 
    }
    public int getDoctorId() { 
    	return doctorId; 
    }
    public void setDoctorId(int doctorId) { 
    	this.doctorId = doctorId; 
    }
    public String getAppointmentDate() { 
    	return appointmentDate; 
    }
    public void setAppointmentDate(String appointmentDate) { 
    	this.appointmentDate = appointmentDate; 
    }
    public String getDescription() { 
    	return description; 
    }
    public void setDescription(String description) { 
    	this.description = description; 
    }
}
