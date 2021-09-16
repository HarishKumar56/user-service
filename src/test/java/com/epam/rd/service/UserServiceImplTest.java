package com.epam.rd.service;

// TODO : deleteUser should delete user
// TODO : deleteUser should throw exception if user not exist


import com.epam.rd.entity.User;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;
import com.epam.rd.repository.UserDao;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Optional;

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
    public void getUserShouldReturnUserByUserName() throws UserNotFoundException {
        User user = new User("","","");
        when(userDao.findById(anyString())).thenReturn(java.util.Optional.of(user));
        Assertions.assertEquals(user, userService.getUserByUserName(""));
    }

    @Test
    @DisplayName("getUser should throw exception if user not exist")
    public void getUserShouldThrowExceptionIfUserNotExist(){
        User user = new User("","","");
        when(userDao.findById(anyString())).thenReturn(java.util.Optional.ofNullable(null));
        Assertions.assertThrows(UserNotFoundException.class , ()->userService.getUserByUserName(""));
    }

    @Test
    @DisplayName("saveUser should save user")
    public void saveUserShouldSaveUser() throws UserNotFoundException {
        User user = new User("","","");
        Assertions.assertDoesNotThrow(()->userService.saveUser(user));
    }
    @Test
    @DisplayName("saveUser should throw exception if user already exist")
    public void saveUserShouldThrowExceptionIfUserAlreadyExist(){
        User user = new User("","","");
        when(userDao.existsById(anyString())).thenReturn(true);
        Assertions.assertThrows(DuplicateUserException.class ,()->userService.saveUser(user));
    }

    @Test
    @DisplayName("updateUser should throw exception if user not exist")
    public void updateUserShouldThrowExceptionIfUserNotExist(){
        User user = new User("","","");
        when(userDao.findById(anyString())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(UserNotFoundException.class ,()->userService.updateUser(user.getUserName() ,user));
    }
    @Test
    @DisplayName("updateUser should update user")
    public void updateUserShouldUpdateUserIfExist(){
        User user = new User("","","");
        when(userDao.findById(anyString())).thenReturn(Optional.ofNullable(user));
        Assertions.assertDoesNotThrow(()->userService.updateUser(user.getUserName() ,user));
    }

}
