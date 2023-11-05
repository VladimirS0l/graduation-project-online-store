package ru.service.user.usersservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.service.user.usersservice.model.User;
import ru.service.user.usersservice.model.converter.UserConverter;
import ru.service.user.usersservice.model.dto.UserDto;
import ru.service.user.usersservice.model.validation.OnUpdate;
import ru.service.user.usersservice.service.UserService;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Пользователи", description = "Методы для управления данными пользователя")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @Operation(summary = "Обновить пользователя")
    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id,@Validated(OnUpdate.class)
                              @RequestBody UserDto userDto) {
        User user = userConverter.dtoToUser(userDto);
        User userUpdate = userService.update(id, user);
        return userConverter.entityToDto(userUpdate);
    }

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return userConverter.entityToDto(user);
    }

    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
