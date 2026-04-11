# Hospital Management System — Backend

A Spring Boot backend for managing hospital operations including patients, doctors, departments, and appointments,
secured using JWT authentication and role-based access control.

---

## Overview

This Hospital Management System (HMS) is a REST API backend built with Spring Boot. It manages hospital workflows such
as patient registration, doctor management, department organization, and appointment scheduling.

Each user has a secure login account using JWT authentication. The system supports three roles:

- PATIENT
- DOCTOR
- ADMIN

---

## Features

- Patient registration with linked user account
- Doctor creation and management
- Department creation and doctor assignment
- Head of Department (HOD) management
- Appointment booking between patient and doctor
- JWT authentication (stateless login system)
- Role-based access control (PATIENT, DOCTOR, ADMIN)
- Paginated patient listing
- Global exception handling
- Swagger API documentation
- Automatic admin user creation on startup
- Request logging filter and interceptors

---

## Tech Stack

| Layer      | Technology            |
|------------|-----------------------|
| Language   | Java 21               |
| Framework  | Spring Boot           |
| Security   | Spring Security + JWT |
| Database   | PostgreSQL            |
| ORM        | Spring Data JPA       |
| Build Tool | Maven                 |
| Utility    | Lombok                |
| API Docs   | Swagger               |

---

## System Architecture

The system uses a User-based identity model:

User is the authentication entity, while Patient and Doctor are profile entities linked to User.

---

## API Overview

Base URL: /api/v1

### Patient APIs

- POST /patient/create → Register patient
- GET /patient/getall → Get all patients (paginated)
- GET /patient/get/{id} → Get patient by ID
- DELETE /patient/delete/{id} → Delete patient

### Doctor APIs

- POST /doctor/add → Create doctor
- DELETE /doctor/delete/{id} → Delete doctor

### Department APIs

- POST /department/add → Create department
- POST /department/addmultiple → Create multiple departments
- PUT /department/assign/hod → Assign HOD
- POST /department/assigndoctor → Assign doctor
- DELETE /department/removehod/{id} → Remove HOD

### Appointment APIs

- POST /appointment/create → Book appointment

### Health Check

- GET /health/check → Server status

---

## Setup Instructions

### Requirements

- Java 21
- Maven
- PostgreSQL

---

### Create Database

```sql
CREATE
DATABASE hms_db;
````

### Configure application-dev.yml

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hms_db
    username: postgres
    password: root

  jpa:
    hibernate:
      ddl-auto: update

utils:
  jwt:
    secret: your-secret
    expiry: 10

  admin:
    email: admin@example.com
    password: admin123

```

## Run Application

```
mvn spring-boot:run
```

## Access the APIs

```
http://localhost:8080/swagger-ui/index.html