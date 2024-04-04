package ba.unsa.etf.nwt.dto;

import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.ServiceEntity;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class AppointmentDto implements Serializable {


    private Long id;
    @NotNull
    private DentistEntity dentist;

    @NotNull
    private PatientEntity patient;

    @NotNull
    private ServiceEntity service;
    private String note;

    public AppointmentDto(Long id,DentistEntity dentist, PatientEntity patient, ServiceEntity service, String note) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.service = service;
        this.note = note;
    }

    public AppointmentDto() {
    }

    public DentistEntity getDentist() {
        return dentist;
    }

    public void setDentist(DentistEntity dentist) {
        this.dentist = dentist;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
