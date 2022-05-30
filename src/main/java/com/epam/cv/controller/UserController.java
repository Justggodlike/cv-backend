package com.epam.cv.controller;

import com.epam.cv.entity.User;
import com.epam.cv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @CrossOrigin
    @GetMapping("/user/me")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

}
