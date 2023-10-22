package ru.service.user.usersservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.service.user.usersservice.model.User;
import ru.service.user.usersservice.model.dto.UserDto;
import ru.service.user.usersservice.model.validation.OnUpdate;
import ru.service.user.usersservice.service.UserService;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    @PutMapping
    @Operation(summary = "Update user")
    public UserDto update(@Validated(OnUpdate.class)
                              @RequestBody UserDto userDto) {
        User user = dtoToUser(userDto);
        User userUpdate = userService.update(user);
        return entityToDto(userUpdate);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public UserDto getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return entityToDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID")
    public void deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
    }


    public User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setConfirmPassword(userDto.getConfirmPassword());
        return user;
    }

    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setUsername(userDto.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setConfirmPassword(userDto.getConfirmPassword());
        return userDto;
    }

}
