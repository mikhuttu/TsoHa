INSERT INTO kilpailu (nimi) VALUES ('Race 1');
INSERT INTO kilpailu (nimi) VALUES ('Race 2');
INSERT INTO kilpailu (nimi) VALUES ('Race 3');

INSERT INTO kilpailija (nimi) VALUES ('Joonas');
INSERT INTO kilpailija (nimi) VALUES ('Seppo');
INSERT INTO kilpailija (nimi) VALUES ('Pekka');

INSERT INTO osallistuja VALUES (1, 1);
INSERT INTO osallistuja VALUES (1, 2);
INSERT INTO osallistuja VALUES (2, 3);


INSERT INTO valiaikapiste (numero, kilpailu) VALUES (1, 1);
INSERT INTO valiaikapiste (numero, kilpailu) VALUES (1, 2);


INSERT INTO tulos (aika, kilpailija, valiaikapiste) VALUES (001122, 1, 1);
INSERT INTO tulos (aika, kilpailija, valiaikapiste) VALUES (001030, 2, 1);
INSERT INTO tulos (aika, kilpailija, valiaikapiste) VALUES (001453, 3, 2);


INSERT INTO kayttaja (Tunnus, Salasana) VALUES ('yllapitaja', 'qwerty123');
