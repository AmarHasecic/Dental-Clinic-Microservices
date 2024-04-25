package ba.unsa.etf.nwt.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class AppointmentResponseDto implements Serializable {

    private Long id;
    @NotNull
    private DentistDto dentist;

    @NotNull
    private PatientDto patient;

    @NotNull
    private Object service;
    private String note;

    public AppointmentResponseDto(Long id, DentistDto dentist, PatientDto patient, ServiceDto service,
                                  String note) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.service = service;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DentistDto getDentist() {
        return dentist;
    }

    public void setDentist(DentistDto dentist) {
        this.dentist = dentist;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
