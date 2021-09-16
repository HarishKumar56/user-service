package com.epam.rd.service;

import com.epam.rd.entity.User;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;
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
    public User getUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userDao.findById(userName);
        if(user.isEmpty()){
            throw new UserNotFoundException("user with this username does not exist");
        }
        return user.get();
    }

    @Override
    public void saveUser(User user) throws DuplicateUserException {
        if(userDao.existsById(user.getUserName())){
            throw new DuplicateUserException("User Already Exist");
        }
        userDao.save(user);
    }

    @Override
    public void updateUser(String userName, User user) throws UserNotFoundException {
        User user1 = getUserByUserName(userName);
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        userDao.save(user1);
    }

    @Override
    public void deleteUser(String userName) throws UserNotFoundException {
        User user = getUserByUserName(userName);
        userDao.delete(user);
    }
}
