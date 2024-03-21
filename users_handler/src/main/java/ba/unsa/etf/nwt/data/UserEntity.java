package ba.unsa.etf.nwt.data;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216053L;
    @Id
    private long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private char accessLevel;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @OneToOne
    @JoinColumn(name = "dentist_id")
    private DentistEntity dentist;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }

    public char getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(char accessLevel) {
        this.accessLevel = accessLevel;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public DentistEntity getDentist() {
        return dentist;
    }

    public void setDentist(DentistEntity dentist) {
        this.dentist = dentist;
    }
}
