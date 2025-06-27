package com.example.ApiBlockChainFinancer.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de Transações Financeiras Descentralizadas com Blockchain, WebSocket e RabbitMQ")
                        .description("""
                    API REST robusta para controle, processamento e registro de transações financeiras utilizando:
                    
                    🔹 Blockchain customizado em Java para histórico imutável  
                    🔹 RabbitMQ para mensageria assíncrona  
                    🔹 Redis para cache, controle de sessão e rate limiter  
                    🔹 WebSocket para notificações em tempo real  
                    🔹 Spring Security com JWT para autenticação  
                    🔹 Actuator para health-check e métricas de produção  
                    
                    📦 Integração full-stack baseada em microsserviços e arquitetura reativa com mensageria.
                    
                    Esse projeto simula uma infraestrutura descentralizada de transações financeiras seguras e auditáveis.
                    """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gabriel Fernandes")
                                .email("gabriel0917py@gmail.com")
                                .url("https://github.com/GabsFns"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa e Roadmap do Projeto")
                        .url("https://github.com/GabsFns")
                );
    }
}
