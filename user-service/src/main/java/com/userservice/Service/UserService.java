package com.userservice.Service;

import com.userservice.Dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto findUserById(Integer id);

    List<UserDto> findAllUsers();

    void deleteUser(Integer id);

    UserDto updateUser(Integer id, UserDto userDto);

    UserDto findUserByName(String username);

}
