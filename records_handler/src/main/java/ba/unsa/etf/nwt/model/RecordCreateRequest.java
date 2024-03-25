package ba.unsa.etf.nwt.model;

import java.io.Serializable;

public class RecordCreateRequest implements Serializable {
    private PatientEntity patient;
    private AppointmentEntity appointment;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }
}
