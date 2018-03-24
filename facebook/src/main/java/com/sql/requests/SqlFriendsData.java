package com.sql.requests;

import static com.sql.requests.SqlInit.friendTable;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.sql.data.SqlUserProfileData;
public class SqlFriendsData{
	
	private String Name;
	private String Email;
	private String DOB;
	//private String Password;
	private String Mobile;
	//private String Hometown;
	//private String Country;
	private String ProfileImage;
	private String Id;

	public ArrayList<SqlUserProfileData> getFriends(int id) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		////System.out.println("asas");
		ArrayList<Integer> ids=new ArrayList<Integer>();
		ArrayList<SqlUserProfileData> pro=new ArrayList<SqlUserProfileData>();
		try {
			conn = SqlInit.makeSQLConn();
			stmt = (Statement) conn.createStatement();
			String sql = "select * from "+friendTable+ " where Id1 = '" + id + "' ";
			rs = stmt.executeQuery(sql);
			if(rs.isBeforeFirst())
			while (rs.next()) {
				ids.add(Integer.parseInt(rs.getString("id2")));
				
			}
			////System.out.println("asas");
			sql = "select * from "+friendTable+" where Id2 = '" + id + "' ";
			rs = stmt.executeQuery(sql);
			if(rs.isBeforeFirst())
			while (rs.next()) {
				ids.add(Integer.parseInt(rs.getString("id1")));
				////System.out.println(rs.getString("id1"));
			}
			
			for(int idd:ids)
			{
				
				sql = "select * from "+userTable+" where Id = '" + idd + "' ";
				rs = stmt.executeQuery(sql);
				if(rs.isBeforeFirst())
				while (rs.next()) {
				
					Name = rs.getString("fname")+" "+rs.getString("lname");
					Email = rs.getString("Email");
					Mobile = rs.getString("Mobileno");
					//Hometown=rs.getString("Hometown");
					DOB=rs.getString("DateOfBirth");
					//Country=rs.getString("Country");
					ProfileImage=rs.getString("picturepath");
					Id=rs.getString("Id");
					//return new SqlUserProfileData(Name,Email,DOB,null,Mobile,Hometown,Country,ProfilePic,null);
					pro.add(new SqlUserProfileData(Name,Email,DOB,null,Mobile,null,null,ProfileImage,null,null,null,null,Id));
					////System.out.println(pro.get(0).getName());
				}	
			}
			conn.close();
			return pro;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		conn.close();
		return null;
	}
	

}
