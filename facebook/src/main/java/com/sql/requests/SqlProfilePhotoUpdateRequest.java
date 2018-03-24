package com.sql.requests;

import static com.sql.requests.SqlInit.groupinfo;
import static com.sql.requests.SqlInit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlProfilePhotoUpdateRequest {
	public static boolean updateGroup(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "UPDATE " + groupinfo + " SET piclocation = ?"
				+ " WHERE group_id = ?";
		int id = Integer.parseInt(args[1]);
		try {
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setInt(2, id);
			// 3. Execute SQL query
			preparedStatement.executeUpdate();
			myConn.close();
			return true;
			// 4. Process the result set

		} catch (Exception exc) {
			myConn.close();
			//System.out.println(" NY");
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}

	public static boolean updateProfile(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "UPDATE " + userTable + " SET picturePath = ?"
				+ " WHERE id = ?";
		int id = Integer.parseInt(args[1]);
		try {
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setInt(2, id);
			// 3. Execute SQL query
			preparedStatement.executeUpdate();
			myConn.close();
			return true;
			// 4. Process the result set

		} catch (Exception exc) {
			myConn.close();
			//System.out.println(" NY");
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}

	public static boolean updateEvent(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "UPDATE " + eventinfo + " SET piclocation = ?"
				+ " WHERE event_id = ?";
		//System.out.println(insertTableSQL);
		int id = Integer.parseInt(args[1]);
		try {
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setInt(2, id);
			// 3. Execute SQL query
			preparedStatement.executeUpdate();
			myConn.close();
			return true;
			// 4. Process the result set

		} catch (Exception exc) {
			myConn.close();
			//System.out.println(" NY");
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}
}
