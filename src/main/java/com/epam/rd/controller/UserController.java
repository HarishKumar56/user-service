package com.epam.rd.controller;

import com.epam.rd.dto.UserDto;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;
import com.epam.rd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userName}")
    public UserDto getUserByUserName(@PathVariable String userName) throws UserNotFoundException {
        return userService.getUserByUserName(userName);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/users")
    public void saveUser(@RequestBody UserDto userDto) throws  DuplicateUserException {
        userService.saveUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{userName}")
    public void updateUser(@PathVariable String userName ,@RequestBody UserDto userDto) throws  UserNotFoundException {
        userService.updateUser(userName,userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable String userName) throws  UserNotFoundException {
        userService.deleteUser(userName);
    }




}
