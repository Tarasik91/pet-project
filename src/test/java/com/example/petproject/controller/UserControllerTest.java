package com.example.petproject.controller;

import com.example.petproject.dto.UserDto;
import com.example.petproject.model.User;
import com.example.petproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Autowired
    private JacksonTester<List<UserDto>> jsonUser;


    @Test
    void returns200ForGetRequest() throws Exception {
        given(userService.findAll())
                .willReturn(createUserList());

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/users/")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println(response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(createUserList()).getJson());
    }

    private UserDto createRobUser() {
        return new UserDto(1L, "Rob", "Mannon", "user");
    }

    private UserDto createBobUser() {
        return new UserDto(1L, "Bob", "User", "user2");
    }

    private List<UserDto> createUserList() {
        return Arrays.asList(createBobUser(), createRobUser());
    }

}