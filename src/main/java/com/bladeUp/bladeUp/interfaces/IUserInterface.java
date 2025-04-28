package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserInterface{
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveOrUpdateUser(User user);
    void deleteUserById(Long id);

    // 🔥 Método para hacer login
    Optional<User> login(String email, String password);
}