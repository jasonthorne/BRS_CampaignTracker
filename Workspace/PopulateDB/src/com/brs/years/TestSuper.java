package com.brs.years;

import java.sql.Connection;

import com.brs.ConnectDB;

public abstract class TestSuper {
	
	protected Connection connection = null; 
	
	protected TestSuper(){
		//connection = ConnectDB.getConnection();	//connect to DB	
	}
	
	protected TestSuper(String string){
		//connection = ConnectDB.getConnection();	//connect to DB	
	}
	
	
	
	protected abstract void insert();
	
	//////////protected abstract void insert(String str);
}
