package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @GetMapping
    public ResponseEntity<List<AppointmentEntity>> getAllAppointments() {
        List<AppointmentEntity> appointments = (List<AppointmentEntity>) appointmentsRepository.findAll();
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointment) {
        AppointmentEntity savedAppointment = appointmentsRepository.save(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentEntity> getAppointmentById(@PathVariable Long id) {
        Optional<AppointmentEntity> appointment = appointmentsRepository.findById(id);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentEntity> updateDentist(@PathVariable long id, @RequestBody AppointmentEntity updatedDentist) {
        Optional<AppointmentEntity> existingDentistOptional = appointmentsRepository.findById(id);
        if (existingDentistOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedDentist.setId(id);
        AppointmentEntity savedDentist = appointmentsRepository.save(updatedDentist);
        return ResponseEntity.ok(savedDentist);
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
}
