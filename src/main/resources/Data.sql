insert into roles(rolename) values ('ROLE_USER'), ('ROLE_BREWER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, email, enabled) VALUES ('User', 'welkom','user1@test.nl', TRUE);

INSERT INTO authorities (username, authority) VALUES ('User', 'ROLE_USER');