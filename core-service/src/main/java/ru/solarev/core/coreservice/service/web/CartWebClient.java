package ru.solarev.core.coreservice.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.solarev.api.apiservice.card.CartDto;
import ru.solarev.api.apiservice.exceptions.AppError;
import ru.solarev.api.apiservice.exceptions.ResourceNotFoundException;
import ru.solarev.api.apiservice.exceptions.enums.ServiceErrors;

import java.rmi.ServerException;
import java.rmi.server.ServerNotActiveException;

@Component
@EnableConfigurationProperties(
        CartWebClientProperties.class
)
@RequiredArgsConstructor
public class CartWebClient {
    private final static String CLEAR_USER_CART_URI = "/api/v1/carts/0/clear";
    private final static String GET_USER_CART_URI = "/api/v1/carts/0";
    private final WebClient.Builder webClient;
    private final CartWebClientProperties cartWebClientProperties;

    public void clearUserCart(String username) {
        getOnStatus(CLEAR_USER_CART_URI, username)
                .toBodilessEntity()
                .block();
    }

    public CartDto getUserCart(String username) {
        return getOnStatus(GET_USER_CART_URI, username)
                .bodyToMono(CartDto.class)
                .block();
    }

    private WebClient.ResponseSpec getOnStatus(String uri, String username) {
        return webClient
                .baseUrl(cartWebClientProperties.getUrl())
                .build()
                .get()
                .uri(uri)
                .header("username", username)
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(AppError.class).map(
                                body -> {
                                    if (body.getStatusCode().equals(ServiceErrors.NOT_FOUND.name())) {
                                        return new ResourceNotFoundException("Выполнен некорректный запрос");
                                    }
                                    return new ResourceNotFoundException("Выполнен некорректный запрос");
                                }
                        )
                )
                .onStatus(
                        HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        clientResponse -> clientResponse.bodyToMono(AppError.class).map(
                                body -> {
                                    if (body.getStatusCode().equals(ServiceErrors.SERVICE_UNAVAILABLE.name())) {
                                        return new ServerNotActiveException("Выполнен некорректный запрос" +
                                                " к сервису корзин: корзина сломана");
                                    }
                                    return new ServerException("Не выполнено. Внутренняя ошибка сервера.");
                                }
                        )
                );
    }
}
