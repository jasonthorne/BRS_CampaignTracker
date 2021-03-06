package com.brs.years;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import com.brs.ConnectDB;

public interface InsertYears {
	 
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_year(?)}");	//create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			Arrays.asList(Year.values()).forEach((year)->{ //forEach year in list of years:
				try {
					callableStatement.setString(1, year.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println(callableStatement.getString(1)); //print response
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
	}
}
