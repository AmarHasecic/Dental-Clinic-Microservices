package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.RecordDto;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.RecordEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import ba.unsa.etf.nwt.repository.RecordsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    RecordsRepository recordsRepository;
    AppointmentsRepository appointmentsRepository;
    PatientsRepository patientsRepository;

    @Autowired
    public RecordServiceImpl(
            RecordsRepository recordsRepository,
            AppointmentsRepository appointmentsRepository,
            PatientsRepository patientsRepository
    ) {
        this.recordsRepository = recordsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.patientsRepository = patientsRepository;
    }

    @Override
    public RecordDto createRecord(RecordDto record) {
        Optional<AppointmentEntity> appointment = appointmentsRepository.findById(record.getAppointment().getId());
        if (appointment.isPresent()) {
            if (appointment.get().getPatient().getId() != record.getPatient().getId()) {
                throw new IllegalArgumentException("Appointment with id: " + record.getAppointment().getId() + " was not for patient with id: " + record.getPatient().getId());
            }
        }else{
            throw new EntityNotFoundException("Unable to find Appointment with id "+record.getAppointment().getId());
        }
        record.setId(Math.abs((new SecureRandom()).nextLong()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RecordEntity recordEntity = modelMapper.map(record, RecordEntity.class);
        System.out.println(recordEntity);
        RecordEntity re = recordsRepository.save(recordEntity);
        return modelMapper.map(re, RecordDto.class);
    }

    @Override
    public RecordDto findRecordById(Long patientId, Long recordId) {
        if(patientsRepository.findById(patientId).isEmpty()){
            throw new NullPointerException("Patient with id "+patientId+" doesn't exist");
        }
        Optional<RecordEntity> entity = recordsRepository.findById(recordId);
        if(entity.isEmpty()){
            throw new NullPointerException("Record with id "+recordId+" doesn't exist");
        }
        if(patientId != entity.get().getPatient().getId()){
            throw new NullPointerException("Record with id "+recordId+" doesn't exist");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return entity.map(recordEntity -> modelMapper.map(recordEntity, RecordDto.class)).orElse(null);
    }

    @Override
    public List<RecordDto> findRecordsByPatient(Long patientId) {
        if(patientsRepository.findById(patientId).isEmpty()){
            throw new NullPointerException("Patient with id "+patientId+" doesn't exist");
        }
        List<RecordEntity> recordsList = recordsRepository.findAllByPatientId(patientId);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return recordsList.stream().map(recordEntity -> modelMapper.map(recordEntity, RecordDto.class)).toList();
    }

    @Override
    public RecordDto updateRecord(RecordDto record) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(recordsRepository.save(modelMapper.map(record, RecordEntity.class)), RecordDto.class);
    }
}
