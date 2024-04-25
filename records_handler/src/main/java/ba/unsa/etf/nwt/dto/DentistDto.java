package ba.unsa.etf.nwt.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class DentistDto implements Serializable {
    @NotNull
    private long id;

    @NotNull
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9] - ([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid work hours format")
    private String workHours;

    public DentistDto() {
    }

    public DentistDto(long id, String workHours) {
        this.id = id;
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
