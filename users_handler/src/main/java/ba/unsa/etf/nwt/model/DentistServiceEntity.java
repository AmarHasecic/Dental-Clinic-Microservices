package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
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

}

