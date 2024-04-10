package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="patients")
public class PatientEntity implements Serializable {


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String address;

    @NotNull
    private Date birthDate;
    @NotNull
    private String phone;
    @NotNull
    @Pattern(regexp = "(Male|Female)", message = "Gender must be 'Male' or 'Female'")
    private String gender;

    public PatientEntity(long id, String address, Date birthDate, String phone, String gender) {
        this.id = id;
        this.address = address;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
    }

    public PatientEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
