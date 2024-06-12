package ba.unsa.etf.nwt.security;

import ba.unsa.etf.nwt.dto.LoginUserDto;
import ba.unsa.etf.nwt.dto.RegisterDentistDto;
import ba.unsa.etf.nwt.dto.RegisterPatientsDto;
import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.UserEntity;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import ba.unsa.etf.nwt.repository.UsersRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsersRepository userRepository;
    private final DentistsRepository dentistsRepository;
    private final PatientsRepository patientsRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UsersRepository userRepository, DentistsRepository dentistsRepository, PatientsRepository patientsRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.dentistsRepository = dentistsRepository;
        this.patientsRepository = patientsRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public DentistEntity signupDentist(@NotNull RegisterDentistDto input) {
        UserEntity user = new UserEntity();

        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole("DENTIST");
        UserEntity savedUser = userRepository.save(user);

        DentistEntity dentist = new DentistEntity();
        dentist.setId(savedUser.getId());
        dentist.setWorkingHours(input.getWorkingHours());

        return dentistsRepository.save(dentist);
    }

    public PatientEntity signupPatient(@NotNull RegisterPatientsDto input) {
        UserEntity user = new UserEntity();

        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole("PATIENT");
        UserEntity savedUser = userRepository.save(user);


        PatientEntity patient = new PatientEntity();
        patient.setId(savedUser.getId());
        patient.setAddress(input.getAddress());
        patient.setPhone(input.getPhone());
        patient.setGender(input.getGender());
        patient.setBirthDate(input.getBirthDate());

        return patientsRepository.save(patient);
    }

    public UserEntity authenticate(@NotNull LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}