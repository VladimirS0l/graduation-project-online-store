package ru.solarev.api.apiservice.core;

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
    @Schema(description = "Имя получателя", example = "Иванов Иван Иванович")
    @NotBlank(message = "Имя пользователя должно быть заполнено")
    @JsonProperty("full_name")
    private String fullName;

    @Schema(description = "Адрес заказа", example = "614000, г. Пермь, ул. Мира, д.1, кв.1")
    @NotBlank(message = "Адрес должен быть заполнен")
    private String address;

    @Schema(description = "Телефон получателя", example = "+7(900)-800-00-00")
    @NotBlank(message = "Телефон должен быть заполнен")
    @Size(min = 5, message = "Длина номера телефона должна быть не менее 10 символов")
    private String phone;
}
