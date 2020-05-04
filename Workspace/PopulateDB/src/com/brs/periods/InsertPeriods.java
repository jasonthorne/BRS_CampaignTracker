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
	
	/*hava a bipred here that takes in both lists, and then 
	loops thjrough both lists with loopy deely thinggbob
	and inserts according to that!! good luck :P */
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{call insert_period(?)(?)}");	//create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
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
					System.out.println(block + " " + year);
		
				}
			}
			
			/*
			Arrays.asList(Year.values()).forEach((year)->{ //forEach year in list of years:
				try {
					callableStatement.setString(1, year.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println("Inserted: " + callableStatement.getString(1)); //confirm insertion
				}catch(Exception e) { e.printStackTrace(); }
				
			});*/
				
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
