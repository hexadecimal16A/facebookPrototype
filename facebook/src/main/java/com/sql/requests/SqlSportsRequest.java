package com.sql.requests;

import static com.sql.requests.SqlInit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlSportsRequest {
	
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	private String insertTableSQL = "update "+usersports+" set sports=? where user_id=?";
	public boolean updateSports(Long user_id,String sports) throws SQLException{
		try{
		conn=makeSQLConn();
		preparedStatement=conn.prepareStatement(insertTableSQL);
		preparedStatement.setLong(2,user_id);
		preparedStatement.setString(1,sports);
		preparedStatement.executeUpdate();
		conn.close();
		return true;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return false;
		}
	}
	
	public String getSports(Long user_id) throws SQLException{
		try{
		conn=makeSQLConn();
		String sql="select sports from sports where user_id=?";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1,user_id);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.isBeforeFirst()){
			while(rs.next()){
				return rs.getString("sports");
			}
		}
		conn.close();
		return null;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
	}
}
