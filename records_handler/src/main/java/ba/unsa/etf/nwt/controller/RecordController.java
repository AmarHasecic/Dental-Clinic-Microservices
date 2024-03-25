package ba.unsa.etf.nwt.controller;


import ba.unsa.etf.nwt.dto.RecordDto;
import ba.unsa.etf.nwt.model.RecordCreateRequest;
import ba.unsa.etf.nwt.service.RecordServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/status/check")
    public String status(){
        return "Service is live";
    }

    @PostMapping
    public ResponseEntity<RecordDto> createRecord(@RequestBody RecordCreateRequest request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RecordDto recordDto = modelMapper.map(request, RecordDto.class);
        recordService.createRecord(recordDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{patientId}/{id}")
    public ResponseEntity<RecordDto> findRecordById(@PathVariable Long patientId,@PathVariable Long id){
        RecordDto record = recordService.findRecordById(id);
        if(record != null){
            if(record.getPatient().getId() != patientId){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(record,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{patientId}")
    public List<RecordDto> findRecords(@PathVariable Long patientId){
        return recordService.findRecordsByPatient(patientId);
    }
}
