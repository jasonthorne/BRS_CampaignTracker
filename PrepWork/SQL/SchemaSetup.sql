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


INSERT INTO airforces (name, description) VALUES ('Spitfire','sss');
INSERT INTO airforces (`name`,`description`) VALUES (`Hurricane`,`ssqqsss`);
INSERT INTO airforces (`name`,`description`) VALUES (`Bf109`,`asadade`);


/* planes involved */
drop table if exists planes;
CREATE TABLE `planes` (
  `planeID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`planeID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


