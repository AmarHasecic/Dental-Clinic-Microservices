package ba.unsa.etf.nwt.controller;


import ba.unsa.etf.nwt.dto.LoginUserDto;
import ba.unsa.etf.nwt.dto.RegisterDentistDto;
import ba.unsa.etf.nwt.dto.RegisterPatientsDto;
import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.UserEntity;
import ba.unsa.etf.nwt.security.LoginResponse;
import ba.unsa.etf.nwt.security.AuthenticationService;
import ba.unsa.etf.nwt.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private JwtService jwtService;

    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailsService userDetailsService;

    public void AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup/dentist")
    public ResponseEntity<DentistEntity> registerDentist(@RequestBody RegisterDentistDto registerDentistDto) {
        DentistEntity registeredUser = authenticationService.signupDentist(registerDentistDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signup/patient")
    public ResponseEntity<PatientEntity> registerPatient(@RequestBody RegisterPatientsDto registerPatientsDto) {
        PatientEntity registeredUser = authenticationService.signupPatient(registerPatientsDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {

        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(token,userDetails)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> checkIfBreathing() {
        return ResponseEntity.status(HttpStatus.OK).body("Alive!");
    }
}