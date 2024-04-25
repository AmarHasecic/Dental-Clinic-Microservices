package ba.unsa.etf.nwt.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class RecordResponseDto implements Serializable {
    @NotNull
    private Long patient;
    @NotNull
    private Long appointment;
    private Long id;

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    public Long getAppointment() {
        return appointment;
    }

    public void setAppointment(Long appointment) {
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
