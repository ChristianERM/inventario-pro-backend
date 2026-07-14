# InventarioPro Backend

Backend de un sistema avanzado de inventario y compras desarrollado con Java, Spring Boot y SQL Server.

Este proyecto simula un sistema empresarial para la gestión de productos, categorías, proveedores, entradas y salidas de stock, historial de movimientos y dashboard resumen.

## Tecnologías utilizadas

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Hibernate
* SQL Server
* Maven
* Lombok
* Bean Validation
* Swagger / OpenAPI
* Git / GitHub

## Módulos implementados

### Categorías

* Crear categorías
* Listar categorías activas
* Buscar categoría por ID
* Actualizar categoría
* Eliminación lógica
* Restaurar categoría eliminada

### Productos

* Crear productos
* Listar productos activos
* Buscar producto por ID
* Actualizar producto
* Eliminación lógica
* Restaurar producto eliminado
* Relación producto-categoría
* Control de stock actual y stock mínimo

### Proveedores

* Crear proveedores
* Listar proveedores activos
* Buscar proveedor por ID
* Actualizar proveedor
* Eliminación lógica
* Restaurar proveedor eliminado

### Movimientos de stock

* Registrar entradas de stock
* Registrar salidas de stock
* Validar stock insuficiente
* Actualizar automáticamente el stock del producto
* Consultar historial general de movimientos
* Consultar historial por producto

### Dashboard

* Total de productos activos
* Total de categorías activas
* Total de proveedores activos
* Total de productos con bajo stock
* Total de movimientos de stock

## Endpoints principales

### Categorías

```http
GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}
PATCH  /api/categories/{id}/restore
```

### Productos

```http
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
PATCH  /api/products/{id}/restore
```

### Proveedores

```http
GET    /api/suppliers
GET    /api/suppliers/{id}
POST   /api/suppliers
PUT    /api/suppliers/{id}
DELETE /api/suppliers/{id}
PATCH  /api/suppliers/{id}/restore
```

### Movimientos de stock

```http
POST /api/stock-movements/entries
POST /api/stock-movements/outputs
GET  /api/stock-movements
GET  /api/stock-movements/product/{productId}
```

### Dashboard

```http
GET /api/dashboard/summary
```

## Swagger

Con el backend ejecutándose, la documentación de la API se puede ver en:

```http
http://localhost:8080/swagger-ui/index.html
```

## Base de datos

El proyecto usa SQL Server.

Base de datos local:

```sql
InventarioProDB
```

Configuración local usada en `application.properties`:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=InventarioProDB;encrypt=true;trustServerCertificate=true
spring.datasource.username=inventario_user
spring.datasource.password=********
```

> Nota: Para un entorno real, las credenciales deberían manejarse mediante variables de entorno.

## Cómo ejecutar el proyecto

Clonar el repositorio:

```bash
git clone https://github.com/ChristianERM/inventario-pro-backend.git
```

Entrar al proyecto:

```bash
cd inventario-pro-backend
```

Ejecutar el backend:

```bash
.\mvnw.cmd spring-boot:run
```

El backend se ejecutará en:

```http
http://localhost:8080
```

## Funcionalidades destacadas

* Arquitectura por capas: Controller, Service, Repository, DTO y Entity.
* Validaciones con Bean Validation.
* Manejo global de errores con `@RestControllerAdvice`.
* Eliminación lógica mediante campo `active`.
* Restauración de registros eliminados.
* Control automático de stock.
* Validación de stock insuficiente.
* Historial de entradas y salidas tipo kardex.
* Documentación con Swagger/OpenAPI.
