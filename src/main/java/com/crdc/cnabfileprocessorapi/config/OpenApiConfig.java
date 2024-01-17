package com.crdc.cnabfileprocessorapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Autor : jeferson.carreira
 * Data  : 16/01/204
 */
@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI().info(
                new Info().title("API Cnab File Upload")
                        .version("1.0.0")
                        .description("Api para importação de duplicatas no padrão CNAB")
        );
    }
}
