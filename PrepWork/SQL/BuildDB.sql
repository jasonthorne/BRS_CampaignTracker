
DROP DATABASE if exists blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/*----------------------------------------------------*/
/* images */

CREATE TABLE images (
  imageID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  path varchar(64) DEFAULT NULL,
  PRIMARY KEY (imageID),
  CONSTRAINT name_path UNIQUE (name, path)	/* make combined columns unique */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_image (IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	INSERT IGNORE INTO images (name, path) VALUES (image_name, image_path); 
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* blocks of a year (early, mid, late) */

CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL, 
  PRIMARY KEY (blockID),
  UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_block (INOUT block VARCHAR(64))
BEGIN
	INSERT IGNORE INTO blocks (name) VALUES (block); 
END $$
DELIMITER ;

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
	INSERT IGNORE INTO periods (blockID, yearID) VALUES (
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
  UNIQUE (name) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_event (IN event VARCHAR(64))
BEGIN
	INSERT IGNORE INTO events (name) VALUES (event);  /* ++++++++++++++++++++++++++++ADD THIS WHERE NEEDED ELSEWHERE!!! ++++++++++++++++*/
END $$
DELIMITER ;

/*----------------------------------------------------*/
/* airforces involved */

CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  /*image_path varchar(64) DEFAULT NULL,++++++++++++++++++++HAVE A SEPERATE TABLWE FOR IMAGES airforce_images HOLDING MULTIPLE IMAGES*/
  PRIMARY KEY (airforceID),
  UNIQUE (name) /* prevent duplicate inserts */
  /*UNIQUE (image_path)*/
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/* insert airforce */

DELIMITER $$
CREATE PROCEDURE insert_airforce (IN airforce VARCHAR(64))
BEGIN
	INSERT IGNORE INTO airforces (name) VALUES (airforce);
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
/*CREATE PROCEDURE insert_event_airforce (INOUT event VARCHAR(64), INOUT airforce VARCHAR(64), INOUT has_home_advantage BOOLEAN)*/
CREATE PROCEDURE insert_event_airforce (IN event VARCHAR(64), IN airforce VARCHAR(64), IN has_home_advantage BOOLEAN)
BEGIN
	INSERT IGNORE INTO event_airforces (eventID, airforceID, has_home_advantage) VALUES (
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

/* ================================================ */
CREATE TABLE airforce_images (
  airforce_imageID int NOT NULL AUTO_INCREMENT,
  airforceID,
  imageID,
  PRIMARY KEY (airforce_imageID),
  FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
  FOREIGN KEY (imageID) REFERENCES images(imageID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*
DELIMITER $$
CREATE PROCEDURE insert_airforce_image (IN airforce_name VARCHAR(64), IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	/* try find existing id for image in images:
	int images_imageID;
	SELECT imageID INTO images_imageID FROM images 
	WHERE images.name = image_name AND images.path = image_path;
	
	/* if id isnt there (images_imageID is null): 
	if (images_imageID is NULL){
	
		/* insert image in images 
		CALL insert_image(image_name, image_path); 
		
		/* get newly created id 
		SELECT imageID 
		INTO images_imageID 
		FROM images 
		WHERE images.name = image_name;
		
		
		/* insert id and airforce_name into airforce_images
		/*
		INSERT IGNORE INTO airforce_images (name, path) VALUES (image, image_path);
		
		INSERT IGNORE INTO periods (blockID, yearID) VALUES (
		(SELECT blockID FROM blocks WHERE blocks.name = block),
		(SELECT yearID FROM years WHERE years.name = year));
	
	
END $$
DELIMITER ;
}*/
/* ================================================ */
/*----------------------------------------------------*/
	/* GENERIC PROCEDURES */
/*----------------------------------------------------*/
/* select all entries */

DELIMITER $$
CREATE PROCEDURE select_all(in table_name VARCHAR(64))
BEGIN
	SELECT * FROM table_name 
	/*ORDER BY airforceID ASC; ++++++++++++++++FIGURE THIS OUT :P */ 
END $$
DELIMITER ;

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