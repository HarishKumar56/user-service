package com.epam.rd.controller;

import com.epam.rd.entity.User;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;
import com.epam.rd.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getAllUsers should return all user with ok status")
    public void getAllUserShouldReturnAllUsersWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUser should return user by userName with Ok Status")
    public void getUserShouldReturnUserByUserNameWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/harish")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getUserByUserName should not found status if user not exist")
    public void getUserByUserNameShouldReturnNotFoundStatusIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).getUserByUserName(anyString());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/harish")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("saveUser should save user with ok status")
    public void saveUserShouldSaveUserWithOkStatus() throws Exception {
        User user = new User("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("saveUser should conflict status if user already exist")
    public void saveUserShouldReturnConflictStatusIfUserAlreadyExist() throws Exception {
        doThrow(DuplicateUserException.class).when(userService).saveUser(any());
        User user = new User("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("updateUser should throw exception if user not exist")
    public void updateUserShouldGiveNotFoundStatusIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).updateUser(anyString() ,any());
        User user = new User("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/harish")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
