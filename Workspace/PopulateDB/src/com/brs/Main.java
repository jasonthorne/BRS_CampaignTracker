package com.brs;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {
	
	private static Connection connection = null;
	static {
		
		try {
			
			//load properties file:
			Properties properties = new Properties();
			properties.load(new FileInputStream("DB_login.properties"));
			String dburl = properties.getProperty("dburl"); 
			
			//get connection to DB using properties:
			connection = DriverManager.getConnection(dburl, properties.getProperty("user"), properties.getProperty("password"));
			System.out.println("Connected to Database: " + dburl + "\n");
			
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
			String sql = "select * from airforces";
			resultSet = statement.executeQuery(sql); //execute statement, storing results in resultSet
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("airforceID") + " " + resultSet.getString("name"));
			}
			
			
		}catch(Exception e) {
			//e.printStackTrace();
		}
		
		

	}

}
