
USE blood_red_skies_db;
DROP TABLE IF EXISTS campaign_hosts; 
DROP TABLE IF EXISTS logs; 
DROP TABLE IF EXISTS mission_results; 
DROP TABLE IF EXISTS missions; 
DROP TABLE IF EXISTS squadron_pilots; 
DROP TABLE IF EXISTS squadrons; 
DROP TABLE IF EXISTS players; 
DROP TABLE IF EXISTS campaigns;

/*----------------------------------------------------*/
/* campaigns being played */

/*DROP TABLE IF EXISTS campaigns; */

CREATE TABLE campaigns (
	campaignID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	periodID INT,
	turn INT DEFAULT 0, 
	created DATETIME,
	PRIMARY KEY (campaignID),
	FOREIGN KEY (eventID) REFERENCES events(eventID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* users */

DROP TABLE IF EXISTS users; 

CREATE TABLE users (
	userID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	password VARCHAR(255) DEFAULT NULL,
	PRIMARY KEY (userID),
	UNIQUE (name), /* prevent duplicate inserts */
	UNIQUE (password) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP PROCEDURE IF EXISTS insert_user; 

DELIMITER $$
CREATE PROCEDURE insert_user (IN user_name VARCHAR(64), IN password_string VARCHAR(64), OUT user_ID INT)
BEGIN
	DECLARE userID_check INT DEFAULT 0; 
	
	/* check if user_name is already in db: */
	SELECT users.userID INTO userID_check FROM users 
	WHERE users.name = user_name;
	
	/* if so, set user_ID as 0: */
	IF userID_check > 0 THEN SET user_ID = 0; 
	ELSE 
		/* else check if password_string is already in db: */
		SELECT users.userID INTO userID_check FROM users 
		WHERE users.password = SHA2(password_string, 512);
		
		/* if so, set user_ID as 0: */
		IF userID_check > 0 THEN SET user_ID = 0; 
		ELSE
			/* else insert new user into db: */
			INSERT INTO users (name, password) VALUES (user_name, SHA2(password_string, 512));
			
			SELECT users.userID INTO user_ID FROM users /* set user_ID as user's id */
			WHERE users.name = user_name;
		END IF;
	END IF;
END $$
DELIMITER ;

/* return userID of user matching user_name & decrypted password_string */
DROP PROCEDURE IF EXISTS select_userID;
	
DELIMITER $$
CREATE PROCEDURE select_userID (IN user_name VARCHAR(64), IN password_string VARCHAR(64), OUT user_ID INT)
BEGIN
	SELECT users.userID INTO user_ID FROM users 
	WHERE users.name = user_name
	AND users.password = SHA2(password_string, 512);
END $$
DELIMITER ;


/*----------------------------------------------------*/
/* players involded in campaigns*/

/*DROP TABLE IF EXISTS players; */

CREATE TABLE players (
	playerID INT NOT NULL AUTO_INCREMENT,
	campaignID INT,
	userID INT,
	score INT DEFAULT 0,
	is_active BOOLEAN DEFAULT TRUE,
	created DATETIME,
	PRIMARY KEY (playerID),
	FOREIGN KEY (campaignID) REFERENCES campaigns(campaignID),
	FOREIGN KEY (userID) REFERENCES users(userID),
	CONSTRAINT campaignID_userID UNIQUE (campaignID, userID) /* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_player (IN campaign_ID INT, IN user_ID INT, IN date_time DATETIME)
BEGIN
	INSERT INTO players (campaignID, userID, created) VALUES (
		campaign_ID, user_ID, date_time);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE select_player_names (IN campaign_ID INT)
BEGIN
	SELECT users.name FROM users
		INNER JOIN players ON users.userID = players.userID
	WHERE players.campaignID = campaign_ID;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* players hosting campaigns*/

CREATE TABLE hosts (
	hostID INT NOT NULL AUTO_INCREMENT,
	playerID INT,
	PRIMARY KEY (hostID),
	UNIQUE (playerID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP PROCEDURE IF EXISTS insert_campaign; 

DELIMITER $$
CREATE PROCEDURE insert_campaign (IN event_name VARCHAR(64), IN user_ID INT, IN date_time DATETIME, OUT campaign_ID INT)
BEGIN
	/* eventID from events with event_name: */
	DECLARE event_ID INT DEFAULT (
		SELECT eventID FROM events WHERE events.name = event_name); 
		
	/* add campaign to campaigns: */
	INSERT INTO campaigns (eventID, periodID, created) VALUES (
		event_ID,
		(SELECT MIN(periodID) FROM event_periods 
			INNER JOIN events ON event_periods.eventID = event_ID 
		WHERE events.name = event_name),
		date_time);
	
	/* get id of inserted campaign: */
	SELECT LAST_INSERT_ID() INTO campaign_ID;
	
	/* add user to players: */
	CALL insert_player(campaign_ID, user_ID, date_time);
	
	/* add id of inserted player to hosts: */
	INSERT INTO hosts (playerID) VALUES (
		(SELECT players.playerID 
		FROM players 
		WHERE players.campaignID = campaign_ID
			AND players.userID = user_ID));
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE select_campaigns () /*+++++++++++++++++++ADD GET TURN HERE TOO! */
BEGIN
	SELECT
		campaigns.campaignID AS campaign_ID,
		events.name AS event_name,
		periods.block AS period_block,
		years.year_value AS period_year,
		campaigns.created AS date_time,
		
		/* get name of host: */
		(SELECT users.name FROM users /* ++++++++++++++++TEST THIS WITH MULTIPLE EXAMPLES ++++++++ */
			INNER JOIN players ON 
				users.userID = players.userID
			INNER JOIN hosts ON 
				players.playerID = hosts.playerID
		WHERE players.campaignID = campaign_ID 
			AND players.playerID = hosts.playerID)
		AS host_name,
		
		/* get count of event_periods: */
		(SELECT COUNT(event_periods.event_periodID) FROM event_periods
		WHERE event_periods.eventID = campaigns.eventID)
		AS periods_count
	
	FROM campaigns
		INNER JOIN periods ON campaigns.periodID = periods.periodID
		INNER JOIN years ON periods.yearID = years.yearID
		INNER JOIN events ON campaigns.eventID = events.eventID;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* squadrons */

/*DROP TABLE IF EXISTS squadrons; */

CREATE TABLE squadrons (
	squadronID INT NOT NULL AUTO_INCREMENT,
	playerID INT, /* needed????????????????????????????????? */
	airforceID INT, /* when they've added their player to the campaign, theyre taken to a 'choose airforce' page that displays all airforce options (planes & dates & whether airforce has home adv) */
	skill_points INT DEFAULT 24, /* skill points start at 24 */
	PRIMARY KEY (squadronID),
	FOREIGN KEY (playerID) REFERENCES players(playerID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* pilots */

DROP TABLE IF EXISTS pilots; 

CREATE TABLE pilots (
	pilotID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	airforce_planeID INT,
	pilot_skill ENUM ('Rookie','Average','Veteran','Ace','Named Ace') NOT NULL DEFAULT 'Rookie',
	experience_points INT DEFAULT 0,
	join_date DATE,
	shot_downs INT DEFAULT 0,
	status ENUM ('OK','Injury','Major Injury','Crippling Injury','KIA','MIA') NOT NULL DEFAULT 'OK',
	PRIMARY KEY (pilotID),
	FOREIGN KEY (airforce_planeID) REFERENCES airforce_planes(airforce_planeID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* squadron pilots */

/*DROP TABLE IF EXISTS squadron_pilots; */

CREATE TABLE squadron_pilots (
	squadron_pilotID INT NOT NULL AUTO_INCREMENT,
	squadronID INT,
	pilotID INT,
	PRIMARY KEY (squadron_pilotID),
	FOREIGN KEY (squadronID) REFERENCES squadrons(squadronID),
	FOREIGN KEY (pilotID) REFERENCES pilots(pilotID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* missions */

/*DROP TABLE IF EXISTS missions; */

CREATE TABLE missions (
	missionID INT NOT NULL AUTO_INCREMENT,
	campaignID INT,
	periodID INT,
	is_active BOOLEAN DEFAULT TRUE,
	PRIMARY KEY (missionID),
	FOREIGN KEY (campaignID) REFERENCES campaigns(campaignID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* logs */

/*DROP TABLE IF EXISTS logs; */

CREATE TABLE logs (
	logID INT NOT NULL AUTO_INCREMENT,
	squadron_pilotID INT,
	missionID INT,
	has_survived BOOLEAN DEFAULT TRUE,
	has_inflicted_boom_chit BOOLEAN DEFAULT FALSE,
	has_received_boom_chit BOOLEAN DEFAULT FALSE,
	was_shot_down BOOLEAN DEFAULT FALSE,
	hits INT DEFAULT 0,
	shot_downs INT DEFAULT 0,
	PRIMARY KEY (logID),
	FOREIGN KEY (squadron_pilotID) REFERENCES squadron_pilots(squadron_pilotID),
	FOREIGN KEY (missionID) REFERENCES missions(missionID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* mission results */

/*DROP TABLE IF EXISTS mission_results; */

CREATE TABLE mission_results (
	mission_resultID INT NOT NULL AUTO_INCREMENT,
	missionID INT,
	playerID INT,
	result ENUM ('Defeat','Victory') NOT NULL DEFAULT 'Defeat',
	PRIMARY KEY (mission_resultID),
	FOREIGN KEY (missionID) REFERENCES missions(missionID),
	FOREIGN KEY (playerID) REFERENCES players(playerID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/*----------------------------------------------------*/

DELIMITER $$
CREATE PROCEDURE select_campaign (IN campaign_ID INT) /*/+++++++++++++++++++++++SHOULD PROB GO!! */
BEGIN
	/*
	SELECT users.name FROM users
		INNER JOIN players ON users.userID = players.userID
	WHERE players.campaignID = campaign_ID;
	
	
	campaignID INT NOT NULL AUTO_INCREMENT, - NO
	eventID INT, - NO
	periodID INT, - NO
	turn INT DEFAULT 0,  - YES - worth pulling just for this????
	created DATETIME, - NO
	
	*/
	
	
END $$
DELIMITER ;

	/*
	campaigns.campaignID AS campaign_ID,
		events.name AS event_name,
		periods.block AS period_block,
		years.year_value AS period_year,
		campaigns.created AS date_time,
		
		
		(SELECT users.name FROM users 
			INNER JOIN players ON 
				users.userID = players.userID
			INNER JOIN hosts ON 
				players.playerID = hosts.playerID
		WHERE players.campaignID = campaign_ID 
			AND players.playerID = hosts.playerID)
		AS host_name,
		
		
		(SELECT COUNT(event_periods.event_periodID) FROM event_periods
		WHERE event_periods.eventID = campaigns.eventID)
		AS periods_count*/


DELIMITER $$
CREATE PROCEDURE select_players (IN campaign_ID INT)
BEGIN
	/*
	get all player data
	playerID - YES
	////////campaignID INT,
	////////////userID INT,
	score INT DEFAULT 0, - YES
	is_active BOOLEAN DEFAULT TRUE,- YES
	created DATETIME, - YES
	*/
	
	
END $$
DELIMITER ;



/*============================================-==================*/
/* select all entries */

DROP PROCEDURE IF EXISTS select_all; 

DELIMITER $$
CREATE PROCEDURE select_all (IN table_name VARCHAR(64))
BEGIN
	/* create query string: */
	SET @query = CONCAT('SELECT * FROM ', table_name, ';'); 
	
    /* prepare and execute statement: */
	PREPARE statement FROM @query;
	EXECUTE statement;
	DEALLOCATE PREPARE statement;
END $$
DELIMITER ;

/* https://stackoverflow.com/questions/27542617/dynamic-table-name-at-sql-statement */

/*===============================================================*/
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */
INSERT INTO users (name, password) VALUES ("jay", SHA2(333, 512));
INSERT INTO users (name, password) VALUES ("jo", SHA2(123, 512));
INSERT INTO users (name, password) VALUES ("dan", SHA2(111, 512));
INSERT INTO users (name, password) VALUES ("laura", SHA2(321, 512));

/*INSERT INTO campaigns (eventID) VALUES (1);
INSERT INTO campaigns (eventID) VALUES (2);
INSERT INTO campaigns (eventID) VALUES (1);
INSERT INTO campaigns (eventID) VALUES (2);*/
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */



