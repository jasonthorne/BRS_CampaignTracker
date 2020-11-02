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
/* insert a player to players. receiving player_name & password */ /*+++++++++++++ NEW (or rather UPDATED!) ++++++++++++*/

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
/* return playerID of player matching player_name & decrypted password_string */ /* +++++++++++++ NEW ++++++++++++*/

DROP PROCEDURE IF EXISTS select_playerID;
	
DELIMITER $$
CREATE PROCEDURE select_playerID (IN player_name VARCHAR(64), IN password_string VARCHAR(64), OUT player_ID INT)
BEGIN
	SELECT players.playerID INTO player_ID FROM players 
		WHERE players.name = player_name
		AND players.password = SHA2(password_string, 512);
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* historical events */

CREATE TABLE events( 
	eventID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	PRIMARY KEY (eventID),
	UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_event (IN event_name VARCHAR(64))
BEGIN
	/* error thrown here on duplicate event_name insert: */ 
	INSERT INTO events (name) VALUES (event_name);
END $$
DELIMITER ;

/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ NEW ++++++++++++*/
DELIMITER $$
CREATE PROCEDURE select_events ()
BEGIN
	SELECT events.name, period FROM events; /* starting with just name as test +++++++++++++++++++++++++ */
END $$
DELIMITER ;

/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */
INSERT INTO events (name) VALUES ("Battle of Britain");
INSERT INTO events (name) VALUES ("Assault on the Reich");
INSERT INTO events (name) VALUES ("Stalingrad");
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */


/*&&&&&&&&&&&&&&&&&&&&&&&&&&&&all of bewlo is copied from BuildDB &&&&&&&&&&&&&&&&&&&*/


/*----------------------------------------------------*/
/* years covered */

CREATE TABLE years (
	yearID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(4) DEFAULT NULL,
	PRIMARY KEY (yearID),
	UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_year (IN year_name VARCHAR(4))
BEGIN
	INSERT IGNORE INTO years (name) VALUES (year_name); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* historical periods (eg: early 1940) */

CREATE TABLE periods ( 
	periodID INT NOT NULL AUTO_INCREMENT,
	block ENUM ('Early','Mid','Late') NOT NULL,
	yearID INT,
	PRIMARY KEY (periodID),
	FOREIGN KEY (yearID) REFERENCES years(yearID),
	CONSTRAINT block_yearID UNIQUE (block, yearID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_period (IN block_option VARCHAR(5), IN year_name VARCHAR(4))
BEGIN
	DECLARE periodID_check INT DEFAULT 0;
	
	/* insert year_name to years if not present; */
	CALL insert_year (year_name);
	
	/* check for periodID relating to block_option & year_name: */
	SELECT periodID INTO periodID_check FROM periods
		INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_option AND years.name = year_name;
	
	IF periodID_check = 0 THEN /* insert entry if periodID not found: */
		/* error thrown here if block_option doesnt match enum options: */ 
		INSERT INTO periods (block, yearID) VALUES ( 
			block_option,(SELECT yearID FROM years WHERE years.name = year_name));
	END IF;
END $$
DELIMITER ;

/*----------------------------------------------------*/



/*----------------------------------------------------*/
/* periods available to events */

CREATE TABLE event_periods (
	event_periodID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	periodID_start INT,
	periodID_end INT,
	PRIMARY KEY (event_periodID),
	FOREIGN KEY (periodID_start) REFERENCES periods(periodID),
	FOREIGN KEY (periodID_end) REFERENCES periods(periodID),
	UNIQUE (eventID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_event_period (IN event_name VARCHAR(64), IN block_start VARCHAR(5), 
IN year_start VARCHAR(4), IN block_end VARCHAR(5), IN year_end VARCHAR(4))
BEGIN
	
	/*++++++++++++++THIS NEEDS TO FAIL IF START AND END PERIODS ARE THE SAME, OR IF END DATE COMES BEFORE START DATE+++++++++++++++++*/
	
	INSERT INTO event_periods (eventID, periodID_start, periodID_end) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = event_name),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = block_start AND years.name = year_start),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = block_end AND years.name = year_end));
END $$
DELIMITER ;



/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */



INSERT INTO periods (block, yearID) VALUES ('Early', '1');
INSERT INTO periods (block, yearID) VALUES ('Mid', '3');




INSERT INTO event_periods (eventID, periodID_start, periodID_end) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = 'Battle of Britain'),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = 'Early' AND years.name = '1940'),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = 'Mid' AND years.name = '1945'));















/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */








