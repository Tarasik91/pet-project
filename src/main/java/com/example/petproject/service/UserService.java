package com.example.petproject.service;

import com.example.petproject.dto.UserDto;
import com.example.petproject.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    User save(User user);

    boolean delete(User user);

    UserDto findById(Long id);
}
