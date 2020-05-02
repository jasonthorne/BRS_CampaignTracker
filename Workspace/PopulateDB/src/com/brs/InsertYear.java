package com.brs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

 public interface InsertYear {
	 
	 static final Consumer<Year>insertYear = (year)->{
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call insert_year(?)}");
			callableStatement.setString(1, year.toString());
			callableStatement.registerOutParameter(1, Types.VARCHAR); 
			callableStatement.execute();
			System.out.println("Inserted: " + year.getClass().getSimpleName() + " [" + callableStatement.getString(1) + "]");
				
		}catch(Exception e) { System.out.println("ERROR: " + e.getMessage()); }
		 finally {
			 if (connection != null) {
				 try { connection.close(); } catch (SQLException e) { e.printStackTrace(); } //finally, try close connection
			 }
		 }
	 };
}
