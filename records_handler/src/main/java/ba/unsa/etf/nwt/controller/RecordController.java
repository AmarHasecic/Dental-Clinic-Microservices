package ba.unsa.etf.nwt.controller;


//import ba.unsa.etf.nwt.client.AppointmentClient;

import ba.unsa.etf.nwt.client.AppointmentClient;
import ba.unsa.etf.nwt.client.RabbitMQSender;
import ba.unsa.etf.nwt.dto.RecordRequestDto;
import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.model.RecordEntity;
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
    public final AppointmentClient appointmentClient;
    public final Environment env;

    public final RabbitMQSender rabbitMQSender;

    @Autowired
    public RecordController(RecordServiceImpl recordService, AppointmentClient appointmentClient, Environment env, RabbitMQSender rabbitMQSender) {
        this.recordService = recordService;
        this.appointmentClient = appointmentClient;
        this.env = env;
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping("/health/status")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Live.\nPort: " + env.getProperty("local.server.port"), HttpStatus.OK);
    }

    @GetMapping("appointments/health/status")
    public ResponseEntity<String> appointmentHealthCheck() {
        return new ResponseEntity<>(appointmentClient.getHealthStatus(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecordResponseDto> createRecord(@RequestBody RecordRequestDto request) {
        RecordResponseDto recordResponseDto = recordService.createRecord(request);
        return new ResponseEntity<>(recordResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{patientId}/{recordId}")
    public ResponseEntity<RecordResponseDto> findRecordById(@PathVariable Long patientId, @PathVariable Long recordId) {
        RecordResponseDto record = recordService.findRecordById(patientId, recordId);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<RecordResponseDto>> findRecords(@PathVariable Long patientId) {
        List<RecordResponseDto> recordList = recordService.findRecordsByPatient(patientId);
        if (recordList.isEmpty()) {
            return new ResponseEntity<>(recordList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recordList, HttpStatus.OK);
    }

    @PutMapping("/{patientId}/{recordId}")
    public ResponseEntity<RecordResponseDto> updateRecord(@PathVariable Long patientId, @PathVariable Long recordId, @RequestBody RecordRequestDto request) {
        RecordResponseDto recordResponseDto = recordService.updateRecord(request);
        return new ResponseEntity<>(recordResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/producemessage")
    public String produceMessage(){
        RecordEntity rec = new RecordEntity();
        rec.setId(1L);
        rec.setImage("path");
        rec.setPatientId(1L);
        rec.setAppointmentId(1L);
        rabbitMQSender.send(rec);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
