package br.com.apiPets.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI petsApiOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pets API")
                        .version("v1")
                        .description("API REST para gerenciamento de pets, tutores, veterinários, vacinas, consultas e histórico médico."));
    }
}
