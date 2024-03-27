package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.RecordDto;

import java.util.List;

public interface RecordService {
    RecordDto createRecord(RecordDto record);
    RecordDto findRecordById(Long patientId,Long recordId);
    List<RecordDto> findRecordsByPatient(Long patientId);
    void updateRecord(RecordDto record);
}
