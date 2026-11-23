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

INSERT INTO tb_medico (id_pessoa_fisica, crm) VALUES
                                                  (3, 'CRM-TO 12345'),
                                                  (4, 'CRM-TO 23456'),
                                                  (5, 'CRM-TO 34567'),
                                                  (6, 'CRM-TO 45678'),
                                                  (7, 'CRM-TO 56789');

INSERT INTO tb_paciente (id_pessoa_fisica) VALUES
                                               (8),
                                               (9),
                                               (10),
                                               (11),
                                               (12);

INSERT INTO perfil (id, nome) VALUES
                                  (1, 'ADMIN'),
                                  (2, 'SECRETARIO'),
                                  (3, 'MEDICO'),
                                  (4, 'PACIENTE');

INSERT INTO usuario
(id, login, pessoa_id, password, ativo) VALUES
                                            (1, 'admin', 1, '$2y$12$ZdEPsKHJ/xYIe8.ZGa6FJeQjVG4br/Q0tEwVQ9b3S5K4An6snFjeW', TRUE),
                                            (2, 'secretario', 2, '$2y$12$8bRZd9omIGxWBOAbM.fzt.Z5p.HWzUHnCtVK2.Yeksv8xSJqbEYMe', TRUE),
                                            (3, 'ana.medica', 3, '$2y$12$vYh9OxuiGo.fEDMhTOA1M.pmzB11vdLktQrN1vmURk3aBpRperejm', TRUE),
                                            (4, 'bruno.medico', 4, '$2y$12$1pnr4sIds4cF1ginyqXTQOWjEt2lwhPmNOukX.JX6zloru0nEkpmG', TRUE),
                                            (5, 'carla.medica', 5, '$2y$12$neO5AwwCbwaebBR4Kwu5i./TXA348pjkV4CSW3KJ03tIW.B0Mi39G', TRUE),
                                            (6, 'diego.medico', 6, '$2y$12$DNkAla3ro0w0sMx9V7zEuOnvGbHiR3Umq4XQTzu/jLqLyphhfoKUK', TRUE),
                                            (7, 'elisa.medica', 7, '$2y$12$9V.z2xoQ8uczjLdlBBVvR.Gdlrgcc59QIk9qC5iR97HsUv.WNyQ4a', TRUE),
                                            (8, 'joao', 8, '$2y$12$OqEUDftPwNNo/xQlBaIOFerEt6qJ6xfG2LyqbGAj/2w92vggQmL5C', TRUE),
                                            (9, 'maria', 9, '$2y$12$m7vGw9Dx1sUSZXJKrcMaf.aO1tFsphsFsDoHVEz5OKNvwBNRqjblq', TRUE),
                                            (10, 'pedro', 10, '$2y$12$ySJVDKte5JfTrl10zgXwdORlOq0ng4.QQ.t4lKTD7bbOe05z.qa.y', TRUE),
                                            (11, 'lucia', 11, '$2y$12$tI02yyQfgA6DL7VvNAR0te6CemE1iDXzGNpEHqICRXg.pA6nHCmUO', TRUE),
                                            (12, 'rafael', 12, '$2y$12$RDZkll7g0UgYuxCY3QgpyeI.MpPwfJ7q302hjyR2VFhOxXNWFCmBC', TRUE);

INSERT INTO usuario_perfil (perfil_id, usuario_id) VALUES
                                                       (1, 1),
                                                       (2, 2),
                                                       (3, 3),
                                                       (3, 4),
                                                       (3, 5),
                                                       (3, 6),
                                                       (3, 7),
                                                       (4, 8),
                                                       (4, 9),
                                                       (4, 10),
                                                       (4, 11),
                                                       (4, 12);

INSERT INTO tb_consulta
(id, data, valor, observacao, medico_id, paciente_id) VALUES
                                                          (1, '2026-10-01', 200.00, 'Consulta inicial e avaliação clínica.', 3, 8),
                                                          (2, '2026-10-15', 180.00, 'Retorno para avaliação dos exames.', 4, 8),
                                                          (3, '2026-11-01', 200.00, 'Acompanhamento do tratamento.', 5, 8),
                                                          (4, '2026-11-15', 180.00, 'Reavaliação clínica.', 6, 8),

                                                          (5, '2026-10-02', 200.00, 'Consulta de rotina.', 4, 9),
                                                          (6, '2026-10-16', 180.00, 'Avaliação dos resultados dos exames.', 5, 9),
                                                          (7, '2026-11-02', 200.00, 'Acompanhamento médico.', 6, 9),
                                                          (8, '2026-11-16', 180.00, 'Consulta de retorno.', 7, 9),

                                                          (9, '2026-10-03', 200.00, 'Avaliação inicial do paciente.', 5, 10),
                                                          (10, '2026-10-17', 180.00, 'Retorno para acompanhamento.', 6, 10),
                                                          (11, '2026-11-03', 200.00, 'Avaliação clínica periódica.', 7, 10),
                                                          (12, '2026-11-17', 180.00, 'Revisão do tratamento.', 3, 10),

                                                          (13, '2026-10-04', 200.00, 'Consulta de avaliação geral.', 6, 11),
                                                          (14, '2026-10-18', 180.00, 'Acompanhamento dos sintomas.', 7, 11),
                                                          (15, '2026-11-04', 200.00, 'Retorno para avaliação clínica.', 3, 11),
                                                          (16, '2026-11-18', 180.00, 'Consulta de acompanhamento.', 4, 11),

                                                          (17, '2026-10-05', 200.00, 'Primeira consulta e anamnese.', 7, 12),
                                                          (18, '2026-10-19', 180.00, 'Avaliação dos exames apresentados.', 3, 12),
                                                          (19, '2026-11-05', 200.00, 'Acompanhamento do paciente.', 4, 12),
                                                          (20, '2026-11-19', 180.00, 'Consulta de retorno.', 5, 12);