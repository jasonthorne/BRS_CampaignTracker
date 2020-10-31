package database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/** look into http://www.jasypt.org/ for encryption +++++++++++++++*/

public class ConnectDB {
	
	private Connection connection = null; //holds an established connection
	
	//constructor connects to DB using properties file:
	private ConnectDB() { 
		
		try { /** ++++++++++++++++++++++++++++++++look at making this a singleton instead!! :P ++++++++++ */
			//load properties:
			Properties properties = new Properties();	
			properties.load(new FileInputStream("./configs/mysql/db_configs/db_configs.properties"));
		
			//get connection using properties: 
			this.connection = DriverManager.getConnection(
					properties.getProperty("db_url"),
					properties.getProperty("db_user"), 	
					properties.getProperty("db_password"));
			
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	//return a newly established connection:
	protected static Connection getConnection() { return new ConnectDB().connection; }
}