package com.bladeUp.bladeUp.service.interfaces;

import com.bladeUp.bladeUp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void saveOrUpdateUser(User user);
    void deleteUserById(Long id);
}