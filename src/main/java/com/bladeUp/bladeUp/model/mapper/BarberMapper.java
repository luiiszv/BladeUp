package com.bladeUp.bladeUp.model.mapper;

import com.bladeUp.bladeUp.model.Barber;
import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.model.dto.request.BarberRequestDto;
import com.bladeUp.bladeUp.model.dto.request.ClientRequestDto;
import com.bladeUp.bladeUp.model.dto.response.BarberResponseDto;
import com.bladeUp.bladeUp.model.dto.response.ClientResponseDTO;

public class BarberMapper {

    //Mapper de registro
    public Barber toBarberEntity(BarberRequestDto dto) {
        if (dto == null) return null;

        Barber barber = new Barber();
        barber.setFirstName(dto.getFirstName());
        barber.setLastName(dto.getLastName());
        barber.setShopAddress(dto.getShopAddress());
        barber.setEmail(dto.getEmail());
        barber.setPassword(dto.getPassword());
        barber.setShopAddress(dto.getShopAddress());
        barber.setBirthdate(dto.getBirthdate());
        barber.setPhoneNumber(dto.getPhoneNumber());

        return barber;


    }

    //Mapper de respuesta (No se incluye el address)
    public BarberResponseDto toBarberDto(Barber barber) {
        if (barber == null) return null;
        return new BarberResponseDto(
                barber.getUserId(),
                barber.getFirstName(),
                barber.getLastName(),
                barber.getShopAddress(),
                barber.getYearsExperience(),
                barber.getStartTime(),
                barber.getEndTime()

        );

    }
}
