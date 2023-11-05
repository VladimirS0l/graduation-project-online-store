package ru.solarev.api.apiservice.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ошибки поиска ресурса")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
