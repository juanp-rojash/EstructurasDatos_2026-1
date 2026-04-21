package com.example.demo.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración global de OpenAPI/Swagger
 * Define la documentación general del API y su metadata
 */
@Configuration
public class OpenApiConfig {

    /**
     * Define la configuración global del OpenAPI 3.0
     * Esta configuración se mostrará en Swagger UI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Gestión de Usuarios")
                .version("1.0.0")
                .description(
                    "API REST para la gestión completa de usuarios.\n\n" +
                    "## Características Principales\n" +
                    "- Creación de usuarios con validación robusta\n" +
                    "- Consulta de usuarios individual o listado completo\n" +
                    "- Validación de email y rango de edad\n" +
                    "- Respuestas JSON estructuradas\n" +
                    "- Documentación interactiva con Swagger UI\n\n" +
                    "## Validaciones Aplicadas\n" +
                    "- **Nombre y Apellido**: No pueden estar vacíos\n" +
                    "- **Email**: Debe ser un formato de email válido\n" +
                    "- **Password**: Requerido y mínimo 6 caracteres\n" +
                    "- **Edad**: Entre 18 y 120 años\n\n" +
                    "## Versión de Tecnología\n" +
                    "- Spring Boot 4.0.5\n" +
                    "- Java 21\n" +
                    "- Jakarta EE 10"
                )
                .contact(new Contact()
                    .name("Soporte API")
                    .email("soporte@example.com")
                    .url("https://example.com")
                )
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                )
            )
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Servidor de Desarrollo")
            )
            .addServersItem(new Server()
                .url("https://api.example.com")
                .description("Servidor de Producción")
            );
    }
}
