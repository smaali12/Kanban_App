package com.kanban.apiGatewayService.api_config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@CrossOrigin
//@Configuration
public class Gateway {
    @Bean
    public RouteLocator getRoute(RouteLocatorBuilder builder){
        return builder.routes().
                route(p->p.path("/api/v1/**")
                        .uri("http://localhost:8081/")).
                route(p->p.path("/api/v2/**")
                        .uri("http://localhost:8082/")).
                route(p->p.path("/api/v3/**")
                        .uri("http://localhost:8083/")).
                build();
    }
}
