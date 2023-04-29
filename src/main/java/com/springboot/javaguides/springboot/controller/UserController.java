package com.springboot.javaguides.springboot.controller;


import com.springboot.javaguides.springboot.dto.UserDto;
import com.springboot.javaguides.springboot.entity.User;
import com.springboot.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //localhost:8080/api/users/create-user
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUserFromController(@RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //
    @GetMapping("/{id}/get-user")
    public ResponseEntity<UserDto> getUserByIdFromController(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //Get all users API
    //localhost:8080/api/users/get-all-users
    @GetMapping("/get-all-users")
    ResponseEntity<List<UserDto>> getAllUsersFromController(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    //localhost:8080/api/users/2/update-user
    @PutMapping("{id}/update-user")
    ResponseEntity<UserDto> updateUserFromController(@PathVariable("id") Long userId,
                                                  @RequestBody UserDto userDto){
        userDto.setId(userId);
        UserDto updatedDtoUser = userService.updateUserById(userDto);
        return new ResponseEntity<>(updatedDtoUser, HttpStatus.CREATED);
    }

    //localhost:8080/api/users/1/delete-user
    @DeleteMapping("{id}/delete-user")
    public ResponseEntity<String> deleteUserByIdFromController(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

}
























