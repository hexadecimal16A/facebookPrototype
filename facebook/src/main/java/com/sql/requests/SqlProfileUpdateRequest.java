package com.sql.requests;

import static com.sql.requests.SqlInit.extrainfo;
import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SqlProfileUpdateRequest {
	public static boolean update(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "UPDATE "+userTable +" SET fname = ?,lname = ?,DateOfBirth = ?, Mobileno = ?"
                + " WHERE Id = ?";
		String insertTableSQL2 = "UPDATE "+extrainfo +" SET hometown = ?,country = ?,work = ?, livesin = ?, college = ?"
                + " WHERE Id = ?";
		int id = Integer.parseInt(args[6]);
		try {
			myConn=makeSQLConn();
			// 2. Create a statement preparedStatement.setNull(5,java.sql.Types.VARCHAR);
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setString(2, args[1]);
			preparedStatement.setString(3, args[2]);
			preparedStatement.setString(4, args[3]);
			preparedStatement.setInt(5, id);
			// 3. Execute SQL query
			preparedStatement.executeUpdate();
			
			preparedStatement = myConn.prepareStatement(insertTableSQL2);
			preparedStatement.setString(1,args[4]);
			preparedStatement.setString(2,args[5]);
			preparedStatement.setString(3,args[7]);
			preparedStatement.setString(4,args[8]);
			preparedStatement.setString(5,args[9]);
			preparedStatement.setInt(6, id);
			preparedStatement.executeUpdate();
			myConn.close();
			return true;
			// 4. Process the result set
			
		}
		catch (Exception exc) {
			//System.out.println(" NY");
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}
	
}
