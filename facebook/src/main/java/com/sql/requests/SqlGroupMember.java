package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.userTable;
import static com.sql.requests.SqlInit.groupmembers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.GroupMemberdata;

public class SqlGroupMember {
	
	private String id;
	private String userName;
	private String Email;
	private String DOB;
	private String Mobile;
	private String ProfileImage;
	private Connection myConn=null;
	private PreparedStatement preparedStatement=null;
	public boolean insert(String... args) throws SQLException {

		String sql = "INSERT INTO " +groupmembers
				+ "  VALUES"
				+ "(?,?)";
			
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(sql);
			preparedStatement.setLong(1,Long.parseLong(args[0]));
			preparedStatement.setLong(2,Long.parseLong(args[1]));
			//System.out.println("asasasasas");
			preparedStatement.executeUpdate();
			myConn.close();	
			return true;
		
	}
	
	public boolean delete(String... args) throws SQLException {
	
		String sql = "delete from " +groupmembers
				+ " where group_id=? and member_id=?";
			
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(sql);
			preparedStatement.setLong(1,Long.parseLong(args[0]));
			preparedStatement.setLong(2,Long.parseLong(args[1]));
			
			preparedStatement.executeUpdate();
			myConn.close();	
			return true;
		
	}
	public ArrayList<GroupMemberdata> getMembers(String group_id) throws SQLException {
		String sql = "select id,concat(fname,' ',lname) as name,email,mobileno,dateofbirth,picturepath from "+ groupmembers
				+","+ userTable+" where group_id=? and member_id=id";

		//System.out.println(sql);
			 myConn=makeSQLConn();
			 preparedStatement =myConn.prepareStatement(sql);
			 preparedStatement.setInt(1,Integer.parseInt(group_id));
			 ResultSet rs=preparedStatement.executeQuery();
			 ArrayList<GroupMemberdata> pro=new ArrayList<GroupMemberdata>();
			 if(rs.isBeforeFirst())
			 while(rs.next()){
				 id=rs.getString("id");
				 userName=rs.getString("name"); 
					Email = rs.getString("Email");
					Mobile = rs.getString("Mobileno");
					DOB=rs.getString("DateOfBirth");
					ProfileImage=rs.getString("picturepath");
				 pro.add(new GroupMemberdata(id,userName,Email,DOB,Mobile,ProfileImage));
			 }
			
		myConn.close();
		return pro;
	}
	
	public String groupMemberCheck(Long id1,Long id2) throws SQLException{
		String checkSQL="select group_id,member_id from "+groupmembers+ 
		" where group_id=? and member_id=?";

		myConn=makeSQLConn();
		preparedStatement=myConn.prepareStatement(checkSQL);
		preparedStatement.setLong(1,id1);
		preparedStatement.setLong(2, id2);

		ResultSet rs=preparedStatement.executeQuery();
		if(rs.isBeforeFirst()){
		myConn.close();
		return "yes";
		}
		return "no";
		}

	
	
}

