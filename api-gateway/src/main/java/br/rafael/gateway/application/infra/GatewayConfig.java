package br.rafael.gateway.application.infra;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    
    @Bean
    RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder
            .routes()
                .route(r -> r
                    .path("/api/v1/clients", "/api/v1/clients/**")
                    .uri("lb://client-api"))
            .build();
    }

}
