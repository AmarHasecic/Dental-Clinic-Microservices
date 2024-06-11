package ba.unsa.etf.nwt.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPatientsDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phone;
    private String gender;
    private String address;
}
