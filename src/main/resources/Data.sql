INSERT INTO users (username, password, enabled) VALUES ('User', 'password', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('brewer', 'password', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('admin', 'password', TRUE);

INSERT INTO authorities (username, authority) VALUES ('User', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('brewer', 'ROLE_BREWER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');





