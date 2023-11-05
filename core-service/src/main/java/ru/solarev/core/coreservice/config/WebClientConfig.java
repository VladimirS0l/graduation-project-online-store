package ru.solarev.core.coreservice.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ru.solarev.core.coreservice.service.web.CartWebClientProperties;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(
        CartWebClientProperties.class
)
@RequiredArgsConstructor
public class WebClientConfig {
    private final CartWebClientProperties cartWebClientProperties;

    @Bean
    @LoadBalanced
    public WebClient.Builder cartServiceWebClient() {
        HttpClient httpClient = reactor.netty.http.client.HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        cartWebClientProperties.getConnectTimeout())
                .responseTimeout(Duration.ofMillis(cartWebClientProperties.getResponseTimeout()))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(cartWebClientProperties.getReadTimeout(),
                                        TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(cartWebClientProperties.getWriteTimeout(),
                                        TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
