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

/* insert airforce */

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

/* insert airforce images */

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

