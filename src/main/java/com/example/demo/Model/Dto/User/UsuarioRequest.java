package com.example.demo.Model.Dto.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;

/**
 * DTO para solicitudes de creación de usuario
 * Contiene todas las validaciones requeridas para registrar un nuevo usuario
 */
@Schema(
    name = "UsuarioRequest",
    description = "Datos requeridos para crear un nuevo usuario"
)
public record UsuarioRequest(

        @NotBlank(message = "Nombre Invalido (Vacio)")
        @Schema(
            description = "Nombre del usuario",
            example = "Juan",
            minLength = 1
        )
        String Nombre,

        @NotBlank(message = "Apellido Invalido (Vacio)")
        @Schema(
            description = "Apellido del usuario",
            example = "Pérez",
            minLength = 1
        )
        String Apellido,

        @Email(message = "Email invalido")
        @Schema(
            description = "Correo electrónico válido del usuario",
            example = "juan.perez@example.com",
            format = "email"
        )
        String Email,

        @NotBlank
        @Schema(
            description = "Contraseña segura del usuario",
            example = "segura123",
            minLength = 6
        )
        String Password,

        @Min(value = 18, message = "No se aceptan usuarios menores de edad")
        @Max(value = 120, message = "Edad superior al limite establecido")
        @Schema(
            description = "Edad del usuario (entre 18 y 120 años)",
            example = "28",
            minimum = "18",
            maximum = "120"
        )
        int Edad

) { }
