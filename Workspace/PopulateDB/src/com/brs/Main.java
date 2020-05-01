package com.brs;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
			String db_url = properties.getProperty("dburl"); 
			
			//get connection to DB using properties:
			connection = DriverManager.getConnection(db_url, properties.getProperty("user"), properties.getProperty("password"));
			System.out.println("Connected to Database: " + db_url + "\n");
			
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
		
		//Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			//---------------------------------------------------
			//4 - test connection:
			
			
			//prepare statement:
			statement = connection.createStatement(); //create a statement using connection
			String sql = "INSERT INTO years (name) VALUES ('1940')"; 
			statement.executeUpdate(sql);
			
			resultSet = statement.executeQuery("SELECT * FROM years"); //execute statement, storing results in resultSet
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("yearID") + " " + resultSet.getString("name"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
