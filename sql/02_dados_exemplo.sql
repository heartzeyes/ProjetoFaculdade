USE sistema_vacinacao;

INSERT INTO regiao (nome) VALUES
('Norte'),
('Sul'),
('Leste'),
('Oeste'),
('Centro');

INSERT INTO escolaridade (descricao) VALUES
('Ensino Fundamental'),
('Ensino Medio'),
('Ensino Superior'),
('Sem escolaridade');

INSERT INTO paciente (nome, idade, endereco, telefone, regiao_id, escolaridade_id, ficou_doente) VALUES
('Ana Souza', 25, 'Rua A, 100', '11999990001', 1, 2, false),
('Bruno Lima', 40, 'Rua B, 200', '11999990002', 2, 3, true),
('Carla Mendes', 32, 'Rua C, 300', '11999990003', 3, 1, false),
('Daniel Costa', 58, 'Rua D, 400', '11999990004', 4, 2, true),
('Eva Rocha', 19, 'Rua E, 500', '11999990005', 5, 3, false),
('Fernando Alves', 70, 'Rua F, 600', '11999990006', 1, 4, true),
('Gabriela Santos', 45, 'Rua G, 700', '11999990007', 2, 1, false);

INSERT INTO vacina (nome, fabricante, doenca_alvo, qtd_doses_recomendadas) VALUES
('CoronaVac', 'Butantan', 'COVID-19', 2),
('Pfizer', 'Pfizer', 'COVID-19', 2),
('Influenza', 'Fiocruz', 'Gripe', 1),
('Hepatite B', 'Instituto Nacional', 'Hepatite B', 3);

INSERT INTO aplicacao_vacina (paciente_id, vacina_id, data_aplicacao, numero_dose, observacao) VALUES
(1, 1, '2024-01-10', 1, 'Primeira dose'),
(1, 1, '2024-02-10', 2, 'Segunda dose'),
(2, 2, '2024-01-15', 1, 'Primeira dose'),
(3, 3, '2024-03-01', 1, 'Dose unica'),
(4, 1, '2024-01-20', 1, 'Primeira dose'),
(4, 1, '2024-02-20', 2, 'Segunda dose'),
(5, 2, '2024-04-05', 1, 'Primeira dose'),
(7, 4, '2024-05-01', 1, 'Primeira dose'),
(7, 4, '2024-06-01', 2, 'Segunda dose');
