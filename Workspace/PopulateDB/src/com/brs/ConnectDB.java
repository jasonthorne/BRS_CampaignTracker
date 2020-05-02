package com.brs;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectDB {
	
	private Connection connection = null; //holds an established connection
	
	//constructor connects to DB using properties file:
	private ConnectDB(){ 
		
		try {
			//load properties:
			Properties properties = new Properties();	
			properties.load(new FileInputStream("DB_login.properties"));
			String db_url = properties.getProperty("db_url"); 
			
			//get connection using properties:
			this.connection = DriverManager.getConnection(db_url, properties.getProperty("user"), properties.getProperty("password"));
			System.out.println("Connected to: " + db_url + "\n");	//inform user of connection
			
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	public static Connection getConnection() { return new ConnectDB().connection; } //instantiate class, and return it's connection
		
}
