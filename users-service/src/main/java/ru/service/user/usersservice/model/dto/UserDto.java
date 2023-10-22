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
@Schema(description = "User DTO")
public class UserDto implements Serializable {

    @Schema(description = "User name", example = "Vladimir")
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @Schema(description = "User email", example = "test@gmail.com")
    @NotNull(message = "Username must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @Schema(description = "User crypted password", example = "1234")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(description = "User password confirmation", example = "1234")
    @NotNull(message = "Password confirmation must be not null", groups = OnCreate.class)
    private String confirmPassword;
}
