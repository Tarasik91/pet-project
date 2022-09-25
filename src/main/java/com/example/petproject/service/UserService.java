package com.example.petproject.service;

import com.example.petproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User save(User user);

    void delete(User user);

    Optional<User> findById(Long id);
}
