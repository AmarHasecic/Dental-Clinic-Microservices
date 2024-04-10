package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.dto.RecordRequestDto;

import java.util.List;

public interface RecordService {
    RecordResponseDto createRecord(RecordRequestDto record);
    RecordResponseDto findRecordById(Long patientId, Long recordId);
    List<RecordResponseDto> findRecordsByPatient(Long patientId);
    RecordResponseDto updateRecord(RecordRequestDto record);
}
