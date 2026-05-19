# Pets API

API REST para cadastro e gerenciamento de pets, tutores, veterinários, vacinas, consultas e histórico médico. O projeto foi estruturado em camadas para manter controllers simples, regras de negócio nos services e acesso ao banco isolado nos repositories.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Flyway
- Swagger/OpenAPI
- Docker Compose
- H2 para testes
- JUnit 5 e Mockito
- Maven

## Como Rodar Localmente

1. Suba o PostgreSQL:

```bash
docker compose up -d
```

2. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Banco de Dados Local

Configuração do profile `dev`:

- Database: `pets_api`
- User: `postgres`
- Password: `postgres`
- Port: `5433` no Windows/host, apontando para `5432` dentro do container

As tabelas são criadas automaticamente pelo Flyway a partir de `src/main/resources/db/migration`.

## Testes

Execute:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

Os testes usam o profile `test` com H2 em memória.

## Swagger

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui.html
```

## Collections

As collections para testar a API estão em `collections/`:

- `collections/postman/pets-api.postman_collection.json`
- `collections/insomnia/pets-api.insomnia_collection.json`
- `collections/bruno/Pets API`

Todas usam `baseUrl` com valor padrão `http://localhost:8080`.

## Principais Endpoints

### Tutores

- `POST /tutores`
- `GET /tutores`
- `GET /tutores/{id}`
- `PUT /tutores/{id}`
- `DELETE /tutores/{id}`
- `GET /tutores/{id}/pets`

### Pets

- `POST /pets`
- `GET /pets`
- `GET /pets/{id}`
- `PUT /pets/{id}`
- `PATCH /pets/{id}`
- `DELETE /pets/{id}`
- `GET /pets/tutor/{tutorId}`

### Veterinários

- `POST /veterinarios`
- `GET /veterinarios`
- `GET /veterinarios/{id}`
- `PUT /veterinarios/{id}`
- `DELETE /veterinarios/{id}`

### Vacinas

- `POST /pets/{petId}/vacinas`
- `GET /pets/{petId}/vacinas`
- `GET /vacinas/{id}`
- `PUT /vacinas/{id}`
- `DELETE /vacinas/{id}`

### Consultas

- `POST /consultas`
- `GET /consultas`
- `GET /consultas/{id}`
- `PUT /consultas/{id}`
- `PATCH /consultas/{id}/status`
- `DELETE /consultas/{id}`
- `GET /consultas/pet/{petId}`
- `GET /consultas/veterinario/{veterinarioId}`

### Histórico Médico

- `POST /pets/{petId}/historico`
- `GET /pets/{petId}/historico`
- `GET /historico/{id}`
- `PUT /historico/{id}`
- `DELETE /historico/{id}`

## Exemplos de Payloads

### Criar Tutor

```json
{
  "nome": "Ana Souza",
  "email": "ana@email.com",
  "telefone": "11999999999",
  "cpf": "12345678900"
}
```

### Criar Pet

```json
{
  "nome": "Thor",
  "especie": "Cachorro",
  "raca": "Golden Retriever",
  "idade": 4,
  "peso": 28.5,
  "sexo": "MACHO",
  "castrado": true,
  "tutorId": 1
}
```

### Criar Veterinário

```json
{
  "nome": "Dra. Marina Lima",
  "crmv": "CRMV-SP-12345",
  "especialidade": "Clínica geral",
  "telefone": "1133334444",
  "email": "marina@clinica.com"
}
```

### Criar Vacina

```json
{
  "nome": "Raiva",
  "dataAplicacao": "2026-05-19",
  "dataProximaDose": "2027-05-19",
  "veterinarioId": 1,
  "observacao": "Aplicação anual"
}
```

### Criar Consulta

```json
{
  "petId": 1,
  "veterinarioId": 1,
  "dataHora": "2026-05-20T14:30:00",
  "motivo": "Consulta de rotina",
  "diagnostico": null,
  "observacoes": "Tutor relatou boa alimentação"
}
```

### Atualizar Status da Consulta

```json
{
  "status": "REALIZADA"
}
```

### Criar Histórico Médico

```json
{
  "descricao": "Alergia a determinado medicamento",
  "tipo": "ALERGIA"
}
```

## Tratamento de Erros

Os erros seguem o formato:

```json
{
  "timestamp": "2026-05-19T16:00:00-03:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Mensagem amigável do erro",
  "path": "/endpoint"
}
```

## Estrutura do Projeto

```text
src/main/java/br/com/apiPets
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
└── service
```

## Boas Práticas Aplicadas

- Entidades não são expostas diretamente nos controllers.
- DTOs de request e response separam contrato HTTP do modelo JPA.
- Validações usam Bean Validation.
- Regras de negócio ficam nos services.
- Flyway versiona o schema do banco.
- Erros são centralizados com `@ControllerAdvice`.
- Testes cobrem services com Mockito e controller com MockMvc.

## Próximos Passos

- Adicionar autenticação e autorização.
- Criar paginação e filtros nos endpoints de listagem.
- Ampliar testes de integração.
- Adicionar auditoria de alterações.
- Publicar uma coleção do Postman/Insomnia.
