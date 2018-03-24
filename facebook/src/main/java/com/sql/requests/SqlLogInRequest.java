package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
public class SqlLogInRequest {
	private String Id;
	
	public String select(String... args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = makeSQLConn();
			stmt = (Statement) conn.createStatement();
			String name = args[0];
			String sql = "select Id from "+ userTable +" where email = '" + name + "' and password ='"+ args[1]+ "'";
			rs = stmt.executeQuery(sql);
			if(!rs.isBeforeFirst())
				return null;
			else
			while (rs.next()) {
				Id = rs.getString("Id");
				conn.close();
				return Id;
			}
			
		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		conn.close();
		return null;
	}
	
	
}

