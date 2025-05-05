package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.Barber;
import com.bladeUp.bladeUp.model.dto.response.BarberResponseDto;

import java.util.List;

public interface IBarberInterface {
    List<BarberResponseDto> getAllBarbers();
}
