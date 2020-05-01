package com.brs;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectDB {
	
	
	ConnectDB(){
		
		try {
			
			//---------------------------------------------------
			//1 - load the properties file:
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("DB_login.properties"));
			
			//---------------------------------------------------
			//3 - get a connection to the db using properties:
			
			Connection connection = DriverManager.getConnection(
					properties.getProperty("dburl"), 
					properties.getProperty("user"), 
					properties.getProperty("password"));
			
			System.out.println("Connection successfull!\n");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
