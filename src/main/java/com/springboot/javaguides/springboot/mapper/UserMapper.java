package com.springboot.javaguides.springboot.mapper;

import com.springboot.javaguides.springboot.dto.UserDto;
import com.springboot.javaguides.springboot.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        UserDto savedUserDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return savedUserDto;
    }

    public static User mapToUser(UserDto userDto){
        User savedUser = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return savedUser;
    }



}
