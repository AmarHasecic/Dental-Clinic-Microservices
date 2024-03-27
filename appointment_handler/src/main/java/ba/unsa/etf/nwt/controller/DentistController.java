package ba.unsa.etf.nwt.controller;
import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    private final DentistsRepository dentistRepository;

    @Autowired
    public DentistController(DentistsRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @GetMapping
    public ResponseEntity<List<DentistEntity>> getAllDentists() {
        List<DentistEntity> dentists = (List<DentistEntity>) dentistRepository.findAll();
        return ResponseEntity.ok(dentists);
    }



    @PostMapping
    public ResponseEntity<DentistEntity> createDentist(@RequestBody DentistEntity dentist) {
        DentistEntity savedDentist = dentistRepository.save(dentist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDentist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistEntity> updateDentist(@PathVariable long id, @RequestBody DentistEntity updatedDentist) {
        Optional<DentistEntity> existingDentistOptional = dentistRepository.findById(id);
        if (existingDentistOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedDentist.setId(id);
        DentistEntity savedDentist = dentistRepository.save(updatedDentist);
        return ResponseEntity.ok(savedDentist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentist(@PathVariable long id) {
        Optional<DentistEntity> existingDentistOptional = dentistRepository.findById(id);
        if (existingDentistOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dentistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
