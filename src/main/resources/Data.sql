INSERT INTO users (username, password) VALUES ('User', '$2a$12$yZA6ANAzGIlrk7bppnWdTOlG9xdnDl8B9jbmZyjFw1.m1nOc/k26O');
INSERT INTO users (username, password) VALUES ('brewer', '$2a$12$yZA6ANAzGIlrk7bppnWdTOlG9xdnDl8B9jbmZyjFw1.m1nOc/k26O');
INSERT INTO users (username, password) VALUES ('admin', '$2a$12$yZA6ANAzGIlrk7bppnWdTOlG9xdnDl8B9jbmZyjFw1.m1nOc/k26O');

INSERT INTO authorities (username, authority) VALUES ('User', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('brewer', 'ROLE_BREWER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');





