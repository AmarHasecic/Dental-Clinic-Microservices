package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto findUserById(Long userId);
    void updateUser(UserDto user);
}
