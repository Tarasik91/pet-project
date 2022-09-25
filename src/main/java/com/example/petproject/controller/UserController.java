package com.example.petproject.controller;

import com.example.petproject.exception.UserNotFoundException;
import com.example.petproject.model.User;
import com.example.petproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public Long postUser(@RequestBody User user) {
        User db = userService.save(user);
        return db.getId();
    }

    @PutMapping
    public Long putUser(@RequestBody User user) {
        User db = userService.save(user);
        return db.getId();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
