DROP DATABASE if exists blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/*DROP TABLE IF EXISTS years; /* ++++++++++++++++++++++++++++++++++++++++++ */


/* enabled to throw errors for invalid enum inserts: */
SET GLOBAL sql_mode = 'STRICT_ALL_TABLES';
SET SESSION sql_mode = 'STRICT_ALL_TABLES';

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
/* images */

CREATE TABLE images (
	imageID INT NOT NULL AUTO_INCREMENT,
	path VARCHAR(64) DEFAULT NULL,
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
	UNIQUE (name)
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

CREATE TABLE airforce_plane_period_statuses(
	airforce_plane_period_statusID INT NOT NULL AUTO_INCREMENT,
	airforce_planeID INT,
	periodID INT,
	status ENUM ('Unavailable','Limit','Auto') NOT NULL DEFAULT 'Unavailable',
	PRIMARY KEY (airforce_plane_period_statusID),
	FOREIGN KEY (airforce_planeID) REFERENCES airforce_planes(airforce_planeID),
	FOREIGN KEY (periodID) REFERENCES periods(periodID),
	CONSTRAINT airforce_planeID_periodID UNIQUE (airforce_planeID, periodID)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_plane_period_status (IN airforce_name VARCHAR(64), IN plane_name VARCHAR(64), 
IN block_option VARCHAR(5), IN year_name VARCHAR(4), IN status_option VARCHAR(11))
BEGIN
	/* insert period to periods if not present */
	CALL insert_period (block_option, year_name);
	
	/* error thrown here if status_option doesnt match enum options, 
	or on an attempt to insert identical periods for the same airforce_plane: */
	
	INSERT INTO airforce_plane_period_statuses (airforce_planeID, periodID, status) VALUES ( 
	(SELECT airforce_planeID FROM airforce_planes
		INNER JOIN airforces ON airforce_planes.airforceID = airforces.airforceID
		INNER JOIN planes ON airforce_planes.planeID = planes.planeID
		WHERE airforces.name = airforce_name AND planes.name = plane_name),
	(SELECT periodID FROM periods
		INNER JOIN years ON periods.yearID = years.yearID
		WHERE block = block_option AND years.name = year_name),
		status_option);
END $$
DELIMITER ;

/* ====================EVENT DATA=================================================*/

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
CREATE PROCEDURE insert_event_airforce (IN event_name VARCHAR(64), IN airforce_name VARCHAR(64), IN home_advantage_value BOOLEAN)
BEGIN
	/* error thrown here on duplicate airforce_plane insert: */
	INSERT INTO event_airforces (eventID, airforceID, has_home_advantage) VALUES ( 
	(SELECT eventID FROM events WHERE events.name = event_name),
	(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
	home_advantage_value);
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
	UNIQUE (eventID)
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












