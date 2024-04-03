package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.DentistDto;
import ba.unsa.etf.nwt.dto.PatientDto;
import ba.unsa.etf.nwt.dto.UserDto;
import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.UserEntity;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;

import ba.unsa.etf.nwt.repository.UsersRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    PatientsRepository patientsRepository;
    DentistsRepository dentistsRepository;

    @Autowired
    public UserServiceImpl (UsersRepository usersRepository,
    PatientsRepository patientsRepository,
    DentistsRepository dentistsRepository){
        this.usersRepository = usersRepository;
        this.patientsRepository = patientsRepository;
        this.dentistsRepository = dentistsRepository;
    }




    @Override
    public
    UserDto createDentist(DentistDto dentist) {
        dentist.setId(Math.abs((new SecureRandom()).nextLong()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DentistEntity dentistEntity = modelMapper.map(dentist, DentistEntity.class);
        System.out.println(dentist.getId());
        DentistEntity de = dentistsRepository.save(dentistEntity);

        UserDto userDto = new UserDto();
        userDto.setDentistId(de.getId());
        userDto.setId(Math.abs((new SecureRandom()).nextLong()));
        userDto.setFirstName(dentist.getFirstName());
        userDto.setLastName(dentist.getLastName());
        userDto.setMail(dentist.getMail());
        userDto.setPassword(dentist.getPassword());

        userDto.setId(Math.abs((new SecureRandom()).nextLong()));

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity UserEntity = modelMapper.map(userDto, UserEntity.class);
        System.out.println(UserEntity);
        UserEntity ue = usersRepository.save(UserEntity);

        return modelMapper.map(ue, UserDto.class);
    }

    @Override
    public UserDto createPatient(PatientDto patient) {
        patient.setId(Math.abs((new SecureRandom()).nextLong()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PatientEntity patientEntity = modelMapper.map(patient, PatientEntity.class);
        System.out.println(patient.getId());
        PatientEntity pe = patientsRepository.save(patientEntity);

        UserDto userDto = new UserDto();
        userDto.setPatientId(pe.getId());
        userDto.setId(Math.abs((new SecureRandom()).nextLong()));
        userDto.setFirstName(patient.getFirstName());
        userDto.setLastName(patient.getLastName());
        userDto.setMail(patient.getMail());
        userDto.setPassword(patient.getPassword());

        userDto.setId(Math.abs((new SecureRandom()).nextLong()));

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity UserEntity = modelMapper.map(userDto, UserEntity.class);
        System.out.println(UserEntity);
        UserEntity ue = usersRepository.save(UserEntity);

        return modelMapper.map(ue, UserDto.class);
    }

    @Override
    public UserDto findUserById(Long userId) {
        Optional<UserEntity> entity = usersRepository.findById(userId);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return entity.map(recordEntity -> modelMapper.map(recordEntity, UserDto.class)).orElse(null);
    }

    @Override
    public void updateUser(UserDto user) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        usersRepository.save(modelMapper.map(user, UserEntity.class));
    }
}