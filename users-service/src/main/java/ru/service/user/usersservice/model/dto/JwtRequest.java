package ru.service.user.usersservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Авторизация пользователя")
public class JwtRequest {
    @Schema(description = "Электронная почта пользователя", example = "johndoe@gmail.com")
    @NotNull(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Schema(description = "Пароль пользователя", example = "12345")
    @NotNull(message = "Пароль не должен быть пустым")
    private String password;
}
