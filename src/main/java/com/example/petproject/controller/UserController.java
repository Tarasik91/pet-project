package com.example.petproject.controller;

import com.example.petproject.dto.UserDto;
import com.example.petproject.model.User;
import com.example.petproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public  ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> dtos = userService.findAll();
        return ResponseEntity.ok(dtos);
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
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        boolean isRemoved = userService.delete(user);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

}
