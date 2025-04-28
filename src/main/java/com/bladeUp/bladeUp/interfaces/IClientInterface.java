package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientInterface {
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    Client saveOrUpdateClient(Client cliente);
    void deleteClientById(Long id);
}