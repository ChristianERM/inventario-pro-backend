# InventarioPro Backend

Backend de un sistema avanzado de inventario y compras desarrollado con Java, Spring Boot y SQL Server.

Este proyecto simula un sistema empresarial para la gestión de productos, categorías, proveedores, entradas y salidas de stock, historial de movimientos, dashboard resumen y automatización de alertas de bajo stock.

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

* Crear categorías.
* Listar categorías activas.
* Buscar categoría por ID.
* Actualizar categoría.
* Eliminación lógica.
* Restaurar categoría eliminada.

### Productos

* Crear productos.
* Listar productos activos.
* Buscar producto por ID.
* Actualizar producto.
* Eliminación lógica.
* Restaurar producto eliminado.
* Relación producto-categoría.
* Control de stock actual y stock mínimo.

### Proveedores

* Crear proveedores.
* Listar proveedores activos.
* Buscar proveedor por ID.
* Actualizar proveedor.
* Eliminación lógica.
* Restaurar proveedor eliminado.

### Movimientos de stock

* Registrar entradas de stock.
* Registrar salidas de stock.
* Validar stock insuficiente.
* Actualizar automáticamente el stock del producto.
* Consultar historial general de movimientos.
* Consultar historial por producto.

### Dashboard

* Total de productos activos.
* Total de categorías activas.
* Total de proveedores activos.
* Total de productos con bajo stock.
* Total de movimientos de stock.

### Automatización de alertas de stock

* Revisión automática de productos con bajo stock mediante tareas programadas con `@Scheduled`.
* Detección de productos cuyo stock actual es menor o igual al stock mínimo.
* Registro automático de alertas en base de datos.
* Validación para evitar alertas duplicadas pendientes.
* Consulta de alertas pendientes mediante endpoint REST.
* Resolución de alertas desde el sistema.
* Integración con frontend Angular para mostrar y resolver alertas.

## Endpoints principales

### Categorías

GET    /api/categories  
GET    /api/categories/{id}  
POST   /api/categories  
PUT    /api/categories/{id}  
DELETE /api/categories/{id}  
PATCH  /api/categories/{id}/restore  

### Productos

GET    /api/products  
GET    /api/products/{id}  
POST   /api/products  
PUT    /api/products/{id}  
DELETE /api/products/{id}  
PATCH  /api/products/{id}/restore  

### Proveedores

GET    /api/suppliers  
GET    /api/suppliers/{id}  
POST   /api/suppliers  
PUT    /api/suppliers/{id}  
DELETE /api/suppliers/{id}  
PATCH  /api/suppliers/{id}/restore  

### Movimientos de stock

POST /api/stock-movements/entries  
POST /api/stock-movements/outputs  
GET  /api/stock-movements  
GET  /api/stock-movements/product/{productId}  

### Dashboard

GET /api/dashboard/summary  

### Alertas de stock

GET   /api/stock-alerts/pending  
PATCH /api/stock-alerts/{id}/resolve  

## Automatización de bajo stock

El sistema cuenta con una tarea programada que se ejecuta automáticamente cada cierto intervalo de tiempo para revisar productos con bajo stock.

La condición evaluada es:

`currentStock <= minimumStock`

Cuando se detecta un producto con bajo stock, el sistema:

* Registra una alerta en la tabla `stock_alerts`.
* Evita crear alertas duplicadas si ya existe una alerta pendiente para ese producto.
* Permite consultar las alertas pendientes desde un endpoint REST.
* Permite marcar una alerta como resuelta.

Flujo general:

Scheduler Spring Boot  
↓  
Consulta productos con bajo stock  
↓  
Registra alerta en SQL Server  
↓  
Expone alertas por API REST  
↓  
Frontend Angular muestra la alerta en el dashboard  
↓  
Usuario puede marcar la alerta como resuelta  

## Swagger

Con el backend ejecutándose, la documentación de la API se puede ver en:

http://localhost:8080/swagger-ui/index.html

## Base de datos

El proyecto usa SQL Server.

Base de datos local:

`InventarioProDB`

Configuración local usada en `application.properties`:

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=InventarioProDB;encrypt=true;trustServerCertificate=true  
spring.datasource.username=inventario_user  
spring.datasource.password=********  

> Nota: Para un entorno real, las credenciales deberían manejarse mediante variables de entorno.

## Cómo ejecutar el proyecto

Clonar el repositorio:

git clone https://github.com/ChristianERM/inventario-pro-backend.git

Entrar al proyecto:

cd inventario-pro-backend

Ejecutar el backend:

.\mvnw.cmd spring-boot:run

El backend se ejecutará en:

http://localhost:8080

## Funcionalidades destacadas

* Arquitectura por capas: Controller, Service, Repository, DTO y Entity.
* Validaciones con Bean Validation.
* Manejo global de errores con `@RestControllerAdvice`.
* Eliminación lógica mediante campo `active`.
* Restauración de registros eliminados.
* Control automático de stock.
* Validación de stock insuficiente.
* Historial de entradas y salidas tipo kardex.
* Dashboard resumen conectado a datos reales.
* Automatización interna con `@Scheduled`.
* Detección automática de productos con bajo stock.
* Registro persistente de alertas en SQL Server.
* Prevención de alertas duplicadas pendientes.
* Endpoint para consultar alertas pendientes.
* Endpoint para marcar alertas como resueltas.
* Integración con frontend Angular para mostrar y resolver alertas.
* Documentación con Swagger/OpenAPI.

## Estado del proyecto

Proyecto backend funcional conectado a SQL Server.

Funcionalidades implementadas:

* CRUD de categorías.
* CRUD de productos.
* CRUD de proveedores.
* Registro de movimientos de stock.
* Validación de stock insuficiente.
* Dashboard resumen.
* Automatización de alertas de bajo stock.
* Consulta y resolución de alertas pendientes.

## Autor

**Christian Rolfer**

Proyecto desarrollado como parte de mi portafolio de desarrollo fullstack con Java, Spring Boot, SQL Server y Angular.