DROP DATABASE IF EXISTS m2ijdbctp;
CREATE DATABASE IF NOT EXISTS m2ijdbctp
CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

USE m2ijdbctp;

DROP TABLE IF EXISTS ingenieur;
CREATE TABLE IF NOT EXISTS ingenieur (
	numMatricule INT UNIQUE PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(80),
    prenom VARCHAR(80),
    datenaissance DATE,
    adresse VARCHAR(150),
    cp VARCHAR(5),
    ville VARCHAR(50),
    tel VARCHAR(20),
    sexe VARCHAR(1),
    situation VARCHAR(15),
    codeprojet DOUBLE
)
ENGINE = InnoDB
CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

DROP PROCEDURE IF EXISTS `spGetIngenieursByCity`;

DELIMITER $$
CREATE PROCEDURE spGetIngenieursByCity
(IN city varchar(50))
BEGIN
	SELECT * FROM ingenieur WHERE ville = city;
END $$
DELIMITER ;