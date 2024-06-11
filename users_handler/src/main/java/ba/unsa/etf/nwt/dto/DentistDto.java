package ba.unsa.etf.nwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DentistDto implements Serializable {

    private Long id;
    private String mail;
    private String password;
    private String firstName;
    private String lastName;
    private String workHours;

}
