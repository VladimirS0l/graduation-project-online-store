package ru.solarev.api.apiservice.core;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String pathname;
    private BigDecimal price;
}
