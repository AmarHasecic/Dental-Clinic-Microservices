package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class AppointmentEntity implements Serializable {
    @Id
    private long id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private DentistEntity dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;
    private String note;

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



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
}
