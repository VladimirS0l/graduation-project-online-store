package ru.solarev.core.coreservice.service.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties()
@Data
public class CartWebClientProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer responseTimeout;
}
