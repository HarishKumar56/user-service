package com.epam.rd.service;

import com.epam.rd.entity.User;

import java.util.List;

public interface UserService {
     List<User> getAllUsers();

     User getUser(String userName);
}
