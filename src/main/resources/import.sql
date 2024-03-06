-- CLUBS
INSERT INTO clubs (club_name, club_logo, club_description) VALUES ('The Best Club', 'logo_url', 'El mejor Club');

-- ROLES
INSERT INTO roles (role_name) VALUES ('Admin'),('Player'),('Coach');

--COACHES -> revisar si se agrega CLUB ID
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Carlos', 'González', '11111', 'carlos.gonzalez@example.com', 'Avenida Principal 456', 'password',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Silvina', 'Boggi', '22222', 'dos@example.com', 'Avenida Principal 400', '5323',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Mariano', 'Mendez', '33333', 'tres@example.com', 'Avenida Principal 333', '12451',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Juliana', 'Basi', '44444', 'cuatro@example.com', 'Avenida Guemes 44', 'basii22',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Cristina', 'Casna', '55555', 'cinco@example.com', 'Avenida Guemes 55', 'criscas',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Marcos', 'Vitro', '66666', 'seis@example.com', 'Libertador 66', '66666',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Ariel', 'Ensusalsa', '77777', 'siete@example.com', 'Federico Lacorze 000', 'comidaaa',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Gerorgi', 'barbarossa', '888888', 'ocho@example.com', 'Av Paseo Colon 550', 'tartadeciruela',3,1);
INSERT INTO coaches (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Coach', 'Prueba', '0', 'zero@example.com', 'Direccion 0', 'cero0',3,1);

-- TEAM
INSERT INTO team (team_name, team_description) VALUES ('Los dragones', 'Vuelan alto y veloz');

-- CATEGORIES
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2018,'2018', 'Martes', '16:00 - 18:00', '100 USD',1,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2017, '2017', 'Viernes', '17:00 - 19:00', '100 USD',2,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2016, '2016', 'Lunes', '16:00 - 18:00', '100 USD',3,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2015, '2015', 'Jueves', '17:00 - 19:00', '100 USD',4,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2014, '2014', 'Martes', '19:00 - 21:00', '100 USD',5,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2013, '2013', 'Jueves', '19:00 - 21:00', '100 USD',6,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2012, '2012', 'Lunes', '19:00 - 21:00', '100 USD',7,1);
INSERT INTO categories (category_id, category_name, category_schedule, category_daytraining, category_fee, coach_number, team_id) VALUES (2011, '2011', 'Viernes', '19:00 - 21:00', '100 USD',8,1);

-- PLAYERS
INSERT INTO players (player_fee_paid, player_password_changed, category_id, club_id, role_id, player_birthdate, player_position, user_address, user_dni, user_email, user_lastname, user_name, user_password) VALUES (1, 1, 2011, 1, 2, '1995-05-15', 'Defensor', '123 Main St', '123456789A', 'player1@example.com', 'Doe', 'John', 'password1');
INSERT INTO players (player_fee_paid, player_password_changed, category_id, club_id, role_id, player_birthdate, player_position, user_address, user_dni, user_email, user_lastname, user_name, user_password) VALUES (1, 1, 2011, 1, 2, '1994-08-22', 'Midfielder', '456 Elm St', '987654321B', 'player2@example.com', 'Smith', 'Alice', 'password2');
INSERT INTO players (player_fee_paid, player_password_changed, category_id, club_id, role_id, player_birthdate, player_position, user_address, user_dni, user_email, user_lastname, user_name, user_password) VALUES (0, 1, 2018, 1, 2, '1996-02-10', 'Forward', '789 Oak St', '456123789C', 'player3@example.com', 'Johnson', 'Bob', 'password3');
INSERT INTO players (player_fee_paid, player_password_changed, category_id, club_id, role_id, player_birthdate, player_position, user_address, user_dni, user_email, user_lastname, user_name, user_password) VALUES (0, 0, 2012, 1, 2, '1997-07-30', 'Goalkeeper', '101 Pine St', '789456123D', 'player4@example.com', 'Williams', 'Emily', 'password4');
INSERT INTO players (player_fee_paid, player_password_changed, category_id, club_id, role_id, player_birthdate, player_position, user_address, user_dni, user_email, user_lastname, user_name, user_password) VALUES (0, 0, 2014, 1, 2, '1993-11-05', 'Midfielder', '202 Maple St', '321654987E', 'player5@example.com', 'Brown', 'Michael', 'password5');

-- EMPLOYEE
INSERT INTO employees (user_name, user_lastname, user_dni, user_email, user_address, user_password, role_id, club_id) VALUES ('Club', 'Admin', '0000', 'admin.admin@example.com', 'Av del Admin', 'employeePassword', 1, 1);

-- FIXTURE
INSERT INTO fixtures (fixture_name) VALUES ('Fixture 1');

-- GAMES
INSERT INTO games (game_day, game_time, game_islocal, game_teamrival, game_localgoals, game_rivalgoals, category_id, id_fixture) VALUES ('2024-02-28', '15:00', 1, 'Equipo Rival', 2, 1, 2016, 1);

-- PAYMENTS
INSERT INTO payments (amount, player_player_id) VALUES ('100', '1');
