package com.sql.requests;
import static com.sql.requests.SqlInit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.*;

public class SqlSearchRequest {
	private String group_id;
	private String group_name;
	private String admin_id;
	private String piclocation;
	private String privacy;
	private String group_count;
	
	private String Name;
	private String Email;
	private String DOB;
	private String Mobile;
	private String ProfilePic;
	private String Id;
	private Connection Conn =null;
	private PreparedStatement preparedStatement = null;
	private String SQLQuery="select * from "+userTable+" where id in "
			+ "(select id from (select concat(fname,lname) as name,id from "+userTable+") "
			+ "as newtable where name LIKE ?)";

	public ArrayList<SqlUserProfileData> searchPeopleRequest(String people) throws SQLException{
		//preparedStatement.setString(1,people);
	try{	
		Conn=makeSQLConn();
		preparedStatement = Conn.prepareStatement(SQLQuery);
		preparedStatement.setString(1,'%'+people+'%');
		ResultSet rs=preparedStatement.executeQuery();
		ArrayList<SqlUserProfileData> pro=new ArrayList<SqlUserProfileData>();
		if(!rs.isBeforeFirst())
			return null;
		while(rs.next()){
			Name = rs.getString("fname")+" "+rs.getString("lname");
			Email = rs.getString("Email");
			Mobile = rs.getString("Mobileno");
			DOB=rs.getString("DateOfBirth");
			ProfilePic=rs.getString("picturepath");
			Id=rs.getString("Id");
			pro.add(new SqlUserProfileData(Name,Email,DOB,null,Mobile,null,null,ProfilePic,null,null,null,null,Id));
		}
		Conn.close();
		return pro;
		}
	catch(SQLException e){
		//System.out.println(e.getMessage());
	}
	Conn.close();
	return null;
	}
	
	//---------------------------------//
	
	public ArrayList<GroupInfoData> searchGroupRequest(String group) throws SQLException{
		//preparedStatement.setString(1,people);
	try{	
		
		Conn=makeSQLConn();
		SQLQuery="select f.group_id,group_name,admin_id,piclocation,privacy,e.group_count from "
				+ "group_info f,(select count(member_id) as group_count, group_id from group_members group by group_id) e "
				+ "where f.group_id in (select group_id from group_info where group_name like ? and privacy=0) "
				+ "and f.group_id=e.group_id";

		preparedStatement = Conn.prepareStatement(SQLQuery);
		preparedStatement.setString(1,'%'+group+'%');
		ResultSet rs=preparedStatement.executeQuery();
		ArrayList<GroupInfoData> pro=new ArrayList<GroupInfoData>();
		if(!rs.isBeforeFirst())
			return null;
		while(rs.next()){
			group_name= rs.getString("group_name");
			group_id = rs.getString("group_id");
			admin_id = rs.getString("admin_id");
			privacy=rs.getString("privacy");
			piclocation=rs.getString("piclocation");
			group_count=rs.getString("group_count");
			pro.add(new GroupInfoData(group_id,group_name,admin_id,piclocation,privacy,group_count));
		}
		Conn.close();
		return pro;
		}
	catch(SQLException e){
		System.out.println(e.getMessage());
	}
	Conn.close();
	return null;
	}
}
