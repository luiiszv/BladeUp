package com.bladeUp.bladeUp.model.mapper;

import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.model.dto.request.ClientRequestDto;
import com.bladeUp.bladeUp.model.dto.response.ClientResponseDTO;


public class ClientMapper {


    //Mapper de registro
    public Client toEntity(ClientRequestDto dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setAddress(dto.getAddress());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setPreferredServices(dto.getPreferredServices());
        client.setBirthdate(dto.getBirthdate());
        client.setPhoneNumber(dto.getPhoneNumber());

        client.setLoyaltyPoints(0); //"Por defecto punto de fidelidad 0"
        client.setIsActive(true); //"Por defecto activo en true si si si.."

        return client;


    }

    //Mapper de respuesta (No se incluye el address)
    public ClientResponseDTO toDto(Client client) {
        if (client == null) return null;
        return new ClientResponseDTO
                (
                        client.getUserId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getEmail(),
                        client.getLoyaltyPoints(),
                        client.getPreferredServices()

                );

    }


}
