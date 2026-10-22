# maintenance-api

É um projeto pessoal para exercitar meus conhecimentos em SpringBoot. 
Se trata de uma API REST para gerenciar manutenções de equipamentos, permitindo criar, atualizar, listar e deletar registros de manutenção.

Para rodar o projeto, é necessário:
- Ter um Banco postgres 16 configurado : 'docker run --name maintenance-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=maintenance -p 5433:5432 -d postgres:16' 
- Ter o Java 21, rodar os testes com .\mvnw.cmd test.