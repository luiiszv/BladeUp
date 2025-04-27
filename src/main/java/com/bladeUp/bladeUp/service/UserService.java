package com.bladeUp.bladeUp.service;

import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //Busca todo los usarios
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    //Find a user
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    //Save or update one user
    public void saveOrUpdate(User user) {
        userRepository.save(user);

    }

    //Delete user by id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
