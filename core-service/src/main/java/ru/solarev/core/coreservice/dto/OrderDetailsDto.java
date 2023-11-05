package ru.solarev.core.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    @Schema(description = "Имя получателя", required = true, example = "Иванов Иван Иванович")
    @NotBlank(message = "Поле имени не должно быть пустым")
    @JsonProperty("full_name")
    private String fullName;
    @Schema(description = "Адрес заказа", required = true, example = "603000, г. Москва, ул. Прямая, д.10, кв.1")
    @NotBlank(message = "Поле адреса не должно быть пустым")
    private String address;
    @Schema(description = "Телефон получателя", required = true, example = "222-22-22")
    @NotBlank(message = "Поле телефона пользователя не должно быть пустым")
    @Size(min = 5, message = "Длина номера телефона должна быть не менее 5 символов")
    private String phone;
}
