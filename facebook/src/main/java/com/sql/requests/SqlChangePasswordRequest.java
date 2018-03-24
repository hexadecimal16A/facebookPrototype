package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class SqlChangePasswordRequest {
	private String pwd;
	
	
	public boolean check(int id, String password) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = makeSQLConn();
			stmt = (Statement) conn.createStatement();
			
			String sql = "select password from userprofile where Id="+id;
			rs = stmt.executeQuery(sql);
			
			if(!rs.isBeforeFirst()){
				
			//System.out.println("no entry i guess");
				return false;
			}
				else
			while (rs.next()) {
				pwd = rs.getString("password");
				//System.out.println("password recieved for sql is::"+pwd);
				conn.close();
				if(pwd.equals(password))
				{
				
					//System.out.println("i am in true case");
					//System.out.println("sent password="+password+" from db password is="+pwd);
					return true;
				}
				else
				{
					//System.out.println("i am in dlase case i.e incorrect password case");
					//System.out.println("sent password="+password+" from db password is="+pwd);
					return false;
				}
			}
			
		} catch (Exception e) {
			conn.close();
			//System.out.println(" Check password trouble...");
			e.printStackTrace();
		} 
		conn.close();
		//System.out.println("i am at the ned of function");
		return false;
		
	}
	public boolean update(int id, String password) throws SQLException{
		Connection conn = null;
		

		
			PreparedStatement preparedStatement = null;
			String sql = "update userprofile set password=? where id=?";
			try {
				conn=makeSQLConn();
				// 2. Create a statement preparedStatement.setNull(5,java.sql.Types.VARCHAR);
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, password);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
				
			return true;
			// 4. Process the result set
			
		}
		catch (Exception exc) {
			//System.out.println(" update password trouble");
			exc.printStackTrace();
		}
		conn.close();
		return false;
	}
		
	

}
