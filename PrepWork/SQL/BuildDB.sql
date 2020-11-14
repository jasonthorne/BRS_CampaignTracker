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
	year_value INT(4) DEFAULT NULL,
	PRIMARY KEY (yearID),
	UNIQUE (year_value) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_year (IN year_value INT(4))
BEGIN
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
CREATE PROCEDURE insert_period (IN block_option VARCHAR(5), IN year_value INT(4))
BEGIN
	DECLARE periodID_check INT DEFAULT 0;
	
	/* insert year_name to years if not present; */
	CALL insert_year (year_value);
	
	/* check for periodID relating to block_option & year_name: */
	SELECT periodID INTO periodID_check FROM periods
		INNER JOIN years ON periods.yearID = years.yearID
	WHERE periods.block = block_option AND years.year_value = year_value;
	
	IF periodID_check = 0 THEN /* insert entry if periodID not found: */
		/* error thrown here if block_option doesnt match enum options: */ 
		INSERT INTO periods (block, yearID) VALUES ( 
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
	/* insert plane_name to planes if not present: */
	CALL insert_plane (plane_name);
	
	/* error thrown here on duplicate airforce_plane insert: */ 
	INSERT INTO airforce_planes (airforceID, planeID) VALUES ( 
		(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
		(SELECT planeID FROM planes WHERE planes.name = plane_name));
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE select_airforce_planes (IN airforce_ID INT)
BEGIN
	SELECT
		planes.name AS plane_name,
		airforce_planes.airforce_planeID AS airforce_plane_ID
	FROM airforce_planes
		INNER JOIN planes ON airforce_planes.planeID = planes.planeID
	WHERE airforce_planes.airforceID = airforce_ID;
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
IN block_option VARCHAR(5), IN year_value INT(4), IN status_option VARCHAR(11))
BEGIN
	/* insert period to periods if not present: */
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
		WHERE block = block_option AND years.year_value = year_value),
		status_option);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE select_plane_availabilities (IN airforce_plane_ID INT, IN event_ID INT)
BEGIN

	/* ++++++++++++++++++++ bring in start and end periods from event
	then grab only the period_status that lie within that range ++++++++++++++ 
	(GET PERIOD_IDS FROM select_events  - or maybe not as we have eventID :P) ++++++++++++++++++ */
	
	/* ++++++++++get period data range using eventID to grab start and end vals */
		/* ++++++++declare as a value here. use this ORDERED value for comparrison when grabbing other periods */
		
		/*event_periodID INT NOT NULL AUTO_INCREMENT,
		eventID INT, 
		periodID_start INT,
		periodID_end INT,*/
		
		
		
	 
	SELECT
		/*(SELECT periods.periodID FROM periods 
			INNER JOIN event_periods ON periods.periodID = event_periods.eventID = event_ID
		WHERE periods.periodID = event_periods.eventID OR periods.periodID = event_periods.eventID) 
		AS target_periods,*/
		
		periods.periodID AS valid_period,
		
		periods.block AS block_option,
		years.year_value AS year_value,
		plane_availabilities.status AS status_option
	FROM plane_availabilities
		INNER JOIN periods ON plane_availabilities.periodID = periods.periodID
		INNER JOIN years ON periods.yearID = years.yearID
	/*WHERE plane_availabilities.airforce_planeID = airforce_plane_ID; ++++++++++++*/
	WHERE plane_availabilities.airforce_planeID = airforce_plane_ID
	AND valid_period IN (SELECT periods.periodID FROM periods 
			INNER JOIN event_periods ON periods.periodID = event_periods.eventID = event_ID);
	/* ++++++++++++++++ Look at what youve added above :P ++++++++++++++++ */
	
	
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
CREATE PROCEDURE select_events ()
BEGIN
	SELECT
		events.name AS event_name,
		events.eventID AS event_ID,
		
		/* select start block: */
		(SELECT periods.block FROM periods 
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID_start
		WHERE periods.periodID = event_periods.periodID_start AND event_periods.eventID = event_ID) 
		AS event_start_block,
		
		/* select start year: */
		(SELECT years.year_value FROM years 
			INNER JOIN periods ON years.yearID = periods.yearID
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID_start
		WHERE periods.periodID = event_periods.periodID_start AND event_periods.eventID = event_ID) 
		AS event_start_year,
		
		/* select end block: */
		(SELECT periods.block FROM periods
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID_end
		WHERE periods.periodID = event_periods.periodID_end AND event_periods.eventID = event_ID) 
		AS event_end_block,
		
		/* select end year: */
		(SELECT years.year_value FROM years 
			INNER JOIN periods ON years.yearID = periods.yearID
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID_end
		WHERE periods.periodID = event_periods.periodID_end AND event_periods.eventID = event_ID) 
		AS event_end_year
		
	FROM events; 
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
CREATE PROCEDURE select_event_airforces (IN event_ID INT)
BEGIN
	SELECT
		airforces.name AS airforce_name, 
		event_airforces.has_home_advantage AS home_advantage_value,
		event_airforces.airforceID AS airforce_ID
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
IN year_start INT(4), IN block_end VARCHAR(5), IN year_end INT(4))
BEGIN
	DECLARE block_start_index INT DEFAULT 0;
	DECLARE block_end_index INT DEFAULT 0;
	
	/* check that start year value isnt later than end year value: */
	IF year_start > year_end
		THEN CALL throw_error("insert_event_period: [year_start] > [year_end]");
	END IF;
	
	/* if years are the same: */
	IF year_start = year_end THEN
			
		/* get enum index of block_start: */
		SELECT periods.block+0 INTO block_start_index FROM periods 
			INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_start AND years.year_value = year_start;
		
		/* get enum index of block_end: */
		SELECT periods.block+0 INTO block_end_index FROM periods
			INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_end AND years.year_value = year_end;
		
		/* check that block_start_index isnt later than block_end_index: */
		IF block_start_index > block_end_index
			THEN CALL throw_error("insert_event_period: [block_start] > [block_end]");
		END IF;
		
		/* check that both enum indexes arent the same: */
		IF block_start_index = block_end_index
			THEN CALL throw_error("insert_event_period: [block_start, year_start] = [block_end, year_end]");
		END IF;
		
	END IF;
	
	/* add event period to event_periods: */
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

