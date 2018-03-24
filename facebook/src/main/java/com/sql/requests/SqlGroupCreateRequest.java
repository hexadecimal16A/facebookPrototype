package com.sql.requests;
import static com.init.requests.PathInit.*;

import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class SqlGroupCreateRequest {
	private String group_id;
	
	public String insert(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "INSERT INTO " +groupinfo
				+ " (group_name,admin_id,privacy) VALUES"
				+ " (?,?,?)";
		try {
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setLong(2, Long.parseLong(args[1]));
			preparedStatement.setLong(3, Long.parseLong(args[2]));
			
			preparedStatement.executeUpdate();
			myConn.close();
			//System.out.println("1st executed");
			return createDir(args[0],args[1]);
			
			// 4. Process the result set
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		myConn.close();
		return null;
	}
	
	private  String createDir(String... args) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		int id=Integer.parseInt(args[1]);
		String sql = "select group_id from " + groupinfo
				+" where group_name='"+ args[0]+"' and admin_id="+id;
		//String Id=new SqlLogInRequest().select(args);
		//System.out.println(sql);
		Connection myConn = makeSQLConn();
		stmt = (Statement) myConn.createStatement();
		rs = stmt.executeQuery(sql);
		if(!rs.isBeforeFirst())
			return null;
		else
		while (rs.next()) {
			group_id = rs.getString("group_id");
			SqlGroupMember s1=new SqlGroupMember();
			//System.out.println("nice---");
			s1.insert(group_id,args[1]);
			
			//	conn.close();
			//return group_id;
		}
		
		File theDir = new File(GROUP_PATH+group_id);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			//System.out.print("DIR");
		    //System.out.println("creating directory: " + group_id);
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		    	myConn.close();
		        return null;//handle it
		    }        
		    if(result) {    
		        //System.out.println("DIR created");  
		        myConn.close();
		        return group_id;
		    }
		    return null;
		}
		return group_id;
	}

}
