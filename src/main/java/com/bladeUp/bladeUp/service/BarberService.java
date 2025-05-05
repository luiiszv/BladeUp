package com.bladeUp.bladeUp.service;

import com.bladeUp.bladeUp.interfaces.IBarberInterface;
import com.bladeUp.bladeUp.model.Barber;
import com.bladeUp.bladeUp.model.dto.response.BarberResponseDto;
import com.bladeUp.bladeUp.model.mapper.BarberMapper;
import com.bladeUp.bladeUp.repository.BarberRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BarberService implements IBarberInterface {

    BarberResponseDto barberResponseDto;
    BarberRepository barberRepository;
    BarberMapper barberMapper = new BarberMapper();


    @Override
    public List<BarberResponseDto> getAllBarbers() {
        return barberRepository.findAll().stream().map(barberMapper::toBarberDto).collect(Collectors.toList());
    }
}
