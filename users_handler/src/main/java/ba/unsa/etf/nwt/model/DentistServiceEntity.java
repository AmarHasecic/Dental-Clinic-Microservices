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


    @Column(name = "service_id")
    private Long service;


    @Column(name = "dentist_id")
    private Long dentist;

    // Default constructor
    public DentistServiceEntity() {
    }

    // Constructor with parameters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public Long getDentist() {
        return dentist;
    }

    public void setDentist(Long dentist) {
        this.dentist = dentist;
    }
}

