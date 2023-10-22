package ru.service.user.usersservice.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
//@Schema(description = "Request for login")
public class JwtRequest {
//    @Schema(description = "User email", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not null")
    private String username;

//    @Schema(description = "User password confirmation", example = "12345")
    @NotNull(message = "Password must be not null")
    private String password;
}
