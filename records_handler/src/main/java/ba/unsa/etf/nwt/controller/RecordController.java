package ba.unsa.etf.nwt.controller;


//import ba.unsa.etf.nwt.client.AppointmentClient;
import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.dto.RecordRequestDto;
import ba.unsa.etf.nwt.service.RecordServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/records")
public class RecordController {

    public final RecordServiceImpl recordService;
//    public final AppointmentClient appointmentClient;
    @Autowired
    public RecordController(RecordServiceImpl recordService, Environment env) {
        this.recordService = recordService;
        this.env = env;
    }
    public final Environment env;

    @GetMapping("/health/status")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("Live.\nPort: "+env.getProperty("local.server.port"),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecordResponseDto> createRecord(@RequestBody RecordRequestDto request){
        RecordResponseDto recordResponseDto = recordService.createRecord(request);
        return new ResponseEntity<>(recordResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{patientId}/{recordId}")
    public ResponseEntity<RecordResponseDto> findRecordById(@PathVariable Long patientId, @PathVariable Long recordId){
        RecordResponseDto record = recordService.findRecordById(patientId,recordId);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<RecordResponseDto>> findRecords(@PathVariable Long patientId){
        List<RecordResponseDto> recordList = recordService.findRecordsByPatient(patientId);
        if(recordList.isEmpty()){
            return new ResponseEntity<>(recordList,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recordList,HttpStatus.OK);
    }

    @PutMapping("/{patientId}/{recordId}")
    public ResponseEntity<RecordResponseDto> updateRecord(@PathVariable Long patientId, @PathVariable Long recordId, @RequestBody RecordRequestDto request){
        RecordResponseDto recordResponseDto = recordService.updateRecord(request);
        return new ResponseEntity<>(recordResponseDto, HttpStatus.CREATED);
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
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
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
