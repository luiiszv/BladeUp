package com.bladeUp.bladeUp.service;

import com.bladeUp.bladeUp.interfaces.IClientInterface;
import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClientInterface {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Client saveOrUpdateClient(Client cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteClientById(Long id) {
        clienteRepository.deleteById(id); // 🔥 CORREGIDO
    }
}