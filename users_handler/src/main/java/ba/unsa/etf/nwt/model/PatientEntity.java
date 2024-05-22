package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="patients")
public class PatientEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2731425678149216052L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Date birthDate;
    private String phone;
    private String gender;
}
