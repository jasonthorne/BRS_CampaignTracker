package com.brs.airforces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.brs.ConnectDB;

public interface SelectAirForces_TEST {
	/*
	enum Call{
		SELECT_AIRFORCES;
	}*/
	
	static void select() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL select_airforces()}"); //create statement
			callableStatement.execute(); //execute statement
			ResultSet resultSet = callableStatement.getResultSet(); //get result set 
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); //get meta data
			
			System.out.println(resultSetMetaData.getTableName(1) + ":\n"); //print table name using pk column
			
			//print column data from result set:
			while(resultSet.next()) {
				for(int i=1,j=resultSetMetaData.getColumnCount();i<=j;i++) {
					System.out.print("[" + resultSet.getString(i).toString() + "]");
				}
				System.out.print("\n");
			}
			
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
