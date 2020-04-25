create database if not exists blood_red_skies_db;

use blood_red_skies_db;

/* airforces involved */
drop table if exists airforces;
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  description varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/*
INSERT INTO airforces (name, description) VALUES ('RAF','sss');
INSERT INTO airforces (name, description) VALUES ('Soviet','ssqqsss');
INSERT INTO airforces (name, description) VALUES ('USAAF','asadade');
*/

/* planes involved */
drop table if exists planes;
CREATE TABLE planes (
  planeID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  description varchar(64) DEFAULT NULL,
  PRIMARY KEY (planeID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/* years covered */
drop table if exists years;
CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL,
  PRIMARY KEY (yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*INSERT INTO years (name) VALUES ('1945');*/


/* blocks of a year */
drop table if exists blocks;
CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL,
  PRIMARY KEY (blockID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*INSERT INTO blocks (name) VALUES ('early');*/


/* status values of a plane */
drop table if exists status;
CREATE TABLE status (
  statusID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL,
  PRIMARY KEY (statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/* every potential status for each period of history covered */
drop table if exists period_status;
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
