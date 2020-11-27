
USE blood_red_skies_db;

DROP TABLE IF EXISTS logs; 
DROP TABLE IF EXISTS mission_results; 
DROP TABLE IF EXISTS missions; 
DROP TABLE IF EXISTS squadron_pilots; 
DROP TABLE IF EXISTS squadrons; 
DROP TABLE IF EXISTS campaign_players; 
DROP TABLE IF EXISTS campaigns;

/*----------------------------------------------------*/
/* campaigns being played */

/*DROP TABLE IF EXISTS campaigns; */

CREATE TABLE campaigns (
	campaignID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	periodID INT,
	created DATETIME,
	PRIMARY KEY (campaignID),
	FOREIGN KEY (eventID) REFERENCES events(eventID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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


DROP PROCEDURE IF EXISTS insert_player; 

DELIMITER $$
CREATE PROCEDURE insert_player (IN player_name VARCHAR(64), IN password_string VARCHAR(64), OUT player_ID INT)
BEGIN
	DECLARE playerID_check INT DEFAULT 0; 
	
	/* check if player_name is already in db: */
	SELECT players.playerID INTO playerID_check FROM players 
	WHERE players.name = player_name;
	
	/* if so, set player_ID as 0: */
	IF playerID_check > 0 THEN SET player_ID = 0; 
	ELSE 
		/* else check if password_string is already in db: */
		SELECT players.playerID INTO playerID_check FROM players 
		WHERE players.password = SHA2(password_string, 512);
		
		/* if so, set player_ID as 0: */
		IF playerID_check > 0 THEN SET player_ID = 0; 
		ELSE
			/* else insert new player into db: */
			INSERT INTO players (name, password) VALUES (player_name, SHA2(password_string, 512));
			
			SELECT players.playerID INTO player_ID FROM players /* set player_ID as player's id */
			WHERE players.name = player_name;
		END IF;
	END IF;
END $$
DELIMITER ;

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


/*----------------------------------------------------*/
/* players involded in campaigns*/

/*DROP TABLE IF EXISTS campaign_players; */

CREATE TABLE campaign_players (
	campaign_playerID INT NOT NULL AUTO_INCREMENT,
	campaignID INT,
	playerID INT,
	score INT DEFAULT 0,
	is_active BOOLEAN DEFAULT TRUE,
	created DATETIME,
	PRIMARY KEY (campaign_playerID),
	FOREIGN KEY (campaignID) REFERENCES campaigns(campaignID),
	FOREIGN KEY (playerID) REFERENCES players(playerID),
	CONSTRAINT campaignID_playerID UNIQUE (campaignID, playerID) /* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_campaign_player (IN campaign_ID INT, IN player_ID INT, IN date_time DATETIME)
BEGIN
	INSERT INTO campaign_players (campaignID, playerID, created) VALUES (
		campaign_ID, player_ID, date_time);
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* campaign players hosting campaigns*/

DROP TABLE IF EXISTS campaign_hosts; 

CREATE TABLE campaign_hosts (
	campaign_hostID INT NOT NULL AUTO_INCREMENT,
	campaign_playerID INT,
	PRIMARY KEY (campaign_hostID),
	UNIQUE (campaign_playerID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP PROCEDURE IF EXISTS insert_campaign; 


DELIMITER $$
CREATE PROCEDURE insert_campaign (IN event_name VARCHAR(64), IN player_ID VARCHAR(64), IN date_time DATETIME)
BEGIN
	DECLARE campaign_ID INT DEFAULT 0; 
	DECLARE campaign_player_ID INT DEFAULT 0;
	
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
	
	/* add player to campaign_players: */
	CALL insert_campaign_player(campaign_ID, player_ID, date_time);
	
	/* get id of inserted campaign_player: */
	SELECT campaign_players.campaign_playerID INTO campaign_player_ID
	FROM campaign_players 
	WHERE campaign_players.campaignID  = campaign_ID
		AND campaign_players.playerID  = player_ID;
	
	/* add campaign player to campaign_hosts: */
	INSERT INTO campaign_hosts (campaign_playerID) VALUES (campaign_player_ID);
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* squadrons */

/*DROP TABLE IF EXISTS squadrons; */

CREATE TABLE squadrons (
	squadronID INT NOT NULL AUTO_INCREMENT,
	campaign_playerID INT,
	airforceID INT, /* when they've added their player to the campaign, theyre taken to a 'choose airforce' page that displays all airforce options (planes & dates & whether airforce has home adv) */
	skill_points INT DEFAULT 24, /* skill points start at 24 */
	PRIMARY KEY (squadronID),
	FOREIGN KEY (campaign_playerID) REFERENCES campaign_players(campaign_playerID),
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
	campaign_playerID INT,
	result ENUM ('Defeat','Victory') NOT NULL DEFAULT 'Defeat',
	PRIMARY KEY (mission_resultID),
	FOREIGN KEY (missionID) REFERENCES missions(missionID),
	FOREIGN KEY (campaign_playerID) REFERENCES campaign_players(campaign_playerID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



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
INSERT INTO players (name, password) VALUES ("jay", SHA2(333, 512));
INSERT INTO players (name, password) VALUES ("jo", SHA2(123, 512));
INSERT INTO players (name, password) VALUES ("dan", SHA2(111, 512));
INSERT INTO players (name, password) VALUES ("laura", SHA2(321, 512));

INSERT INTO campaigns (eventID) VALUES (1);
INSERT INTO players (eventID) VALUES (2);
INSERT INTO players (eventID) VALUES (1);
INSERT INTO players (eventID) VALUES (2);
/* ++++++++++++++ TESTING HERE +++++++++++++++++++++ */



