# 🦷 Sistema de Citas Dentales
Proyecto Final – Soluciones Web y Aplicaciones Distribuidas
Carrera: Ingeniería de Sistemas Computacionales

## 📌Descripción del Proyecto
El **Sistema de Citas Dentales** es una aplicación web diseñada para que los pacientes puedan **registrarse, iniciar sesión y reservar citas** con dentistas según la disponibilidad de horarios.

Incluye:
- Gestión de usuarios (clientes y administradores).
- Gestión de médicos y servicios dentales.
- Registro, consulta, modificación y cancelación de citas.
- Validaciones inteligentes (no permitir dos citas con el mismo médico, misma fecha y hora).
- Panel administrativo para gestión interna.

Este sistema está desarrollado utilizando **Angular** (front-end) y **Spring Boot** (back-end), aplicando arquitectura cliente-servidor como se enseñó en el curso.

## 🧑‍💻Tecnologías Utilizadas
### Frontend
- Angular 17+
- TypeScript
- HTML / CSS
- Bootstrap
- JWT para autenticación

### Backend
- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL

### Herramientas de Desarrollo
- GitHub Desktop
- Git / GitHub
- Spring Tool Suite (STS)
- VS Code / Cursor
- Postman
- Xampp

## ⚙️ Cómo Ejecutar el Proyecto
### 🔧 1. Clonar el repositorio
```
git clone https://github.com/tu-usuario/sistema-citas-dentales.git
```

### 2. Configurar base de datos (backend/Spring Boot)
Crear una base de datos en MySQL (Xampp):
```sql
CREATE DATABASE consultorio_dental;
```

Modificar el application.properties o application.yml:
```java
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_citas_dental
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3. Ejecutar el backend
Desde Spring Tool Suite:
Run As → Spring Boot App

O desde consola:
```
mvn spring-boot:run
```
Backend disponible en:
👉 http://localhost:8080

### 🌐 Configurar Frontend (Angular)
### 4. Instalar dependencias
En la carpeta /frontend/:
```
npm install

```
### 5. Ejecutar Angular
```
ng serve -o
```
Frontend disponible en:
👉 http://localhost:4200

## 🎓 Créditos
Proyecto desarrollado por:

**Piero Samir Sotomayor Pinto**,
**Angélica Geraldine Quispe Manayay**

Estudiantes de **Ingeniería de Sistemas Computacionales**
Curso: Soluciones Web y Aplicaciones Distribuidas

Docente: César Edinson Aguirre Rodríguez
Universidad: Universidad Privada del Norte (UPN)

## 📄 Licencia
Este proyecto es de uso académico y no está destinado para producción.