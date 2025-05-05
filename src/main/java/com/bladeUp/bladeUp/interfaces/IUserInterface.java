package com.bladeUp.bladeUp.interfaces;

import com.bladeUp.bladeUp.model.User;
import com.bladeUp.bladeUp.model.dto.request.LoginRequestDto;
import com.bladeUp.bladeUp.model.dto.request.LoginResponseDto;

public interface IUserInterface {


    User login(String email, String password);
}