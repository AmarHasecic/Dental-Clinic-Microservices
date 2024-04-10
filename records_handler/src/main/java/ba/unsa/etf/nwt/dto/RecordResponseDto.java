package ba.unsa.etf.nwt.dto;

import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class RecordResponseDto implements Serializable {
    @NotNull
    private PatientEntity patient;
    @NotNull
    private AppointmentEntity appointment;
    private String image;
    private Long id;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
