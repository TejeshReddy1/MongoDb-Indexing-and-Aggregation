package com.example.mongodb.controller;



import com.example.mongodb.model.User;
import com.example.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        return userService.findUsersByName(name);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return userService.getAverageAge();
    }
}
