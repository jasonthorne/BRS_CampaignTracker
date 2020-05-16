DROP DATABASE if exists blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

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
CREATE PROCEDURE insert_year (IN year VARCHAR(4))
BEGIN
	INSERT IGNORE INTO years (name) VALUES (year); 
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
	
	/* insert year_name to years if not present */
	CALL insert_year (year_name);
	
	/* check for existing id relating to block_option & year_name: */
	SELECT periodID INTO periodID_check FROM periods
		INNER JOIN years ON periods.yearID = years.yearID
		WHERE periods.block = block_option AND years.name = year_name;
	
	IF periodID_check = 0 THEN /* if id isn't there: */
		/*no IGNORE here as exception should be thrown if block_option doesnt match enum options */ 
		INSERT INTO periods (block, yearID) VALUES (
			block_option,(SELECT yearID FROM years WHERE years.name = year_name));
	END IF;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* images */

CREATE TABLE images (
	imageID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	path VARCHAR(64) DEFAULT NULL,
	PRIMARY KEY (imageID),
	UNIQUE (path) /* prevent duplicate inserts */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_image (IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	INSERT IGNORE INTO images (name, path) VALUES (image_name, image_path); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces involved */

CREATE TABLE airforces( 
	airforceID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	PRIMARY KEY (airforceID),
	UNIQUE (name) /* prevent duplicate inserts */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce (IN airforce_name VARCHAR(64))
BEGIN
	INSERT IGNORE INTO airforces (name) VALUES (airforce_name);
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforce images */

CREATE TABLE airforce_images (
	airforce_imageID INT NOT NULL AUTO_INCREMENT,
	airforceID INT,
	imageID INT,
	PRIMARY KEY (airforce_imageID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
	FOREIGN KEY (imageID) REFERENCES images(imageID),
	UNIQUE (imageID) /* prevent duplicate inserts */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_image (IN airforce_name VARCHAR(64), IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	/* check for existing id relating to path: */
	DECLARE imageID_check INT DEFAULT 0;
	SELECT imageID INTO imageID_check FROM images WHERE images.path = image_path;
		
	IF imageID_check = 0 THEN	/* if id isn't there: */
		CALL insert_image(image_name, image_path); /* insert image in images */
		
		/* insert airforceID and imageID into airforce_images */
		INSERT IGNORE INTO airforce_images (airforceID, imageID) VALUES (
			(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
			(SELECT imageID FROM images WHERE images.name = image_name AND images.path = image_path));
	END IF;
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* planes available */

CREATE TABLE planes(
	planeID INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) DEFAULT NULL,
	PRIMARY KEY (planeID),
	UNIQUE (name)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


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
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_plane (IN airforce_name VARCHAR(64), IN plane_name VARCHAR(64))
BEGIN
	/* insert plane_name to planes if not present */
	CALL insert_plane (plane_name);

	INSERT IGNORE INTO airforce_planes (airforceID, planeID) VALUES (
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
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_plane_period_status (IN airforce_name VARCHAR(64), IN plane_name VARCHAR(64), 
IN block_option VARCHAR(64), IN year_name VARCHAR(4), IN status_option VARCHAR(64))
BEGIN
	/* insert period to periods if not present */
	CALL insert_period (block_option, year_name);
	
	/* no IGNORE here as exception should be thrown if status_option doesnt match enum options, 
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


	





