
/* every potential status for each period of history covered (eg: early, 1940, limit) */
/*

VERSION WITHOUT USING PERIODS:
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


INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));
*/




/*
INSERT INTO period_status2 (periodID, statusID) VALUES (
	(SELECT periodID FROM periods 
	JOIN blocks ON blocks.blockID = periods.periodID
	JOIN years ON years.yearID = periods.yearID 
	WHERE blocks.name = 'Early' AND years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

*/





/* Early 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Mid 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Late 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/*----------------*/

/* Early 1941 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Mid 1941 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));
	
/* Late 1941 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/*----------------*/

/* Early 1942 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Mid 1942 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));
	
/* Late 1942 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1942'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/*----------------*/

/* Early 1943 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Mid 1943 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));
	
/* Late 1943 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1943'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/*----------------*/