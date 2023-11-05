package ru.service.user.usersservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.service.user.usersservice.model.User;
import ru.service.user.usersservice.model.converter.UserConverter;
import ru.service.user.usersservice.model.dto.JwtRequest;
import ru.service.user.usersservice.model.dto.JwtResponse;
import ru.service.user.usersservice.model.dto.UserDto;
import ru.service.user.usersservice.model.validation.OnCreate;
import ru.service.user.usersservice.service.AuthService;
import ru.service.user.usersservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Авторизация", description = "Методы работы с авторизацией")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserConverter userConverter;

    @Operation(summary = "Вход в аккаунт")
    @PostMapping("/login")
    public JwtResponse login(@Validated
                             @RequestBody final JwtRequest loginRequest) {
        System.out.println(loginRequest);
        return authService.login(loginRequest);
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class)
                            @RequestBody final UserDto userDto) {
        System.out.println(userDto);
        User user = userConverter.dtoToUser(userDto);
        System.out.println("UserDtoToUser" + user);
        User createdUser = userService.create(user);
        return userConverter.entityToDto(createdUser);
    }

    @Operation(summary = "Обновить токен")
    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody final String refreshToken) {
        return authService.refresh(refreshToken);
    }

}

