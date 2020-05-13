
DROP DATABASE if exists blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/*----------------------------------------------------*/
/* blocks of a year (early, mid, late) */

CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL, 
  PRIMARY KEY (blockID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_block (INOUT block VARCHAR(64))
BEGIN
	INSERT INTO blocks (name) VALUES (block); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* years covered */

CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL,
  PRIMARY KEY (yearID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_year (INOUT year VARCHAR(4))
BEGIN
	INSERT INTO years (name) VALUES (year); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* historical periods (eg: early 1940) */

CREATE TABLE periods ( /* ++++++++++++++++++++++++++++This can probvably be made automatically, once years and blocks are constructed +++++++++*/
  periodID int NOT NULL AUTO_INCREMENT,
  blockID int,
  yearID int,
  PRIMARY KEY (periodID),
  FOREIGN KEY (blockID) REFERENCES blocks(blockID),
  FOREIGN KEY (yearID) REFERENCES years(yearID),
  CONSTRAINT blockID_yearID UNIQUE (blockID, yearID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_period (INOUT block VARCHAR(64), INOUT year VARCHAR(4))
BEGIN
	INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = block),
	(SELECT yearID FROM years WHERE years.name = year));
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* historical events */

CREATE TABLE events( 
  eventID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (eventID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_event (INOUT event VARCHAR(64))
BEGIN
	INSERT INTO events (name) VALUES (event); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces involved */
/*
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
*/

CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  image_path varchar(64) DEFAULT NULL, /* +++++++++++++++++++++Make this unique too!! +++++++++++++++++++++++*/
  PRIMARY KEY (airforceID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/* insert airforce */

DELIMITER $$
CREATE PROCEDURE insert_airforce (IN airforce VARCHAR(64), IN image VARCHAR(64))
BEGIN
	INSERT INTO airforces (name, image_path) VALUES (airforce, image); 
END $$
DELIMITER ;

/*
DELIMITER $$
CREATE PROCEDURE insert_airforce (INOUT airforce VARCHAR(64))
BEGIN
	INSERT INTO airforces (name) VALUES (airforce); 
END $$
DELIMITER ;
*/

/* select all airforces */

DELIMITER $$
CREATE PROCEDURE select_airforces ()
BEGIN
	SELECT * FROM airforces 
	ORDER BY airforceID ASC;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* planes available */

CREATE TABLE planes( 
  planeID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (planeID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_plane (INOUT plane VARCHAR(64))
BEGIN
	INSERT INTO planes (name) VALUES (plane); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* availability status of a plane */

CREATE TABLE statuses (
  statusID int NOT NULL AUTO_INCREMENT,
  name varchar(11) DEFAULT NULL,
  PRIMARY KEY (statusID),
  UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_status (INOUT status VARCHAR(11))
BEGIN
	INSERT INTO statuses (name) VALUES (status); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces available to events */

CREATE TABLE event_airforces (
  event_airforceID int NOT NULL AUTO_INCREMENT,
  eventID int,
  airforceID int,
  has_home_advantage boolean DEFAULT FALSE,
  PRIMARY KEY (event_airforceID),
  FOREIGN KEY (eventID) REFERENCES events(eventID),
  FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
  CONSTRAINT eventID_airforceID UNIQUE (eventID, airforceID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_event_airforce (INOUT event VARCHAR(64), INOUT airforce VARCHAR(64), INOUT has_home_advantage BOOLEAN)
BEGIN
	INSERT INTO event_airforces (eventID, airforceID, has_home_advantage) VALUES (
	(SELECT eventID FROM events WHERE events.name = event),
	(SELECT airforceID FROM airforces WHERE airforces.name = airforce),
	has_home_advantage);
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* periods available to events */

CREATE TABLE event_periods (
  event_periodID int NOT NULL AUTO_INCREMENT,
  eventID int,
  periodID_start int,
  periodID_end int,
  PRIMARY KEY (event_periodID),
  FOREIGN KEY (periodID_start) REFERENCES periods(periodID),
  FOREIGN KEY (periodID_end) REFERENCES periods(periodID),
  UNIQUE (eventID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_event_period (INOUT event VARCHAR(64), 
INOUT block_start VARCHAR(64), INOUT year_start VARCHAR(4), INOUT block_end VARCHAR(64), INOUT year_end VARCHAR(4))

BEGIN
	INSERT INTO event_periods (eventID, periodID_start, periodID_end) VALUES (
	(SELECT eventID FROM events WHERE events.name = event),
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = block_start AND years.name = year_start),
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = block_end AND years.name = year_end));
END $$
DELIMITER ;

/*----------------------------------------------------*/
/*
INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'));
*/

/* airforces involved */
/*
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO airforces (name) VALUES ('RAF');
INSERT INTO airforces (name) VALUES ('Luftwaffe');
INSERT INTO airforces (name) VALUES ('USAAF');
INSERT INTO airforces (name) VALUES ('VVS');
INSERT INTO airforces (name) VALUES ('IJAAF');
*/