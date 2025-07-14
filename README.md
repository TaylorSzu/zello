# 🔊 Zello Backend API

Este é um projeto backend desenvolvido em **Java 17** utilizando o **Spring Boot 3.5.3**, com autenticação, persistência de dados em **MySQL**, documentação automática via **Swagger (OpenAPI)**, e organização pronta para produção.

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3.5.3  
- Spring Web  
- Spring Data JPA  
- Spring Security  
- MySQL  
- Lombok  
- MapStruct  
- Springdoc OpenAPI  
- DevTools  

## 📦 Requisitos

- Java 17+  
- Maven 3.8+  
- MySQL 8+ em execução  

## ⚙️ Configuração

No arquivo `src/main/resources/application.properties`, configure seu banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zello_db
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📥 Instalação

```bash
git clone https://github.com/TaylorSzu/zello.git
cd zello
mvn clean install
```

## ▶️ Execução

```bash
mvn spring-boot:run
```

Aplicação rodando em: http://localhost:8080  
Swagger UI: http://localhost:8080/swagger-ui.html

## 🗂 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/zello/
│   │   ├── controller/       # Camada de API (REST)
│   │   ├── dto/              # Objetos de Transferência de Dados
│   │   ├── entity/           # Entidades do banco de dados
│   │   ├── mapper/           # MapStruct: DTO <-> Entity
│   │   ├── repository/       # Interfaces JPA
│   │   ├── service/          # Regras de negócio
│   │   └── security/         # Configurações de segurança
│   └── resources/
│       ├── application.properties
│       └── static/ (opcional)
└── test/
```
