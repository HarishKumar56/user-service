package com.epam.rd.service;

import com.epam.rd.entity.User;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
     List<User> getAllUsers();

     User getUserByUserName(String userName) throws UserNotFoundException;

     void saveUser(User user) throws DuplicateUserException;

     void updateUser(String userName, User user) throws UserNotFoundException;
}
