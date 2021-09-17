package com.epam.rd.controller;

import com.epam.rd.entity.User;
import com.epam.rd.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
        User user = new User("","","");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
