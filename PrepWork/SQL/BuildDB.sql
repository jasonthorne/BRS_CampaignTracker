
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

CREATE TABLE periods (
  periodID int NOT NULL AUTO_INCREMENT,
  blockID int,
  yearID int,
  PRIMARY KEY (periodID),
  FOREIGN KEY (blockID) REFERENCES blocks(blockID),
  FOREIGN KEY (yearID) REFERENCES years(yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
/*CREATE PROCEDURE insert_period (INOUT block VARCHAR(64), INOUT year VARCHAR(4))*/
CREATE PROCEDURE insert_period (IN block VARCHAR(64), IN year VARCHAR(4), OUT block_name VARCHAR(64), OUT year_name VARCHAR(64))
BEGIN
	INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = block),
	(SELECT yearID FROM years WHERE years.name = year));
    
    /*NEED  TO DO JOINS HERE !! */
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