package com.springboot.javaguides.springboot.service;

import com.springboot.javaguides.springboot.dto.UserDto;
import com.springboot.javaguides.springboot.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUserById(UserDto updatedUser);

    void deleteUserById(Long userId);
}
