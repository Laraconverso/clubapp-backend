package com.APIclubApp.clubApp.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                    .title("API REST ClubApp")
                    .version("1.0")
                    .description("Api de la aplicacion de ClubApp"));
    }
}
