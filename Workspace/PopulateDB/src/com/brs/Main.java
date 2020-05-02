package com.brs;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {
	
	private static Connection connection = null;
	static {//possibly deserialise properties file. then ask user to input password and username and IF same as loaded versions, then login.
		
		try {
			
			//load properties file:
			Properties properties = new Properties();
			properties.load(new FileInputStream("DB_login.properties"));
			String db_url = properties.getProperty("db_url"); 
			
			//get connection to DB using properties:
			connection = DriverManager.getConnection(db_url, properties.getProperty("user"), properties.getProperty("password"));
			System.out.println("Connected to: " + db_url + "\n");
			
		}catch(Exception e) { e.printStackTrace(); }
	}
	

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
		
		insertString(Call.INSERT_YEAR, Year.FORTY_ONE);
		insertString(Call.INSERT_YEAR, Year.FORTY_TWO);
		insertString(Call.INSERT_YEAR, Year.FORTY_FIVE);
		
		/*
		try { //put al this in a method!!! +++++++++++++
			
			CallableStatement callableStatement = connection.prepareCall("{call insert_year(?)}"); //change string with param
			callableStatement.setString(1, "9867");
			callableStatement.registerOutParameter(1, Types.VARCHAR); 
			
			callableStatement.execute();
			
			System.out.println("inserted: " + callableStatement.getString(1));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
	}
	
	private static <T extends Enum<T>> void insertString(Call call, Enum<T> data) {
		
		try {
			CallableStatement callableStatement = connection.prepareCall(call.toString());
			callableStatement.setString(1, data.toString());
			callableStatement.registerOutParameter(1, Types.VARCHAR); 
			callableStatement.execute();
			System.out.println("Inserted: " + callableStatement.getString(1));
			
		}catch(Exception e) { System.out.println("ERROR: " + e.getMessage()); }
	}

}
