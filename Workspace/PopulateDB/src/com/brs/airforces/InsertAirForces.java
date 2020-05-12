package com.brs.airforces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import com.brs.ConnectDB;
import com.brs.Insert;

public interface InsertAirForces {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			Arrays.asList(AirForce.values()).forEach((airForce)->{ //forEach air force in list of air forces:
				try {
					callableStatement.setString(1, airForce.toString()); //set input param
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
	
	//+++++++++++++++++++++++++++++++++++++++Overloaded method of this!! woohoo!! 
	static void insert(String yo) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			Arrays.asList(AirForce.values()).forEach((airForce)->{ //forEach air force in list of air forces:
				try {
					callableStatement.setString(1, airForce.toString()); //set input param
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