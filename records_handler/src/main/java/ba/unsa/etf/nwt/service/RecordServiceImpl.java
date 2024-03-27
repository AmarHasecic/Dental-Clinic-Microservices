package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.RecordDto;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.RecordEntity;
import ba.unsa.etf.nwt.repository.RecordsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService{
    RecordsRepository recordsRepository;
    @Autowired
    public RecordServiceImpl(RecordsRepository recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    @Override
    public RecordDto createRecord(RecordDto record) {
        record.setId(Long.valueOf(Math.abs((new SecureRandom()).nextLong())));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RecordEntity recordEntity = modelMapper.map(record, RecordEntity.class);
        System.out.println(recordEntity);
        RecordEntity re = recordsRepository.save(recordEntity);
        return modelMapper.map(re,RecordDto.class);
    }

    @Override
    public RecordDto findRecordById(Long id) {
        Optional<RecordEntity> entity = recordsRepository.findById(id);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return entity.map(recordEntity -> modelMapper.map(recordEntity, RecordDto.class)).orElse(null);
    }

    @Override
    public List<RecordDto> findRecordsByPatient(Long patientId) {
        List<RecordEntity> recordsList = recordsRepository.findAllByPatientId(patientId);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return recordsList.stream().map(recordEntity -> modelMapper.map(recordEntity, RecordDto.class)).toList();
    }

    @Override
    public void updateRecord(RecordDto record) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        recordsRepository.save(modelMapper.map(record,RecordEntity.class));
    }
}
