
/*CREATE database if not exists blood_red_skies_db;*/

DROP DATABASE blood_red_skies_db;

CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;

/* campaigns being played */

/* ?????????????????
CREATE TABLE campaigns( 
  campaignID int NOT NULL AUTO_INCREMENT,
  campaign BLOB NOT NULL,
  eventID_event_name int, 
  start_date date,	
  players BLOB NOT NULL, 
  periodID_current_period int
  PRIMARY KEY (campaignID)
  FOREIGN KEY (periodID) REFERENCES periods(periodID),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
*/



/*

lookup enums & sets. set for possible opponents list!!
https://dev.mysql.com/doc/refman/5.6/en/set.html
*/


/* airforces involved */
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO airforces (name) VALUES ('RAF');
INSERT INTO airforces (name) VALUES ('Luftwaffe');
INSERT INTO airforces (name) VALUES ('USAAF');
INSERT INTO airforces (name) VALUES ('VVS');
INSERT INTO airforces (name) VALUES ('IJAAF');
*/

/* models of plane involved */
CREATE TABLE models (
  modelID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (modelID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO models (name) VALUES ('Spitfire II');
INSERT INTO models (name) VALUES ('Hurricane I');
INSERT INTO models (name) VALUES ('Bf109 E');
INSERT INTO models (name) VALUES ('Bf110 C');
*/

/* years covered */
CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL, 
  PRIMARY KEY (yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO years (name) VALUES ('1940');
INSERT INTO years (name) VALUES ('1941');
INSERT INTO years (name) VALUES ('1942');
INSERT INTO years (name) VALUES ('1943');
INSERT INTO years (name) VALUES ('1944');
INSERT INTO years (name) VALUES ('1945');
*/

/* blocks of a year (early, mid, late) */

/* look up enus for this!! https://stackoverflow.com/questions/22168972/how-can-i-add-list-of-items-in-single-column-in-mysql */ 
CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL, 
  PRIMARY KEY (blockID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO blocks (name) VALUES ('Early');
INSERT INTO blocks (name) VALUES ('Mid');
INSERT INTO blocks (name) VALUES ('Late');
*/

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
/*
INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'));
	
	
INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1941'));
	
INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1942'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1942'));

INSERT INTO periods (blockID, yearID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1942'));
	*/
	
/* ============================ */	
	

/* status values of a plane (none, limit, auto) https://stackoverflow.com/questions/22168972/how-can-i-add-list-of-items-in-single-column-in-mysql*/
CREATE TABLE status (
  statusID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL,
  PRIMARY KEY (statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO status (name) VALUES ('None');
INSERT INTO status (name) VALUES ('Limit');
INSERT INTO status (name) VALUES ('Auto');
*/

/* each potential status for each period of history covered (eg: early, 1940, limit) */
CREATE TABLE period_status (
  period_statusID int NOT NULL AUTO_INCREMENT,
  periodID int,
  statusID int,
  PRIMARY KEY (period_statusID),
  FOREIGN KEY (periodID) REFERENCES periods(periodID),
  FOREIGN KEY (statusID) REFERENCES status(statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/* Early, 1940, None */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID 
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Early' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

*/
/* Early, 1940, Limit */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Early' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));
*/
/* Early, 1940, Auto */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Early' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));
	*/
/* Mid, 1940, None */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Mid' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));
	*/
/* Mid, 1940, Limit */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Mid' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));
/*
/* Mid, 1940, Auto */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Mid' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

*/
/* Late, 1940, None */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Late' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));
	*/
/* Late, 1940, Limit */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Late' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));
*/
/* Late, 1940, Auto */
/*
INSERT INTO period_status (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Late' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

*/
/* planes */
CREATE TABLE planes (
	planeID int NOT NULL AUTO_INCREMENT,
	modelID int,
	airforceID int,
	period_statusID_early_1940 int, period_statusID_mid_1940 int, period_statusID_late_1940 int,
	period_statusID_early_1941 int, period_statusID_mid_1941 int, period_statusID_late_1941 int,
	period_statusID_early_1942 int, period_statusID_mid_1942 int, period_statusID_late_1942 int,
	period_statusID_early_1943 int, period_statusID_mid_1943 int, period_statusID_late_1943 int,
	period_statusID_early_1944 int, period_statusID_mid_1944 int, period_statusID_late_1944 int,
	period_statusID_early_1945 int, period_statusID_mid_1945 int,
	PRIMARY KEY (planeID),
	FOREIGN KEY (modelID) REFERENCES models(modelID),
	FOREIGN KEY (airforceID) REFERENCES airforces(airforceID),
	FOREIGN KEY (period_statusID_early_1940) REFERENCES period_status(period_statusID),	/* 1940 */
	FOREIGN KEY (period_statusID_mid_1940) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_late_1940) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_early_1941) REFERENCES period_status(period_statusID),	/* 1941 */
	FOREIGN KEY (period_statusID_mid_1941) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_late_1941) REFERENCES period_status(period_statusID), 
	FOREIGN KEY (period_statusID_early_1942) REFERENCES period_status(period_statusID),	/* 1942 */
	FOREIGN KEY (period_statusID_mid_1942) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_late_1942) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_early_1943) REFERENCES period_status(period_statusID),	/* 1943 */
	FOREIGN KEY (period_statusID_mid_1943) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_late_1943) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_early_1944) REFERENCES period_status(period_statusID),	/* 1944 */
	FOREIGN KEY (period_statusID_mid_1944) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_late_1944) REFERENCES period_status(period_statusID),
	FOREIGN KEY (period_statusID_early_1945) REFERENCES period_status(period_statusID),	/* 1945 */
	FOREIGN KEY (period_statusID_mid_1945) REFERENCES period_status(period_statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
https://codeshare.io/Gq0M6j?fbclid=IwAR3QkBGNZf9qPfRy4g4Frv84m0qUiSZxQTQ9Bwrmgo2urceTHIUBVxWD-70
*/

/*
INSERT INTO planes (
	modelID, 
	airforceID,
	period_statusID_early_1940*//*, period_statusID_mid_1940, period_statusID_late_1940,
	period_statusID_early_1941, period_statusID_mid_1941, period_statusID_late_1941,
	period_statusID_early_1942,	period_statusID_mid_1942, period_statusID_late_1942,
	period_statusID_early_1943,	period_statusID_mid_1943, period_statusID_late_1943,
	period_statusID_early_1944, period_statusID_mid_1944, period_statusID_late_1944,
	period_statusID_early_1945,	period_statusID_mid_1945*//*
	) VALUES (
	(SELECT modelID FROM models WHERE models.name = 'Spitfire II'),
	(SELECT airforceID FROM airforces WHERE airforces.name = 'RAF'),
	(SELECT period_statusID FROM period_status
		INNER JOIN periods ON period_status.periodID = periods.periodID
		INNER JOIN blocks ON periods.blockID = blocks.blockID 
        INNER JOIN years ON periods.yearID = years.yearID
        INNER JOIN status ON period_status.statusID = status.statusID
		WHERE blocks.name = 'Early' AND years.name = '1940' AND status.name = 'None'));

*/

/* airforces involed in an event 

https://www.w3schools.com/sql/sql_alter.asp
*/

/* names of historical events */
CREATE TABLE event_names( 
  event_nameID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (event_nameID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
INSERT INTO event_names (name) VALUES ('Battle of Britain');
INSERT INTO event_names (name) VALUES ('Stalingrad');
*/

/* historical events */
CREATE TABLE events( 
  eventID int NOT NULL AUTO_INCREMENT,
  event_nameID int,
  periodID_start_period int,
  periodID_end_period int,
  PRIMARY KEY (eventID),
  FOREIGN KEY (event_nameID) REFERENCES event_names(event_nameID),
  FOREIGN KEY (periodID_start_period) REFERENCES periods(periodID),
  FOREIGN KEY (periodID_end_period) REFERENCES periods(periodID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
	


/*
ALTER TABLE events
ADD test int,
ADD FOREIGN KEY (test) REFERENCES airforces(airforceID);


INSERT INTO events (test) VALUES (
	(SELECT airforceID FROM airforces WHERE airforces.name ='USAAF'));
*/


/*
this
------
thisID
eventID fk

oneof these made for each airforce
then add airforces to this during event building
then myabe link this to events????


OR


event_airforces
thisID, eventId, 


*/



/*
INSERT INTO events (event_nameID, periodID_start_period, periodID_end_period) VALUES (
	(SELECT event_nameID FROM event_names WHERE event_names.name = 'Battle of Britain'),
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Late' AND years.name = '1940'),
	(SELECT periodID FROM periods 
		INNER JOIN blocks ON periods.blockID = blocks.blockID
		INNER JOIN years ON periods.yearID  = years.yearID
		WHERE blocks.name = 'Mid' AND years.name = '1941'));
*/
	/* add airforces */ 
	/* add home advantage airforces */

/* +++++++++++++++++woohoo!!
SELECT period_statusID FROM period_status
		INNER JOIN periods ON period_status.periodID = periods.periodID
		INNER JOIN blocks ON periods.blockID = blocks.blockID 
        INNER JOIN years ON periods.yearID = years.yearID
        INNER JOIN status ON period_status.statusID = status.statusID
		WHERE blocks.name = 'Early' AND years.name = '1940'
        AND status.name = 'None';
*/