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
  /*CONSTRAINT name_path UNIQUE (name, path)	/* make combined columns unique */
  UNIQUE (path)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE insert_image (IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	/*INSERT IGNORE INTO images (name, path) VALUES (image_name, image_path); */
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
CREATE PROCEDURE insert_airforce (IN airforce VARCHAR(64))
BEGIN
	INSERT IGNORE INTO airforces (name) VALUES (airforce);
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
  CONSTRAINT airforceID_imageID UNIQUE (airforceID, imageID)	/* make combined columns unique */
  /*UNIQUE (imageID) /* prevent duplicate inserts */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/* insert airforce images */

DELIMITER $$
CREATE PROCEDURE insert_airforce_image (IN airforce_name VARCHAR(64), IN image_name VARCHAR(64), IN image_path VARCHAR(64))
BEGIN
	/* try find existing id for image in images: */
	DECLARE images_imageID INT DEFAULT 0;
	SELECT imageID
	INTO images_imageID
	FROM images
	WHERE images.name = image_name AND images.path = image_path;
	/*WHERE images.path = image_path;*/
	
	/*IF images_imageID = 0 THEN	/* if id isnt there: */
		/*CALL insert_image(image_name, image_path); /* insert image in images */
		
	/* insert airforceID and imageID into airforce_images */
	/*INSERT IGNORE INTO airforce_images (airforceID, imageID) VALUES (*/
	/*
	INSERT IGNORE INTO airforce_images (airforceID, imageID) VALUES (
	(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
	(SELECT imageID FROM images WHERE images.name = image_name));
	

	END IF;
	*/
	INSERT IGNORE INTO images (name, path) VALUES (image_name, image_path);
	
	
	INSERT IGNORE INTO airforce_images (airforceID, imageID) VALUES (
	(SELECT airforceID FROM airforces WHERE airforces.name = airforce_name),
	(SELECT imageID FROM images WHERE images.name = image_name AND images.path = image_path));
	
END $$
DELIMITER ;



