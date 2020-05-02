package com.brs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

 public interface InsertYear {
	 
	 static final BiConsumer<Connection,Year>insertYear = (connection, year)->{
		 try {
			CallableStatement callableStatement = connection.prepareCall("{call insert_year(?)}");
			callableStatement.setString(1, year.toString());
			callableStatement.registerOutParameter(1, Types.VARCHAR); 
			callableStatement.execute();
			System.out.println("Inserted: " + year.getClass().getSimpleName() + " [" + callableStatement.getString(1) + "]");
				
		}catch(Exception e) { System.out.println("ERROR: " + e.getMessage()); }
	 };
	
	 
	 
	/* 
	static void insertYear(Connection connection, Year year) {
		try {
			CallableStatement callableStatement = connection.prepareCall("{call insert_year(?)}");
			callableStatement.setString(1, year.toString());
			callableStatement.registerOutParameter(1, Types.VARCHAR); 
			callableStatement.execute();
			System.out.println("Inserted: " + year.getClass().getSimpleName() + " [" + callableStatement.getString(1) + "]");
			
		}catch(Exception e) { System.out.println("ERROR: " + e.getMessage()); }
		
	}*/
}
