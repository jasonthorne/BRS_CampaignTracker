package com.brs.events;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.brs.ConnectDB;

public interface InsertEventAirForces {
	
	static void insert(Event event, List<EventAirForce>eventAirForces) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_event_airforce(?,?,?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register event out param
			callableStatement.registerOutParameter(2, Types.VARCHAR); //register air force out param
			callableStatement.registerOutParameter(3, Types.BOOLEAN); //register home advantage out param
			
			System.out.println("------------------");
			System.out.println("EventAirforces:\n");
			
			eventAirForces.forEach((airForce)->{ //forEach air force in eventAirForces:
				try {
					callableStatement.setString(1, event.toString()); //set event input
					callableStatement.setString(2, airForce.getAirForce().toString()); //set air force input
					callableStatement.setBoolean(3, airForce.getHomeAdvantage().getBoolean()); //set home advantage input
					callableStatement.execute(); //execute statement
					System.out.println("Inserted: "	//print response
							+ callableStatement.getString(1) + " " 
							+ callableStatement.getString(2) + " "
							+ callableStatement.getBoolean(3)); 
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
