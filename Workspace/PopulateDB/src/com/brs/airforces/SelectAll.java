package com.brs.airforces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.brs.ConnectDB;

public interface SelectAll {
	
	//stored procedure call options:
	enum Call{
		SELECT_BLOCKS("{CALL select_blocks()}"),
		SELECT_AIR_FORCES("{CALL select_airforces()}"); ///////////????????think  about provign tables with no data!!???
		
		private final String call; //call
		private Call(String call) { this.call = call; } //set call
		@Override 
		public String toString() { return call; } //return call
	}
	
	static void select(Call call) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall(call.toString()); //create statement
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
