
DROP DATABASE if exists blood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;


/* years covered */
CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL, 
  PRIMARY KEY (yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE PROCEDURE setYears (IN year VARCHAR(4))
BEGIN
	INSERT INTO years (name) VALUES (year); 
END $$
DELIMITER ;

/*
ROCEDURE `get_count_for_department`(IN the_department VARCHAR(64), OUT the_count INT)
BEGIN
	
	SELECT COUNT(*) INTO the_count FROM employees where department=the_department;

END$$


DELIMITER $$
DROP PROCEDURE IF EXISTS `get_count_for_department`$$

CREATE DEFINER=`student`@`localhost` PROCEDURE `get_count_for_department`(IN the_department VARCHAR(64), OUT the_count INT)
BEGIN
	
	SELECT COUNT(*) INTO the_count FROM employees where department=the_department;

END$$
DELIMITER ;
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