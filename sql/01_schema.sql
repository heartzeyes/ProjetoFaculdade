DROP DATABASE IF EXISTS sistema_vacinacao;

CREATE DATABASE sistema_vacinacao
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE sistema_vacinacao;

CREATE TABLE regiao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE escolaridade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    idade INT NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    telefone VARCHAR(30) NOT NULL,
    regiao_id INT NOT NULL,
    escolaridade_id INT NOT NULL,
    ficou_doente BOOLEAN NOT NULL,
    CONSTRAINT fk_paciente_regiao FOREIGN KEY (regiao_id) REFERENCES regiao(id),
    CONSTRAINT fk_paciente_escolaridade FOREIGN KEY (escolaridade_id) REFERENCES escolaridade(id)
);

CREATE TABLE vacina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100),
    doenca_alvo VARCHAR(100) NOT NULL,
    qtd_doses_recomendadas INT NOT NULL
);

CREATE TABLE aplicacao_vacina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    vacina_id INT NOT NULL,
    data_aplicacao DATE NOT NULL,
    numero_dose INT NOT NULL,
    observacao VARCHAR(255),
    CONSTRAINT fk_aplicacao_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_aplicacao_vacina FOREIGN KEY (vacina_id) REFERENCES vacina(id)
);

CREATE INDEX idx_paciente_regiao ON paciente(regiao_id);
CREATE INDEX idx_paciente_escolaridade ON paciente(escolaridade_id);
CREATE INDEX idx_aplicacao_paciente ON aplicacao_vacina(paciente_id);
CREATE INDEX idx_aplicacao_vacina ON aplicacao_vacina(vacina_id);
