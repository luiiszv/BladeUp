package com.bladeUp.bladeUp.service;

import com.bladeUp.bladeUp.interfaces.IClientInterface;
import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientInterface {
    private final ClienteRepository clientRepository;

    @Autowired
    public ClientService(ClienteRepository clienteRepository) {
        this.clientRepository = clienteRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client saveOrUpdateClient(Client cliente) {
        return clientRepository.save(cliente);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id); // 🔥 CORREGIDO
    }
}