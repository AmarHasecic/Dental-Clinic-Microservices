package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.UserDto;
import ba.unsa.etf.nwt.model.UserEntity;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;

import ba.unsa.etf.nwt.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
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
    public UserDto createUser(UserDto user) {
        user.setId(Math.abs((new SecureRandom()).nextLong()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity UserEntity = modelMapper.map(user, UserEntity.class);
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