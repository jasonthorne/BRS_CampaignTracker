DROP DATABASE IF EXISTS blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/*DROP TABLE IF EXISTS years; /* ++++++++++++++++++++++++++++++++++++++++++ */

/* ==============================AIRFORCE DATA (plus generics that are used in building thme like 'years') */
/* ===================this is used in InsertAirForceData_TEST in com.brs.airforces of buildDB program. */
/* =======================and pulls in data from airforceData.json */


/* enabled to throw errors for invalid enum inserts: */
SET GLOBAL sql_mode = 'STRICT_ALL_TABLES';
SET SESSION sql_mode = 'STRICT_ALL_TABLES';

/* throw custom error: */
DELIMITER $$
CREATE PROCEDURE throw_error (IN message VARCHAR(256))
BEGIN
	SIGNAL SQLSTATE 'ERR0R'
	SET MESSAGE_TEXT = message;
END $$
DELIMITER ;

/*https://stackoverflow.com/questions/4862911/how-to-throw-an-error-in-mysql-procedure */

/*----------------------------------------------------*/
/* years covered */

CREATE TABLE years (
	yearID INT NOT NULL AUTO_INCREMENT,
	/*name VARCHAR(4) DEFAULT NULL,*/
	year_value INT(4) DEFAULT NULL,
	PRIMARY KEY (yearID),
	/*UNIQUE (name) /* prevent duplicate inserts */
	UNIQUE (year_value) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
/*CREATE PROCEDURE insert_year (IN year_name VARCHAR(4))*/
CREATE PROCEDURE insert_year (IN year_value INT(4))
BEGIN
	/*INSERT IGNORE INTO years (name) VALUES (year_name); */
	INSERT IGNORE INTO years (year_value) VALUES (year_value);
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
/*CREATE PROCEDURE insert_period (IN block_option VARCHAR(5), IN year_name VARCHAR(4))*/
CREATE PROCEDURE insert_period (IN block_option VARCHAR(5), IN year_value INT(4))
BEGIN
	DECLARE periodID_check INT DEFAULT 0;
	
	/* insert year_name to years if not present; */
	/*CALL insert_year (year_name);*/
	CALL insert_year (year_value);
	
	/* check for periodID relating to block_option & year_name: */
	SELECT periodID INTO periodID_check FROM periods
		/*INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_option AND years.name = year_name;*/
		INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_option AND years.year_value = year_value;
	
	IF periodID_check = 0 THEN /* insert entry if periodID not found: */
		/* error thrown here if block_option doesnt match enum options: */ 
		INSERT INTO periods (block, yearID) VALUES ( 
			/*block_option,(SELECT yearID FROM years WHERE years.name = year_name));*/
			block_option,(SELECT yearID FROM years WHERE years.year_value = year_value));
	END IF;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* images */

CREATE TABLE images (
	imageID INT NOT NULL AUTO_INCREMENT,
	path VARCHAR(64) DEFAULT NULL, /*+++++++++++++++++ CHANGE TO BLOB ++++++++++*/
	PRIMARY KEY (imageID),
	UNIQUE (path) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_image (IN image_path VARCHAR(64))
BEGIN
	/* error thrown here on duplicate image_path insert: */ 
	INSERT INTO images (path) VALUES (image_path); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces involved */

CREATE TABLE airforces( 
	airforceID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	PRIMARY KEY (airforceID),
	UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce (IN airforce_name VARCHAR(64))
BEGIN
	/* error thrown here on duplicate airforce_name insert: */ 
	INSERT INTO airforces (name) VALUES (airforce_name); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforce images */

CREATE TABLE airforce_images (
	airforce_imageID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	airforceID INT,
	imageID INT,
	PRIMARY KEY (airforce_imageID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
	FOREIGN KEY (imageID) REFERENCES images(imageID),
	CONSTRAINT airforceID_name UNIQUE (airforceID, name)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_image (IN airforce_name VARCHAR(64), IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	 /* insert image_path to images: */
	CALL insert_image(image_path);
	
	/* error thrown here on duplicate airforce_image insert: */ 
	INSERT INTO airforce_images (name, airforceID, imageID) VALUES (
		image_name,
		(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
		(SELECT imageID FROM images WHERE images.path = image_path));
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* planes available */

CREATE TABLE planes(
	planeID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	PRIMARY KEY (planeID),
	UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_plane (IN plane_name VARCHAR(64))
BEGIN
	INSERT IGNORE INTO planes (name) VALUES (plane_name); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* planes available to each airforce */

CREATE TABLE airforce_planes(
	airforce_planeID INT NOT NULL AUTO_INCREMENT,
	airforceID INT,
	planeID INT,
	PRIMARY KEY (airforce_planeID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
	FOREIGN KEY (planeID) REFERENCES planes(planeID),
	CONSTRAINT airforceID_planeID UNIQUE (airforceID, planeID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_plane (IN airforce_name VARCHAR(64), IN plane_name VARCHAR(64))
BEGIN
	/* insert plane_name to planes if not present */
	CALL insert_plane (plane_name);
	
	/* error thrown here on duplicate airforce_plane insert: */ 
	INSERT INTO airforce_planes (airforceID, planeID) VALUES ( 
		(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
		(SELECT planeID FROM planes WHERE planes.name = plane_name));
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* availability statuses of air force planes in relation to historical periods */

CREATE TABLE plane_availabilities(
	plane_availabilityID INT NOT NULL AUTO_INCREMENT,
	airforce_planeID INT,
	periodID INT,
	status ENUM ('Unavailable','Limit','Auto') NOT NULL DEFAULT 'Unavailable',
	PRIMARY KEY (plane_availabilityID),
	FOREIGN KEY (airforce_planeID) REFERENCES airforce_planes(airforce_planeID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID),
	CONSTRAINT airforce_planeID_periodID UNIQUE (airforce_planeID, periodID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_plane_availability (IN airforce_name VARCHAR(64), IN plane_name VARCHAR(64), 
/*IN block_option VARCHAR(5), IN year_name VARCHAR(4), IN status_option VARCHAR(11))*/
IN block_option VARCHAR(5), IN year_value INT(4), IN status_option VARCHAR(11))
BEGIN
	/* insert period to periods if not present */
	/*CALL insert_period (block_option, year_name);*/
	CALL insert_period (block_option, year_value);
	
	/* error thrown here if status_option doesnt match enum options, 
	or on an attempt to insert identical periods for the same airforce_plane: */
	
	INSERT INTO plane_availabilities (airforce_planeID, periodID, status) VALUES ( 
	(SELECT airforce_planeID FROM airforce_planes
		INNER JOIN airforces ON airforce_planes.airforceID = airforces.airforceID
		INNER JOIN planes ON airforce_planes.planeID = planes.planeID
		WHERE airforces.name = airforce_name AND planes.name = plane_name),
	(SELECT periodID FROM periods
		INNER JOIN years ON periods.yearID = years.yearID
		/*WHERE block = block_option AND years.name = year_name),*/
		WHERE block = block_option AND years.year_value = year_value),
		status_option);
END $$
DELIMITER ;

/* ====================EVENT DATA==========used in InsertEventData_TEST in com.brs.events to populate eventData.json=======================================*/

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

DELIMITER $$
CREATE PROCEDURE select_events () /* starting with just name as test +++++8888888888888888888888888++++++++++++++++++++ */
BEGIN

	/*
	SELECT
		events.name AS event_name,		
		events.eventID AS event_ID,
		////////////event_periods.event_periodID AS event_periodID
		///////////periods.periodID AS periodID_start
		periods.periodID AS periodID_end
		periods.block AS 
			(SELECT )period_start_block
		//////////////years.name AS period_start_year
		
		
	FROM events
		INNER JOIN event_periods ON events.eventID = event_periods.eventID
		INNER JOIN periods ON event_periods.periodID = periods.periodID
			//////////////WHERE event_periods.periodID_start = periodID
		//////////INNER JOIN periods ON event_periods.periodID = periods.periodID
			///////////WHERE event_periods.periodID_end = periodID
		WHERE events.eventID = event_periods.eventID;
		////////////AND event_periods.periodID_start = periodID;
	
	*/
	
	
	

	/*
	INSERT INTO event_periods (eventID, periodID_start, periodID_end) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = event_name),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = block_start AND years.name = year_start),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = block_end AND years.name = year_end));
	*/
	
	/*
	SELECT 
		events.name AS event_name,
		airforces.name AS airforce_name
	FROM events 
		INNER JOIN event_airforces ON event_airforces.eventID = events.eventID
		INNER JOIN airforces ON event_airforces.airforceID = airforces.airforceID
	*/
	
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces available to events */

CREATE TABLE event_airforces (
	event_airforceID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	airforceID INT,
	has_home_advantage BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (event_airforceID),
	FOREIGN KEY (eventID) REFERENCES events(eventID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
	CONSTRAINT eventID_airforceID UNIQUE (eventID, airforceID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_event_airforce (IN event_name VARCHAR(64), IN airforce_name VARCHAR(64),
IN home_advantage_value BOOLEAN)
BEGIN
	/* error thrown here on duplicate airforce_plane insert: */
	INSERT INTO event_airforces (eventID, airforceID, has_home_advantage) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = event_name),
	(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
	home_advantage_value);
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE select_event_airforces (event_ID INT)
BEGIN
	SELECT
		airforces.name AS airforce_name, 
		has_home_advantage AS home_advantage_value
	FROM event_airforces
		INNER JOIN airforces ON event_airforces.airforceID = airforces.airforceID
	WHERE event_airforces.eventID = event_ID;
END $$
DELIMITER ;

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
		WHERE periods.block = block_start AND years.year_value = year_start),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = block_end AND years.year_value = year_end));
END $$
DELIMITER ;


/* ===============================NEW PERIOD TESTING =============================== */

CREATE TABLE event_starts (
	event_startID INT NOT NULL AUTO_INCREMENT,
	eventID INT, 
	periodID INT,
	PRIMARY KEY (event_startID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID),
	UNIQUE (eventID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_event_start (IN event_name VARCHAR(64), IN block_option VARCHAR(5),
/*IN year_name VARCHAR(4))*/
IN year_value INT(4))
BEGIN
	INSERT INTO event_starts (eventID, periodID) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = event_name),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		/*WHERE periods.block = block_option AND years.name = year_name));*/ 
		WHERE periods.block = block_option AND years.year_value = year_value)); 
END $$
DELIMITER ;

/*-----------------------*/

CREATE TABLE event_ends (
	event_endID INT NOT NULL AUTO_INCREMENT,
	eventID INT,
	periodID INT,
	PRIMARY KEY (event_endID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID),
	UNIQUE (eventID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 
/*-----------------------*/

/* text into number: +++++++++++++++++++++++
https://stackoverflow.com/questions/5960620/convert-text-into-number-in-mysql-query
*/

DELIMITER $$
CREATE PROCEDURE insert_event_end (IN event_name VARCHAR(64), IN block_option VARCHAR(5),
/*IN year_name VARCHAR(4))*/
IN year_value INT(4))
BEGIN
	DECLARE event_start_block_index VARCHAR(5) DEFAULT NULL;
	DECLARE event_start_year_value VARCHAR(4) DEFAULT NULL;
	DECLARE block_option_index INT DEFAULT 0;
	
	/* get years.year_value from event_starts: */
	SELECT years.year_value INTO event_start_year_value FROM years 
		INNER JOIN periods on years.yearID = periods.yearID
		INNER JOIN event_starts ON periods.periodID = event_starts.periodID
		INNER JOIN events ON event_starts.eventID = events.eventID
	WHERE events.name = event_name;
	
	/* check that start year value isnt later than end year value: */
	IF event_start_year_value > year_value
		THEN CALL throw_error("Error: event_starts [year_value] > event_ends [year_value]");
	END IF;
	
	/* if years are the same: */
	IF event_start_year_value = year_value THEN
			
		/* get enum index of periods.block from event_starts: */
		SELECT periods.block+0 INTO event_start_block_index FROM periods 
			INNER JOIN event_starts ON periods.periodID = event_starts.periodID
			INNER JOIN events ON event_starts.eventID = events.eventID
		WHERE events.name = event_name;
		
		/* get enum index of block option: */
		SELECT periods.block+0 INTO block_option_index FROM periods
			INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_option AND years.year_value = year_value;
		
		/* check that start block option index isnt later than end block option index: */
		IF (event_start_block_index) > block_option_index
			THEN CALL throw_error("Error: event_starts [periods.block] > event_ends [periods.block]");
		END IF;
		
		/* check that both enum indexes arent the same: */
		IF (event_start_block_index) = block_option_index
			THEN CALL throw_error("Error: event_starts entry = event_ends entry");
		END IF;
	END IF;
	
	/* add event end entry to event_ends: */
	INSERT INTO event_ends (eventID, periodID) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = event_name),
	(SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
	WHERE periods.block = block_option AND years.year_value = year_value));
	
	
	/*
	IF event_start_year_value IS NOT NULL
		THEN CALL throw_error(event_start_year_value);
	END IF;
	*/
	
	/* look for id from event_starts with same event_name & periodID
	(i.e are you trying to insert the same event period into event_ends as in event_starts) */
	/*SELECT event_starts.event_startID INTO event_startID_check FROM event_starts
		INNER JOIN events ON event_starts.eventID = events.eventID
		INNER JOIN periods ON event_starts.periodID = periods.periodID
	WHERE events.name = event_name AND periods.periodID = (SELECT periodID FROM periods 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE periods.block = block_option AND years.name = year_name);*/
		
	/*+++++++++++ getting periods.block from event_starts +++++++++*/
	/*SELECT periods.block INTO event_start_block_option FROM periods 
		INNER JOIN event_starts ON periods.periodID = event_starts.periodID
		INNER JOIN events ON event_starts.eventID = events.eventID
	WHERE events.name = event_name;*/
		
	/*IF event_start_block_option IS NOT NULL
		THEN CALL throw_error(event_start_block_option);
	END IF;*/
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	/* throw error if above select was successfull: */
	/*IF event_startID_check > 0 */
		/*THEN CALL throw_error('Error: Inserting entry into event_ends, identical to event_starts entry.');*/
	/*ELSE*/
		/* ++++++++++checking that start period doesnt come after this end period ++++++*/
		/* get corresponding event_start value's period: */
		/*SELECT */
	/*END IF;*/
	
	
	
	
END $$
DELIMITER ;


/*THIS NEEDS TO FAIL IF START AND END PERIODS ARE THE SAME, 
OR IF END DATE COMES BEFORE START DATE+++++++++++++++++*/










