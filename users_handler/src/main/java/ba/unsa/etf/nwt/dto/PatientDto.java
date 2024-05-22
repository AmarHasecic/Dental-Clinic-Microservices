package ba.unsa.etf.nwt.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class PatientDto implements Serializable {

    private Long id;
    private String mail;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phone;
    private String gender;
    private String address;


}
