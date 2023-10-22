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
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public UserDto update(@PathVariable("id") Long id,@Validated(OnUpdate.class)
                              @RequestBody UserDto userDto) {
        User user = userConverter.dtoToUser(userDto);
        User userUpdate = userService.update(id, user);
        return userConverter.entityToDto(userUpdate);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public UserDto getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return userConverter.entityToDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID")
    public void deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
