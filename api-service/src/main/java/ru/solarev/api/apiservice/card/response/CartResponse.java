package ru.solarev.api.apiservice.card.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class CartResponse {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CartResponse(String value) {
        this.value = value;
    }

    public CartResponse() {
    }
}
