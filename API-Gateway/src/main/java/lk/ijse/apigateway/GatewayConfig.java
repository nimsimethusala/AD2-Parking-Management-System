package lk.ijse.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/v1/user/**", "/api/v1/authenticate")
                        .filters(f -> f.filter(jwtAuthFilter))
                        .uri("lb://user-service"))

                .route("vehicle-service", r -> r.path("/api/v1/vehicles/**")
                        .filters(f -> f.filter(jwtAuthFilter))
                        .uri("lb://vehicle-service"))

                .route("parking-service", r -> r.path("/api/v1/parking/**")
                        .filters(f -> f.filter(jwtAuthFilter))
                        .uri("lb://parking-service"))

                .route("payment-service", r -> r.path("/api/v1/payment/**")
                        .filters(f -> f.filter(jwtAuthFilter))
                        .uri("lb://payment-service"))

                .build();
    }
}
