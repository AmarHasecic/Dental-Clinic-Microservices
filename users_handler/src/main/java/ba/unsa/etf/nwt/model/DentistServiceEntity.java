package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "dentistservice")
public class DentistServiceEntity implements Serializable {
    private static final long serialVersionUID = -2731425678149216056L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private DentistEntity dentist;

    // Default constructor
    public DentistServiceEntity() {
    }

    // Constructor with parameters
    public DentistServiceEntity(ServiceEntity service, DentistEntity dentist) {
        this.service = service;
        this.dentist = dentist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public DentistEntity getDentist() {
        return dentist;
    }

    public void setDentist(DentistEntity dentist) {
        this.dentist = dentist;
    }
}

