package de.hs_bremen.mkss.application.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("start OPENAPI");
        return new OpenAPI()
                .info(new Info().title("Order Management API")
                        .description("API for managing orders and items")
                        .version("1.0.0")
                        .termsOfService("http://localhost:2222/terms"))
                .addServersItem(new Server().url("http://localhost:2222"));
    }
}