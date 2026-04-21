# Documentación Técnica - OpenAPI/Swagger Implementation

## 📌 Resumen Ejecutivo

Este documento detalla la implementación técnica de OpenAPI 3.0 y Swagger UI en la aplicación REST de Usuario usando la librería **SpringDoc OpenAPI** (`springdoc-openapi-starter-webmvc-ui:2.7.0`).

---

## 🏗️ Arquitectura de Documentación

```
┌─────────────────────────────────────────────────────────┐
│          Spring Boot Application (Java 21)              │
├─────────────────────────────────────────────────────────┤
│  ┌────────────────────────────────────────────────────┐ │
│  │  UserController                                     │ │
│  │  - @RestController                                  │ │
│  │  - @Tag(name="Gestión de Usuarios")                │ │
│  │  - @Operation(summary="...")                        │ │
│  │  - @ApiResponses                                    │ │
│  │  - @Parameter                                       │ │
│  └────────────────────────────────────────────────────┘ │
│                           ↓                              │
│  ┌────────────────────────────────────────────────────┐ │
│  │  DTOs (UsuarioRequest, UsuarioResponse)             │ │
│  │  - @Schema(name="...", description="...")          │ │
│  │  - @Schema per field                               │ │
│  └────────────────────────────────────────────────────┘ │
│                           ↓                              │
│  ┌────────────────────────────────────────────────────┐ │
│  │  OpenAPI Config (OpenApiConfig.java)                │ │
│  │  - Metadata global                                  │ │
│  │  - Servidores                                       │ │
│  │  - Información de contacto                          │ │
│  └────────────────────────────────────────────────────┘ │
│                           ↓                              │
│  ┌────────────────────────────────────────────────────┐ │
│  │  SpringDoc OpenAPI Library (v2.7.0)                 │ │
│  │  - Escanea anotaciones                              │ │
│  │  - Genera especificación OpenAPI 3.0                │ │
│  │  - Expone endpoints /v3/api-docs                    │ │
│  └────────────────────────────────────────────────────┘ │
│                           ↓                              │
│  ┌────────────────────────────────────────────────────┐ │
│  │  Swagger UI (HTML5 + JavaScript)                    │ │
│  │  - Interfaz interactiva                             │ │
│  │  - Consumidor de /v3/api-docs                       │ │
│  │  - Localización automática en /swagger-ui.html      │ │
│  └────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────┘
                           ↓
                    Cliente Web
                (Navegador + JavaScript)
```

---

## 📦 Dependencia Principal

```gradle
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0'
```

### Qué proporciona SpringDoc OpenAPI 2.7.0:

| Componente | Función |
|-----------|---------|
| **springdoc-openapi-common** | Core de escaneo de anotaciones |
| **springdoc-openapi-webmvc-core** | Integración con Spring MVC |
| **swagger-ui** | Interfaz HTML/JS interactiva |
| **swagger-core** | Anotaciones OpenAPI 3.0 |

---

## 🎯 Anotaciones OpenAPI Utilizadas

### 1. @Tag (Nivel de Clase)

```java
@Tag(name = "Gestión de Usuarios", description = "API REST para operaciones CRUD de usuarios")
public class UserController { ... }
```

**Propósito:**
- Agrupa endpoints relacionados bajo una sección en Swagger UI
- Facilita navegación y organización
- Aparece como pestaña colapsable en Swagger

**Generación en OpenAPI:**
```yaml
tags:
  - name: Gestión de Usuarios
    description: API REST para operaciones CRUD de usuarios
```

---

### 2. @Operation (Nivel de Método)

```java
@Operation(
    summary = "Crear nuevo usuario",
    description = "Crea un nuevo usuario en el sistema con validación completa de datos"
)
@PostMapping("/crear")
public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest usuario) {
    ...
}
```

**Propósito:**
- Documenta un endpoint específico
- Define resumen y descripción detallada
- Vincula el método HTTP al endpoint

**Generación en OpenAPI:**
```yaml
paths:
  /v1/usuario/crear:
    post:
      tags:
        - Gestión de Usuarios
      summary: Crear nuevo usuario
      description: Crea un nuevo usuario en el sistema...
      operationId: crearUsuario
```

---

### 3. @ApiResponses (Nivel de Método)

```java
@ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Usuario creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioResponse.class)
        )
    ),
    @ApiResponse(responseCode = "400", description = "Validación fallida"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
```

**Propósito:**
- Documenta todas las respuestas HTTP posibles
- Especifica códigos de estado
- Vincula esquemas de respuesta

**Generación en OpenAPI:**
```yaml
responses:
  '201':
    description: Usuario creado exitosamente
    content:
      application/json:
        schema:
          $ref: '#/components/schemas/UsuarioResponse'
  '400':
    description: Validación fallida
  '500':
    description: Error interno del servidor
```

---

### 4. @Parameter (Nivel de Parámetro)

```java
@Parameter(
    name = "id",
    description = "ID único del usuario",
    example = "1",
    required = true
)
@PathVariable long id
```

**Propósito:**
- Documenta parámetros de ruta, query o header
- Proporciona ejemplos
- Indica si es requerido

**Generación en OpenAPI:**
```yaml
parameters:
  - name: id
    in: path
    required: true
    description: ID único del usuario
    schema:
      type: integer
      format: int64
      example: 1
```

---

### 5. @Schema (Nivel de Clase/Campo)

#### En Clase DTO:
```java
@Schema(
    name = "UsuarioRequest",
    description = "Datos requeridos para crear un nuevo usuario"
)
public record UsuarioRequest(...)
```

#### En Campos:
```java
@Schema(
    description = "Nombre del usuario",
    example = "Juan",
    minLength = 1
)
String Nombre,

@Email
@Schema(
    description = "Email válido",
    example = "juan@example.com",
    format = "email"
)
String Email,

@Min(18) @Max(120)
@Schema(
    description = "Edad entre 18-120 años",
    example = "28",
    minimum = "18",
    maximum = "120"
)
int Edad
```

**Propósito:**
- Define estructura y propiedades de modelos de datos
- Proporciona validaciones y ejemplos
- Crea esquemas reutilizables en OpenAPI

**Generación en OpenAPI:**
```yaml
components:
  schemas:
    UsuarioRequest:
      type: object
      properties:
        Nombre:
          type: string
          minLength: 1
          example: Juan
        Email:
          type: string
          format: email
          example: juan@example.com
        Edad:
          type: integer
          minimum: 18
          maximum: 120
          example: 28
      required:
        - Nombre
        - Email
        - Edad
```

---

## 🔄 Flujo de Procesamiento

### 1. Iniciación (Startup de la Aplicación)

```
a) Spring Boot inicia
    ↓
b) SpringDoc escanea el classpath
    ↓
c) Encuentra todas las clases anotadas con @RestController/@Controller
    ↓
d) Escanea métodos con @PostMapping/@GetMapping/etc.
    ↓
e) Extrae anotaciones: @Tag, @Operation, @ApiResponses, @Parameter, @Schema
    ↓
f) Construye árbol de especificación OpenAPI 3.0
    ↓
g) Expone especificación en /v3/api-docs (JSON) y /v3/api-docs.yaml (YAML)
```

### 2. Solicitud a /v3/api-docs

```
Cliente (Browser)
    ↓
GET /v3/api-docs
    ↓
SpringDoc OpenAPI Controller
    ↓
Retorna JSON OpenAPI 3.0
    ↓
Swagger UI recibe JSON
    ↓
Renderiza HTML interactivo
```

### 3. Interacción del Usuario en Swagger UI

```
Usuario ve lista de endpoints
    ↓
Hace clic en endpoint
    ↓
Swagger expande detalles:
  - Descripción
  - Parámetros
  - Request body (schema)
  - Respuestas posibles
    ↓
Usuario hace clic en "Try it out"
    ↓
Swagger habilita inputs para completar
    ↓
Usuario completa datos
    ↓
Usuario hace clic en "Execute"
    ↓
JavaScript envía HTTP request
    ↓
Spring Boot procesa solicitud
    ↓
Retorna respuesta
    ↓
Swagger UI muestra:
  - HTTP Status
  - Response Headers
  - Response Body (formateado)
```

---

## 📊 Especificación OpenAPI Generada

### Estructura Completa

```yaml
openapi: 3.0.1
info:
  title: API Gestión de Usuarios
  version: 1.0.0
  description: API REST para gestión de usuarios...
  contact:
    name: Soporte API
    email: soporte@example.com
  license:
    name: Apache 2.0
    
servers:
  - url: http://localhost:8080
    description: Servidor de Desarrollo
  - url: https://api.example.com
    description: Servidor de Producción

tags:
  - name: Gestión de Usuarios
    description: API REST para operaciones CRUD

paths:
  /v1/usuario/crear:
    post:
      tags:
        - Gestión de Usuarios
      summary: Crear nuevo usuario
      description: Crea un nuevo usuario con validación...
      operationId: crearUsuario
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/UsuarioRequest'
      responses:
        '201':
          description: Usuario creado exitosamente
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UsuarioResponse'
        '400':
          description: Validación fallida
        '500':
          description: Error interno del servidor

  /v1/usuario:
    get:
      tags:
        - Gestión de Usuarios
      summary: Obtener todos los usuarios
      description: Retorna la lista completa de usuarios
      operationId: obtenerUsuarios
      responses:
        '200':
          description: Lista de usuarios obtenida
        '500':
          description: Error interno del servidor

  /v1/usuario/{id}:
    get:
      tags:
        - Gestión de Usuarios
      summary: Obtener usuario por ID
      description: Retorna datos de un usuario específico
      operationId: obtenerUsuario
      parameters:
        - name: id
          in: path
          required: true
          description: ID único del usuario
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: Usuario encontrado
        '404':
          description: Usuario no encontrado
        '500':
          description: Error interno del servidor

components:
  schemas:
    UsuarioRequest:
      type: object
      properties:
        Nombre:
          type: string
          description: Nombre del usuario
          example: Juan
        Apellido:
          type: string
          description: Apellido del usuario
          example: Pérez
        Email:
          type: string
          format: email
          description: Email válido
          example: juan@example.com
        Password:
          type: string
          description: Contraseña
          example: segura123
        Edad:
          type: integer
          format: int32
          description: Edad (18-120)
          minimum: 18
          maximum: 120
          example: 28
      required:
        - Nombre
        - Apellido
        - Email
        - Password
        - Edad

    UsuarioResponse:
      type: object
      properties:
        id:
          type: integer
          format: int64
          example: 1
        nombre:
          type: string
          example: Juan
        apellido:
          type: string
          example: Pérez
        email:
          type: string
          format: email
          example: juan@example.com
```

---

## 🔐 Validación y Swagger

### Anotaciones Jakarta Validation vs OpenAPI

| Anotación | Propósito | Impacto en Swagger |
|-----------|-----------|-------------------|
| `@NotBlank` | Validación | Campo requerido, minLength = 1 |
| `@Email` | Validación | format = "email" |
| `@Min(18)` | Validación | minimum = 18 |
| `@Max(120)` | Validación | maximum = 120 |
| `@Valid` | Validación recursiva | Se aplica a objetos anidados |

### Ejemplo Completo

```java
public record UsuarioRequest(
    @NotBlank(message = "Nombre no puede estar vacío")
    @Schema(
        description = "Nombre completo",
        example = "Juan",
        minLength = 1
    )
    String Nombre,
    
    @Email(message = "Email debe ser válido")
    @Schema(
        description = "Email válido",
        example = "juan@example.com",
        format = "email"
    )
    String Email,
    
    @Min(value = 18, message = "Mínimo 18 años")
    @Max(value = 120, message = "Máximo 120 años")
    @Schema(
        description = "Edad del usuario",
        example = "28",
        minimum = "18",
        maximum = "120"
    )
    int Edad
)
```

**Resultado en Swagger:**
- Campo con asterisco (*)
- Validaciones mostradas en descripción
- Ejemplo disponible
- Rango visual en inputs numéricos

---

## 🛠️ Configuración Global (OpenApiConfig.java)

```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Gestión de Usuarios")
                .version("1.0.0")
                .description("Descripción detallada del API")
                .contact(new Contact()
                    .name("Nombre")
                    .email("email@example.com")
                    .url("https://example.com")
                )
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                )
            )
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Desarrollo")
            )
            .addServersItem(new Server()
                .url("https://api.example.com")
                .description("Producción")
            );
    }
}
```

**Configuración disponible:**
- Título y versión
- Descripción (soporta Markdown)
- Información de contacto
- Licencia
- Múltiples servidores
- Variables de servidor
- Esquemas de seguridad
- Extensiones OpenAPI

---

## 📱 Integración con Herramientas Externas

### 1. Postman
```
1. Abre Postman
2. File → Import
3. Paste URL: http://localhost:8080/v3/api-docs
4. Postman importa automáticamente todos los endpoints
5. Prueba con datos pre-llenados
```

### 2. Swagger Editor
```
1. Accede a https://editor.swagger.io/
2. Abre: http://localhost:8080/v3/api-docs.yaml
3. Edita especificación visualmente
```

### 3. Redoc (Alternativa a Swagger UI)
```html
<!DOCTYPE html>
<html>
<head>
    <title>Redoc</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,700|Roboto:300,400,700" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
    <redoc spec-url='http://localhost:8080/v3/api-docs.yaml'></redoc>
    <script src="https://cdn.jsdelivr.net/npm/redoc/bundles/redoc.standalone.js"></script>
</body>
</html>
```

---

## 🚀 Mejoras Implementadas en el Código

### 1. **Cambio de Tipo de Retorno**
```java
// Antes
public UsuarioResponse crearUsuario(...) { ... }

// Después
public ResponseEntity<UsuarioResponse> crearUsuario(...) {
    return ResponseEntity.status(HttpStatus.CREATED).body(...);
}
```
**Ventaja:** HTTP 201 en lugar de 200, mejor semántica REST

### 2. **Parámetros Documentados**
```java
// Antes
public String obtenerUsuario(@PathVariable long id) { ... }

// Después
public ResponseEntity<String> obtenerUsuario(
    @Parameter(name = "id", description = "ID único...", example = "1")
    @PathVariable long id
) { ... }
```
**Ventaja:** Parámetros claramente documentados en Swagger

### 3. **Respuestas Estructuradas**
```java
// Antes
return "Usuarios";

// Después
return ResponseEntity.ok("Usuarios");
```
**Ventaja:** Uso de HttpStatus, mejor control de respuestas

---

## 📈 Estadísticas de Documentación

| Métrica | Valor |
|---------|-------|
| Endpoints Documentados | 3 |
| Esquemas Definidos | 2 (Request + Response) |
| Validaciones Documentadas | 5 |
| Códigos HTTP Documentados | 7 |
| Tags de Grupo | 1 |
| Anotaciones Usadas | 8 tipos |

---

## ✅ Checklist de Implementación

- [x] Agregar dependencia SpringDoc OpenAPI 2.7.0
- [x] Crear configuración OpenAPI global (OpenApiConfig.java)
- [x] Documentar clase con @Tag
- [x] Documentar métodos con @Operation
- [x] Documentar respuestas con @ApiResponses
- [x] Documentar parámetros con @Parameter
- [x] Documentar DTOs con @Schema
- [x] Agregar ejemplos en @Schema
- [x] Usar ResponseEntity para mejor semántica HTTP
- [x] Agregar comentarios JavaDoc
- [x] Validar documentación en Swagger UI

---

## 🔗 URLs de Acceso

```
Swagger UI:        http://localhost:8080/swagger-ui.html
OpenAPI JSON:      http://localhost:8080/v3/api-docs
OpenAPI YAML:      http://localhost:8080/v3/api-docs.yaml
Swagger UI (alt):  http://localhost:8080/swagger-ui/
Swagger Resources: http://localhost:8080/swagger-resources
```

---

## 📚 Documentación de Referencia

- [SpringDoc OpenAPI Docs](https://springdoc.org/)
- [OpenAPI 3.0 Specification](https://spec.openapis.org/oas/v3.0.3)
- [Swagger Editor](https://editor.swagger.io/)
- [Spring Boot Reference](https://spring.io/projects/spring-boot)

---

**Documentación Técnica Generada:** 17 de Abril, 2026  
**Versión:** 1.0.0  
**SpringDoc OpenAPI:** 2.7.0  
**Java:** 21  
**Spring Boot:** 4.0.5
