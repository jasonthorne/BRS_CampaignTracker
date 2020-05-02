package com.brs;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Supplier;

public class Main implements InsertYear {
	/*
	private static Connection connection = null;
	static {	//connect to DB using properties file:
		
		try {
			//load properties:
			Properties properties = new Properties();	
			properties.load(new FileInputStream("DB_login.properties"));
			String db_url = properties.getProperty("db_url"); 
			
			//get connection using properties:
			connection = DriverManager.getConnection(db_url, properties.getProperty("user"), properties.getProperty("password"));
			System.out.println("Connected to: " + db_url + "\n");	//inform user of connection
			
		}catch(Exception e) { e.printStackTrace(); }
	}
	 */

	public static void main(String[] args) {
		
		
		
		
		// each county has its own class,. with - airforce name, planes, events (if home adv or not)
		
		/*
		Plane plane1 = new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
				new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
				new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO)));*/
		
		List<Plane>planes = Arrays.asList(
				new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
						new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO))),
				new Plane(Model.HURRICANE_I, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.AUTO),
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT)))
		);
		
		/*
		insertString(Call.INSERT_YEAR, Year.FORTY_ONE);
		insertString(Call.INSERT_YEAR, Year.FORTY_TWO);
		insertString(Call.INSERT_YEAR, Year.FORTY_FIVE);
		
		https://www.baeldung.com/java-connection-pooling
		*/
		
		
		/*
		insertYear.accept(connection, Year.FORTY_TWO); //should just be passing in yearS (list of all years) here into PREDICATE instead, and letting insertYear esatblish the connection
		insertYear.accept(connection, Year.FORTY_THREE);
		*/
		
		insertYear.accept(Year.FORTY_ONE);
		insertYear.accept(Year.FORTY_FOUR);
		
		//InsertYear.insertYear(connection, Year.FORTY_ONE);
		
		
	}
	

}
