package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.RecordRequestDto;
import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
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
    public RecordResponseDto createRecord(RecordRequestDto record) {
        Optional<AppointmentEntity> appointment = appointmentsRepository.findById(record.getAppointmentId());
        if (appointment.isPresent()) {
            if (appointment.get().getPatient().getId() != record.getPatientId()) {
                throw new IllegalArgumentException("Appointment with id: " + record.getAppointmentId() + " was not for patient with id: " + record.getPatientId());
            }
        } else {
            throw new EntityNotFoundException("Unable to find Appointment with id " + record.getAppointmentId());
        }
        RecordEntity recordEntity = createRecordEntityFromRequest(record);
        recordEntity.setId(Math.abs((new SecureRandom()).nextLong()) % 1000000000L);

        RecordEntity re = recordsRepository.save(recordEntity);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(re, RecordResponseDto.class);
    }

    @Override
    public RecordResponseDto findRecordById(Long patientId, Long recordId) {
        if (patientsRepository.findById(patientId).isEmpty()) {
            throw new NullPointerException("Patient with id " + patientId + " doesn't exist");
        }
        Optional<RecordEntity> entity = recordsRepository.findById(recordId);
        if (entity.isEmpty()) {
            throw new NullPointerException("Record with id " + recordId + " doesn't exist");
        }
        if (patientId != entity.get().getPatient().getId()) {
            throw new NullPointerException("Record with id " + recordId + " doesn't exist");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return entity.map(recordEntity -> modelMapper.map(recordEntity, RecordResponseDto.class)).orElse(null);
    }

    @Override
    public List<RecordResponseDto> findRecordsByPatient(Long patientId) {
        if (patientsRepository.findById(patientId).isEmpty()) {
            throw new NullPointerException("Patient with id " + patientId + " doesn't exist");
        }
        List<RecordEntity> recordsList = recordsRepository.findAllByPatientId(patientId);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return recordsList.stream().map(recordEntity -> modelMapper.map(recordEntity, RecordResponseDto.class)).toList();
    }

    @Override
    public RecordResponseDto updateRecord(RecordRequestDto record) {
        RecordEntity recordEntity = createRecordEntityFromRequest(record);
        recordEntity.setId(record.getId());
        if (recordsRepository.findById(record.getId()).isEmpty()) {
            throw new EntityNotFoundException("Unable to find record with id " + record.getId());
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(recordsRepository.save(recordEntity), RecordResponseDto.class);
    }

    private RecordEntity createRecordEntityFromRequest(RecordRequestDto record) {
        RecordEntity recordEntity = new RecordEntity();
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(record.getAppointmentId());
        recordEntity.setAppointment(appointmentEntity);
        recordEntity.setImage(record.getImage());
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(record.getPatientId());
        recordEntity.setPatient(patientEntity);
        return recordEntity;
    }

}
