package com.springboot.javaguides.springboot.service.impl;

import com.springboot.javaguides.springboot.dto.UserDto;
import com.springboot.javaguides.springboot.entity.User;
import com.springboot.javaguides.springboot.mapper.UserMapper;
import com.springboot.javaguides.springboot.repository.UserRepository;
import com.springboot.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto){
        //convert user Dto to JPA to persist data to DB
//        User user = new User(
//                userDto.getId(),
//                userDto.getFirstName(),
//                userDto.getLastName(),
//                userDto.getEmail()
//        );
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        //convert User JPA entity to UserDTO
//
//        UserDto savedUserDto = new UserDto(
//                savedUser.getId(),
//                savedUser.getFirstName(),
//                savedUser.getLastName(),
//                savedUser.getEmail()
//        );

        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {

        User existingUser = userRepository.findById(id).get();
        return UserMapper.mapToUserDto(existingUser);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDto>  userDtoList= new ArrayList<>();
        for(User user: userList){
            userDtoList.add(UserMapper.mapToUserDto(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUserById(UserDto updatedDtoUser){
        User existingUser = null;
        User updatedUser = UserMapper.mapToUser(updatedDtoUser);
        try {
            existingUser = userRepository.findById(updatedUser.getId()).get();
            System.out.println("found the user id ");
        }
        catch (Exception e){
            System.out.println("User not found exception");
        }

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        userRepository.save(existingUser);

        UserDto userDto = UserMapper.mapToUserDto(existingUser);

        return userDto;

    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
        System.out.println("User deleted");
    }
}
