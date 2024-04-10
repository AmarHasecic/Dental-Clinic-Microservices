package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.DentistDto;
import ba.unsa.etf.nwt.dto.PatientDto;
import ba.unsa.etf.nwt.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createDentist(DentistDto dentist);
    UserDto createPatient(PatientDto patient);
    UserDto findUserById(Long userId);
    void updateUser(UserDto user);
}
