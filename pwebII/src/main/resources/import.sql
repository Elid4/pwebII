
-- =========================================
-- PERFIS
-- =========================================

INSERT INTO perfil (id, nome) VALUES
                                  (1, 'ADMIN'),
                                  (2, 'SECRETARIO');

-- =========================================
-- PESSOAS
-- 1: Administrador
-- 2: Secretário
-- 3 a 7: Médicos
-- 8 a 12: Pacientes
-- =========================================

INSERT INTO pessoa (id, email, telefone) VALUES
                                             (1, 'admin@amrita.com', '(63) 99999-0001'),
                                             (2, 'secretaria@amrita.com', '(63) 99999-0002'),
                                             (3, 'ana.medica@amrita.com', '(63) 99999-0003'),
                                             (4, 'bruno.medico@amrita.com', '(63) 99999-0004'),
                                             (5, 'carla.medica@amrita.com', '(63) 99999-0005'),
                                             (6, 'diego.medico@amrita.com', '(63) 99999-0006'),
                                             (7, 'elisa.medica@amrita.com', '(63) 99999-0007'),
                                             (8, 'joao@email.com', '(63) 99999-0008'),
                                             (9, 'maria@email.com', '(63) 99999-0009'),
                                             (10, 'pedro@email.com', '(63) 99999-0010'),
                                             (11, 'lucia@email.com', '(63) 99999-0011'),
                                             (12, 'rafael@email.com', '(63) 99999-0012');

-- =========================================
-- PESSOAS FÍSICAS
-- =========================================

INSERT INTO pessoa_fisica
(id_pessoa, nome, cpf, data_nascimento) VALUES
                                            (1, 'Administrador do Sistema', '11111111111', '1980-01-10'),
                                            (2, 'Mariana Oliveira', '22222222222', '1985-04-15'),
                                            (3, 'Ana Paula Mendes', '33333333333', '1982-03-20'),
                                            (4, 'Bruno Henrique Costa', '44444444444', '1979-07-12'),
                                            (5, 'Carla Fernanda Lima', '55555555555', '1988-11-05'),
                                            (6, 'Diego Martins Rocha', '66666666666', '1984-02-18'),
                                            (7, 'Elisa Rodrigues Alves', '77777777777', '1990-09-25'),
                                            (8, 'João Pedro Santos', '88888888888', '1995-05-14'),
                                            (9, 'Maria Eduarda Silva', '99999999999', '1992-08-21'),
                                            (10, 'Pedro Augusto Souza', '10101010101', '1987-12-03'),
                                            (11, 'Lúcia Helena Ferreira', '12121212121', '1975-06-17'),
                                            (12, 'Rafael Gomes Pereira', '13131313131', '2000-10-09');

-- =========================================
-- MÉDICOS
-- =========================================

INSERT INTO tb_medico (id_pessoa_fisica, crm) VALUES
                                                  (3, 'CRM-TO 10001'),
                                                  (4, 'CRM-TO 10002'),
                                                  (5, 'CRM-TO 10003'),
                                                  (6, 'CRM-TO 10004'),
                                                  (7, 'CRM-TO 10005');

-- =========================================
-- PACIENTES
-- =========================================

INSERT INTO tb_paciente (id_pessoa_fisica) VALUES
                                               (8),
                                               (9),
                                               (10),
                                               (11),
                                               (12);

-- =========================================
-- USUÁRIOS
-- Cada usuário está associado a uma Pessoa.
-- =========================================

INSERT INTO usuario
(id, login, pessoa_id, password, ativo) VALUES
                                            (1, 'admin', 1, 'senha-demo-admin', TRUE),
                                            (2, 'secretario', 2, 'senha-demo-secretario', TRUE);

-- =========================================
-- PERFIS DOS USUÁRIOS
-- =========================================

INSERT INTO usuario_perfil (perfil_id, usuario_id) VALUES
                                                       (1, 1),
                                                       (2, 2);

-- =========================================
-- CONSULTAS
-- 4 médicos diferentes por paciente
-- =========================================

-- João Pedro
INSERT INTO tb_consulta
(id, data, valor, observacao, medico_id, paciente_id) VALUES
                                                          (1, '2026-10-01', 180.00, 'Consulta de rotina', 3, 8),
                                                          (2, '2026-10-08', 200.00, 'Avaliação clínica', 4, 8),
                                                          (3, '2026-10-15', 160.00, 'Retorno médico', 5, 8),
                                                          (4, '2026-10-22', 190.00, 'Avaliação de exames', 6, 8);

-- Maria Eduarda
INSERT INTO tb_consulta
(id, data, valor, observacao, medico_id, paciente_id) VALUES
                                                          (5, '2026-10-02', 180.00, 'Consulta inicial', 4, 9),
                                                          (6, '2026-10-09', 200.00, 'Avaliação clínica', 5, 9),
                                                          (7, '2026-10-16', 160.00, 'Acompanhamento', 6, 9),
                                                          (8, '2026-10-23', 190.00, 'Retorno médico', 7, 9);

-- Pedro Augusto
INSERT INTO tb_consulta
(id, data, valor, observacao, medico_id, paciente_id) VALUES
                                                          (9, '2026-10-03', 180.00, 'Consulta de rotina', 3, 10),
                                                          (10, '2026-10-10', 200.00, 'Avaliação clínica', 5, 10),
                                                          (11, '2026-10-17', 160.00, 'Retorno médico', 6, 10),
                                                          (12, '2026-10-24', 190.00, 'Análise de exames', 7, 10);

-- Lúcia Helena
INSERT INTO tb_consulta
(id, data, valor, observacao, medico_id, paciente_id) VALUES
                                                          (13, '2026-10-04', 180.00, 'Consulta inicial', 3, 11),
                                                          (14, '2026-10-11', 200.00, 'Avaliação clínica', 4, 11),
                                                          (15, '2026-10-18', 160.00, 'Acompanhamento', 6, 11),
                                                          (16, '2026-10-25', 190.00, 'Retorno médico', 7, 11);

-- Rafael Gomes
INSERT INTO tb_consulta
(id, data, valor, observacao, medico_id, paciente_id) VALUES
                                                          (17, '2026-10-05', 180.00, 'Consulta de rotina', 3, 12),
                                                          (18, '2026-10-12', 200.00, 'Avaliação clínica', 4, 12),
                                                          (19, '2026-10-19', 160.00, 'Retorno médico', 5, 12),
                                                          (20, '2026-10-26', 190.00, 'Avaliação de exames', 7, 12);

INSERT INTO usuario
(id, login, pessoa_id, password, ativo) VALUES

                                            (3, 'ana.medica', 3, 'senha-demo-ana', TRUE),
                                            (4, 'bruno.medico', 4, 'senha-demo-bruno', TRUE),
                                            (5, 'carla.medica', 5, 'senha-demo-carla', TRUE),
                                            (6, 'diego.medico', 6, 'senha-demo-diego', TRUE),
                                            (7, 'elisa.medica', 7, 'senha-demo-elisa', TRUE),

                                            (8, 'joao', 8, 'senha-demo-joao', TRUE),
                                            (9, 'maria', 9, 'senha-demo-maria', TRUE),
                                            (10, 'pedro', 10, 'senha-demo-pedro', TRUE),
                                            (11, 'lucia', 11, 'senha-demo-lucia', TRUE),
                                            (12, 'rafael', 12, 'senha-demo-rafael', TRUE);