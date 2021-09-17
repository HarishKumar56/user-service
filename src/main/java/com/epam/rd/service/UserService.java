package com.epam.rd.service;

import com.epam.rd.dto.UserDto;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
     List<UserDto> getAllUsers();

     UserDto getUserByUserName(String userName) throws UserNotFoundException;

     void saveUser(UserDto user) throws DuplicateUserException;

     void updateUser(String userName, UserDto user) throws UserNotFoundException;

     void deleteUser(String userName) throws UserNotFoundException;
}
