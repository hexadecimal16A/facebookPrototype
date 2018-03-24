package com.sql.requests;

import static com.sql.requests.SqlInit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.FriendPendingRequestData;

public class SqlFriendSuggestion {
	//select id1 from (select id1 from friendlist where id2=12 union select id2 from friendlist where id1=12) as a where id1 not in (select id1 from friendlist where id2=7 union select id2 from friendlist where id1=7) and id1<>7 and id1<>12;
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	
	public ArrayList<FriendPendingRequestData> friendSuggestion(Long from,Long to) throws SQLException{
		try{
		String getPeople="select * from "+userTable+" where id in (select id1 from (select id1 from "+friendTable+" where id2=? union "
				+ "select id2 from "+friendTable+" where id1=?) as a where id1 not in "
				+ "(select id1 from "+friendTable+" where id2=? union select id2 from "
				+ friendTable+" where id1=? union select from_id from pending_request "
						+ "where to_id=? union select to_id from pending_request where from_id=? "
						+ "union select to_id from suggestion where user_id=? union "
						+ "select user_id from suggestion where to_id=?) and id1<>? and id1<>?) limit 5";
		String name;
		String dOB;
		String email;
		String picturePath;
		String Id;
		ArrayList<FriendPendingRequestData> pro=new ArrayList<FriendPendingRequestData>();
		conn=makeSQLConn();
		preparedStatement=conn.prepareStatement(getPeople);
		preparedStatement.setLong(1,from);
		preparedStatement.setLong(2,from);
		preparedStatement.setLong(3,to);
		preparedStatement.setLong(4,to);
		preparedStatement.setLong(5,from);
		preparedStatement.setLong(6,to);
		preparedStatement.setLong(7,from);
		preparedStatement.setLong(8,to);
		preparedStatement.setLong(9,from);
		preparedStatement.setLong(10,to);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.isBeforeFirst()){
			while(rs.next()){
				name=rs.getString("fname")+" "+rs.getString("lname");
				email=rs.getString("email");
				dOB=rs.getString("dateofBirth");
				picturePath=rs.getString("picturePath");
				Id=rs.getString("id");
				pro.add(new FriendPendingRequestData(name,dOB,Id,email,picturePath,null,"0"));
			}
		}
		conn.close();
		return pro;
		}
		catch(Exception e){
			conn.close();
			//System.out.println(e.toString());
			return null;
		}
	}
	
	public boolean suggest(Long from,Long to,Long id) throws SQLException{
		try{
		conn=makeSQLConn();
		String sql="insert into suggestion (from_id,to_id,user_id) values (?,?,?)";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1,from);
		preparedStatement.setLong(2,to);
		preparedStatement.setLong(3,id);
		preparedStatement.executeUpdate();
		return true;
		}
		catch(Exception e){
			conn.close();
			//System.out.println(e.toString());
			return false;
		}
	}
	
	public ArrayList<FriendPendingRequestData> getSuggestion(Long id)throws SQLException{
		try{
			String name;
			String dOB;
			String email;
			String picturePath;
			String Id;
			String reqId;
			ArrayList<FriendPendingRequestData> pro=new ArrayList<FriendPendingRequestData>();
			conn=makeSQLConn();
			String sql="select * from "+userTable
					+ " inner join suggestion on id=user_id where to_id=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.isBeforeFirst())
				while(rs.next()){
					name=rs.getString("fname")+" "+rs.getString("lname");
					email=rs.getString("email");
					dOB=rs.getString("dateofBirth");
					picturePath=rs.getString("picturePath");
					Id=rs.getString("id");
					reqId=rs.getString("sug_id");
					sql="select id from (select id1 as id from friendlist where id2=? union "
							+ "select id2 as id from friendlist where id1=? union "
							+ "select to_id as id from pending_request where from_id=? union "
							+ "select from_id as id from pending_request where to_id=?)as t  "
							+ "where id in (select user_id as id from suggestion where user_id=? and to_id=?)";
					preparedStatement=conn.prepareStatement(sql);
					preparedStatement.setLong(1,id);
					preparedStatement.setLong(2,id);
					preparedStatement.setLong(3,id);
					preparedStatement.setLong(4,id);
					preparedStatement.setLong(5,Long.parseLong(Id));
					preparedStatement.setLong(6,id);
					if(preparedStatement.executeQuery().isBeforeFirst()){
						sql="delete from suggestion where user_id=? and to_id=?";
						preparedStatement=conn.prepareStatement(sql);
						preparedStatement.setLong(2,id);
						preparedStatement.setLong(1,Long.parseLong(Id));
						preparedStatement.execute();
					}
					else
						pro.add(new FriendPendingRequestData(name,dOB,Id,email,picturePath,reqId,"0"));
				}
			conn.close();
			return pro;
		}
		catch(Exception e){
			//System.out.println(e.toString());
			return null;
		}
	}
	
	public boolean suggestRemove(Long reqId) throws SQLException{
		try{
		conn=makeSQLConn();
		String sql="delete from suggestion where sug_id=?";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1,reqId);
		preparedStatement.executeUpdate();
		return true;
		}
		catch(Exception e){
			conn.close();
			//System.out.println(e.toString());
			return false;
		}
	}
	
}
