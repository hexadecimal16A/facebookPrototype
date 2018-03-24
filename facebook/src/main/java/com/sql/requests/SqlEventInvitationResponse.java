package com.sql.requests;

import static com.sql.requests.SqlInit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class SqlEventInvitationResponse {

	public boolean insert(String event_id,String member_id,String status) throws SQLException {
		Connection conn = makeSQLConn();
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		stmt = (Statement) conn.createStatement();
		String sql1 = "select * from event_participants where event_id="+event_id+" and member_id="+member_id;
		rs = stmt.executeQuery(sql1);
		
		
		if(!rs.isBeforeFirst())
		{	
			//if request is sent for the first time from event page
			try {
			//	String sql="Insert into "+eventparticipants+" values("+event_id+","	+member_id+","+event_type+")";
				String sql="Insert into "+eventparticipants+"(event_id,member_id,status) values "
						+ "(?,?,?)";
				System.out.println(sql);
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1,Integer.parseInt(event_id));
				preparedStatement.setInt(2, Integer.parseInt(member_id));
				preparedStatement.setInt(3, Integer.parseInt(status));
				
				preparedStatement.executeUpdate();
				
				
				//rs = stmt.executeQuery(sql);	
				conn.close();	
				return true;
			}
			catch(Exception e)
			{
				System.out.println(e);
			}	
		}
		else
		try {
			
			//if status is changed from anywhere in the project
			//String sql="Insert into "+eventparticipants+" values("+event_id+","	+member_id+","+event_type+","+status+")";
			
		
			String sql="update "+eventparticipants+" set "
					+ "status=? where event_id=? and member_id=?" ;
			System.out.println(sql);
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(status));
			preparedStatement.setInt(2,Integer.parseInt(event_id));
			preparedStatement.setInt(3, Integer.parseInt(member_id));
			preparedStatement.executeUpdate();
			//rs = stmt.executeQuery(sql);	
			conn.close();	
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
		
	}
	
	
}
