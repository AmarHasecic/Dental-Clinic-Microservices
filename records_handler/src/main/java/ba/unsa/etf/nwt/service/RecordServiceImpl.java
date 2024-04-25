package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.client.AppointmentClient;
import ba.unsa.etf.nwt.client.UserClient;
import ba.unsa.etf.nwt.dto.AppointmentResponseDto;
import ba.unsa.etf.nwt.dto.RecordRequestDto;
import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.model.RecordEntity;
import ba.unsa.etf.nwt.repository.RecordsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    RecordsRepository recordsRepository;
    AppointmentClient appointmentClient;
    UserClient userClient;
    @Autowired
    public RecordServiceImpl(
            RecordsRepository recordsRepository,
            AppointmentClient appointmentClient,
            UserClient userClient
    ) {
        this.recordsRepository = recordsRepository;
        this.appointmentClient = appointmentClient;
        this.userClient = userClient;
    }

    @Override
    public RecordResponseDto createRecord(RecordRequestDto record) {
        AppointmentResponseDto appointment = appointmentClient.getAppointmentById(record.getAppointmentId());
        if (appointment != null) {
            if (appointment.getPatient().getId() != record.getPatientId()) {
                throw new IllegalArgumentException("Appointment with id: " + record.getAppointmentId() + " was not for patient with id: " + record.getPatientId());
            }
        } else {
            throw new EntityNotFoundException("Unable to find Appointment with id " + record.getAppointmentId());
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RecordEntity recordEntity = modelMapper.map(record, RecordEntity.class);
        recordEntity.setId(Math.abs((new SecureRandom()).nextLong()) % 1000000000L);

        RecordEntity re = recordsRepository.save(recordEntity);
        return modelMapper.map(re, RecordResponseDto.class);
    }

    @Override
    public RecordResponseDto findRecordById(Long patientId, Long recordId) {
        if (userClient.getUserById(patientId) == null) {
            throw new NullPointerException("Patient with id " + patientId + " doesn't exist");
        }
        Optional<RecordEntity> entity = recordsRepository.findById(recordId);
        if (entity.isEmpty()) {
            throw new NullPointerException("Record with id " + recordId + " doesn't exist");
        }
        if (!Objects.equals(entity.get().getPatientId(), patientId)) {
            throw new NullPointerException("Record with id " + recordId + " doesn't exist");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return entity.map(recordEntity -> modelMapper.map(recordEntity, RecordResponseDto.class)).orElse(null);
    }

    @Override
    public List<RecordResponseDto> findRecordsByPatient(Long patientId) {
        if (userClient.getUserById(patientId) == null) {
            throw new NullPointerException("Patient with id " + patientId + " doesn't exist");
        }
        List<RecordEntity> recordsList = recordsRepository.findAllByPatientId(patientId);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return recordsList.stream().map(recordEntity -> modelMapper.map(recordEntity, RecordResponseDto.class)).toList();
    }

    @Override
    public RecordResponseDto updateRecord(RecordRequestDto record) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RecordEntity recordEntity = modelMapper.map(record, RecordEntity.class);
        recordEntity.setId(record.getId());
        if (recordsRepository.findById(record.getId()).isEmpty()) {
            throw new EntityNotFoundException("Unable to find record with id " + record.getId());
        }
        return modelMapper.map(recordsRepository.save(recordEntity), RecordResponseDto.class);
    }

}
