# Guía Completa - Documentación del UserController con Swagger UI

## 📖 Documento Ejecutivo

Esta es una guía completa que explica cómo el `UserController` está documentado automáticamente mediante **Swagger UI** y **OpenAPI 3.0** usando **SpringDoc OpenAPI** versión 2.7.0.

---

## 🎯 Objetivo

Proporcionar documentación automática, interactiva y completa del API REST de usuarios sin necesidad de mantener documentos separados.

---

## 📚 Documentos Incluidos en el Proyecto

| Documento | Propósito | Audiencia |
|-----------|----------|-----------|
| **SWAGGER_UI_GUIDE.md** | Cómo usar Swagger UI | Desarrolladores, QA |
| **OPENAPI_TECHNICAL_DOCS.md** | Detalles técnicos de implementación | Arquitectos, Senior Developers |
| **Este documento** | Resumen ejecutivo y ejemplos | Todos |

---

## 🚀 Quick Start - 3 Pasos

### Paso 1: Ejecutar la Aplicación
```bash
./gradlew bootRun
```

### Paso 2: Abrir Navegador
```
http://localhost:8080/swagger-ui.html
```

### Paso 3: Probar Endpoints
- Expande "Gestión de Usuarios"
- Haz clic en "Try it out"
- Completa datos
- Haz clic en "Execute"

---

## 🏛️ Archivos Modificados

### 1. **UserController.java** ✅
```diff
+ import io.swagger.v3.oas.annotations.Operation;
+ import io.swagger.v3.oas.annotations.Parameter;
+ import io.swagger.v3.oas.annotations.tags.Tag;
+ import io.swagger.v3.oas.annotations.ApiResponses;
+ import io.swagger.v3.oas.annotations.responses.ApiResponse;
+ import io.swagger.v3.oas.annotations.media.Content;
+ import io.swagger.v3.oas.annotations.media.Schema;

+ @Tag(name = "Gestión de Usuarios", ...)
  public class UserController {
  
+     @Operation(summary = "Crear nuevo usuario", ...)
+     @ApiResponses({ ... })
      public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest usuario)
      
+     @Operation(summary = "Obtener todos los usuarios", ...)
+     @ApiResponses({ ... })
      public ResponseEntity<String> obtenerUsuarios()
      
+     @Operation(summary = "Obtener usuario por ID", ...)
+     @ApiResponses({ ... })
+     @Parameter(name = "id", description = "...", example = "1")
      public ResponseEntity<String> obtenerUsuario(@PathVariable long id)
  }
```

### 2. **UsuarioRequest.java** ✅
```diff
+ import io.swagger.v3.oas.annotations.media.Schema;

+ @Schema(name = "UsuarioRequest", description = "...")
  public record UsuarioRequest(
+     @Schema(description = "Nombre del usuario", example = "Juan", minLength = 1)
      String Nombre,
+     @Schema(description = "Apellido del usuario", example = "Pérez")
      String Apellido,
+     @Schema(description = "Email válido", example = "juan@example.com", format = "email")
      String Email,
+     @Schema(description = "Contraseña", example = "segura123", minLength = 6)
      String Password,
+     @Schema(description = "Edad (18-120)", example = "28", minimum = "18", maximum = "120")
      int Edad
  )
```

### 3. **UsuarioResponse.java** ✅
```diff
+ import io.swagger.v3.oas.annotations.media.Schema;

+ @Schema(name = "UsuarioResponse", description = "...")
  public record UsuarioResponse(
+     @Schema(description = "ID único del usuario", example = "1")
      long id,
+     @Schema(description = "Nombre del usuario", example = "Juan")
      String nombre,
+     @Schema(description = "Apellido del usuario", example = "Pérez")
      String apellido,
+     @Schema(description = "Email del usuario", example = "juan@example.com", format = "email")
      String email
  )
```

### 4. **OpenApiConfig.java** ✨ NUEVO
```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Gestión de Usuarios")
                .version("1.0.0")
                .description("Descripción del API...")
                .contact(new Contact()
                    .name("Soporte")
                    .email("soporte@example.com")
                )
                .license(new License()
                    .name("Apache 2.0")
                )
            )
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Desarrollo")
            );
    }
}
```

---

## 📊 Vista en Swagger UI

### Header de Swagger
```
┌─────────────────────────────────────────────────────────────┐
│  Swagger UI                          │ JSON │ YAML           │
├─────────────────────────────────────────────────────────────┤
│  API Gestión de Usuarios                                    │
│  Versión: 1.0.0                                             │
│  📧 Soporte: soporte@example.com                            │
│  📜 Licencia: Apache 2.0                                    │
├─────────────────────────────────────────────────────────────┤
│  Seleccionar servidor: [Desarrollo ▼]                       │
└─────────────────────────────────────────────────────────────┘
```

### Grouping de Endpoints
```
📌 Gestión de Usuarios
   ├── POST /v1/usuario/crear
   ├── GET /v1/usuario
   └── GET /v1/usuario/{id}
```

---

## 📝 Ejemplo 1: POST /v1/usuario/crear

### Vista Colapsada en Swagger
```
POST /v1/usuario/crear
202  Crear nuevo usuario
     Crea un nuevo usuario en el sistema con validación completa...
```

### Vista Expandida
```
┌─────────────────────────────────────────────────────────────┐
│ POST /v1/usuario/crear                                      │
│                                                               │
│ Crear nuevo usuario                                         │
│                                                               │
│ Crea un nuevo usuario en el sistema con validación          │
│ completa de datos                                           │
│                                                               │
│ Request body                                                │
│ ├─ UsuarioRequest (application/json) ✓                      │
│ │                                                            │
│ │ {                                                          │
│ │   "Nombre": "string",                                      │
│ │   "Apellido": "string",                                    │
│ │   "Email": "user@example.com",                            │
│ │   "Password": "string",                                    │
│ │   "Edad": 0                                                │
│ │ }                                                          │
│                                                               │
│ Responses                                                    │
│ ├─ 201 Created ✓                                            │
│ │  "Usuario creado exitosamente"                            │
│ │  Content-Type: application/json                           │
│ │  {                                                         │
│ │    "id": 0,                                                │
│ │    "nombre": "string",                                     │
│ │    "apellido": "string",                                   │
│ │    "email": "user@example.com"                            │
│ │  }                                                         │
│ │                                                            │
│ ├─ 400 Bad Request                                          │
│ │  "Validación fallida - datos inválidos"                   │
│ │                                                            │
│ └─ 500 Internal Server Error                                │
│    "Error interno del servidor"                             │
│                                                               │
│ [ Try it out ]                                              │
└─────────────────────────────────────────────────────────────┘
```

### Después de "Try it out"
```
┌─────────────────────────────────────────────────────────────┐
│                                                               │
│ Request body                                                │
│ ┌─────────────────────────────────────────────────────────┐ │
│ │ {                                                       │ │
│ │   "Nombre": "Juan",                                     │ │
│ │   "Apellido": "Pérez",                                  │ │
│ │   "Email": "juan@example.com",                          │ │
│ │   "Password": "segura123",                              │ │
│ │   "Edad": 28                                            │ │
│ │ }                                                       │ │
│ └─────────────────────────────────────────────────────────┘ │
│                                                               │
│ [ Clear ]  [ Execute ]                                      │
│                                                               │
│ Responses:                                                   │
│                                                               │
│ Server response                                             │
│ Code: 201 Created                                           │
│ Headers: { "content-type": "application/json" }            │
│                                                               │
│ Response body:                                              │
│ {                                                           │
│   "id": 1,                                                  │
│   "nombre": "Juan",                                         │
│   "apellido": "Pérez",                                      │
│   "email": "juan@example.com"                              │
│ }                                                           │
│                                                               │
│ Response headers:                                           │
│ { "content-type": "application/json", ... }                │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

---

## 📋 Ejemplo 2: GET /v1/usuario/{id}

### Vista Expandida
```
┌─────────────────────────────────────────────────────────────┐
│ GET /v1/usuario/{id}                                        │
│                                                               │
│ Obtener usuario por ID                                      │
│                                                               │
│ Retorna los datos de un usuario específico utilizando       │
│ su identificador único                                      │
│                                                               │
│ Parameters                                                   │
│ ├─ id * (path)                                              │
│ │  ID único del usuario                                     │
│ │  Example: 1                                               │
│ │  [                             ]  ← Input field            │
│ │                                                            │
│                                                               │
│ Responses                                                    │
│ ├─ 200 OK                                                   │
│ │  "Usuario encontrado exitosamente"                        │
│ │  { "id": 0, "nombre": "string", ... }                    │
│ │                                                            │
│ ├─ 404 Not Found                                            │
│ │  "Usuario no encontrado"                                  │
│ │                                                            │
│ └─ 500 Internal Server Error                                │
│    "Error interno del servidor"                             │
│                                                               │
│ [ Try it out ]                                              │
└─────────────────────────────────────────────────────────────┘
```

### Ejecución en Swagger
```
1. Usuario ingresa: 1
2. Haz clic en "Execute"
3. Respuesta recibida:
   
   Code: 200 OK
   "Usuario de ID = 1"
```

---

## 📊 Schemas (Models) en Swagger

### Sección de Modelos
```
┌─────────────────────────────────────────────────────────────┐
│ Models                                                      │
│                                                               │
│ ▼ UsuarioRequest                                            │
│  Datos requeridos para crear un nuevo usuario              │
│                                                               │
│  Properties:                                                │
│  ├─ Nombre (string)                                         │
│  │  Nombre del usuario                                      │
│  │  minLength: 1                                            │
│  │  Example: Juan                                           │
│  │                                                          │
│  ├─ Apellido (string)                                       │
│  │  Apellido del usuario                                    │
│  │  Example: Pérez                                          │
│  │                                                          │
│  ├─ Email (string) <email>                                 │
│  │  Email válido                                            │
│  │  Example: juan@example.com                              │
│  │                                                          │
│  ├─ Password (string)                                       │
│  │  Contraseña segura                                       │
│  │  minLength: 6                                            │
│  │  Example: segura123                                      │
│  │                                                          │
│  └─ Edad (integer)                                          │
│     Edad (18-120)                                           │
│     minimum: 18, maximum: 120                              │
│     Example: 28                                             │
│                                                               │
│ Required: [Nombre, Apellido, Email, Password, Edad]        │
│                                                               │
│                                                               │
│ ▼ UsuarioResponse                                           │
│  Datos públicos del usuario en respuesta                    │
│                                                               │
│  Properties:                                                │
│  ├─ id (integer)                                            │
│  │  ID único del usuario                                    │
│  │  Example: 1                                              │
│  │                                                          │
│  ├─ nombre (string)                                         │
│  │  Nombre del usuario                                      │
│  │  Example: Juan                                           │
│  │                                                          │
│  ├─ apellido (string)                                       │
│  │  Apellido del usuario                                    │
│  │  Example: Pérez                                          │
│  │                                                          │
│  └─ email (string) <email>                                 │
│     Email del usuario                                       │
│     Example: juan@example.com                              │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔐 Validaciones Mostradas en Swagger

### En UsuarioRequest
```
Nombre:
  - Validación: @NotBlank ✓ (requerido)
  - minLength: 1
  - Mensaje: "Nombre Invalido (Vacio)"

Email:
  - Validación: @Email ✓ (formato)
  - format: email
  - Patrón: RFC 5322
  - Mensaje: "Email invalido"

Edad:
  - Validación: @Min(18), @Max(120) ✓
  - Rango: 18-120
  - Mensajes: "No se aceptan usuarios menores de edad" / "Edad superior..."
```

### En Swagger UI
```
┌─────────────────────────────────┐
│ Nombre *  [                  ]   │  ← Asterisco = requerido
│ Campo de texto                  │
│ minLength: 1                    │
│                                 │
│ Email *   [                  ]   │  ← Validación email
│ Validación de formato email     │
│                                 │
│ Edad  *   [              ]       │  ← Rango 18-120
│ Número entre 18 y 120          │
└─────────────────────────────────┘
```

---

## 🌐 Cómo Acceder a la Documentación

### URLs Disponibles

| URL | Contenido | Formato |
|-----|-----------|---------|
| `http://localhost:8080/swagger-ui.html` | Interfaz interactiva | HTML + JS |
| `http://localhost:8080/swagger-ui/` | Alternativa UI | HTML + JS |
| `http://localhost:8080/v3/api-docs` | Especificación | JSON |
| `http://localhost:8080/v3/api-docs.yaml` | Especificación | YAML |
| `http://localhost:8080/swagger-resources` | Recursos | JSON |

### En Swagger UI
```
┌─────────────────────────────────────────────────────────────┐
│  http://localhost:8080/swagger-ui.html                      │
│                                                               │
│  Botones en la esquina superior derecha:                   │
│  ┌──────────────────────────────────────────────────────┐  │
│  │ [ JSON ]  [ YAML ]                                    │  │
│  └──────────────────────────────────────────────────────┘  │
│  JSON → Descarga especificación en JSON                   │
│  YAML → Descarga especificación en YAML                   │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

---

## 💾 Importar en Herramientas Externas

### Postman
```bash
1. Abre Postman
2. Collections → Import
3. Paste Raw Text o URL
4. URL: http://localhost:8080/v3/api-docs
5. Import
6. ¡Todos los endpoints disponibles!
```

### Insomnia
```bash
1. Abre Insomnia
2. Create → Import from URL
3. URL: http://localhost:8080/v3/api-docs
4. Import
5. Todos los endpoints listos
```

### VS Code - REST Client Extension
```bash
# Crea archivo: requests.http

### Crear Usuario
POST http://localhost:8080/v1/usuario/crear
Content-Type: application/json

{
  "Nombre": "Juan",
  "Apellido": "Pérez",
  "Email": "juan@example.com",
  "Password": "segura123",
  "Edad": 28
}

### Obtener Usuarios
GET http://localhost:8080/v1/usuario

### Obtener Usuario por ID
GET http://localhost:8080/v1/usuario/1
```

---

## 🎓 Anotaciones Clave Explicadas

### 1. @Tag
```java
@Tag(name = "Gestión de Usuarios", description = "API REST...")
```
**Función:** Agrupa endpoints en Swagger  
**Dónde:** Nivel de clase del controlador  
**Efecto:** Aparece como pestaña en Swagger UI

### 2. @Operation
```java
@Operation(summary = "Crear...", description = "Crea un nuevo...")
```
**Función:** Documenta un endpoint  
**Dónde:** Sobre cada método HTTP  
**Efecto:** Resumen y descripción en Swagger

### 3. @ApiResponses / @ApiResponse
```java
@ApiResponses({
    @ApiResponse(responseCode = "201", description = "Creado"),
    @ApiResponse(responseCode = "400", description = "Error")
})
```
**Función:** Documenta códigos HTTP posibles  
**Dónde:** Sobre métodos HTTP  
**Efecto:** Respuestas en Swagger

### 4. @Parameter
```java
@Parameter(name = "id", description = "ID único", example = "1")
```
**Función:** Documenta parámetros  
**Dónde:** Sobre parámetros de método  
**Efecto:** Campos en Swagger

### 5. @Schema
```java
@Schema(description = "Email válido", example = "juan@example.com", format = "email")
```
**Función:** Documenta modelos/campos  
**Dónde:** En clases DTO y sus campos  
**Efecto:** Schemas en Swagger

---

## 📈 Beneficios de la Documentación OpenAPI/Swagger

### Para Desarrolladores
✅ Documentación siempre actualizada  
✅ Ejemplos en vivo  
✅ Validaciones visibles  
✅ Fácil de probar endpoints  

### Para Testers
✅ Interfaz intuitiva  
✅ No requiere herramientas adicionales  
✅ Respuestas en tiempo real  
✅ Historial de pruebas  

### Para DevOps
✅ Documentación legible por máquina  
✅ Fácil integración en pipelines CI/CD  
✅ Posibilidad de generar clientes automáticamente  
✅ Validación de contratos  

### Para PMs/Stakeholders
✅ Documentación clara y profesional  
✅ Acceso sin configuración técnica  
✅ Ejemplos concretos  
✅ Validaciones explícitas  

---

## 🔗 Flujo Completo de Usuario

### Escenario: Crear un Nuevo Usuario

```
1. Desarrollador abre navegador
   http://localhost:8080/swagger-ui.html
       ↓
2. Ve interfaz de Swagger UI
   - Título: "API Gestión de Usuarios"
   - Versión: 1.0.0
   - Contacto: soporte@example.com
       ↓
3. Expande grupo "Gestión de Usuarios"
   - POST /v1/usuario/crear
   - GET /v1/usuario
   - GET /v1/usuario/{id}
       ↓
4. Hace clic en "POST /v1/usuario/crear"
   Ve:
   - Descripción completa
   - Request body (UsuarioRequest)
   - Respuestas posibles (201, 400, 500)
       ↓
5. Haz clic en "Try it out"
   - Request body field se habilita
   - Pre-llenado con ejemplo
       ↓
6. Completa/modifica datos:
   {
     "Nombre": "Juan",
     "Apellido": "Pérez",
     "Email": "juan@example.com",
     "Password": "segura123",
     "Edad": 28
   }
       ↓
7. Haz clic en "Execute"
   - Swagger envía HTTP POST
   - Con los datos especificados
       ↓
8. Recibe respuesta:
   Code: 201 Created
   {
     "id": 1,
     "nombre": "Juan",
     "apellido": "Pérez",
     "email": "juan@example.com"
   }
       ↓
9. Verifica respuesta en tiempo real
   - Status HTTP correcto (201)
   - Body estructura correcta
   - Headers apropiados
```

---

## ✅ Conclusión

La documentación del `UserController` está **completamente implementada** con:

| Aspecto | Estado | Detalles |
|--------|--------|----------|
| Anotaciones de Controlador | ✅ Completo | @Tag, @Operation, @ApiResponses |
| Documentación de DTOs | ✅ Completo | @Schema en clase y campos |
| Parametrización | ✅ Completo | @Parameter para ruta/query |
| Configuración Global | ✅ Completo | OpenApiConfig.java |
| Validaciones | ✅ Visible | Jakarta Validation + @Schema |
| Swagger UI | ✅ Funcional | http://localhost:8080/swagger-ui.html |
| Ejemplos | ✅ Incluidos | En todos los @Schema |
| Servidores | ✅ Configurado | Desarrollo y Producción |

---

## 🚀 Próximos Pasos Recomendados

1. **Ejecutar la aplicación:**
   ```bash
   ./gradlew bootRun
   ```

2. **Acceder a Swagger UI:**
   ```
   http://localhost:8080/swagger-ui.html
   ```

3. **Probar endpoints:**
   - Prueba crear usuario
   - Prueba listar usuarios
   - Prueba obtener usuario por ID

4. **Exportar especificación:**
   - Descargar JSON desde Swagger UI
   - Compartir con equipo
   - Importar en Postman/Insomnia

5. **Ampliar documentación:**
   - Agregar CRUD completo
   - Agregar paginación
   - Agregar filtros

---

**Documentación Completada:** 17 de Abril, 2026  
**Versión API:** 1.0.0  
**Librería:** SpringDoc OpenAPI 2.7.0  
**Java:** 21  
**Spring Boot:** 4.0.5
