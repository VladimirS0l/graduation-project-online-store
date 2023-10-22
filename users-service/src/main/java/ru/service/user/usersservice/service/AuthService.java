package ru.service.user.usersservice.service;

import ru.service.user.usersservice.model.dto.JwtRequest;
import ru.service.user.usersservice.model.dto.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);
}
