package com.epam.rd.service;

// TODO : getUser should return user by userName
// TODO : getUser should throw exception if user not exist
// TODO : saveUser should save user
// TODO : saveUser should throw exception if user already exist
// TODO : updateUser should update user
// TODO : updateUser should throw exception if user not exist
// TODO : deleteUser should delete user
// TODO : deleteUser should throw exception if user not exist


import com.epam.rd.entity.User;
import com.epam.rd.repository.UserDao;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserDao userDao;


    @Test
    @DisplayName("getAllUsers should return all user")
    public void getAllUserShouldReturnAllUsers(){
        User user = new User("","","");
        when(userDao.findAll()).thenReturn(List.of(user,user));
        Assertions.assertEquals(List.of(user , user) , userService.getAllUsers());
    }
    @Test
    @DisplayName("getUser should return user by userName")
    public void getUserShouldReturnUserByUserName(){
        User user = new User("","","");
        when(userDao.findById(anyString())).thenReturn(java.util.Optional.of(user));
        Assertions.assertEquals(user, userService.getUser(""));
    }
}
