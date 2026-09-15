# 🚀 Help Desk API

API REST para gerenciamento de chamados de suporte técnico de TI.

O sistema permite que funcionários abram chamados e que técnicos assumam, acompanhem e concluam os atendimentos, mantendo o fluxo de status e as regras de negócio centralizadas na aplicação.

Projeto desenvolvido com **Java e Spring Boot** para prática de desenvolvimento backend, arquitetura em camadas, JPA, validações, consultas, relatórios e documentação de APIs.

---

## 🛠️ Tecnologias

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Flyway
* Bean Validation
* Springdoc OpenAPI / Swagger
* Lombok
* Docker / Docker Compose

---

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura organizada por domínio e separação de responsabilidades.

```text
src/main/java/dev/joaorooliveira/help_desk

├── domain
│   ├── chamado
│   │   ├── dto
│   │   ├── enums
│   │   └── validacoes
│   │       ├── assumir
│   │       └── concluir
│   │
│   ├── funcionario
│   │   └── dto
│   │
│   ├── tecnico
│   │   └── dto
│   │
│   └── relatorio
│
├── projection
│
└── infra
    ├── exception
    ├── specification
    └── springdoc
```

Principais responsabilidades:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
```

As regras de negócio mais específicas dos chamados são separadas em validadores.

---

## 👥 Principais entidades

### Funcionário

Representa o usuário que solicita suporte.

Principais informações:

* nome
* email
* ramal
* setor
* datas de criação e atualização

### Técnico

Representa o profissional responsável pelo atendimento.

Principais informações:

* nome
* email
* ramal
* especialidade
* datas de criação e atualização

### Chamado

Representa a solicitação de suporte.

Principais informações:

* título
* descrição
* categoria
* prioridade
* status
* funcionário solicitante
* técnico responsável
* solução
* data de abertura
* data de conclusão
* datas de criação e atualização

---

## 🔄 Fluxo do chamado

O chamado segue um fluxo controlado por regras de negócio:

```text
ABERTO
   │
   │ Técnico assume
   ▼
EM_ANDAMENTO
   │
   │ Técnico conclui
   ▼
CONCLUIDO
```

### Regras principais

* Todo chamado é criado com status `ABERTO`.
* Um chamado aberto pode ser assumido por um técnico.
* Ao ser assumido, o status passa para `EM_ANDAMENTO`.
* Um chamado precisa estar em andamento para ser concluído.
* Apenas o técnico responsável pode concluir o chamado.
* Para concluir um chamado, uma solução deve ser informada.
* Um chamado que já possui técnico não pode ser assumido novamente.
* Chamados concluídos não podem voltar para estados anteriores.
* Funcionários que possuem chamados associados não podem ser excluídos.

---

## 📋 Funcionalidades

### Funcionários

* Cadastro
* Consulta por ID
* Listagem paginada
* Filtros
* Atualização
* Exclusão com validação de chamados associados

### Técnicos

* Cadastro
* Consulta por ID
* Listagem paginada
* Filtros
* Atualização
* Exclusão

### Chamados

* Abertura de chamado
* Consulta por funcionário
* Consulta por técnico
* Filtros
* Paginação
* Busca por ID
* Atribuição de técnico
* Conclusão de chamado

### Relatórios

O projeto possui consultas específicas para geração de estatísticas utilizando **SQL nativo e Projections**.

Relatórios disponíveis:

* Chamados por prioridade
* Chamados por status
* Chamados por categoria
* Chamados por técnico
* Chamados por setor

---

## 🔎 Filtros e paginação

As consultas de chamados e demais listagens utilizam paginação através do Spring Data.

Exemplo:

```http
GET /chamados/tecnico?status=ABERTO&prioridade=ALTA&page=0&size=10
```

Também são utilizados filtros dinâmicos através de `JpaSpecificationExecutor` e Specifications.

---

## 📊 Projections

Para relatórios e estatísticas, o projeto utiliza Projections para retornar somente os dados necessários.

Exemplo:

```sql
SELECT
    prioridade,
    COUNT(*) AS total
FROM chamado
GROUP BY prioridade
```

Isso permite gerar respostas como:

```json
[
  {
    "prioridade": "ALTA",
    "total": 15
  },
  {
    "prioridade": "MEDIA",
    "total": 32
  }
]
```

Os relatórios possuem um `Repository` específico para consultas analíticas, separado do repository responsável pelo CRUD de chamados.

---

## 🌐 Principais endpoints

### Funcionários

| Método | Endpoint             | Descrição             |
| ------ | -------------------- | --------------------- |
| POST   | `/funcionarios`      | Cadastrar funcionário |
| GET    | `/funcionarios`      | Listar funcionários   |
| GET    | `/funcionarios/{id}` | Buscar funcionário    |
| PUT    | `/funcionarios/{id}` | Atualizar funcionário |
| DELETE | `/funcionarios/{id}` | Excluir funcionário   |

### Técnicos

| Método | Endpoint         | Descrição         |
| ------ | ---------------- | ----------------- |
| POST   | `/tecnicos`      | Cadastrar técnico |
| GET    | `/tecnicos`      | Listar técnicos   |
| GET    | `/tecnicos/{id}` | Buscar técnico    |
| PUT    | `/tecnicos/{id}` | Atualizar técnico |
| DELETE | `/tecnicos/{id}` | Excluir técnico   |

### Chamados

| Método | Endpoint                         | Descrição                              |
| ------ | -------------------------------- | -------------------------------------- |
| POST   | `/chamados`                      | Abrir chamado                          |
| GET    | `/chamados/funcionario`          | Listar chamados para funcionários      |
| GET    | `/chamados/tecnico`              | Listar chamados para técnicos          |
| GET    | `/chamados/funcionario/{id}`     | Buscar chamado na visão do funcionário |
| GET    | `/chamados/tecnico/{id}`         | Buscar chamado na visão do técnico     |
| POST   | `/chamados/{idChamado}/assumir`  | Técnico assume o chamado               |
| POST   | `/chamados/{idChamado}/concluir` | Concluir chamado                       |

### Relatórios

| Método | Endpoint                              | Descrição                         |
| ------ | ------------------------------------- | --------------------------------- |
| GET    | `/relatorios/chamados/por-prioridade` | Chamados agrupados por prioridade |
| GET    | `/relatorios/chamados/por-status`     | Chamados agrupados por status     |
| GET    | `/relatorios/chamados/por-categoria`  | Chamados agrupados por categoria  |
| GET    | `/relatorios/chamados/por-tecnico`    | Chamados agrupados por técnico    |
| GET    | `/relatorios/chamados/por-setor`      | Chamados agrupados por setor      |

---

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** com **Flyway** para controle das migrações.

A aplicação utiliza:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

Dessa forma, o Hibernate valida a estrutura das entidades contra o banco, enquanto o Flyway é responsável pelas migrações.

### Docker

O PostgreSQL pode ser executado utilizando Docker Compose.

Exemplo:

```bash
docker compose up -d
```

O banco utilizado pela aplicação é:

```text
Banco: help_desk
Host: localhost
Porta: 5433
Usuário: postgres
```

A senha é carregada através da variável de ambiente:

```text
DB_PASSWORD
```

---

## ⚙️ Configuração

Crie um arquivo `.env`:

```env
DB_PASSWORD=postgres
```

Exemplo de configuração:

```properties
spring.application.name=help-desk

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.datasource.url=jdbc:postgresql://localhost:5433/help_desk

spring.flyway.enabled=true

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never
```

---

## ▶️ Executando o projeto

### 1. Subir o PostgreSQL

```bash
docker compose up -d
```

### 2. Executar a aplicação

Utilizando Maven:

```bash
./mvnw spring-boot:run
```

Ou execute a classe principal:

```text
HelpDeskApplication
```

A API estará disponível em:

```text
http://localhost:8080
```

---

## 📚 Documentação da API

A API possui documentação gerada automaticamente através do **Springdoc OpenAPI**.

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação está organizada por grupos:

* Funcionários
* Técnicos
* Chamados
* Relatórios

---

## 🧩 Tratamento de erros

O projeto utiliza exceções específicas para representar diferentes situações.

Exemplos:

```text
EntidadeNaoEncontradaException
RegraNegocioException
```

Além disso, as validações de entrada são realizadas utilizando **Bean Validation**.

Exemplo:

```java
@NotBlank
@NotNull
@Size
```

O tratamento global das exceções é realizado através de um `GlobalExceptionHandler`.

---

## ✅ Conceitos praticados

Este projeto foi desenvolvido com foco na prática dos seguintes conceitos:

* Java 21
* Orientação a objetos
* Spring Boot
* REST API
* DTOs
* Bean Validation
* Spring Data JPA
* Hibernate
* PostgreSQL
* Flyway
* Transactions
* Dirty Checking
* Specifications
* Projections
* SQL
* JOINs
* Paginação
* Exceções personalizadas
* Global Exception Handler
* Injeção de dependência
* Validações de regras de negócio
* OpenAPI / Swagger
* Docker e Docker Compose

---

## 📌 Status do projeto

Projeto desenvolvido para fins de estudo e portfólio, com foco em práticas de desenvolvimento backend utilizando Java e Spring Boot.
