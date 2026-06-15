# Sistema de Vacinacao

Projeto Integrador academico para gerenciamento simples de informacoes de vacinacao da populacao.

O sistema foi desenvolvido em Java com interface de texto no terminal, banco de dados MySQL e conexao via JDBC.

## Escopo

O sistema permite:

- Cadastrar pacientes.
- Cadastrar vacinas.
- Registrar aplicacoes de vacina em pacientes.
- Listar pacientes, vacinas e aplicacoes.
- Consultar paciente por ID, nome ou telefone.
- Exibir analises simples sobre vacinacao.
- Demonstrar uso de estruturas de dados em Java antes da persistencia no banco.

## Tecnologias

- Java 17.
- MySQL 8.
- JDBC.
- Maven.
- MySQL Connector/J.
- Terminal Windows.

## Arquitetura

Pacotes principais:

- `app`: menu principal e interacao via terminal.
- `model`: classes de dominio, como `Paciente`, `Vacina` e `AplicacaoVacina`.
- `service`: regras simples, validacoes e estruturas de dados temporarias.
- `dao`: comandos SQL e acesso ao MySQL.
- `database`: criacao da conexao JDBC.
- `util`: leitura segura de dados pelo terminal.

Fluxo geral:

```text
Terminal -> Main -> Service -> DAO -> MySQL
```

## Estruturas de dados usadas

O requisito de estrutura de dados em Java e atendido nos services:

- `ArrayList<Paciente> pacientesTemporarios`.
- `HashMap<String, Paciente> pacientesPorTelefone`.
- `ArrayList<Vacina> vacinasTemporarias`.
- `ArrayList<AplicacaoVacina> aplicacoesTemporarias`.

Essas estruturas recebem os objetos durante a execucao antes da gravacao no MySQL. A opcao `9` do menu mostra os totais armazenados nessas estruturas durante a execucao atual.

## Estrutura do projeto

```text
ProjetoFaculdade/
  pom.xml
  README.md
  sql/
    01_schema.sql
    02_dados_exemplo.sql
    03_consultas_analise.sql
  docs/
    RELATORIO_E_VIDEO.md
  src/main/java/br/com/projetointegrador/
    app/Main.java
    database/ConexaoMySQL.java
    dao/
    model/
    service/
    util/
```

## A. Pre-requisitos

- Java JDK 17 ou superior.
- MySQL Server 8 ou superior.
- Maven 3.8 ou superior.
- Windows.
- Editor recomendado: VS Code, IntelliJ IDEA ou Eclipse.

Verificar Java no PowerShell ou CMD:

```powershell
java -version
```

Verificar Maven no PowerShell ou CMD:

```powershell
mvn -version
```

## B. Configuracao do banco de dados

1. Abra o terminal na pasta do projeto.
2. Execute o script de criacao do banco.
3. Execute o script de dados de exemplo.

Windows PowerShell ou CMD:

```powershell
mysql -u root -p < sql\01_schema.sql
mysql -u root -p < sql\02_dados_exemplo.sql
```

O script cria o banco `sistema_vacinacao`.

Atencao: `01_schema.sql` usa `DROP DATABASE IF EXISTS`, ou seja, recria o banco e apaga dados anteriores.

## Configuracao de usuario e senha

Por padrao, a aplicacao tenta conectar usando:

```text
URL: jdbc:mysql://localhost:3306/sistema_vacinacao?useSSL=false&serverTimezone=America/Sao_Paulo&characterEncoding=UTF-8
Usuario: root
Senha: vazia
```

Voce pode configurar de duas formas no Windows.

Windows PowerShell:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="sua_senha"
```

Tambem e possivel passar por parametro ao executar no Windows:

```powershell
java -DDB_USER=root -DDB_PASSWORD=sua_senha -jar target\sistema-vacinacao-1.0.0.jar
```

Se necessario, altere diretamente em:

```text
src/main/java/br/com/projetointegrador/database/ConexaoMySQL.java
```

## C. Como executar no Windows

No PowerShell ou CMD, dentro da pasta do projeto:

```powershell
mvn clean package
java -jar target\sistema-vacinacao-1.0.0.jar
```

Com senha do MySQL por parametro:

```powershell
mvn clean package
java -DDB_USER=root -DDB_PASSWORD=sua_senha -jar target\sistema-vacinacao-1.0.0.jar
```

## D. Como acessar o sistema

O sistema roda no terminal/console. Ao executar o `.jar`, sera exibido o menu:

```text
===== SISTEMA DE VACINACAO =====
1 - Cadastrar paciente
2 - Cadastrar vacina
3 - Registrar aplicacao de vacina
4 - Listar pacientes
5 - Listar vacinas
6 - Consultar paciente
7 - Exibir aplicacoes de vacina
8 - Exibir analises
9 - Exibir uso de estruturas de dados
0 - Sair
```

## E. Como testar cada funcionalidade

### Cadastro de paciente

1. Escolha a opcao `1`.
2. Preencha nome, idade, endereco e telefone.
3. Escolha o ID da regiao exibida pelo sistema.
4. Escolha o ID da escolaridade exibida pelo sistema.
5. Informe se ja ficou doente com `S` ou `N`.
6. Resultado esperado: `Paciente cadastrado com sucesso`.
7. Confirme na opcao `4 - Listar pacientes`.

### Cadastro de vacina

1. Escolha a opcao `2`.
2. Preencha nome, fabricante, doenca alvo e quantidade de doses recomendadas.
3. Resultado esperado: `Vacina cadastrada com sucesso`.
4. Confirme na opcao `5 - Listar vacinas`.

### Registro de aplicacao de vacina

1. Escolha a opcao `3`.
2. O sistema lista pacientes e vacinas disponiveis.
3. Informe o ID do paciente.
4. Informe o ID da vacina.
5. Informe a data no formato `AAAA-MM-DD`.
6. Informe o numero da dose.
7. Informe observacao, se necessario.
8. Resultado esperado: `Aplicacao registrada com sucesso`.
9. Confirme na opcao `7 - Exibir aplicacoes de vacina`.

### Listagem de pacientes

1. Escolha a opcao `4`.
2. O sistema deve mostrar todos os pacientes cadastrados.

### Listagem de vacinas

1. Escolha a opcao `5`.
2. O sistema deve mostrar todas as vacinas cadastradas.

### Consulta de paciente

1. Escolha a opcao `6`.
2. Escolha consulta por ID ou por nome/telefone.
3. Informe o dado solicitado.
4. Resultado esperado: dados do paciente ou mensagem de nao encontrado.

### Analise vacinado x doente

1. Escolha a opcao `8`.
2. Escolha a opcao `2 - Vacinado x doente`.
3. Interprete a quantidade de pessoas que ficaram doentes separada por vacinadas e nao vacinadas.

### Analise vacinado x escolaridade

1. Escolha a opcao `8`.
2. Escolha a opcao `3 - Vacinado x escolaridade`.
3. Interprete a quantidade de vacinados em cada escolaridade.

### Analise vacinado x regiao

1. Escolha a opcao `8`.
2. Escolha a opcao `1 - Quantidade de vacinados por regiao` ou `4 - Percentual de vacinacao por regiao`.
3. Interprete quais regioes possuem mais ou menos pacientes vacinados.

### Pacientes com doses incompletas

1. Escolha a opcao `8`.
2. Escolha a opcao `5 - Pacientes com doses incompletas`.
3. Usando os dados de exemplo, devem aparecer pacientes com menos doses recebidas que o recomendado para a vacina.

### Uso de estrutura de dados

1. Cadastre um paciente, vacina ou aplicacao durante a execucao.
2. Escolha a opcao `9`.
3. O sistema mostra os objetos mantidos em `ArrayList` e o uso de `HashMap`.

### Teste no Windows

1. Rode no Windows usando os comandos da secao C.
2. Verifique conexao com MySQL, menu, cadastro, listagens e analises.
3. Antes da apresentacao, teste no computador Windows onde o projeto sera demonstrado.

## F. Problemas comuns

Erro de conexao com MySQL:

- Verifique se o servico MySQL esta iniciado.
- Confirme usuario, senha e porta.

Porta incorreta:

- O padrao e `3306`.
- Se o MySQL estiver em outra porta, configure `DB_URL`.

Usuario ou senha incorretos:

- Configure `DB_USER` e `DB_PASSWORD`.
- Ou passe `-DDB_USER` e `-DDB_PASSWORD` ao executar.

Banco nao criado:

- Execute `sql/01_schema.sql`.
- Execute `sql/02_dados_exemplo.sql` para popular os dados.

Driver JDBC nao encontrado:

- Execute `mvn clean package`.
- Confirme acesso a internet na primeira execucao para o Maven baixar dependencias.

Java nao encontrado no terminal:

- Instale JDK 17.
- Configure a variavel `PATH`.
- Teste com `java -version`.

Problema de encoding com acentos:

- Use terminal configurado para UTF-8.
- No Windows, prefira PowerShell ou Windows Terminal.

Erro ao executar no Windows:

- Use barra invertida no caminho do jar: `target\sistema-vacinacao-1.0.0.jar`.
- Confirme que Java, Maven e MySQL estao no `PATH`.

## Consultas SQL de analise

As consultas tambem estao em:

```text
sql/03_consultas_analise.sql
```

Elas demonstram:

- Quantidade de vacinados por regiao.
- Doentes entre vacinados e nao vacinados.
- Vacinacao por escolaridade.
- Pacientes com doses incompletas.
- Lista de vacinas aplicadas por paciente.
- Percentual simples de vacinacao por regiao.
- Quantidade de vacinas aplicadas por regiao.
