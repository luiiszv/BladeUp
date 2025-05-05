package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.dto.request.ClientRequestDto;
import com.bladeUp.bladeUp.model.dto.response.ClientResponseDTO;

import java.util.List;

public interface IClientInterface {
    List<ClientResponseDTO> getAllClients();
    ClientResponseDTO getClientById(Long id);
    void deleteClientById(Long id);
    boolean existsClientById(Long id);

    ClientResponseDTO register(ClientRequestDto dto);
}