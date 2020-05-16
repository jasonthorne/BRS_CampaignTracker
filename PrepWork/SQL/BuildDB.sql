DROP DATABASE if exists blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/*SET SESSION sql_mode='STRICT_TRANS_TABLES';*/
SET GLOBAL sql_mode = 'STRICT_ALL_TABLES';

/*----------------------------------------------------*/
/* blocks of a year (early, mid, late) */
 /* https://www.mysqltutorial.org/mysql-enum/ +++++++++++++*/ 
 /*name varchar(64) DEFAULT NULL, */
/*
CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (blockID),
  UNIQUE (name)  /* prevent duplicate inserts 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO blocks (name) VALUES ('Early');
INSERT INTO blocks (name) VALUES ('Mid');
INSERT INTO blocks (name) VALUES ('Late');
*/
/*----------------------------------------------------*/
/* years covered */

CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL,
  PRIMARY KEY (yearID),
  UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_year (INOUT year VARCHAR(4))
BEGIN
	INSERT IGNORE INTO years (name) VALUES (year); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* historical periods (eg: early 1940) */

CREATE TABLE periods ( 
  periodID int NOT NULL AUTO_INCREMENT,
  /*blockID int,*/
  block ENUM ('Early','Mid','Late') NOT NULL,
  yearID int,
  PRIMARY KEY (periodID),
  /*FOREIGN KEY (blockID) REFERENCES blocks(blockID),*/
  FOREIGN KEY (yearID) REFERENCES years(yearID),
  /*CONSTRAINT blockID_yearID UNIQUE (blockID, yearID)	/* make combined columns unique */
  CONSTRAINT block_yearID UNIQUE (block, yearID) /* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_period (IN block_name VARCHAR(5), IN year_name VARCHAR(4))
BEGIN
	/* insert year_name to years if not present */
	CALL insert_year (year_name);
	
	/*no IGNORE here as exception should be thrown if block_value doesnt match enum options */ /* this now doesnt ignore duplicate periods! DOH!! */
	INSERT IGNORE INTO periods (block, yearID) VALUES (
	block_name,
	/*(SELECT blockID FROM blocks WHERE blocks.name = block_name),*/
	(SELECT yearID FROM years WHERE years.name = year_name));
END $$
DELIMITER ;

/*
DELIMITER $$
CREATE PROCEDURE insert_period (IN block VARCHAR(64), IN year VARCHAR(4))
BEGIN
	INSERT IGNORE INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = block),
	(SELECT yearID FROM years WHERE years.name = year));
END $$
DELIMITER ;
*/

/*----------------------------------------------------*/
/* images */

CREATE TABLE images (
  imageID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  path varchar(64) DEFAULT NULL,
  PRIMARY KEY (imageID),
  UNIQUE (path) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_image (IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	INSERT IGNORE INTO images (name, path) VALUES (image_name, image_path); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces involved */

CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID),
  UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce (IN airforce_name VARCHAR(64))
BEGIN
	INSERT IGNORE INTO airforces (name) VALUES (airforce_name);
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforce images */

CREATE TABLE airforce_images (
  airforce_imageID int NOT NULL AUTO_INCREMENT,
  airforceID int,
  imageID int,
  PRIMARY KEY (airforce_imageID),
  FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
  FOREIGN KEY (imageID) REFERENCES images(imageID),
  UNIQUE (imageID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_image (IN airforce_name VARCHAR(64), IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	/* check for existing id relating to path: */
	DECLARE images_imageID INT DEFAULT 0;
	SELECT imageID
	INTO images_imageID
	FROM images
	WHERE images.path = image_path;
	
	IF images_imageID = 0 THEN	/* if id isn't there: */
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
  planeID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
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
  airforce_planeID int NOT NULL AUTO_INCREMENT,
  airforceID int,
  planeID int,
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

	INSERT IGNORE INTO airforce_planes (airforceID, planeID) VALUES (
	(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
	(SELECT planeID FROM planes WHERE planes.name = plane_name));	
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* status values of a plane */
/*
CREATE TABLE plane_statuses (
  plane_statusID int NOT NULL AUTO_INCREMENT,
  /*name varchar(5) DEFAULT NULL,
  name enum ('Unavailable','Limit','Auto') NOT NULL DEFAULT 'Unavailable',
  PRIMARY KEY (plane_statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO plane_statuses (name) VALUES ('Unavailable');
INSERT INTO plane_statuses (name) VALUES ('Limit');
INSERT INTO plane_statuses (name) VALUES ('Auto');
*/

/*----------------------------------------------------*/
/* availability statuses of air force planes in relation to historical periods */

CREATE TABLE airforce_plane_period_statuses(
  airforce_plane_period_statusID int NOT NULL AUTO_INCREMENT,
  airforce_planeID int,
  periodID int,
  status ENUM ('Unavailable','Limit','Auto') NOT NULL DEFAULT 'Unavailable',
  /*plane_statusID int,*/
  PRIMARY KEY (airforce_plane_period_statusID),
  FOREIGN KEY (airforce_planeID) REFERENCES airforce_planes(airforce_planeID),
  FOREIGN KEY (periodID) REFERENCES periods(periodID),
  /*FOREIGN KEY (plane_statusID) REFERENCES plane_statuses(plane_statusID),*/
  CONSTRAINT airforce_planeID_periodID UNIQUE (airforce_planeID, periodID)	/* make combined columns unique */
  /*CONSTRAINT airforce_planeID_periodID_status UNIQUE (airforce_planeID, periodID, status)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DELIMITER $$
CREATE PROCEDURE insert_airforce_plane_period_status (IN airforce_name VARCHAR(64), IN plane_name VARCHAR(64), 
IN block_name VARCHAR(64), IN year_name VARCHAR(4), IN status_name VARCHAR(64))
BEGIN
	
	/*
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
    ROLLBACK;
    SELECT 'An error has occurred, operation rollbacked and the stored procedure was terminated';
	END;
	*/

	/* insert period to periods if not present */
	CALL insert_period (block_name, year_name);
	
	/* change this along with periods +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
	/*no IGNORE here as exception should be thrown if status_value doesnt match enum options */ 
	INSERT INTO airforce_plane_period_statuses (airforce_planeID, periodID, status) VALUES ( 
	(SELECT airforce_planeID FROM airforce_planes
		INNER JOIN airforces ON airforce_planes.airforceID = airforces.airforceID
		INNER JOIN planes ON airforce_planes.planeID = planes.planeID
		WHERE airforces.name = airforce_name AND planes.name = plane_name),
	(SELECT periodID FROM periods
		/*INNER JOIN blocks ON periods.blockID = blocks.blockID*/
		INNER JOIN years ON periods.yearID = years.yearID
		/*WHERE blocks.name = block_name AND years.name = year_name),*/
		WHERE block = block_name AND years.name = year_name),
	status_name);
	/*(SELECT plane_statusID FROM plane_statuses WHERE plane_statuses.name = status_name));*/
	
	/*
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	make a sperate table for plane_statuses then check if given status is valid, and if not, then throw exception 
	*/
	
END $$
DELIMITER ;

/*
BEGIN block
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
*/


/*
	(SELECT airforce_planeID FROM airforce_planes WHERE airforces.name = airforce_name),
	(SELECT planeID FROM planes WHERE planes.name = plane_name
	*/
	/*join airforces, join planes, join periods */
	
	





