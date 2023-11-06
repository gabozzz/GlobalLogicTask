INSERT INTO userData (id,name, email, password, created, last_login, token, is_active)
VALUES
    ('1','Ejemplo Usuario 1', 'usuario1@example.com', 'contrasena1', '2023-01-01 00:00:00', '2023-10-15 12:30:00', 'token123', true),
    ('2','Ejemplo Usuario 2', 'usuario2@example.com', 'contrasena2', '2023-02-05 00:00:00', '2023-10-16 11:45:00', 'token456', true),
    ('3','Ejemplo Usuario 3', 'usuario3@example.com', 'contrasena3', '2023-03-10 00:00:00', '2023-10-17 09:20:00', 'token789', false);

INSERT INTO phone (user_id, number, citycode, countrycode)
VALUES
    (1, '123456789', 123, 'XX'),
    (2, '987654321', 456, 'YY'),
    (3, '555555555', 789, 'ZZ');
