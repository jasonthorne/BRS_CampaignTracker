
USE blood_red_skies_db;

/*----------------------------------------------------*/
/* campaigns being played */
DROP TABLE IF EXISTS campaign_players; 
DROP TABLE IF EXISTS campaigns; 

CREATE TABLE campaigns (
	campaignID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	periodID INT,
	created DATE,
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
	PRIMARY KEY (playerID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* players involded in campaigns*/

/*DROP TABLE IF EXISTS campaign_players; */

CREATE TABLE campaign_players (
	campaign_playerID INT NOT NULL AUTO_INCREMENT,
	campaignID INT,
	playerID INT,
	score INT DEFAULT 0,
	is_active BOOLEAN DEFAULT TRUE,
	created DATE,
	PRIMARY KEY (campaign_playerID),
	FOREIGN KEY (campaignID) REFERENCES campaigns(campaignID),
	FOREIGN KEY (playerID) REFERENCES players(playerID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*----------------------------------------------------*/
/* squadrons */

DROP TABLE IF EXISTS squadrons; 

CREATE TABLE squadrons (
	squadronID INT NOT NULL AUTO_INCREMENT,
	campaign_playerID INT,
	airforceID INT, /* when they've added their player to the campaign, theyre taken to a 'choose airforce' page that displays all airforce options (planes & dates & whether airforce has home adv) */
	skill_points INT DEFAULT 24, /* skill points start at 24 */
	PRIMARY KEY (squadronID),
	FOREIGN KEY (campaign_playerID) REFERENCES campaign_players(campaign_playerID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;





