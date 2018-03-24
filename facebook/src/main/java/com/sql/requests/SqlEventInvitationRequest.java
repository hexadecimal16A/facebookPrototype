package com.sql.requests;

import static com.sql.requests.SqlInit.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.sql.data.SqlUserProfileData;

public class SqlEventInvitationRequest {

	

	public boolean insert(String event_id,String member_id,String from_id) throws SQLException {
		Connection conn = makeSQLConn();
		Statement stmt = null;
		ResultSet rs = null;
		int admininvite=Integer.parseInt(from_id);
		PreparedStatement preparedStatement = null;
		try {
			conn = SqlInit.makeSQLConn();
			stmt = (Statement) conn.createStatement();
		//	String sql="Insert into "+eventparticipants+" values("+event_id+","	+member_id+","+event_type+")";
			String sql="Insert into "+eventparticipants+"(event_id,member_id,flag) values "
					+ "(?,?,?)";
			System.out.println(sql);
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1,Integer.parseInt(event_id));
			preparedStatement.setInt(2, Integer.parseInt(member_id));
			preparedStatement.setInt(3, admininvite);
			preparedStatement.executeUpdate();
			
			
			//rs = stmt.executeQuery(sql);	
			conn.close();	
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return false;
		
	}
	
	
	
	
}
