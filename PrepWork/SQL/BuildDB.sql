
DROP DATABASE if not exist sblood_red_skies_db;
CREATE DATABASE blood_red_skies_db;

USE blood_red_skies_db;



/* airforces involved */
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
