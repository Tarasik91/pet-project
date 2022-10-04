package com.example.petproject.service;

import com.example.petproject.dto.UserDto;
import com.example.petproject.exception.UserNotFoundException;
import com.example.petproject.model.User;
import com.example.petproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean delete(User user) {
        boolean exists = userRepository.existsById(user.getId());
        userRepository.delete(user);
        return exists;
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository
                .findById(id)
                .map(UserDto::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
