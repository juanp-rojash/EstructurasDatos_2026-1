package com.example.demo.Controller;

import com.example.demo.Model.Dto.User.UsuarioRequest;
import com.example.demo.Model.Dto.User.UsuarioResponse;
import com.example.demo.Servicio.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de usuarios
 * Proporciona endpoints para crear, consultar y gestionar usuarios
 */
@RestController
@RequestMapping("/v1/usuario")
@Tag(name = "Gestión de Usuarios", description = "API REST para operaciones CRUD de usuarios")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    @Operation(
        summary = "Crear nuevo usuario",
        description = "Crea un nuevo usuario en el sistema con validación completa de datos"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Usuario creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Validación fallida - datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Retorna la lista completa de todos los usuarios registrados en el sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<UsuarioResponse> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener usuario por ID",
        description = "Retorna los datos de un usuario específico utilizando su identificador único"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> obtenerUsuario(
        @Parameter(name = "id", description = "ID único del usuario", example = "1", required = true)
        @PathVariable long id
    ) {
        return ResponseEntity.ok("Usuario de ID = " + id);
    }

}
