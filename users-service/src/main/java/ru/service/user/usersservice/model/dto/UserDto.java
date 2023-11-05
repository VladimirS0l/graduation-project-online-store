package ru.service.user.usersservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.service.user.usersservice.model.validation.OnCreate;
import ru.service.user.usersservice.model.validation.OnUpdate;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пользователь ДТО")
public class UserDto implements Serializable {

    @Schema(description = "Имя пользователя", example = "Vladimir")
    @NotNull(message = "Имя не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Имя должно быть меньше 255 символов", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @Schema(description = "Электронная почта пользователя", example = "test@gmail.com")
    @NotNull(message = "Электронная почта не заполнена", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Электронная почта должна быть меньше 255 символов", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @Schema(description = "Пароль", example = "1234")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Пароль не должен быть пустым", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(description = "Подтверждение пароля", example = "1234")
    @NotNull(message = "Пароль не должен быть пустым", groups = OnCreate.class)
    private String confirmPassword;
}
