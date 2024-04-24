package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
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

}