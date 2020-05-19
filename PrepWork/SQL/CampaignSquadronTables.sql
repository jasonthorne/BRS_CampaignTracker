
USE blood_red_skies_db;

/*----------------------------------------------------*/
/* campaigns being played */

DROP TABLE IF EXISTS campaigns; 

CREATE TABLE campaigns (
	campaignID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	periodID INT,
	creation_date DATE,
	PRIMARY KEY (campaignID),
	FOREIGN KEY (eventID) REFERENCES events(eventID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

