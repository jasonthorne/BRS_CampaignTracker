package com.brs.blocks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import com.brs.ConnectDB;
import com.brs.Insert;

public interface InsertBlocks {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_block(?)}");	//create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			Arrays.asList(Block.values()).forEach((block)->{ //forEach block in list of blocks:
				try {
					callableStatement.setString(1, block.toString()); //set input param
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
	};
}
