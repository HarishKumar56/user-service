package com.epam.rd.service;

import com.epam.rd.entity.User;
import com.epam.rd.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(String userName) {
        Optional<User> user = userDao.findById(userName);
        return user.get();
    }
}
