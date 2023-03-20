package com.uygunaldim.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Uygunaldim API")
                        .description("This is an uygunaldim project RESTful service using springdoc-openapi and OpenAPI 3.")
                        .contact(new Contact().email("anilcan.ipsalali@gmail.com"))
                        .version("0.1.0")
                );
    }
}
