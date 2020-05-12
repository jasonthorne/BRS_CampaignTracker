package com.brs.airforces;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.brs.ConnectDB;

public interface SelectAirForces_TEST {
	
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
					System.out.print("|" + resultSet.getString(i));
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
	
	//https://stackoverflow.com/questions/2461667/centering-strings-with-printf
	
	
	
	
	//int columnCount = resultSetMetaData.getColumnCount();
	
	
	//String columnName = null;
	//int columnWidth = 0;
	//String format = null;
	
	
	
	/* MAYBE return to this. Maybe!! 
	 * 
	 
	//+++++++++++++++Have to CHECK WHICH IS LONGER - column name or contents!! 
	static void centerText(String text, int width) {
		int padding = (width - text.length())/2;
		String l = String.valueOf(text.length());
		System.out.printf("%*s%" + text.length() + "s%*s", padding, text, padding);
		//System.out.printf("%" + padding + "s", "");
		//System.out.printf("%" + l + "s", text);
		//System.out.printf("%" + padding + "s", "");
	}
	
	for (int i=1;i<=columnCount;i++) { //////////////look a tcomapreTos to copare both these lengths to find biggest for layout width sizing
		String columnName = resultSetMetaData.getColumnName(i); ////////////////////SIZE OF COLUMN NAME
		int columnNameSizeTEST = resultSetMetaData.getColumnName(i).length(); ////////////////////SIZE OF COLUMN NAME
		int columnsizeTEST = resultSetMetaData.getPrecision(i); ////////////////////SIZE OF colum - compare these to find max size of rows!
		//columnWidth = columnName.length();
		//System.out.println("bum: " + columnName);
		String format = "%-" + columnName.length() + "s";
		//System.out.print(resultSetMetaData.getColumnName(i) + " | ");
		System.out.printf(format, columnName + "|");
	}
	
	
	while(resultSet.next()) {
		//for (int i=1;i<=columnCount;i++) {
		for (int i=1, j=resultSetMetaData.getColumnCount(); i<=j;i++) {
			String columnName = resultSetMetaData.getColumnName(i);
			//System.out.println("yo: " + columnName);
			String format = "%-" + (columnName.length()+1) + "s";
			//System.out.print(resultSet.getString(i) + " | ");
			//System.out.printf(format2, resultSet.getString(i) + " | ");
			//System.out.printf(format, resultSet.getString(i));
			System.out.print("|" + resultSet.getString(i));
			//centerText(resultSet.getString(i), columnName.length());
		}
		System.out.print("\n");
	}
	*/
	

}
