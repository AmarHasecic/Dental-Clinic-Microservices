package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class AppointmentEntity implements Serializable {

    @Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    @NotNull
    private DentistEntity dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @NotNull
    private ServiceEntity service;

    private String note;

    public AppointmentEntity(long id, Date date, DentistEntity dentist, PatientEntity patient, ServiceEntity service, String note) {
        this.id = id;
        this.date = date;
        this.dentist = dentist;
        this.patient = patient;
        this.service = service;
        this.note = note;
    }

    public AppointmentEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}