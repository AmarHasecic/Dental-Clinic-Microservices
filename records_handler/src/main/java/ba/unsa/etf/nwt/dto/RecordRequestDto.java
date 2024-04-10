package ba.unsa.etf.nwt.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class RecordRequestDto implements Serializable {
    private Long id;
    @NotNull
    private Long patientId;
    @NotNull
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

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
