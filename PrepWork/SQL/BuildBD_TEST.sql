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
	INSERT INTO players (name, password) VALUES (player_name, password_string); /*SHA2(password_string, 512));*/
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */

INSERT INTO players (name, password) VALUES ("bob", SHA2(123, 512));
INSERT INTO players (name, password) VALUES ("frank", SHA2(111, 512));

/* select players matching player_name & decrypted password_string.  Return playerID */
	
DELIMITER $$
CREATE PROCEDURE select_player (IN player_name VARCHAR(64), IN password_string VARCHAR(64), OUT player_ID INT)
BEGIN
	SELECT players.playerID INTO player_ID FROM players 
		WHERE players.name = player_name
		AND players.password = SHA2(password_string, 512);
		/*AND players.password = password_string;*/
END $$
DELIMITER ;

/*
https://www.mysqltutorial.org/mysql-stored-procedures-return-multiple-values/
*/






