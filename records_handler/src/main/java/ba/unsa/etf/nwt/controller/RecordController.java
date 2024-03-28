package ba.unsa.etf.nwt.controller;


import ba.unsa.etf.nwt.dto.RecordDto;
import ba.unsa.etf.nwt.service.RecordServiceImpl;
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
@RequestMapping("/records")
public class RecordController {

    final
    RecordServiceImpl recordService;

    @Autowired
    public RecordController(RecordServiceImpl recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<RecordDto> createRecord(@RequestBody RecordDto request){
        RecordDto recordDto = recordService.createRecord(request);
        return new ResponseEntity<>(recordDto, HttpStatus.CREATED);
    }

    @GetMapping("/{patientId}/{recordId}")
    public ResponseEntity<RecordDto> findRecordById(@PathVariable Long patientId,@PathVariable Long recordId){
        RecordDto record = recordService.findRecordById(patientId,recordId);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<RecordDto>> findRecords(@PathVariable Long patientId){
        List<RecordDto> recordList = recordService.findRecordsByPatient(patientId);
        if(recordList.isEmpty()){
            return new ResponseEntity<>(recordList,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recordList,HttpStatus.OK);
    }

    @PutMapping("/{patientId/{recordId")
    public ResponseEntity<RecordDto> updateRecord(@PathVariable Long patientId, @PathVariable Long recordId, @RequestBody RecordDto request){
        RecordDto recordDto = recordService.updateRecord(request);
        return new ResponseEntity<>(recordDto, HttpStatus.CREATED);
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
        return new ResponseEntity<>("Record for Appointment already exists",HttpStatus.NOT_ACCEPTABLE);
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
