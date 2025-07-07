# Reto Técnico Ceiba Software

Este proyecto es una aplicación para la gestión de órdenes e inventario, desarrollada en Java con Spring Boot. Incluye servicios de dominio, persistencia con JPA/Hibernate y pruebas de integración para asegurar la correcta funcionalidad de los controladores REST. Está diseñada para demostrar buenas prácticas en arquitectura, pruebas automatizadas y uso de base de datos en memoria para testing.

## Requisitos Previos

- Java 21 o superior (se recomienda Java 21)
- Maven o Gradle para gestión de dependencias y construcción del proyecto
- IDE compatible con Java y Spring Boot (IntelliJ IDEA, Eclipse, VSCode, etc.)
- Conexión a internet para descargar dependencias

## Instalación

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd <nombre-del-proyecto>
```

### 2. Configurar el entorno

El proyecto utiliza Spring Boot con configuración para ejecutar tests con base de datos H2 en memoria.
La configuración de tests está en src/test/resources/application-test.properties.
El script de datos iniciales para pruebas está en src/test/resources/data.sql.

### 3. Construir el proyecto y descargar dependencias

```shell
gradle clean build
```

### 5. Levantar la aplicación:

El comando para levantar la aplicación usando Gradle es:

```shell
./gradlew bootRun
```

Si estás en Windows, puedes usar:

```shell
gradlew.bat bootRun
```

Este comando compila el proyecto y arranca la aplicación Spring Boot en modo desarrollo.

### 6. Ejecutar los tests de integración

Los tests de integración están configurados para usar la base de datos H2 en memoria y ejecutan automáticamente el script data.sql para preparar datos de prueba.

Para ejecutar los tests:

```shell
gradle test
```

Los tests cubren los controladores REST, servicios de dominio y repositorios, aplicando el patrón AAA y usando Test Data Builders para facilitar la construcción de objetos de prueba.

## Estructura del Proyecto

	src/main/java - Código fuente principal
	    domain - Lógica de negocio y servicios
	    infrastructure - Adaptadores, controladores y persistencia
	    application - DTOs, comandos y handlers
	src/test/java - Tests automatizados
	src/test/resources - Configuración y scripts para tests (application-test.properties, data.sql)

## Consideraciones

Las entidades JPA usan secuencias para generación de IDs, configuradas para funcionar con H2 en tests.
El script data.sql inicializa la base de datos con datos consistentes para pruebas.
Los tests usan perfil test para cargar configuración específica y evitar interferencias con otros entornos.
Se recomienda limpiar el contexto de Spring entre tests para evitar efectos colaterales (@DirtiesContext).
