package com.brs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

 public interface InsertYears {
	 
	 static final Consumer<List<Year>>insertYears = (years)->{
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{call insert_year(?)}");	//create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			System.out.println("\n------------------");
			System.out.println("Inserting Years:\n"); 
			
			years.forEach((year)->{ //forEach year in list of years:
				try {
					callableStatement.setString(1, year.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println("Inserted: " + callableStatement.getString(1)); //confirm insertion
				}catch(Exception e) { e.printStackTrace(); }
			});
				
		}catch(Exception e) { e.printStackTrace(); }
		 finally {	
			 if (connection != null) {	//finally, try close connection:
				 try {
					 connection.close(); 
				 } catch (SQLException e) { e.printStackTrace(); } 
			 }
		 }
	 };
}
