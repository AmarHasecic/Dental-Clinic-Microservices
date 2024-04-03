package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "dentists")
public class DentistEntity implements Serializable {
    private static final long serialVersionUID = -2731425678149216052L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Work hours must not be blank")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]\\s?-[\\s]?(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid work hours format")
    private String workHours;

    public DentistEntity() {
    }

    // Constructor with workHours parameter
    public DentistEntity(String workHours) {
        this.workHours = workHours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}