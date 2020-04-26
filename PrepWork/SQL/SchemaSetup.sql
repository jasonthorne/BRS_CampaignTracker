
/*CREATE database if not exists blood_red_skies_db;*/

DROP DATABASE blood_red_skies_db;

CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/* airforces involved */
/*
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  description varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO airforces (name, description) VALUES ('RAF','RAF description');
INSERT INTO airforces (name, description) VALUES ('Luftwaffe','Luftwaffe description');
INSERT INTO airforces (name, description) VALUES ('USAAF','USAAF description');
INSERT INTO airforces (name, description) VALUES ('VVS','VVS description');
INSERT INTO airforces (name, description) VALUES ('IJAAF','IJAAF description');
*/

/* planes involved */
/*
CREATE TABLE planes (
  planeID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  description varchar(64) DEFAULT NULL,
  PRIMARY KEY (planeID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO planes (name, description) VALUES ('Spitfire II','Spitfire II description');
INSERT INTO planes (name, description) VALUES ('Hurricane I','Hurricane I description');
INSERT INTO planes (name, description) VALUES ('Bf109 E','Bf109 E description');
INSERT INTO planes (name, description) VALUES ('Bf110 C','Bf110 C description');
*/

/* years covered */
CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL, 
  PRIMARY KEY (yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO years (name) VALUES ('1940');
INSERT INTO years (name) VALUES ('1941');
INSERT INTO years (name) VALUES ('1942');
INSERT INTO years (name) VALUES ('1943');
INSERT INTO years (name) VALUES ('1944');
INSERT INTO years (name) VALUES ('1945');


/* blocks of a year (early, mid, late) */
CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL, 
  PRIMARY KEY (blockID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO blocks (name) VALUES ('Early');
INSERT INTO blocks (name) VALUES ('Mid');
INSERT INTO blocks (name) VALUES ('Late');

/* historical periods (eg: early 1940) */
CREATE TABLE periods (
  periodID int NOT NULL AUTO_INCREMENT,
  blockID int,
  yearID int,
  PRIMARY KEY (periodID),
  FOREIGN KEY (blockID) REFERENCES blocks(blockID),
  FOREIGN KEY (yearID) REFERENCES years(yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/* =========================== */
INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'));
/* =========================== */	
	

/* status values of a plane (none, limit, auto) */
CREATE TABLE status (
  statusID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL,
  PRIMARY KEY (statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO status (name) VALUES ('None');
INSERT INTO status (name) VALUES ('Limit');
INSERT INTO status (name) VALUES ('Auto');


/* every potential status for each period of history covered (eg: early, 1940, limit) */
CREATE TABLE period_status (
  period_statusID int NOT NULL AUTO_INCREMENT,
  blockID int,
  yearID int,
  statusID int,
  PRIMARY KEY (period_statusID),
  FOREIGN KEY (blockID) REFERENCES blocks(blockID),
  FOREIGN KEY (yearID) REFERENCES years(yearID),
  FOREIGN KEY (statusID) REFERENCES status(statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/* Early 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));


CREATE TABLE period_status2 (
  period_statusID int NOT NULL AUTO_INCREMENT,
  periodID int,
  statusID int,
  PRIMARY KEY (period_statusID),
  FOREIGN KEY (periodID) REFERENCES periods(periodID),
  FOREIGN KEY (statusID) REFERENCES status(statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/* Early, 1940, None */
INSERT INTO period_status2 (periodID, statusID) VALUES (
	(SELECT periodID FROM periods INNER JOIN blocks ON periods WHERE periods.blockID = 'Early'),
	(SELECT statusID FROM status WHERE status.name = 'None'));









