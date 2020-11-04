package com.brs.airforces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.brs.ConnectDB;

public interface SelectAll {
	
	static void select(String tableName) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL select_all(?)}"); //create statement
			callableStatement.setString(1, tableName); //set input with table name
			callableStatement.execute(); //execute statement
			ResultSet resultSet = callableStatement.getResultSet(); //get result set 
			
			System.out.println(tableName+ ":");
			
			//print column data from result set:
			while(resultSet.next()) {
				for(int i=1,j=resultSet.getMetaData().getColumnCount();i<=j;i++) {
					//System.out.print(resultSet.getMetaData().getColumnName(i) + ": ");
					System.out.print("[" + resultSet.getString(i).toString() + "] ");
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
