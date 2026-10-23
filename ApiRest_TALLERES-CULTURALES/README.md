# API REST de Talleres Culturales

API REST desarrollada con **Spring Boot** para gestionar los talleres de un centro cultural, sus organizadores y las personas que se apuntan.

Proyecto de *AD y PSP*

---

## Índice

1. [Qué hace la API](#qué-hace-la-api)
2. [Tecnologías](#tecnologías)
3. [Modelo de datos](#modelo-de-datos)
4. [Cómo arrancar el proyecto](#cómo-arrancar-el-proyecto)
5. [Documentación con Swagger](#documentación-con-swagger)
6. [Endpoints](#endpoints)
7. [Validaciones y errores](#validaciones-y-errores)
8. [Estructura del proyecto](#estructura-del-proyecto)
9. [Probar con Postman](#probar-con-postman)

---

## Qué hace la API

* Gestiona **organizadores** y **talleres** (crear, consultar, modificar y borrar).
* Gestiona **participantes** (crear y listar).
* Permite **inscribir** participantes en talleres, consultar quién está inscrito y **anular** inscripciones.

---

## Tecnologías

|Tecnología|Uso|
|-|-|
|Java 21|Lenguaje|
|Spring Boot 4.1.1|Framework|
|Spring Data JPA|Acceso a datos|
|H2|Base de datos|
|Bean Validation|Validación de los datos de entrada|
|springdoc-openapi|Documentación OpenAPI 3 / Swagger|
|Lombok|Menos código repetitivo|
|Maven|Gestión de dependencias|

---

## Modelo de datos

Hay tres entidades:

**Organizador**

|Campo|Tipo|
|-|-|
|id|Long|
|nombre|String|
|email|String|

**Taller**

|Campo|Tipo|
|-|-|
|id|Long|
|titulo (único)|String|
|fechaHoraInicio|LocalDateTime|
|fechaHoraFin|LocalDateTime|

**Participante**

|Campo|Tipo|
|-|-|
|id|Long|
|nombreCompleto|String|

**Relaciones**

* Un Organizador tiene muchos Talleres, y cada Taller tiene un solo Organizador.
* Un Taller tiene muchos Participantes, y un Participante puede estar en muchos Talleres.
* La inscripción es solo esa relación entre Taller y Participante, no tiene datos extra.

---

## Cómo arrancar el proyecto

**Requisitos:** tener instalado **Java 21** o superior.

1. Clona el repositorio:

```bash
   git clone https://github.com/gmarcar-triana/ApiRest\\\_TALLERES-CULTURALES.git
   cd ApiRest\\\_TALLERES-CULTURALES
   ```

2. Arranca la aplicación:

```bash
   ./mvnw spring-boot:run
   ```

En Windows: `mvnw.cmd spring-boot:run`

3. La API queda disponible en **http://localhost:8080**.

Al arrancar se cargan **datos de ejemplo** automáticamente (2 organizadores, 3 participantes y 2 talleres), así que puedes probarla sin crear nada.

> Los datos se \\\*\\\*borran y se vuelven a crear\\\*\\\* cada vez que arrancas la aplicación.

---

## Documentación con Swagger

Con la aplicación arrancada, abre:

**http://localhost:8080/swagger-ui.html**

Ahí aparecen todos los endpoints con sus descripciones, ejemplos y posibles respuestas, y puedes probarlos desde el navegador.

---

## Endpoints

### Organizadores: `/organizadores`

|Método|Ruta|Descripción|Respuestas|
|-|-|-|-|
|GET|`/organizadores`|Listar organizadores|200|
|GET|`/organizadores/{id}`|Ver un organizador|200, 404|
|POST|`/organizadores`|Crear un organizador|201, 400|
|PUT|`/organizadores/{id}`|Modificar un organizador|200, 400, 404|
|DELETE|`/organizadores/{id}`|Borrar un organizador|204, 404, 409|

### Talleres: `/talleres`

|Método|Ruta|Descripción|Respuestas|
|-|-|-|-|
|GET|`/talleres`|Listar talleres|200|
|GET|`/talleres/{id}`|Ver un taller con su organizador y participantes|200, 404|
|POST|`/talleres`|Crear un taller|201, 400, 404, 409|
|PUT|`/talleres/{id}`|Modificar un taller|200, 400, 404, 409|
|DELETE|`/talleres/{id}`|Borrar un taller|204, 404|

### Participantes: `/participantes`

|Método|Ruta|Descripción|Respuestas|
|-|-|-|-|
|GET|`/participantes`|Listar participantes|200|
|POST|`/participantes`|Crear un participante|201, 400|

### Inscripciones: `/talleres/{tallerId}/participantes`

|Método|Ruta|Descripción|Respuestas|
|-|-|-|-|
|POST|`/talleres/{tallerId}/participantes/{participanteId}`|Inscribir un participante en un taller|201, 404, 409|
|GET|`/talleres/{tallerId}/participantes`|Ver los participantes inscritos (lista vacía si no hay)|200, 404|
|DELETE|`/talleres/{tallerId}/participantes/{participanteId}`|Anular la inscripción (el taller y el participante se conservan)|204, 404|

> Si el participante y el taller existen pero \\\*\\\*no estaban inscritos\\\*\\\*, el `DELETE` termina con 204 sin cambiar nada.

### Ejemplos de peticiones

**Crear un organizador:** `POST /organizadores`

```json
{
  "nombre": "Centro Cultural Triana",
  "email": "info@centrotriana.es"
}
```

**Crear un taller:** `POST /talleres`

```json
{
  "titulo": "Cerámica para principiantes",
  "fechaHoraInicio": "2026-11-10T17:00:00",
  "fechaHoraFin": "2026-11-10T19:00:00",
  "organizadorId": 1
}
```

**Respuesta de `GET /talleres/1`**

```json
{
  "id": 1,
  "titulo": "Cerámica",
  "fechaHoraInicio": "2026-11-10T17:00:00",
  "fechaHoraFin": "2026-11-10T19:00:00",
  "organizador": {
    "id": 1,
    "nombre": "Centro Cultural Triana",
    "email": "info@centrotriana.es"
  },
  "participantes": \\\[
    { "id": 1, "nombreCompleto": "Ana García López" },
    { "id": 2, "nombreCompleto": "Luis Pérez Martín" }
  ]
}
```

**Crear un participante:** `POST /participantes`

```json
{
  "nombreCompleto": "Marta Ruiz Soto"
}
```

---

## Validaciones y errores

### Validaciones

* Los nombres, títulos y el email del organizador **no pueden estar vacíos**.
* El email debe tener un **formato válido**.
* Las fechas de inicio y fin son **obligatorias**.
* **Validación personalizada** (`@FechasValidas`): la fecha de fin debe ser **estrictamente posterior** a la de inicio.
* El **título del taller es único**. Se ignoran las mayúsculas y los espacios al principio y al final. Al modificar un taller, mantener su propio título no da error.

### Errores

Todos los errores se devuelven en formato **ProblemDetail**:

|Código|Cuándo ocurre|
|-|-|
|**400 Bad Request**|Los datos enviados no son válidos (incluye la lista de campos con error en `invalid-params`)|
|**404 Not Found**|El organizador, taller o participante no existe|
|**409 Conflict**|`TallerYaExisteException`: ya existe un taller con ese título|
|**409 Conflict**|`ParticipanteYaInscritoException`: el participante ya está inscrito en ese taller|
|**409 Conflict**|Se intenta borrar algo que tiene datos asociados (por ejemplo, un organizador con talleres)|

**Ejemplo de error 409:**

```json
{
  "type": "https://example.com/errors/participante-ya-inscrito",
  "title": "Participante ya inscrito",
  "status": 409,
  "detail": "El participante 3 ya está inscrito en el taller 2",
  "instance": "/talleres/2/participantes/3"
}
```

---

## Estructura del proyecto

```
src/main/java/com/salesianostriana/dam
├── config/        Configuración de los mensajes de validación
├── controllers/   Controladores REST (reciben las peticiones)
├── dto/           Objetos de entrada y salida de la API
├── error/         Excepciones y gestor global de errores
│   └── notfound/  Excepciones de "no encontrado" (404)
├── model/         Entidades JPA (Organizador, Taller, Participante)
├── repo/          Repositorios (acceso a la base de datos)
├── service/       Lógica de negocio
├── utils/         Datos de ejemplo (DataSeed)
└── validation/    Validación personalizada de fechas
```

La API sigue una arquitectura por capas: **Controller, Service y Repository**. Los controladores no devuelven las entidades directamente, sino **DTOs**, para evitar referencias circulares y no exponer datos innecesarios.

---

## Probar con Postman

En la carpeta [`postman/`](postman) hay una colección con todas las peticiones de la API, incluidos los casos de error.

1. Abre Postman y pulsa **Import**.
2. Selecciona el archivo `postman/talleres.postman\\\_collection.json`.
3. Arranca la aplicación y lanza las peticiones.
