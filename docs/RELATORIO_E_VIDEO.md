# Relatorio e Roteiro de Video

## Estrutura recomendada do relatorio

O relatorio pode ter ate 5 paginas e seguir esta organizacao.

### 1. Contextualizacao do problema

Explique que o controle de informacoes de vacinacao e importante para acompanhar pacientes, vacinas aplicadas, regioes atendidas e possiveis relacoes entre vacinacao e situacao de saude.

### 2. Objetivo do projeto

O objetivo e desenvolver um sistema simples em Java, com interface de texto no terminal, usando banco de dados MySQL para cadastro, consulta, listagem e analise de informacoes de vacinacao.

### 3. Tecnologias utilizadas

Liste:

- Java 17.
- MySQL 8.
- JDBC.
- Maven.
- Terminal Windows.

Explique que essas tecnologias foram escolhidas por serem simples, multiplataforma e adequadas ao enunciado.

### 4. Estrutura de dados utilizada

Explique o uso de:

- `ArrayList<Paciente>` para pacientes cadastrados temporariamente durante a execucao.
- `HashMap<String, Paciente>` para busca rapida de paciente por telefone.
- `ArrayList<Vacina>` para vacinas temporarias.
- `ArrayList<AplicacaoVacina>` para aplicacoes temporarias.

Destaque que os dados passam por essas estruturas antes da persistencia no banco MySQL.

### 5. Modelagem conceitual, logica e fisica

Entidades principais:

- Paciente.
- Vacina.
- AplicacaoVacina.
- Regiao.
- Escolaridade.

Relacionamentos:

- Um paciente pertence a uma regiao.
- Um paciente possui uma escolaridade.
- Um paciente pode receber varias aplicacoes de vacina.
- Uma vacina pode ser aplicada em varios pacientes.
- A tabela `aplicacao_vacina` relaciona paciente e vacina.

Informe que a modelagem fisica esta no arquivo `sql/01_schema.sql`.

### 6. Dados inseridos

Explique que foram criados dados ficticios com:

- Pacientes de regioes diferentes.
- Escolaridades diferentes.
- Pacientes vacinados e nao vacinados.
- Pacientes que ficaram doentes e que nao ficaram.
- Vacinas com dose unica, duas doses e tres doses.
- Casos de doses completas e incompletas.

Informe que os dados estao em `sql/02_dados_exemplo.sql`.

### 7. Consultas realizadas

Descreva as analises implementadas:

- Vacinados por regiao.
- Vacinado x doente.
- Vacinacao por escolaridade.
- Percentual de vacinacao por regiao.
- Pacientes com doses incompletas.
- Vacinas aplicadas por paciente.
- Vacinas aplicadas por regiao.

Informe que as consultas estao em `sql/03_consultas_analise.sql` e tambem sao acessadas pelo menu da aplicacao.

### 8. Interface de texto

Mostre o menu principal:

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

Explique que toda interacao ocorre no terminal, conforme exigido pelo enunciado.

### 9. Exemplos de interacao

Inclua exemplos curtos:

```text
Opcao 1 - Cadastrar paciente
Nome: Joao Silva
Idade: 30
Telefone: 11999990010
ID da regiao: 1
ID da escolaridade: 2
Ja ficou doente? N
Paciente cadastrado com sucesso.
```

```text
Opcao 8 - Analises
Opcao 5 - Pacientes com doses incompletas
```

### 10. Conclusao

Conclua que o sistema atende aos requisitos principais do Projeto Integrador: Java, MySQL, JDBC, interface de texto, estrutura de dados e consultas de analise.

## Roteiro de video de ate 5 minutos

### 0:00 a 0:30 - Problema

Apresente a necessidade de organizar dados de vacinacao da populacao, permitindo consultas e analises simples.

### 0:30 a 1:00 - Objetivo

Explique que o objetivo foi criar uma aplicacao Java no terminal para cadastrar pacientes, vacinas, aplicacoes e exibir analises.

### 1:00 a 1:30 - Tecnologias

Mostre rapidamente:

- Java 17.
- MySQL.
- JDBC.
- Maven.
- Terminal.

### 1:30 a 2:00 - Banco de dados

Abra ou mostre o script `sql/01_schema.sql`.

Comente as tabelas:

- `paciente`.
- `vacina`.
- `aplicacao_vacina`.
- `regiao`.
- `escolaridade`.

### 2:00 a 2:30 - Estrutura de dados

Mostre os services e explique:

- `ArrayList` guarda objetos temporarios.
- `HashMap` permite localizar paciente por telefone.
- Depois os dados sao salvos no MySQL.

### 2:30 a 3:15 - Menu no terminal

Execute o sistema e mostre o menu principal.

Comando:

```bash
java -jar target/sistema-vacinacao-1.0.0.jar
```

### 3:15 a 3:45 - Cadastro

Demonstre um cadastro de paciente ou vacina.

Mostre a mensagem de sucesso e depois liste os registros.

### 3:45 a 4:20 - Aplicacao de vacina

Registre uma aplicacao escolhendo um paciente, uma vacina, data e numero da dose.

Depois mostre a opcao `7 - Exibir aplicacoes de vacina`.

### 4:20 a 4:45 - Analises

Mostre a opcao `8 - Exibir analises`.

Demonstre:

- Vacinado x doente.
- Vacinados por regiao.
- Pacientes com doses incompletas.

### 4:45 a 5:00 - Conclusao

Finalize dizendo que o sistema e simples, funcional e atende ao enunciado: Java, MySQL, estrutura de dados e interface de texto no terminal.
