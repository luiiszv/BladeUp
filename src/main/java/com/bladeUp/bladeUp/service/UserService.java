package com.bladeUp.bladeUp.service;

import com.bladeUp.bladeUp.exeption.AuthenticationException;
import com.bladeUp.bladeUp.exeption.DisabledException;
import com.bladeUp.bladeUp.exeption.GlobalExceptionHandler;
import com.bladeUp.bladeUp.interfaces.IUserInterface;
import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.model.dto.request.LoginRequestDto;
import com.bladeUp.bladeUp.model.dto.request.LoginResponseDto;
import com.bladeUp.bladeUp.repository.ClienteRepository;
import com.bladeUp.bladeUp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.rmi.server.LogStream.log;

@Service

public class UserService implements IUserInterface {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    //Constructor
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;


    }


    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Transactional(readOnly = true)
    public User login(String email, String password) {
        // Log de entrada (DEBUG)
        log.debug("Validando credenciales para email: {}", email);

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> {
//                    log.warn("Email no registrado: {}", email);
                    return new AuthenticationException("Email not found");
                });

        if (!user.isActive()) {
            throw new DisabledException("The account is deactivated");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            // Log de advertencia (WARN) para contraseña incorrecta
            log.warn("Contraseña incorrecta para usuario: {}", email);
            throw new AuthenticationException("Incorrect Password");
        }

        // Log exitoso (INFO)
        log.info("Autenticación exitosa: {}", email);
        return user;
    }
}