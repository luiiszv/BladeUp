package com.bladeUp.bladeUp.service;

import com.bladeUp.bladeUp.interfaces.IClientInterface;
import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.model.dto.request.ClientRequestDto;
import com.bladeUp.bladeUp.model.dto.response.ClientResponseDTO;
import com.bladeUp.bladeUp.model.mapper.ClientMapper;
import com.bladeUp.bladeUp.repository.ClienteRepository;
import com.bladeUp.bladeUp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClientService implements IClientInterface {
    private final ClienteRepository clientRepository;
    private final UserRepository userRepository;

    ClientMapper clientMapper = new ClientMapper();
    PasswordEncoder passwordEncoder;


    //Constructor
    @Autowired
    public ClientService(ClienteRepository clienteRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }


    @Override
    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found: " + id));
        return clientMapper.toDto(client);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);

    }


    @Override
    public boolean existsClientById(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public ClientResponseDTO register(ClientRequestDto dto) {

        Optional<User> existingClient = userRepository.findUserByEmail(dto.getEmail());
        if (existingClient.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese correo.");
        }

        //Encrypted pass
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encryptedPassword);

        Client client = clientMapper.toEntity(dto);
        Client clientSaved = clientRepository.save(client);
        return clientMapper.toDto(clientSaved);
    }


}