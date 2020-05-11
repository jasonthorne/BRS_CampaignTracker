package com.brs.events;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.brs.ConnectDB;
import com.brs.periods.Period;

public interface InsertEventPeriods {
	
	static void insert(Event event, Period startPeriod, Period endPeriod) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_event_period(?,?,?,?,?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //event out param
			callableStatement.registerOutParameter(2, Types.VARCHAR); //startPeriod block out param
			callableStatement.registerOutParameter(3, Types.VARCHAR); //startPeriod year out param
			callableStatement.registerOutParameter(4, Types.VARCHAR); //endPeriod block out param
			callableStatement.registerOutParameter(5, Types.VARCHAR); //endPeriod year out param
			
			try {
				callableStatement.setString(1, event.toString()); //set event input
				callableStatement.setString(2, startPeriod.getBlock().toString()); //set startPeriod block input
				callableStatement.setString(3, startPeriod.getYear().toString()); //set startPeriod year input
				callableStatement.setString(4, endPeriod.getBlock().toString()); //set endPeriod block input
				callableStatement.setString(5, endPeriod.getYear().toString()); //set endPeriod year input
				callableStatement.execute(); //execute statement
				System.out.println("EventPeriod: ["	//print response
						+ callableStatement.getString(1) + ", " 
						+ callableStatement.getString(2) + " "
						+ callableStatement.getString(3) + ", "
						+ callableStatement.getString(4) + " "
						+ callableStatement.getString(5) + "]");
			}catch(Exception e) { e.printStackTrace(); }
			
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
