		
		
		
		/* select start block: */
		(SELECT periods.block FROM periods
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID
		WHERE event_periods.event_periodID = (SELECT MIN(event_periodID) 
			FROM event_periods WHERE event_periods.eventID = event_ID))
		AS event_start_block, 
		
		/* select start year: */
		(SELECT years.year_value FROM years
			INNER JOIN periods ON years.yearID = periods.yearID
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID
		WHERE event_periods.event_periodID = (SELECT MIN(event_periodID) 
			FROM event_periods WHERE event_periods.eventID = event_ID))
		AS event_start_year,  
		
		/* select end block: */
		(SELECT periods.block FROM periods
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID
		WHERE event_periods.event_periodID = (SELECT MAX(event_periodID) 
			FROM event_periods WHERE event_periods.eventID = event_ID))
		AS event_end_block, 
		
		/* select end year: */
		(SELECT years.year_value FROM years
			INNER JOIN periods ON years.yearID = periods.yearID
			INNER JOIN event_periods ON periods.periodID = event_periods.periodID
		WHERE event_periods.event_periodID = (SELECT MAX(event_periodID) 
			FROM event_periods WHERE event_periods.eventID = event_ID))
		AS event_end_year  