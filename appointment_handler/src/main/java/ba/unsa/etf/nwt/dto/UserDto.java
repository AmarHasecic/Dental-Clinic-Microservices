package ba.unsa.etf.nwt.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserDto implements Serializable {

    private Long id;
    private String mail;
    private String password;
    private String firstName;
    private String lastName;
    private Long patientId;
    private Long dentistId;

}
