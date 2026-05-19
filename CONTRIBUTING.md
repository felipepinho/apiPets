# Fluxo para Proximos PRs

Este projeto usa um fluxo simples baseado em branches curtas saindo da `main`.

## 1. Atualizar a main local

```bash
git switch main
git pull origin main
```

## 2. Criar uma branch para a tarefa

Use nomes curtos e claros:

- `feature/nome-da-funcionalidade`
- `fix/nome-do-ajuste`
- `docs/nome-da-documentacao`
- `test/nome-do-teste`
- `refactor/nome-da-mudanca`
- `chore/nome-da-tarefa`

Exemplo:

```bash
git switch -c feature/paginacao-pets
```

## 3. Desenvolver em commits pequenos

Mantenha cada PR focado em uma entrega. Evite misturar feature, refatoracao grande e mudanca de documentacao sem relacao.

Sugestoes de mensagens de commit:

- `Adiciona paginacao em pets`
- `Corrige validacao de CPF duplicado`
- `Atualiza collections da API`
- `Adiciona testes para consulta`

## 4. Validar antes de subir

No Windows:

```bash
mvnw.cmd test
```

No Linux/macOS:

```bash
./mvnw test
```

Se a mudanca afetar banco de dados, crie uma migration Flyway em `src/main/resources/db/migration`.

## 5. Revisar o README

Antes de abrir um PR, revise o `README.md` e confirme se ele continua correto para a mudanca feita.

Atualize o README quando houver:

- novo endpoint;
- mudanca de payload;
- mudanca em comandos de execucao ou testes;
- mudanca no Docker Compose ou banco local;
- nova collection ou ajuste no Swagger;
- qualquer instrucao que possa ficar desatualizada para quem clonar o projeto.

## 6. Enviar a branch

```bash
git add .
git commit -m "Mensagem curta e clara"
git push -u origin nome-da-branch
```

## 7. Abrir o PR

Abra o PR para `main` e preencha o template:

- resumo objetivo do que mudou;
- tipo de mudanca;
- checklist de validacao;
- evidencias quando houver comportamento visual, payloads ou respostas importantes.

## 8. Regras de qualidade do PR

Antes de pedir review, confira:

- controllers continuam sem regra de negocio;
- services concentram regras e validacoes de negocio;
- entities nao sao expostas diretamente nos controllers;
- DTOs, mappers e repositories seguem o padrao existente;
- erros novos seguem o formato padronizado;
- endpoints novos aparecem no Swagger;
- collections sao atualizadas quando a API muda;
- testes cobrem o comportamento principal;
- nenhum arquivo local ou sensivel foi versionado.

## 9. Depois do merge

```bash
git switch main
git pull origin main
git branch -d nome-da-branch
```

Se a branch remota ainda existir:

```bash
git push origin --delete nome-da-branch
```
