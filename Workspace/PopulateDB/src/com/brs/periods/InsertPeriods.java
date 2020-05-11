package com.brs.periods;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Iterator;

import com.brs.ConnectDB;
import com.brs.blocks.Block;
import com.brs.years.Year;

public interface InsertPeriods {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_period(?,?)}");	//create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register block out param
			callableStatement.registerOutParameter(2, Types.VARCHAR); //register year out param
			
			Iterator<Year>yearsIterator = Arrays.asList(Year.values()).iterator(); //years iterator
			Iterator<Block>blocksIterator; //blocks iterator
			Year year; //current year
			Block block; //current block
			
			System.out.println("------------------");
			System.out.println("Periods:\n"); 
			
			while(yearsIterator.hasNext()) { //iterate through years
				year = yearsIterator.next(); //advance to next year
				blocksIterator = Arrays.asList(Block.values()).iterator(); //(re)set blocks iterator
				
				while(blocksIterator.hasNext()) { //iterate through blocks
					block = blocksIterator.next(); //advance to next block
					
					try {
						callableStatement.setString(1, block.toString()); //set block input
						callableStatement.setString(2, year.toString()); //set year input
						callableStatement.execute(); //execute statement
						//print response:
						System.out.println("Inserted: "+callableStatement.getString(1)+" "+callableStatement.getString(2)); 
					}catch(Exception e) { e.printStackTrace(); }
				}
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
