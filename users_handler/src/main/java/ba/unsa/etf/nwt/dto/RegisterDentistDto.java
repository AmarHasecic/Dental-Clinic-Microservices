package ba.unsa.etf.nwt.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDentistDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String workingHours;

}
