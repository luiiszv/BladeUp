package com.bladeUp.bladeUp.controller;


import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired //Se usa para enlazar cosas creo
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();

    }

    @GetMapping("{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long userId ) {
        return userService.getUser(userId);

    }

    @PostMapping
    public void saveUpdateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

    }

}
