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
			
			
			//String columnName = null;
			//int columnWidth = 0;
			//String format = null;
			
			for (int i=1;i<=columnCount;i++) {
				String columnName = resultSetMetaData.getColumnName(i);
				//columnWidth = columnName.length();
				//System.out.println("bum: " + columnName);
				String format = "%-" + columnName.length() + "s";
				//System.out.print(resultSetMetaData.getColumnName(i) + " | ");
				System.out.printf(format, columnName + "|");
			}
			
			System.out.println();
			
			int columnCount2 = resultSetMetaData.getColumnCount();
		
			while(resultSet.next()) {
				for (int i=1;i<=columnCount2;i++) {
					String columnName = resultSetMetaData.getColumnName(i);
					//System.out.println("yo: " + columnName);
					String format = "%-" + columnName.length() + "s";
					//System.out.print(resultSet.getString(i) + " | ");
					//System.out.printf(format2, resultSet.getString(i) + " | ");
					/////////System.out.printf(format, resultSet.getString(i));
					centerText(resultSet.getString(i), columnName.length());
				}
				System.out.println();
			}
			
			/*
			void centerText(char *text, int fieldWidth) {
			    int padlen = (fieldWidth - strlen(text)) / 2;
			    printf(%*s%s%*s\n", padLen, "", text, padlen, "");
			}*/
		
			
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
	
	//https://stackoverflow.com/questions/2461667/centering-strings-with-printf
	
	
	//+++++++++++++++Have to CHECK WHICH IS LONGER - column name or contents!! 
	static void centerText(String text, int width) {
		int padding = (width - text.length())/2;
		//System.out.printf("%s%s%s\n", padding, "", text, padding, "");
		System.out.printf("%" + padding + "s", "");
		System.out.printf("%" + text.length() + "s", text);
		System.out.printf("%" + padding + "s", "");
	}

}
