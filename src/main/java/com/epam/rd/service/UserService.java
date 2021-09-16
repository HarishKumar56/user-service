package com.epam.rd.service;

import com.epam.rd.entity.User;
import com.epam.rd.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
     List<User> getAllUsers();

     User getUserByUserName(String userName) throws UserNotFoundException;

     void saveUser(User user);
}
