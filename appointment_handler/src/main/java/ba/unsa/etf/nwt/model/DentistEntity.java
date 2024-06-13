package ba.unsa.etf.nwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "dentists")
public class DentistEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2731425678149216052L;

    @Id
    private long id;

    @NotBlank(message = "Work hours must not be blank")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]\\s?-\\s?(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid work hours format")
    private String workingHours;

    public DentistEntity() {
    }

    public DentistEntity(String workHours) {
        this.workingHours = workHours;
    }

    public DentistEntity(long id, String workHours) {
        this.id = id;
        this.workingHours = workHours;
    }

    @Override
    public java.lang.String toString() {
        return "{id: "+ this.id + ",workHours:"+this.workingHours+"}";
    }

}
