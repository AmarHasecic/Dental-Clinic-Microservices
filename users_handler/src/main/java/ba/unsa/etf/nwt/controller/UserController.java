package ba.unsa.etf.nwt.controller;


import ba.unsa.etf.nwt.dto.DentistDto;
import ba.unsa.etf.nwt.dto.PatientDto;
import ba.unsa.etf.nwt.dto.UserDto;
import ba.unsa.etf.nwt.model.UserEntity;
import ba.unsa.etf.nwt.repository.UsersRepository;
import ba.unsa.etf.nwt.service.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final
    UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public UsersRepository usersRepository;



    @PostMapping("/dentist")
    public ResponseEntity<UserDto> createDentist(@RequestBody DentistDto request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DentistDto dentistDto = modelMapper.map(request, DentistDto.class);
        UserDto userDto = userService.createDentist(dentistDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/patient")
    public ResponseEntity<UserDto> createPatient(@RequestBody PatientDto request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PatientDto patientDto = modelMapper.map(request, PatientDto.class);
        UserDto userDto = userService.createPatient(patientDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUsers(@PathVariable Long userId){
        UserDto user = userService.findUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = (List<UserEntity>) usersRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {

        String message =ex.getMessage();
        message = message.replace("ba.unsa.etf.nwt.model.","");
        message = message.replace("Entity", "");
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>("User with that ID already exists",HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}