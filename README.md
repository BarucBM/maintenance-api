# maintenance-api

É um projeto pessoal para exercitar meus conhecimentos em SpringBoot. 
Se trata de uma API REST para gerenciar manutenções de equipamentos, permitindo criar, atualizar, listar e deletar registros de manutenção.

Para rodar o projeto, é necessário:
- Ter um Banco postgres 16 configurado : 'docker run --name maintenance-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=maintenance -p 5433:5432 -d postgres:16' 
- Ter o Java 21, rodar os testes com .\mvnw.cmd test.

## Rodando com Docker Compose (app + PostgreSQL)

Pré-requisito: Docker com Docker Compose.

1. Copie o arquivo de exemplo e preencha os valores:

   ```bash
   cp .env.example .env
   ```

   Variáveis:
    - `DB_USER` e `DB_PASSWORD`: credenciais do Postgres (use valores locais; o `.env` não vai para o Git).
    - `COMPOSE_DB_URL`: URL JDBC usada pela app dentro do Compose,
      `jdbc:postgresql://db:5432/maintenance`. O host `db` é o nome do serviço na rede do Compose.

2. Suba tudo:

   ```bash
   docker compose up --build
   ```

   A app só inicia depois que o banco estiver `healthy` (`depends_on` com `service_healthy`).
   O Flyway aplica as migrations na primeira subida.

3. Teste:

   ```bash
   curl http://localhost:8080/health
   ```

   Resposta esperada: HTTP 200 com `{"status":"UP"}`.

Parar:
- `docker compose down`: remove os containers e **mantém os dados** (volume `dbdata`).
- `docker compose down -v`: remove também o volume, **apagando os dados**; na próxima subida o Flyway recria o schema.