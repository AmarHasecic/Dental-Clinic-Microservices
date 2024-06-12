package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.dto.UserDto;
import ba.unsa.etf.nwt.feign.AppointmentInterface;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import ba.unsa.etf.nwt.util.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private AppointmentInterface appointmentInterface;

    @GetMapping("/health/status")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("Live.",HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<AppointmentEntity>> getAllAppointments() {
        List<AppointmentEntity> appointments = (List<AppointmentEntity>) appointmentsRepository.findAll();
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@Valid @RequestBody AppointmentEntity appointment) {


        //check if dentist still exists in dentists database
        UserDto dentist = appointmentInterface.findUsers(appointment.getDentist().getId()).getBody();
        if(dentist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Dentist not found", "Dentist does not exist in dentists database"));
        }

        //check if patient still exists in dentists database
        UserDto patient = appointmentInterface.findUsers(appointment.getPatient().getId()).getBody();
        if(patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Patient not found", "Patient does not exist in patients database"));
        }

        try {
            AppointmentEntity savedAppointment = appointmentsRepository.save(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error creating appointment", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentEntity updatedAppointment) {
        try {
            Optional<AppointmentEntity> existingAppointment = appointmentsRepository.findById(id);
            if (existingAppointment.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Appointment not found", "Appointment with id: " + id + " not found"));
            }
            updatedAppointment.setId(id);
            AppointmentEntity savedAppointment = appointmentsRepository.save(updatedAppointment);
            return ResponseEntity.ok(savedAppointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error updating appointment", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        Optional<AppointmentEntity> existingAppointment = appointmentsRepository.findById(id);
        if (existingAppointment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Appointment not found", "Appointment with id: " + id + " not found"));
        }
        return ResponseEntity.ok(existingAppointment.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        Optional<AppointmentEntity> optionalAppointment = appointmentsRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            appointmentsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/myappointments/{id}")
    public ResponseEntity<?> getMyAppointments(@PathVariable Long id) {
        try {

            List<AppointmentEntity> allAppointments = StreamSupport.stream(appointmentsRepository.findAll().spliterator(), false)
                    .toList();

            List<AppointmentEntity> patientAppointments = allAppointments.stream()
                    .filter(appointment -> appointment.getPatient().getId() == id)
                    .toList();

            if (patientAppointments.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(patientAppointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error retrieving appointments", e.getMessage()));
        }
    }


}
