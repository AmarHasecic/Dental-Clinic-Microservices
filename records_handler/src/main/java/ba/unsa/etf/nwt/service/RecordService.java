package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.RecordDto;

import java.util.List;

public interface RecordService {
    RecordDto createRecord(RecordDto record);
    RecordDto findRecordById(Long id);
    List<RecordDto> findRecordsByPatient(Long patientId);
    void updateRecord(RecordDto record);
}
