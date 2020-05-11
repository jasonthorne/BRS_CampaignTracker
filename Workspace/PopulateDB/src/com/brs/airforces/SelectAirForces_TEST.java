package com.brs.airforces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.ConnectDB;

public interface SelectAirForces_TEST {
	
	static void select() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL select_airforces()}"); //create statement

			callableStatement.execute(); //execute statement
			
			ResultSet resultSet = callableStatement.getResultSet();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			
			System.out.println("\nTESTING:\n");
			
			int columnCount = resultSetMetaData.getColumnCount();
			
			for (int i=1;i<=columnCount;i++) {
				System.out.print(resultSetMetaData.getColumnName(i) + " | ");
			}
			
			System.out.println();
			
			while(resultSet.next()) {
				for (int i=1;i<=columnCount;i++) {
					System.out.print(resultSet.getString(i) + " | ");
				}
				System.out.println();
			}
			
		
		
			
			//System.out.println(callableStatement.getString(1)); //print response
				
		
				
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
