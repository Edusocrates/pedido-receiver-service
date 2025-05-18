# 🧾 pedido-receiver-service

Microsserviço responsável por **receber, processar e armazenar** pedidos realizados no sistema. Ele é parte integrante de um sistema baseado em microserviços, usando arquitetura Clean Architecture, mensageria com RabbitMQ e banco de dados PostgreSQL.

---

## 📦 Funcionalidades

- Criar um novo pedido com itens e dados de pagamento.
- Atualizar o status do pedido diretamente no banco.
- Persistência de pedidos com relacionamento completo:
    - `Pedido` → `ItemPedido` (1:N)
    - `Pedido` → `DadosPagamento` (1:1)
- Integração com RabbitMQ (escuta de eventos de pedidos).
- Implementação com Clean Architecture:
    - Entities
    - Use Cases
    - Handlers
    - Gateways (interfaces e implementações)
    - Adapters
    - DTOs para entrada/saída
    - Mappers
    - Controllers REST

---

## 🧱 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **RabbitMQ**
- **Lombok**
- **Docker / Docker Compose**
- **Clean Architecture**

---

## 🗂️ Estrutura de Pastas
```plaintext
src/main/java/com/fiap/rm358568/edusocrates/pedido_receiver_service
├── API
│ ├── controller
│ ├── requests
│ ├── responses
├── application
│ ├── gateways
│ ├── handlers
│ ├── usecases
├── dominio
│ ├── entities
│ ├── enums
│ ├── exceptions
│ └── mapper
├── infrastructure
│ ├── config
│ ├── consumer
│ ├── entity
│ ├── mapper
│ ├── persistence
│ └── repository

```
## 🔄 Fluxo de Criação de Pedido

1. **Recebe** um `CriarPedidoRequest` via endpoint HTTP (JSON).
2. Mapeia para `Pedido` (domínio).
3. Persiste o pedido (incluindo dados de pagamento e itens) com `CascadeType.ALL`.
4. Retorna `PedidoResponse` com dados persistidos.


## 📘 Endpoints REST
| Método | Endpoint                | Descrição                   |
|--------|-------------------------|-----------------------------|
| POST   | /api/pedido             | Cria um novo pedido         |
| GET    | /api/pedido/{id}        | Busca um pedido pelo ID     |
| PATCH  | /api/pedido/{id}/status | Atualiza o status do pedido |
| GET    | /api/pedido/            | Busca TODOS pedidos         |

## 🐇 Mensageria com RabbitMQ
Configuração pronta para consumo de mensagens (em construção).

Suporte para integração com eventos de pedido-criado e pedido-atualizado.


## 🛠️ Como rodar localmente
### Pré-requisitos
- Java 17
- Maven 3.8.6
- PostgreSQL 15
- RabbitMQ 3.11.14
- Docker
- Docker Compose

### Comandos
docker-compose up --build

## 🐘 Acesso ao Banco de Dados
- Host: localhost
- Porta: 5432
- Usuário: postgres
- Senha: postgres
- Banco: pedido_receiver_service
- URL: jdbc:postgresql://localhost:5432/pedido_receiver_service
- Driver: org.postgresql.Driver
- Dialect: org.hibernate.dialect.PostgreSQLDialect


## 🧪 Testes
- Testes unitários com JUnit 5 e Mockito.
- Testes de integração com Spring Boot Test.
- Cobertura de testes com Jacoco.

## 🐳  Docker
- Dockerfile para criar a imagem do serviço.
- docker-compose.yml para orquestração com PostgreSQL e RabbitMQ.
- Volumes para persistência de dados.
- Rede para comunicação entre serviços.

## 📦 Acesso ao RabbitMQ
- Host: localhost
- Porta: 15672
- Usuário: guest
- Senha: guest
- URL: http://localhost:15672
- Management URL: http://localhost:15672/api/queues
- Management API: http://localhost:15672/api/overview
- Management Port: 15672
- Management User: guest





## ✍️ Autor
- **Eduardo Sócrates Caria**
- **RM 358568**
- Turma: 6ATDJ
- Grupo: 15