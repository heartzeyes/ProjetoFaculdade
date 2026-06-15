USE sistema_vacinacao;

-- Quantidade de vacinados por regiao
SELECT r.nome AS regiao, COUNT(DISTINCT av.paciente_id) AS total_vacinados
FROM regiao r
JOIN paciente p ON p.regiao_id = r.id
JOIN aplicacao_vacina av ON av.paciente_id = p.id
GROUP BY r.nome
ORDER BY r.nome;

-- Quantidade de doentes entre vacinados e nao vacinados
SELECT
    CASE WHEN av.paciente_id IS NULL THEN 'Nao vacinado' ELSE 'Vacinado' END AS status_vacinacao,
    CASE WHEN p.ficou_doente THEN 'Ficou doente' ELSE 'Nao ficou doente' END AS situacao_saude,
    COUNT(*) AS quantidade
FROM paciente p
LEFT JOIN (SELECT DISTINCT paciente_id FROM aplicacao_vacina) av ON av.paciente_id = p.id
GROUP BY status_vacinacao, situacao_saude
ORDER BY status_vacinacao, situacao_saude;

-- Vacinacao por escolaridade
SELECT e.descricao AS escolaridade, COUNT(DISTINCT av.paciente_id) AS total_vacinados
FROM escolaridade e
JOIN paciente p ON p.escolaridade_id = e.id
JOIN aplicacao_vacina av ON av.paciente_id = p.id
GROUP BY e.descricao
ORDER BY e.descricao;

-- Pacientes com doses incompletas
SELECT p.nome AS paciente,
       v.nome AS vacina,
       COUNT(av.id) AS doses_recebidas,
       v.qtd_doses_recomendadas AS doses_recomendadas
FROM paciente p
JOIN aplicacao_vacina av ON av.paciente_id = p.id
JOIN vacina v ON v.id = av.vacina_id
GROUP BY p.id, p.nome, v.id, v.nome, v.qtd_doses_recomendadas
HAVING COUNT(av.id) < v.qtd_doses_recomendadas
ORDER BY p.nome, v.nome;

-- Lista de vacinas aplicadas por paciente
SELECT p.nome AS paciente,
       v.nome AS vacina,
       av.numero_dose,
       av.data_aplicacao,
       COALESCE(av.observacao, '-') AS observacao
FROM aplicacao_vacina av
JOIN paciente p ON p.id = av.paciente_id
JOIN vacina v ON v.id = av.vacina_id
ORDER BY p.nome, v.nome, av.numero_dose;

-- Percentual simples de vacinacao por regiao
SELECT r.nome AS regiao,
       COUNT(DISTINCT p.id) AS total_pacientes,
       COUNT(DISTINCT av.paciente_id) AS total_vacinados,
       ROUND((COUNT(DISTINCT av.paciente_id) / COUNT(DISTINCT p.id)) * 100, 2) AS percentual_vacinados
FROM regiao r
JOIN paciente p ON p.regiao_id = r.id
LEFT JOIN aplicacao_vacina av ON av.paciente_id = p.id
GROUP BY r.nome
ORDER BY r.nome;

-- Quantidade de vacinas aplicadas por regiao
SELECT r.nome AS regiao, COUNT(av.id) AS total_aplicacoes
FROM regiao r
JOIN paciente p ON p.regiao_id = r.id
JOIN aplicacao_vacina av ON av.paciente_id = p.id
GROUP BY r.nome
ORDER BY r.nome;
