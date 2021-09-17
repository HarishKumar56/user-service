package com.epam.rd.controller;

import com.epam.rd.entity.User;
import com.epam.rd.exception.UserNotFoundException;
import com.epam.rd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userName}")
    public User getUserByUserName(@PathVariable String userName) throws UserNotFoundException {
        return userService.getUserByUserName(userName);
    }




}
