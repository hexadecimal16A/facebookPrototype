package com.sql.requests;
import static com.init.requests.PathInit.*;
import static com.sql.requests.SqlInit.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SqlNewUserRequest {
	public boolean insert(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "INSERT INTO " +userTable
				+ " (fname,lname,email,password,dateofbirth,gender) VALUES"
				+ "(?,?,?,?,?,?)";
		try {
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setString(2, args[1]);
			preparedStatement.setString(3, args[2]);
			preparedStatement.setString(4, args[3]);
			preparedStatement.setString(5, args[4]);
			preparedStatement.setString(6, args[5]);
			preparedStatement.executeUpdate();
			myConn.close();
			return createDir(args[2],args[3]);
			
			// 4. Process the result set
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}
	
	private  boolean createDir(String... args) throws SQLException{
		String insertTableSQL2 = "INSERT INTO " + extrainfo
				+ " (id) VALUES"
				+ "(?)";
		String Id=new SqlLogInRequest().select(args);
		Connection myConn = makeSQLConn();
		PreparedStatement preparedStatement = myConn.prepareStatement(insertTableSQL2);
		preparedStatement.setString(1,Id);
		preparedStatement.executeUpdate();
		
		String insertTableSQL3 = "INSERT INTO " + usersports
				+ " (user_id) VALUES"
				+ "(?)";
		Id=new SqlLogInRequest().select(args);
		preparedStatement = myConn.prepareStatement(insertTableSQL3);
		preparedStatement.setString(1,Id);
		preparedStatement.executeUpdate();
		File theDir = new File(USER_PATH+Id);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			//System.out.print("DIR");
		    //System.out.println("creating directory: " + Id);
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		    	myConn.close();
		        return false;//handle it
		    }        
		    if(result) {    
		        //System.out.println("DIR created");  
		        myConn.close();
		        return true;
		    }
		    return false;
		}
		return false;
	}
	
}
