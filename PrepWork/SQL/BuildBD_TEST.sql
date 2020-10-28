DROP DATABASE IF EXISTS blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/*----------------------------------------------------*/
/* players */

DROP TABLE IF EXISTS players; 

CREATE TABLE players (
	playerID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	password VARCHAR(255) DEFAULT NULL,
	PRIMARY KEY (playerID) /*,
	/* turn on later! - UNIQUE (name), /* prevent duplicate inserts */
	/* turn on later! - UNIQUE (password) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP PROCEDURE IF EXISTS insert_player; 

DELIMITER $$
CREATE PROCEDURE insert_player (IN player_name VARCHAR(64), IN password_string VARCHAR(64))
BEGIN
	INSERT INTO players (name, password) VALUES (player_name, SHA2(password_string, 512));
END $$
DELIMITER ;

/*----------------------------------------------------*/


INSERT INTO players (name, password) VALUES ("bob", 123 );
INSERT INTO players (name, password) VALUES ("frank", 111 );










