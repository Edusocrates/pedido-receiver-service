package com.fiap.rm358568.edusocrates.pedido_receiver_service.infraestrutura.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Controle de Pedidos (Pedidos Receiver Service)")
                        .description("Documentação da API de Controle de Pedidos (Pedidos Receiver Service)")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa")
                        .url("https://example.com"));//trocar para url do miro
    }
}