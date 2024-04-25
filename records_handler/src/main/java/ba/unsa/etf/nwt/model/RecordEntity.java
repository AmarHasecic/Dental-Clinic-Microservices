package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "records")
public class RecordEntity implements Serializable {
    @Id
    private Long id;

    private Long patientId;

    private Long appointmentId;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patient) {
        this.patientId = patient;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointment) {
        this.appointmentId = appointment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
