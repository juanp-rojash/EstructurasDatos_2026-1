package com.example.demo.Model.Dto.User;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para respuestas de usuario
 * Contiene la información pública del usuario sin datos sensibles (como password)
 */
@Schema(
    name = "UsuarioResponse",
    description = "Datos públicos del usuario en respuesta"
)
public record UsuarioResponse(

        @Schema(
            description = "ID único del usuario",
            example = "1"
        )
        String id,

        @Schema(
            description = "Nombre del usuario",
            example = "Juan"
        )
        String nombre,

        @Schema(
            description = "Apellido del usuario",
            example = "Pérez"
        )
        String apellido,

        @Schema(
            description = "Correo electrónico del usuario",
            example = "juan.perez@example.com",
            format = "email"
        )
        String email

) { }
