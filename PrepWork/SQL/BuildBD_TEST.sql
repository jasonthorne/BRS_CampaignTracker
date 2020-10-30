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
	PRIMARY KEY (playerID),
	UNIQUE (name), /* prevent duplicate inserts */
	UNIQUE (password) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* insert a player to players. receiving player_name & password */

DROP PROCEDURE IF EXISTS insert_player; 

DELIMITER $$
CREATE PROCEDURE insert_player (IN player_name VARCHAR(64), IN password_string VARCHAR(64), OUT player_ID INT)
BEGIN
	DECLARE playerID_check INT DEFAULT 0; 
	
	/* check if player_name is already in db: */
	SELECT players.playerID INTO playerID_check FROM players 
		WHERE players.name = player_name;
		
	IF playerID_check > 0 THEN SET player_ID = 0; /* if so, set player_ID as 0 */
	ELSE 
		/* else check if password_string is already in db: */
		SELECT players.playerID INTO playerID_check FROM players 
			WHERE players.password = SHA2(password_string, 512);
			
		IF playerID_check > 0 THEN SET player_ID = 0; /* if so, set player_ID as 0 */
		ELSE
			/* else insert new player into db: */
			INSERT INTO players (name, password) VALUES (player_name, SHA2(password_string, 512));
			
			SELECT players.playerID INTO player_ID FROM players /* set player_ID as player's id */
				WHERE players.name = player_name;
		END IF;
	END IF;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */
INSERT INTO players (name, password) VALUES ("1", SHA2(1, 512));
INSERT INTO players (name, password) VALUES ("bob", SHA2(123, 512));
INSERT INTO players (name, password) VALUES ("frank", SHA2(111, 512));
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */
/*----------------------------------------------------*/ 
/* return playerID of player matching player_name & decrypted password_string */

DROP PROCEDURE IF EXISTS select_playerID;
	
DELIMITER $$
CREATE PROCEDURE select_playerID (IN player_name VARCHAR(64), IN password_string VARCHAR(64), OUT player_ID INT)
BEGIN
	SELECT players.playerID INTO player_ID FROM players 
		WHERE players.name = player_name
		AND players.password = SHA2(password_string, 512);
END $$
DELIMITER ;

/*
https://www.mysqltutorial.org/mysql-stored-procedures-return-multiple-values/
*/






